package model;

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
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.ArrayUtils;

import view.VehicleButton;
import view.ViewManager;

public class KarProtocol extends Protocol {

	private static final byte SYN = (byte) 0x16;
	private static final byte SOH = (byte) 0x01;
	private static final byte ACK = (byte) 0x03;
	private static final byte NACK = (byte) 0x04;

	private short sidDestination;
	private short sidSource;
	private byte sequenceNumber;

	ViewManager viewManager;
	
	public KarProtocol() {
		sequenceNumber = 1;
		viewManager = ViewManager.getInstance();
	}

	@Override
	public ArrayList<Byte> createSerialMessage(VehicleButton vb) {
		ArrayList<Byte> dataFrame = new ArrayList<Byte>();

		dataFrame.add(SOH);

		sidDestination = (short) Integer.parseUnsignedInt(viewManager.getProtocolPanel().getKarSid());
		dataFrame.addAll(short2bytesLSB(sidDestination));
		// TODO make source editable
		dataFrame.addAll(short2bytesLSB(sidSource));

//		sequenceNumber++;
		dataFrame.add(sequenceNumber);
		
		List<Byte> data = createMessageData(vb);
		dataFrame.add((byte) data.size());

		// crc1
		short crc1 = calculateCRC(dataFrame);
		dataFrame.addAll(short2bytesLSB(crc1));

		// data
		dataFrame.addAll(data);

		// crc2
		short crc2 = calculateCRC(data);
		dataFrame.addAll(short2bytesLSB(crc2));

		// insert message start
		dataFrame.add(0, SYN);
		
		sendMessage = dataFrame;
		
		String message = DatatypeConverter.printHexBinary(ArrayUtils.toPrimitive(dataFrame.toArray(new Byte[dataFrame.size()])));
		System.out.println("Dataframe: " + message);
		
		signalSubscriber("Sending KAR message\n");

		return dataFrame;
	}

	private List<Byte> createMessageData(VehicleButton vb) {
		List<Byte> dataFrame = new ArrayList<Byte>();
		List<Byte> attributeData = new ArrayList<Byte>();

		byte messageType = 1;
		dataFrame.add(messageType);

		// TODO
		int usedAttributes = vb.getUsedAttributes();
		dataFrame.add((byte) (usedAttributes & 0xFF));
		dataFrame.add((byte) ((usedAttributes >> 8) & 0xFF));
		dataFrame.add((byte) ((usedAttributes >> 16) & 0xFF));
		
		boolean keyExists = viewManager.getProtocolPanel().getKarKey().length > 0;
		boolean reserveAttributesUsed = (usedAttributes & (1 << 22)) != 0 && (usedAttributes & (1 << 23)) != 0;
		boolean messageContainsKey = keyExists && reserveAttributesUsed;
		
		for (int a = 0; a < 24; a++) {
			if ((usedAttributes & (1 << a)) == 0) {
				continue;
			}
			switch (a) {
			case 0:
				attributeData.add((byte) vb.getLoopNr());
				break;
			case 1:
				attributeData.add((byte) vb.getVehicleType().getNr());
				break;
			case 2:
				attributeData.addAll(short2bytesLSB((short) vb.getLineNr()));
				break;
			case 3:
				attributeData.addAll(short2bytesLSB((short) vb.getVehServiceNr()));
				break;
			case 4:
				attributeData.add((byte) vb.getCompanyNr());
				break;
			case 5:
				attributeData.addAll(short2bytesLSB((short) vb.getVehicleId()));
				break;
			case 6:
				attributeData.add((byte) vb.getDirection().getNr());
				break;
			case 7:
				attributeData.add((byte) vb.getVehicleStatus().getNr());
				break;
			case 8:
				attributeData.add((byte) vb.getPriorityClass().getNr());
				break;
			case 9:
				attributeData.add((byte) vb.getPunctualityClass().getNr());
				break;
			case 10:
				attributeData.addAll(short2bytesLSB((short) vb.getPunctuality()));
				break;
			case 11:
				attributeData.add((byte) vb.getVehicleLength());
				break;
			case 12:
				attributeData.add((byte) vb.getVehicleSpeed());
				break;
			case 13:
				attributeData.addAll(short2bytesLSB((short) vb.getDistanceToStop()));
				break;
			case 14:
				attributeData.add((byte) vb.getTimeToStop());
				break;
			case 15:
				attributeData.addAll(short2bytesLSB((short) vb.getJourneyNr()));
				break;
			case 16:
				attributeData.add((byte) vb.getJourneyType().getNr());
				break;
			case 17:
				attributeData.add((byte) vb.getRoute());
				break;
			case 18:
				attributeData.add((byte) vb.getCommand().getNr());
				break;
			case 19:
				attributeData.addAll(short2bytesLSB((short) vb.getActivation()));
				break;
			case 20:
				attributeData.add((byte) vb.getLatDeg());
				attributeData.add((byte) vb.getLatMin());
				attributeData.add((byte) vb.getLatSec());
				attributeData.add((byte) vb.getLatSSec());
				attributeData.add((byte) vb.getLongDeg());
				attributeData.add((byte) vb.getLongMin());
				attributeData.add((byte) vb.getLongSec());
				attributeData.add((byte) vb.getLongSSec());
				break;
			case 21:
				attributeData.addAll(short2bytesLSB((short) vb.getYear()));
				attributeData.add((byte) vb.getMonth());
				attributeData.add((byte) vb.getDay());
				attributeData.add((byte) vb.getHour());
				attributeData.add((byte) vb.getMinute());
				attributeData.add((byte) vb.getSecond());
			case 22:
				if (!messageContainsKey) {
					attributeData.addAll(short2bytesLSB((short) vb.getReserve1()));
				}
				break;
			case 23:
				if (!messageContainsKey) {
					attributeData.addAll(short2bytesLSB((short) vb.getReserve2()));
				}
				break;
			default:
				break;
			}
		}
		
		if (messageContainsKey) {
			List<Byte> signature = generateHmacSignature(attributeData);
			// Reserve 1
			attributeData.add(signature.get(3));
			attributeData.add(signature.get(2));
			
			// Reserve 2
			attributeData.add(signature.get(1));
			attributeData.add(signature.get(0));
		}
		dataFrame.addAll(attributeData);
		return dataFrame;
	}

	List<Byte> short2bytesLSB(short number) {
		List<Byte> bytes = new ArrayList<>();
		bytes.add((byte) (number & 0xFF));
		bytes.add((byte) ((number >> 8) & 0xFF));
		return bytes;
	}
	
	short bytes2shortLSB(List<Byte> bytes) {
		short number = (short) (bytes.get(1) << 8 + bytes.get(0));
		return number;
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
		// TODO Auto-generated method stub

	}
}
