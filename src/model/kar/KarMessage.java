package model.kar;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import model.Encodings;
import model.Encodings.Command;
import model.Encodings.Encoding;
import model.Encodings.JourneyType;
import model.Encodings.KarVehicleType;
import model.Encodings.PriorityClass;
import model.Encodings.PunctualityClass;
import model.Encodings.VehicleStatus;
import model.interfaces.AttributeID;
import model.interfaces.ProtocolMessage;
import model.kar.KarAttribute.KAR;

public class KarMessage implements ProtocolMessage {
	private static final int NAME_OFFSET = 25;
	private static final int VALUE_OFFSET = 15;
	
	private static final long serialVersionUID = -3869574970121950028L;
	
	@Getter private List<KarAttribute> karAttributes;
	
	public KarMessage() {
		fillKARAttributes();
	}
	
	@Override
	public KarAttribute getAttribute(AttributeID id) {
		for (KarAttribute attribute : karAttributes) {
			if (attribute.getId() == id) {
				return attribute;
			}
		}
		return null;
	}
	
	@Override
	public KarVehicleType getVehicleType() {
		return (KarVehicleType) Encodings.getTypeByValue(KarVehicleType.class, getValue(KAR.VEH_TYPE, 0));
	}
	
	@Override
	public String toShortString() {
		return getAttribute(KAR.LOOP_NR) + " " + getAttribute(KAR.DIRECTION);
	}
	
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Sending KAR message.\n");
		int pos = 0;
		for (KarAttribute karAttribute : karAttributes) {
			if (karAttribute.isEnabled()) {
				List<KarField> fields = karAttribute.getKarFields();
				for (int j = 0; j < fields.size(); j++) {
					KarField field = fields.get(j);
					sBuilder.append(StringUtils.rightPad(field.getFieldName() + " ("+ karAttribute.getId().getValue() + ")", NAME_OFFSET));
					Class<? extends Encoding> encoding = field.getEncoding();
					if (field.getEncoding() != null) {
						sBuilder.append(StringUtils.rightPad(Encodings.getNameByNr(encoding, field.getValue()), VALUE_OFFSET));
					} else {
						sBuilder.append(StringUtils.rightPad(Integer.toString(field.getValue()), VALUE_OFFSET));
					}
					sBuilder.append(pos++ % 2 == 0 ? "| " : "\n");
				}
			}
		}
		return sBuilder.toString();
	}
	
	public int getValue(KAR id, int fieldIndex) {
		return getAttribute(id).getKarFields().get(fieldIndex).getValue();
	}
	
	public void setValue(AttributeID id, int fieldIndex, int value) {
		getAttribute(id).getKarFields().get(fieldIndex).setValue(value);
	}
	
	/**
	 * Method to set one of the encoded variables
	 * @param enumValue
	 */
	public <E extends Enum<E> & Encoding> void setAttribute(AttributeID id, E enumValue) {
		getAttribute(id).getKarFields().get(0).setValue(enumValue);
	}
	
	private void fillKARAttributes() {
		karAttributes = new ArrayList<>();
		
		KAR number = KAR.LOOP_NR;
		String fieldName = "Loop nr";
		int sizeInBytes = 1;
		Range<Integer> range = Range.between(0, 127);
		Class<? extends Encoding> encoding = null;
		KarField karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, karField));
		
		number = KAR.VEH_TYPE;
		fieldName = "Veh. type";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = KarVehicleType.class;
		karField = new KarField(fieldName, sizeInBytes, range, KarVehicleType.class);
		karAttributes.add(new KarAttribute(number, karField));
		
		number = KAR.LINE_NR;
		fieldName = "Line nr";
		sizeInBytes = 2;
		range = Range.between(0, 9999);
		encoding = null;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.SERVICE_NR;
		fieldName = "Service nr";
		sizeInBytes = 2;
		range = Range.between(0, 9999);
		encoding = null;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.COMPANY_NR;
		fieldName = "Company nr";
		sizeInBytes = 1;
		range = Range.between(0, 255);
		encoding = null;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.VEH_ID;
		fieldName = "Veh. id";
		sizeInBytes = 2;
		range = Range.between(0, 32767);
		encoding = null;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.DIRECTION;
		fieldName = "Direction";
		sizeInBytes = 1;
		range = Range.between(0, 255);
		encoding = null;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.VEH_STATUS;
		fieldName = "Veh. status";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = VehicleStatus.class;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.PRIO_CLASS;
		fieldName = "Prio. class";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = PriorityClass.class;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.PUNCT_CLASS;
		fieldName = "Punct. class";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = PunctualityClass.class;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.PUNCTUALITY;
		fieldName = "Punctuality";
		sizeInBytes = 2;
		range = Range.between(-3600, 3600);
		encoding = null;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.VEH_LENGTH;
		fieldName = "Veh. length";
		sizeInBytes = 1;
		range = Range.between(0, 255);
		encoding = null;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.VEH_SPEED;
		fieldName = "Veh. speed";
		sizeInBytes = 1;
		range = Range.between(0, 255);
		encoding = null;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.DISTANCE_TO_STOP;
		fieldName = "Dist. to stop";
		sizeInBytes = 2;
		range = Range.between(-99, 9999);
		encoding = null;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.TIME_TO_STOP;
		fieldName = "Time to stop";
		sizeInBytes = 1;
		range = Range.between(0, 255);
		encoding = null;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.JOURNEY_NR;
		fieldName = "Journey nr";
		sizeInBytes = 2;
		range = Range.between(0, 9999);
		encoding = null;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.JOURNEY_TYPE;
		fieldName = "Journey type";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = JourneyType.class;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.ROUTE;
		fieldName = "Route";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = null;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.COMMAND;
		fieldName = "Command";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = Command.class;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.ACTIVATION;
		fieldName = "Activation";
		sizeInBytes = 2;
		range = Range.between(0, 32767);
		encoding = null;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.LOCATION;
		List<KarField> locationFields = new ArrayList<>();
		encoding = null;
		locationFields.add(new KarField(0, "Latitude deg", 1, Range.between(0, 89), encoding));
		locationFields.add(new KarField(1, "Latitude min", 1, Range.between(0, 59), encoding));
		locationFields.add(new KarField(2, "Latitude sec", 1, Range.between(0, 59), encoding));
		locationFields.add(new KarField(3, "Latitude 1/100 sec", 1, Range.between(0, 99), encoding));
		locationFields.add(new KarField(4, "Longitude deg", 1, Range.between(0, 179), encoding));
		locationFields.add(new KarField(5, "Longitude min", 1, Range.between(0, 59), encoding));
		locationFields.add(new KarField(6, "Longitude sec", 1, Range.between(0, 59), encoding));
		locationFields.add(new KarField(7, "Longitude 1/100 sec", 1, Range.between(0, 99), encoding));
		karAttributes.add(new KarAttribute(number, locationFields));

		number = KAR.DATE;
		List<KarField> dateFields = new ArrayList<>();
		encoding = null;
		dateFields.add(new KarField(0, "Year", 2, Range.between(0, 9999), encoding));
		dateFields.add(new KarField(1, "Month", 1, Range.between(1, 12), encoding));
		dateFields.add(new KarField(2, "Day", 1, Range.between(1, 31), encoding));
		dateFields.add(new KarField(3, "Hours", 1, Range.between(0, 23), encoding));
		dateFields.add(new KarField(4, "Min", 1, Range.between(0, 59), encoding));
		dateFields.add(new KarField(5, "Seconds", 1, Range.between(0, 59), encoding));
		karAttributes.add(new KarAttribute(number, dateFields));

		number = KAR.RESERVE1;
		fieldName = "Reserve 1";
		sizeInBytes = 2;
		range = Range.between(0, 32767);
		encoding = null;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.RESERVE2;
		fieldName = "Reserve 2";
		sizeInBytes = 2;
		range = Range.between(0, 32767);
		encoding = null;
		karField = new KarField(fieldName, sizeInBytes, range, encoding);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
	}

}
