package model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Range;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import lombok.Getter;
import model.CVNAttribute.Commands;
import model.CVNAttribute.JourneyType;
import model.CVNAttribute.PriorityClass;
import model.CVNAttribute.PunctualityClass;
import model.CVNAttribute.VehicleStatus;
import model.CVNAttribute.VehicleType;
import model.KarAttribute.KAR;

public class KarMessage {
	@Getter private List<KarAttribute> karAttributes;
	
	public KarMessage() {
		fillKARAttributes();
	}
	
	public void fillKARAttributes() {
		karAttributes = new ArrayList<>();
		
		KAR number = KAR.LOOP_NR;
		String fieldName = "Loop nr";
		int sizeInBytes = 1;
		Range<Integer> range = Range.between(0, 127);
		BiMap<Integer, String> encoding = HashBiMap.create();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.VEH_TYPE;
		fieldName = "Veh. type";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = createVehicleTypeEncoding();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.LINE_NR;
		fieldName = "Line nr";
		sizeInBytes = 2;
		range = Range.between(0, 9999);
		encoding = HashBiMap.create();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.SERVICE_NR;
		fieldName = "Service nr";
		sizeInBytes = 2;
		range = Range.between(0, 9999);
		encoding = HashBiMap.create();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.COMPANY_NR;
		fieldName = "Company nr";
		sizeInBytes = 1;
		range = Range.between(0, 255);
		encoding = HashBiMap.create();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.VEH_ID;
		fieldName = "Veh. id";
		sizeInBytes = 2;
		range = Range.between(0, 32767);
		encoding = HashBiMap.create();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.DIRECTION;
		fieldName = "Direction";
		sizeInBytes = 1;
		range = Range.between(0, 255);
		encoding = HashBiMap.create();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.VEH_STATUS;
		fieldName = "Veh. status";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = createVehicleStatusEncoding();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.PRIO_CLASS;
		fieldName = "Prio. class";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = createPriorityClassEncoding();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.PUNCT_CLASS;
		fieldName = "Punct. class";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = createPunctualityClassEncoding();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.PUNCTUALITY;
		fieldName = "Punctuality";
		sizeInBytes = 2;
		range = Range.between(-3600, 3600);
		encoding = HashBiMap.create();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.VEH_LENGTH;
		fieldName = "Veh. length";
		sizeInBytes = 1;
		range = Range.between(0, 255);
		encoding = HashBiMap.create();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.VEH_SPEED;
		fieldName = "Veh. speed";
		sizeInBytes = 1;
		range = Range.between(0, 255);
		encoding = HashBiMap.create();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.DISTANCE_TO_STOP;
		fieldName = "Dist. to stop";
		sizeInBytes = 2;
		range = Range.between(-99, 9999);
		encoding = HashBiMap.create();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.TIME_TO_STOP;
		fieldName = "Time to stop";
		sizeInBytes = 1;
		range = Range.between(0, 255);
		encoding = HashBiMap.create();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.JOURNEY_NR;
		fieldName = "Journey nr";
		sizeInBytes = 2;
		range = Range.between(0, 9999);
		encoding = HashBiMap.create();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.JOURNEY_TYPE;
		fieldName = "Journey nr";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = createJourneyTypeEncoding();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.ROUTE;
		fieldName = "Route";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = HashBiMap.create();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.COMMAND;
		fieldName = "Command";
		sizeInBytes = 1;
		range = Range.between(0, 99);
		encoding = createCommandsEncoding();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.ACTIVATION;
		fieldName = "Activation";
		sizeInBytes = 2;
		range = Range.between(0, 32767);
		encoding = HashBiMap.create();
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
		encoding = HashBiMap.create();
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
		encoding = HashBiMap.create();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
		
		number = KAR.RESERVE2;
		fieldName = "Reserve 2";
		sizeInBytes = 2;
		range = Range.between(0, 32767);
		encoding = HashBiMap.create();
		karAttributes.add(new KarAttribute(number, new KarField(fieldName, sizeInBytes, range, encoding)));
	}
	
	public String getName(KAR key) {
		return karAttributes.get(key.getValue()).getKarFields().get(0).getFieldName();
	}
	
	public KarAttribute getAttribute(KAR key) {
		return karAttributes.get(key.getValue());
	}
	
	private BiMap<Integer, String> createVehicleTypeEncoding() {
		BiMap<Integer, String> encoding = HashBiMap.create();
		for (VehicleType val : VehicleType.values()) {
			encoding.put(val.getNr(), val.getName());
		}
		return encoding;
	}
	
	private BiMap<Integer, String> createVehicleStatusEncoding() {
		BiMap<Integer, String> encoding = HashBiMap.create();
		for (VehicleStatus val : VehicleStatus.values()) {
			encoding.put(val.getNr(), val.getName());
		}
		return encoding;
	}
	
	private BiMap<Integer, String> createPriorityClassEncoding() {
		BiMap<Integer, String> encoding = HashBiMap.create();
		for (PriorityClass val : PriorityClass.values()) {
			encoding.put(val.getNr(), val.getName());
		}
		return encoding;
	}
	
	private BiMap<Integer, String> createPunctualityClassEncoding() {
		BiMap<Integer, String> encoding = HashBiMap.create();
		for (PunctualityClass val : PunctualityClass.values()) {
			encoding.put(val.getNr(), val.getName());
		}
		return encoding;
	}
	
	private BiMap<Integer, String> createJourneyTypeEncoding() {
		BiMap<Integer, String> encoding = HashBiMap.create();
		for (JourneyType val : JourneyType.values()) {
			encoding.put(val.getNr(), val.getName());
		}
		return encoding;
	}
	
	private BiMap<Integer, String> createCommandsEncoding() {
		BiMap<Integer, String> encoding = HashBiMap.create();
		for (Commands val : Commands.values()) {
			encoding.put(val.getNr(), val.getName());
		}
		return encoding;
	}
}
