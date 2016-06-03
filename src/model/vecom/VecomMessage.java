package model.vecom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Range;
import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import model.Encodings;
import model.Encodings.CategoryType;
import model.Encodings.Encoding;
import model.Encodings.Inteli;
import model.Encodings.JourneyType;
import model.Encodings.ManualControl;
import model.Encodings.PunctualityClass;
import model.Encodings.VecomVehicleType;
import model.interfaces.AttributeID;
import model.interfaces.ProtocolMessage;
import model.kar.KarAttribute.KAR;
import model.vecom.VecomAttribute.VECOM;

public class VecomMessage implements ProtocolMessage, Serializable {
	private static final long serialVersionUID = 1422953515197092382L;

	private static final int NAME_OFFSET = 25;
	private static final int VALUE_OFFSET = 15;

	@Getter	private List<VecomAttribute> vecomAttributes;

	public VecomMessage() {
		fillVecomAttributes();
	}

	@Override
	public VecomAttribute getAttribute(AttributeID id) {
		for (VecomAttribute attribute : vecomAttributes) {
			if (attribute.getId() == id) {
				return attribute;
			}
		}
		return null;
	}
	
	@Override
	public VecomVehicleType getVehicleType() {
		return (VecomVehicleType) Encodings.getTypeByValue(VecomVehicleType.class, getAttribute(VECOM.VEH_TYPE).getValue());
	}
	
	@Override
	public String toShortString() {
		return getAttribute(VECOM.LOOP_NR).toShortString();
	}
	
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("Sending VECOM message.\n");
		int pos = 0;
		for (VecomAttribute vecomAttribute : vecomAttributes) {
			sBuilder.append(StringUtils.rightPad(vecomAttribute.getFieldName() + " ("+ vecomAttribute.getId().getValue() + ")", NAME_OFFSET));
			Class<? extends Encoding> encoding = vecomAttribute.getEncoding();
			if (vecomAttribute.getEncoding() != null) {
				sBuilder.append(StringUtils.rightPad(Encodings.getNameByNr(encoding, vecomAttribute.getValue()), VALUE_OFFSET));
			} else {
				sBuilder.append(StringUtils.rightPad(Integer.toString(vecomAttribute.getValue()), VALUE_OFFSET));
			}
			sBuilder.append(pos++ % 2 == 0 ? "| " : "\n");
		}
		return sBuilder.toString();
	}
	
	public void setAttribute(AttributeID id, int value) {
		getAttribute(id).setValue(value);
	}
	
	public void setAttribute(AttributeID id, Encoding enumValue) {
		getAttribute(id).setValue(enumValue);
	}
	
	private void fillVecomAttributes() {
		vecomAttributes = new ArrayList<>();

		VECOM number = VECOM.VEH_TYPE;
		String fieldName = "Veh. type";
		int sizeInBits = 8;
		Range<Integer> range = Range.between(0, 255);
		Class<? extends Encoding> encoding = VecomVehicleType.class;
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		getAttribute(VECOM.VEH_TYPE).setValue(VecomVehicleType.POLICE);

		number = VECOM.LINE_NR;
		fieldName = "Line nr";
		sizeInBits = 14;
		range = Range.between(0, 16383);
		encoding = null;
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		
		number = VECOM.SERVICE_NR;
		fieldName = "Service nr";
		sizeInBits = 10 + 3;	// 3 extra 0 stuffing bits
		range = Range.between(0, 1023);
		encoding = null;
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));

		// These fields are not used in the CVN interface
		number = VECOM.INTELI;
		fieldName = "Inteli";
		sizeInBits = 1;
		range = Range.between(0, 1);
		encoding = Inteli.class;
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		
		number = VECOM.PUNCTUALITY;
		fieldName = "Punctualiteit";
		sizeInBits = 2;
		range = Range.between(0, 3);
		encoding = PunctualityClass.class;
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		
		number = VECOM.CATEGORY;
		fieldName = "Category";
		sizeInBits = 2;
		range = Range.between(0, 3);
		encoding = CategoryType.class;
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		
//		number = VECOM.BCD;
//		fieldName = "Bcd";
//		sizeInBits = 3;
//		range = Range.between(0, 7);
//		encoding = null;
//		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));

		number = VECOM.STAFF_NR;
		fieldName = "Staff nr";
		sizeInBits = 24;
		range = Range.between(0, 16777215);
		encoding = null;
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		
		number = VECOM.FLEET_NR;
		fieldName = "Fleet nr";
		sizeInBits = 18;
		range = Range.between(0, 262143);
		encoding = null;
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		
		number = VECOM.MANUAL_CONTROL;
		fieldName = "Manual control";
		sizeInBits = 3 + 5; // 5 extra 0 stuffing bits
		range = Range.between(0, 7);
		encoding = ManualControl.class;
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		
		number = VECOM.LOOP_NR;
		fieldName = "Loop nr";
		sizeInBits = 4 + 3; // 3 extra 0 stuffing bits
		range = Range.between(0, 15);
		encoding = null;
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));

//		number = VECOM.DIRECTION;
//		fieldName = "Direction";
//		sizeInBits = 2;
//		range = Range.between(0, 3);
//		encoding = Direction.class;
//		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		
//		number = VECOM.OVER_LOOP;
//		fieldName = "Over loop";
//		sizeInBits = 1;
//		range = Range.between(0, 1);
//		encoding = OverLoop.class;
//		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
	}
}
