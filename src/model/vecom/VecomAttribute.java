package model.vecom;

import java.io.Serializable;

import org.apache.commons.lang3.Range;

import com.google.common.collect.BiMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import model.Attribute;
import model.AttributeID;
import model.Encodings.Encoding;

@RequiredArgsConstructor
public class VecomAttribute implements Attribute, Serializable {
	private static final long serialVersionUID = 3316417190893869878L;
	
	@AllArgsConstructor
	public enum VECOM implements AttributeID {
		VEH_TYPE (0), 
		LINE_NR (1),
		SERVICE_NR (2),
		CATEGORY (3),
		PUNCTUALITY (4),
		INTELI (5),
		BCD (6),
		STAFF_NR (7),
		FLEET_NR (8),
		MANUAL_CONTROL (9),
		OVER_LOOP (10),
		DIRECTION (11),
		LOOP_NR (12); 
		
	    @Getter private int value;
	}
	
	@Getter private final VECOM id;
	@Getter private final String fieldName;
	@Getter private final int sizeInBits;
	@Getter private final Range<Integer> range;
	@Getter private final BiMap<Integer, String> encoding;
	@Getter private int value;
	
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * Method to set one of the encoded variables
	 * @param enumValue
	 */
	public <E extends Enum<E> & Encoding> void setValue(E enumValue) {
		value = encoding.inverse().get(enumValue.getName());
	}
}
