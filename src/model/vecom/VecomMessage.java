package model.vecom;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Range;

import com.google.common.collect.BiMap;

import lombok.Getter;
import model.AttributeID;
import model.Encodings;
import model.Encodings.CategoryType;
import model.Encodings.Direction;
import model.Encodings.Encoding;
import model.Encodings.ManualControl;
import model.Encodings.OverLoop;
import model.Encodings.VecomPunctualityClass;
import model.Encodings.VecomVehicleType;
import model.ProtocolMessage;
import model.vecom.VecomAttribute.VECOM;

public class VecomMessage implements ProtocolMessage, Serializable {
	private static final long serialVersionUID = 1422953515197092382L;

	@Getter	private List<VecomAttribute> vecomAttributes;

	public VecomMessage() {
		fillVecomAttributes();
	}

	@Override
	public int getValue(AttributeID id) {
		return getAttribute((VECOM) id).getValue();
	}

	public VecomAttribute getAttribute(AttributeID id) {
		for (VecomAttribute attribute : vecomAttributes) {
			if (attribute.getId() == id) {
				return attribute;
			}
		}
		return null;
	}
	
	public void setAttribute(AttributeID id, int value) {
		getAttribute(id).setValue(value);
	}
	
	/**
	 * Method to set one of the encoded variables
	 * @param enumValue
	 */
	public <E extends Enum<E> & Encoding> void setAttribute(AttributeID id, E enumValue) {
		getAttribute(id).setValue(enumValue);
	}

	private void fillVecomAttributes() {
		vecomAttributes = new ArrayList<>();

		VECOM number = VECOM.VEH_TYPE;
		String fieldName = "Veh. type";
		int sizeInBits = 8;
		Range<Integer> range = Range.between(0, 99);
		BiMap<Integer, String> encoding = Encodings.createEncoding(VecomVehicleType.class);
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));

		number = VECOM.LINE_NR;
		fieldName = "Line nr";
		sizeInBits = 12;
		range = Range.between(0, 9999);
		encoding = Encodings.createEncoding(null);
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		
		number = VECOM.SERVICE_NR;
		fieldName = "Service nr";
		sizeInBits = 12;
		range = Range.between(0, 9999);
		encoding = Encodings.createEncoding(null);
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		
		number = VECOM.CATEGORY;
		fieldName = "Category";
		sizeInBits = 2;
		range = Range.between(0, 99);
		encoding = Encodings.createEncoding(CategoryType.class);
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		
		number = VECOM.PUNCTUALITY;
		fieldName = "Punctualiteit";
		sizeInBits = 2;
		range = Range.between(0, 3);
		encoding = Encodings.createEncoding(VecomPunctualityClass.class);
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));

		number = VECOM.INTELI;
		fieldName = "Inteli";
		sizeInBits = 1;
		range = Range.between(0, 1);
		encoding = Encodings.createEncoding(null);
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		
		number = VECOM.BCD;
		fieldName = "Bcd";
		sizeInBits = 3;
		range = Range.between(0, 7);
		encoding = Encodings.createEncoding(null);
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		
		number = VECOM.STAFF_NR;
		fieldName = "Staff nr";
		sizeInBits = 24;
		range = Range.between(0, 9999);
		encoding = Encodings.createEncoding(null);
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		
		number = VECOM.FLEET_NR;
		fieldName = "Fleet nr";
		sizeInBits = 16;
		range = Range.between(0, 9999);
		encoding = Encodings.createEncoding(null);
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		
		number = VECOM.MANUAL_CONTROL;
		fieldName = "Manual control";
		sizeInBits = 4;
		range = Range.between(0, 9999);
		encoding = Encodings.createEncoding(ManualControl.class);
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		
		number = VECOM.OVER_LOOP;
		fieldName = "Over loop";
		sizeInBits = 1;
		range = Range.between(0, 1);
		encoding = Encodings.createEncoding(OverLoop.class);
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		
		number = VECOM.DIRECTION;
		fieldName = "Direction";
		sizeInBits = 2;
		range = Range.between(0, 3);
		encoding = Encodings.createEncoding(Direction.class);
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
		
		number = VECOM.LOOP_NR;
		fieldName = "Loop nr";
		sizeInBits = 4;
		range = Range.between(0, 15);
		encoding = Encodings.createEncoding(null);
		vecomAttributes.add(new VecomAttribute(number, fieldName, sizeInBits, range, encoding));
	}
}
