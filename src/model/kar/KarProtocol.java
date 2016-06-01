package model.kar;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.ArrayUtils;

import model.Protocol;
import model.interfaces.ProtocolMessage;
import model.kar.KarAttribute.KAR;
import view.ViewManager;

public class KarProtocol extends Protocol {

	private static final byte SYN = (byte) 0x16;
	private static final byte SOH = (byte) 0x01;
//	private static final byte ACK = (byte) 0x03;
//	private static final byte NACK = (byte) 0x04;

	private short sidDestination;
	private short sidSource;
	private byte sequenceNumber;

	ViewManager viewManager;
	
	public KarProtocol() {
		sequenceNumber = 1;
		viewManager = ViewManager.getInstance();
	}

	@Override
	public ArrayList<Byte> createSerialMessage(ProtocolMessage message) {
		ArrayList<Byte> dataFrame = new ArrayList<Byte>();

		dataFrame.add(SOH);

		sidDestination = (short) Integer.parseUnsignedInt(viewManager.getProtocolPanel().getKarSid());
		dataFrame.addAll(short2bytesLSB(sidDestination));
		dataFrame.addAll(short2bytesLSB(sidSource));

//		sequenceNumber++;
		dataFrame.add(sequenceNumber);
		
		List<Byte> data = createMessageData((KarMessage) message);
		// length
		int length = data.size();
		dataFrame.add((byte) length);
		
		// crc1
		short crc1 = calculateCRC(dataFrame);
		dataFrame.addAll(short2bytesLSB(crc1));
		
		// data
		dataFrame.addAll(data);
		
		if (length > 0) {
			// crc2
			short crc2 = calculateCRC(data);
			dataFrame.addAll(short2bytesLSB(crc2));
		}
		
		// escape all SYN characters
		escapeSynCharacters(dataFrame);
		
		// insert message start
		dataFrame.add(0, SYN);
		
		sendMessage = dataFrame;
		
//		String dataFrameMessage = DatatypeConverter.printHexBinary(ArrayUtils.toPrimitive(dataFrame.toArray(new Byte[dataFrame.size()])));
//		System.out.println("Dataframe: " + dataFrameMessage);
		
		signalSubscriber("");

		return dataFrame;
	}

	private void escapeSynCharacters(ArrayList<Byte> dataFrame) {
		for (int i = 0; i < dataFrame.size(); i++) {
			if (dataFrame.get(i) == SYN) {
				dataFrame.add(i, SYN);
				i++;
			}
		}
	}

	private List<Byte> createMessageData(KarMessage message) {
		List<Byte> dataFrame = new ArrayList<Byte>();
		List<Byte> attributeDataFrame = new ArrayList<Byte>();

		byte messageType = 1;
		dataFrame.add(messageType);

		int usedAttributes = 0;
		List<KarAttribute> attributes = message.getKarAttributes();
		for (int i = 0; i < attributes.size(); i++) {
			if (attributes.get(i).isEnabled()) {
				usedAttributes |= 1 << i;
			}
		}
		dataFrame.add((byte) (usedAttributes & 0xFF));
		dataFrame.add((byte) ((usedAttributes >> 8) & 0xFF));
		dataFrame.add((byte) ((usedAttributes >> 16) & 0xFF));
		
		boolean keyExists = viewManager.getProtocolPanel().getKarKey().length > 0;
		boolean reserveAttributesUsed = message.getAttribute(KAR.RESERVE1).isEnabled() && 
				message.getAttribute(KAR.RESERVE2).isEnabled();
		boolean messageContainsKey = keyExists && reserveAttributesUsed;
		
		for (KarAttribute attribute : message.getKarAttributes()) {
			// Don't add the reserve attributes if the message should contain a signature.
			if (messageContainsKey) {
				if (attribute.getId() == KAR.RESERVE1 || attribute.getId() == KAR.RESERVE2) {
					continue;
				}
			}
			addAttributeToDataFrame(attributeDataFrame, attribute);
		}
		
		if (messageContainsKey) {
			List<Byte> signature = generateHmacSignature(attributeDataFrame);
			// Reserve 1
			attributeDataFrame.add(signature.get(3));
			attributeDataFrame.add(signature.get(2));
			
			// Reserve 2
			attributeDataFrame.add(signature.get(1));
			attributeDataFrame.add(signature.get(0));
		}
		dataFrame.addAll(attributeDataFrame);
		return dataFrame;
	}

	private void addAttributeToDataFrame(List<Byte> dataFrame, KarAttribute attribute) {
		if (attribute.isEnabled()) {
			for (KarField field : attribute.getKarFields()) {
				int value = field.getValue();
				if (field.getSizeInBytes() == 1) {
					dataFrame.add((byte) value);
				} else if (field.getSizeInBytes() == 2) {
					dataFrame.addAll(short2bytesLSB((short) value));
				}
			}
		}
	}

	List<Byte> short2bytesLSB(short number) {
		List<Byte> bytes = new ArrayList<>();
		bytes.add((byte) (number & 0xFF));
		bytes.add((byte) ((number >> 8) & 0xFF));
		return bytes;
	}

	/**
	 * calculates CRC over given dataFrame
	 * 
	 * @param dataFrame
	 * @return crc: 2 Bytes CRC
	 */
	private short calculateCRC(List<Byte> dataFrame) {
		int crc = 0, tmp = 0;
		int x = 0;
		byte b = 0;

		for (int p = 0; p < dataFrame.size(); ++p) {
			tmp = crc;
			b = dataFrame.get(p);

			for (int i = 0; i < 8; ++i) {
				x = b ^ tmp;
				tmp >>= 1;
				b >>= 1;
				if ((x & 1) == 1)
					tmp ^= 0x8408;
			}
			crc = tmp;
		}
		return (short) crc;
	}
	
	private byte[] getSecretKarKey() {
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
		Calendar cal = Calendar.getInstance();
		String date = dateFormat.format(cal.getTime());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream( );
		try {
			outputStream.write( date.getBytes("US-ASCII") );
			outputStream.write( viewManager.getProtocolPanel().getKarKey() );
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return outputStream.toByteArray( );
	}

	public List<Byte> generateHmacSignature(List<Byte> dataFrame) {
		String algorithm = "HmacMD5";
		List<Byte> signature = new ArrayList<>();
		try {
			SecretKeySpec key = new SecretKeySpec(getSecretKarKey(), algorithm);
			Mac mac = Mac.getInstance(algorithm);
			mac.init(key);

			// Convert ArrayList<Byte> to Byte[] then convert Byte[] to byte[]
			byte[] dataBytes = ArrayUtils.toPrimitive(dataFrame.toArray(new Byte[dataFrame.size()]));
			byte[] bytes = mac.doFinal(dataBytes);
			
			// Convert byte[] to Byte[], then convert Byte[] to ArrayList<Byte>
			signature = new ArrayList<Byte>(Arrays.asList(ArrayUtils.toObject(bytes)));
			
			// Take the first 4 bytes
			signature = signature.subList(0, 4);
		} catch (InvalidKeyException e) {
			System.out.println(e.getStackTrace());
		} catch (NoSuchAlgorithmException e) {
			System.out.println(e.getStackTrace());
		}
		return signature;
	}

	@Override
	public void processData(Byte b) {

	}
}
