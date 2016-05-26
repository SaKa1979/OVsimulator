package model.vecom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Range;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import lombok.Getter;
import model.AttributeID;
import model.CVNAttribute.Commands;
import model.CVNAttribute.JourneyType;
import model.CVNAttribute.PriorityClass;
import model.CVNAttribute.PunctualityClass;
import model.CVNAttribute.VehicleStatus;
import model.CVNAttribute.VehicleType;
import model.ProtocolMessage;
import model.vecom.VecomAttribute.VECOM;

public class VecomMessage implements ProtocolMessage, Serializable {
	private static final long serialVersionUID = 1422953515197092382L;

	@Getter	private List<VecomAttribute> vecomAttributes;

	public VecomMessage() {
		fillVecomAttributes();
	}

	public int getValue(AttributeID id) {
		return getAttribute((VECOM) id).getValue();
	}

	public String getName(AttributeID id) {
		return getAttribute((VECOM) id).getFieldName();
	}

	public VecomAttribute getAttribute(VECOM id) {
		for (VecomAttribute attribute : vecomAttributes) {
			if (attribute.getId() == id) {
				return attribute;
			}
		}
		return null;
	}

	private void fillVecomAttributes() {
		vecomAttributes = new ArrayList<>();

		VECOM number = VECOM.LOOP_NR;
		String fieldName = "Loop nr";
		int sizeInBits = 8;
		Range<Integer> range = Range.between(0, 127);
		BiMap<Integer, String> encoding = HashBiMap.create();
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));

		number = VECOM.VEH_TYPE;
		fieldName = "Veh. type";
		sizeInBits = 8;
		range = Range.between(0, 99);
		encoding = createVehicleTypeEncoding();
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));

		number = VECOM.LINE_NR;
		fieldName = "Line nr";
		sizeInBits = 16;
		range = Range.between(0, 9999);
		encoding = HashBiMap.create();
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));

		// number = KAR.SERVICE_NR;
		// fieldName = "Service nr";
		// sizeInBits = 2;
		// range = Range.between(0, 9999);
		// encoding = HashBiMap.create();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
		//
		// number = KAR.COMPANY_NR;
		// fieldName = "Company nr";
		// sizeInBits = 1;
		// range = Range.between(0, 255);
		// encoding = HashBiMap.create();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
		//
		// number = KAR.VEH_ID;
		// fieldName = "Veh. id";
		// sizeInBits = 2;
		// range = Range.between(0, 32767);
		// encoding = HashBiMap.create();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
		//
		// number = KAR.DIRECTION;
		// fieldName = "Direction";
		// sizeInBits = 1;
		// range = Range.between(0, 255);
		// encoding = HashBiMap.create();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
		//
		// number = KAR.VEH_STATUS;
		// fieldName = "Veh. status";
		// sizeInBits = 1;
		// range = Range.between(0, 99);
		// encoding = createVehicleStatusEncoding();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
		//
		// number = KAR.PRIO_CLASS;
		// fieldName = "Prio. class";
		// sizeInBits = 1;
		// range = Range.between(0, 99);
		// encoding = createPriorityClassEncoding();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
		//
		// number = KAR.PUNCT_CLASS;
		// fieldName = "Punct. class";
		// sizeInBits = 1;
		// range = Range.between(0, 99);
		// encoding = createPunctualityClassEncoding();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
		//
		// number = KAR.PUNCTUALITY;
		// fieldName = "Punctuality";
		// sizeInBits = 2;
		// range = Range.between(-3600, 3600);
		// encoding = HashBiMap.create();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
		//
		// number = KAR.VEH_LENGTH;
		// fieldName = "Veh. length";
		// sizeInBits = 1;
		// range = Range.between(0, 255);
		// encoding = HashBiMap.create();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
		//
		// number = KAR.VEH_SPEED;
		// fieldName = "Veh. speed";
		// sizeInBits = 1;
		// range = Range.between(0, 255);
		// encoding = HashBiMap.create();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
		//
		// number = KAR.DISTANCE_TO_STOP;
		// fieldName = "Dist. to stop";
		// sizeInBits = 2;
		// range = Range.between(-99, 9999);
		// encoding = HashBiMap.create();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
		//
		// number = KAR.TIME_TO_STOP;
		// fieldName = "Time to stop";
		// sizeInBits = 1;
		// range = Range.between(0, 255);
		// encoding = HashBiMap.create();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
		//
		// number = KAR.JOURNEY_NR;
		// fieldName = "Journey nr";
		// sizeInBits = 2;
		// range = Range.between(0, 9999);
		// encoding = HashBiMap.create();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
		//
		// number = KAR.JOURNEY_TYPE;
		// fieldName = "Journey type";
		// sizeInBits = 1;
		// range = Range.between(0, 99);
		// encoding = createJourneyTypeEncoding();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
		//
		// number = KAR.ROUTE;
		// fieldName = "Route";
		// sizeInBits = 1;
		// range = Range.between(0, 99);
		// encoding = HashBiMap.create();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
		//
		// number = KAR.COMMAND;
		// fieldName = "Command";
		// sizeInBits = 1;
		// range = Range.between(0, 99);
		// encoding = createCommandsEncoding();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
		//
		// number = KAR.ACTIVATION;
		// fieldName = "Activation";
		// sizeInBits = 2;
		// range = Range.between(0, 32767);
		// encoding = HashBiMap.create();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
		//
		// number = KAR.RESERVE1;
		// fieldName = "Reserve 1";
		// sizeInBits = 2;
		// range = Range.between(0, 32767);
		// encoding = HashBiMap.create();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
		//
		// number = KAR.RESERVE2;
		// fieldName = "Reserve 2";
		// sizeInBits = 2;
		// range = Range.between(0, 32767);
		// encoding = HashBiMap.create();
		// vecomAttributes.add(new KarAttribute(number, new KarField(fieldName,
		// sizeInBits, range, encoding)));
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
