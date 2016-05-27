package model.kar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Range;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import lombok.Getter;
import model.AttributeID;
import model.Encodings;
import model.Encodings.Command;
import model.Encodings.Encoding;
import model.Encodings.JourneyType;
import model.Encodings.KarPunctualityClass;
import model.Encodings.KarVehicleType;
import model.Encodings.PriorityClass;
import model.Encodings.VehicleStatus;
import model.ProtocolMessage;
import model.kar.KarAttribute.KAR;

public class KarMessage implements ProtocolMessage, Serializable {
	private static final long serialVersionUID = -2330790358385718884L;
	
	@Getter private List<KarAttribute> karAttributes;
	
	public KarMessage() {
		fillKARAttributes();
	}
	
	@Override
	public int getValue(AttributeID id) {
		return getValue((KAR) id, 0);
	}

	public KarAttribute getAttribute(AttributeID id) {
		for (KarAttribute attribute : karAttributes) {
			if (attribute.getId() == id) {
				return attribute;
			}
		}
		return null;
	}
	
	public int getValue(KAR id, int fieldIndex) {
		return getAttribute(id).getKarFields().get(fieldIndex).getValue();
	}
	
	public void setAttribute(KAR id, int value) {
		getAttribute(id).getKarFields().get(0).setValue(value);
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
		BiMap<Integer, String> encoding = Encodings.createEncoding(null);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.VEH_TYPE;
		fieldName = "Veh. type";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = Encodings.createEncoding(KarVehicleType.class);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.LINE_NR;
		fieldName = "Line nr";
		sizeInBytes = 2;
		range = Range.between(0, 9999);
		encoding = Encodings.createEncoding(null);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.SERVICE_NR;
		fieldName = "Service nr";
		sizeInBytes = 2;
		range = Range.between(0, 9999);
		encoding = Encodings.createEncoding(null);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.COMPANY_NR;
		fieldName = "Company nr";
		sizeInBytes = 1;
		range = Range.between(0, 255);
		encoding = Encodings.createEncoding(null);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.VEH_ID;
		fieldName = "Veh. id";
		sizeInBytes = 2;
		range = Range.between(0, 32767);
		encoding = Encodings.createEncoding(null);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.DIRECTION;
		fieldName = "Direction";
		sizeInBytes = 1;
		range = Range.between(0, 255);
		encoding = Encodings.createEncoding(null);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.VEH_STATUS;
		fieldName = "Veh. status";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = Encodings.createEncoding(VehicleStatus.class);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.PRIO_CLASS;
		fieldName = "Prio. class";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = Encodings.createEncoding(PriorityClass.class);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.PUNCT_CLASS;
		fieldName = "Punct. class";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = Encodings.createEncoding(KarPunctualityClass.class);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.PUNCTUALITY;
		fieldName = "Punctuality";
		sizeInBytes = 2;
		range = Range.between(-3600, 3600);
		encoding = Encodings.createEncoding(null);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.VEH_LENGTH;
		fieldName = "Veh. length";
		sizeInBytes = 1;
		range = Range.between(0, 255);
		encoding = Encodings.createEncoding(null);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.VEH_SPEED;
		fieldName = "Veh. speed";
		sizeInBytes = 1;
		range = Range.between(0, 255);
		encoding = Encodings.createEncoding(null);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.DISTANCE_TO_STOP;
		fieldName = "Dist. to stop";
		sizeInBytes = 2;
		range = Range.between(-99, 9999);
		encoding = Encodings.createEncoding(null);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.TIME_TO_STOP;
		fieldName = "Time to stop";
		sizeInBytes = 1;
		range = Range.between(0, 255);
		encoding = Encodings.createEncoding(null);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.JOURNEY_NR;
		fieldName = "Journey nr";
		sizeInBytes = 2;
		range = Range.between(0, 9999);
		encoding = Encodings.createEncoding(null);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.JOURNEY_TYPE;
		fieldName = "Journey type";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = Encodings.createEncoding(JourneyType.class);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.ROUTE;
		fieldName = "Route";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = Encodings.createEncoding(null);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.COMMAND;
		fieldName = "Command";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = Encodings.createEncoding(Command.class);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.ACTIVATION;
		fieldName = "Activation";
		sizeInBytes = 2;
		range = Range.between(0, 32767);
		encoding = Encodings.createEncoding(null);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.LOCATION;
		List<KarField> locationFields = new ArrayList<>();
		encoding = HashBiMap.create();
		locationFields.add(new KarField("Latitude deg", 1, Range.between(0, 89), encoding));
		locationFields.add(new KarField("Latitude min", 1, Range.between(0, 59), encoding));
		locationFields.add(new KarField("Latitude sec", 1, Range.between(0, 59), encoding));
		locationFields.add(new KarField("Latitude 1/100 sec", 1, Range.between(0, 99), encoding));
		locationFields.add(new KarField("Longitude deg", 1, Range.between(0, 179), encoding));
		locationFields.add(new KarField("Longitude min", 1, Range.between(0, 59), encoding));
		locationFields.add(new KarField("Longitude sec", 1, Range.between(0, 59), encoding));
		locationFields.add(new KarField("Longitude 1/100 sec", 1, Range.between(0, 99), encoding));
		karAttributes.add(new KarAttribute(number, locationFields));

		number = KAR.DATE;
		List<KarField> dateFields = new ArrayList<>();
		encoding = Encodings.createEncoding(null);
		dateFields.add(new KarField("Year", 2, Range.between(0, 9999), encoding));
		dateFields.add(new KarField("Month", 1, Range.between(1, 12), encoding));
		dateFields.add(new KarField("Day", 1, Range.between(1, 31), encoding));
		dateFields.add(new KarField("Hours", 1, Range.between(0, 23), encoding));
		dateFields.add(new KarField("Min", 1, Range.between(0, 59), encoding));
		dateFields.add(new KarField("Seconds", 1, Range.between(0, 59), encoding));
		karAttributes.add(new KarAttribute(number, dateFields));

		number = KAR.RESERVE1;
		fieldName = "Reserve 1";
		sizeInBytes = 2;
		range = Range.between(0, 32767);
		encoding = Encodings.createEncoding(null);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.RESERVE2;
		fieldName = "Reserve 2";
		sizeInBytes = 2;
		range = Range.between(0, 32767);
		encoding = Encodings.createEncoding(null);
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
	}
}
