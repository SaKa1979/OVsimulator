package model.vecom;

import java.io.Serializable;

import org.apache.commons.lang3.Range;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import model.Encodings;
import model.Encodings.Encoding;
import model.interfaces.Attribute;
import model.interfaces.AttributeID;

@RequiredArgsConstructor
public class VecomAttribute implements Attribute, Serializable {
	private static final long serialVersionUID = 3316417190893869878L;
	
	@RequiredArgsConstructor
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
		DIRECTION (11, "FC"),
		LOOP_NR (12, "L");
		
		VECOM(int value) {
			this.value = value;
			this.shortName = "";
		}
		
	    @Getter private final int value;
	    @Getter private final String shortName;
	}
	
	@Getter private final VECOM id;
	@Getter private final String fieldName;
	@Getter private final int sizeInBits;
	@Getter private final Range<Integer> range;
	@Getter private final Class<? extends Encoding> encoding;
	@Getter private int value;
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void setValue(Encoding encoding) {
		this.value = encoding.getValue();
	}
	
	public void setValue(String name) {
		this.value = Encodings.getValueByName(encoding, name);
	}
	
	public String toString() {
		return id.getShortName() + value;
	}
}
