package model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Range;

import lombok.Getter;

public class CVNMessage {
	private static final int size = 24;
	@Getter private CVNAttribute[] attributes = new CVNAttribute[size];
	
	public CVNMessage() {
		fillCVNAttributes();
	}
	
	public void fillCVNAttributes() {
		attributes[0] = new CVNAttribute(1, new CVNField("Loop nr", 1, Range.between(0, 127)));
		attributes[1] = new CVNAttribute(2, new CVNField("Veh. Type", 1, Range.between(0, 99)));
		attributes[2] = new CVNAttribute(3, new CVNField("Line nr", 2, Range.between(0, 9999)));
		attributes[3] = new CVNAttribute(4, new CVNField("Service nr", 2, Range.between(0, 9999)));
		attributes[4] = new CVNAttribute(5, new CVNField("Company nr", 1, Range.between(0, 255)));
		attributes[5] = new CVNAttribute(6, new CVNField("Veh. id", 2, Range.between(0, 32767)));
		attributes[6] = new CVNAttribute(7, new CVNField("SignalGroup", 1, Range.between(0, 255)));
		attributes[7] = new CVNAttribute(8, new CVNField("Veh. Status", 1, Range.between(0, 99)));
		attributes[8] = new CVNAttribute(9, new CVNField("Prio. Class", 1, Range.between(0, 99)));
		attributes[9] = new CVNAttribute(10, new CVNField("Punct. Class", 1, Range.between(0, 99)));
		attributes[10] = new CVNAttribute(11, new CVNField("Punctuality", 2, Range.between(-3600, 3600)));
		attributes[11] = new CVNAttribute(12, new CVNField("Veh. Length", 1, Range.between(0, 255)));
		attributes[12] = new CVNAttribute(13, new CVNField("Veh. Speed", 1, Range.between(0, 255)));
		attributes[13] = new CVNAttribute(14, new CVNField("Dist. to Stop", 2, Range.between(-99, 9999)));
		attributes[14] = new CVNAttribute(15, new CVNField("Time to Stop", 1, Range.between(0, 255)));
		attributes[15] = new CVNAttribute(16, new CVNField("Journey nr", 2, Range.between(0, 9999)));
		attributes[16] = new CVNAttribute(17, new CVNField("Journey Type", 1, Range.between(0, 99)));
		attributes[17] = new CVNAttribute(18, new CVNField("Route", 1, Range.between(0, 99)));
		attributes[18] = new CVNAttribute(19, new CVNField("Command", 1, Range.between(0, 99)));
		attributes[19] = new CVNAttribute(20, new CVNField("Activation", 2, Range.between(0, 32767)));
		
		List<CVNField> positionFields = new ArrayList<>();
		positionFields.add(new CVNField("Latitude degrees", 1, Range.between(0, 89)));
		positionFields.add(new CVNField("Latitude minutes", 1, Range.between(0, 59)));
		positionFields.add(new CVNField("Latitude seconds", 1, Range.between(0, 59)));
		positionFields.add(new CVNField("Latitude hundredth seconds", 1, Range.between(0, 99)));
		positionFields.add(new CVNField("Longitude degrees", 1, Range.between(0, 89)));
		positionFields.add(new CVNField("Longitude minutes", 1, Range.between(0, 59)));
		positionFields.add(new CVNField("Longitude seconds", 1, Range.between(0, 59)));
		positionFields.add(new CVNField("Longitude hundredth seconds", 1, Range.between(0, 99)));
		attributes[20] = new CVNAttribute(21, positionFields);

		List<CVNField> dateFields = new ArrayList<>();
		dateFields.add(new CVNField("Year", 2, Range.between(0, 9999)));
		dateFields.add(new CVNField("Month", 1, Range.between(1, 12)));
		dateFields.add(new CVNField("Day", 1, Range.between(1, 31)));
		dateFields.add(new CVNField("Hours", 1, Range.between(0, 23)));
		dateFields.add(new CVNField("Min", 1, Range.between(0, 59)));
		dateFields.add(new CVNField("Seconds", 1, Range.between(0, 59)));
		attributes[21] = new CVNAttribute(22, dateFields);
		
		attributes[22] = new CVNAttribute(23, new CVNField("Reserve 1", 2, Range.between(Integer.MIN_VALUE, Integer.MAX_VALUE)));
		attributes[23] = new CVNAttribute(24, new CVNField("Reserve 2", 2, Range.between(Integer.MIN_VALUE, Integer.MAX_VALUE)));
	}
}
