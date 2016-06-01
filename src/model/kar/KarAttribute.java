package model.kar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import model.interfaces.Attribute;
import model.interfaces.AttributeID;

@RequiredArgsConstructor
public class KarAttribute implements Attribute, Serializable {
	private static final long serialVersionUID = 2643942916709961309L;

	@RequiredArgsConstructor
	public enum KAR implements AttributeID {
		LOOP_NR (0, "L"), 
		VEH_TYPE (1), 
		LINE_NR (2), 
		SERVICE_NR (3), 
		COMPANY_NR (4), 
		VEH_ID (5), 
		DIRECTION (6, "FC"), 
		VEH_STATUS (7), 
		PRIO_CLASS (8), 
		PUNCT_CLASS (9), 
		PUNCTUALITY (10), 
		VEH_LENGTH (11), 
		VEH_SPEED (12), 
		DISTANCE_TO_STOP (13), 
		TIME_TO_STOP (14), 	
		JOURNEY_NR (15), 
		JOURNEY_TYPE (16), 
		ROUTE (17), 
		COMMAND (18), 
		ACTIVATION (19), 
		LOCATION (20), 
		DATE (21),
		RESERVE1 (22), 
		RESERVE2 (23);
		
		KAR(int value) {
			this.value = value;
			this.shortName = "";
		}
		
	    @Getter private final int value;
	    @Getter private final String shortName;
	}
	
	@Getter private final KAR id;
	@Getter private final List<KarField> karFields;
	@Getter @Setter	private boolean enabled;
	
	public KarAttribute(KAR id, KarField karField) {
		this.id = id;
		this.karFields = new ArrayList<>();
		this.karFields.add(karField);
	}
	
	public String toString() {
		return id.getShortName() + karFields.get(0).getValue();
	}
}
