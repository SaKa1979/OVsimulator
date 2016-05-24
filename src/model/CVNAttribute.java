package model;

import org.apache.commons.lang3.Range;

import com.google.common.collect.BiMap;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CVNAttribute {
	
	@Getter private CVN number;
	@Getter private String fieldName;
	@Getter private Range<Integer> range;
	@Getter private BiMap<Integer, String> encoding;
	
	@AllArgsConstructor
	public enum CVN {
		LOOP_NR (0), 
		VEH_TYPE (1), 
		LINE_NR (2), 
		SERVICE_NR (3), 
		COMPANY_NR (4), 
		VEH_ID (5), 
		DIRECTION (6), 
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
		LOCATION_LAT_DEG (20), 
		LOCATION_LAT_MIN (21), 
		LOCATION_LAT_SEC (22), 
		LOCATION_LAT_H_SEC (23),
		LOCATION_LONG_DEG (24), 
		LOCATION_LONG_MIN (25), 
		LOCATION_LONG_SEC (26), 
		LOCATION_LONG_H_SEC (27),
		DATE_YEAR (28), 
		DATE_MONTH (29), 
		DATE_DAY (30), 
		DATE_HOUR (31), 
		DATE_MIN (32), 
		DATE_SEC (33), 
		RESERVE1 (34), 
		RESERVE2 (35);
		
	    @Getter private int number;
	}
	
	@AllArgsConstructor
	public enum ManualControl {
	    NOMANUALCONTROL ("geen info",0), 
	    TURNRIGHT ("turn right",1),
	    TURNLEFT ("turn left ",2),
	    FORWARD ("straight ahead",3),
	    READYTOSTART ("ready to start", 5),
	    RTS_TR ("ready to start and turn right", 6),
	    RTS_TL ("ready to start and turn left", 7),
	    RTS_F ("ready to start and forward", 8);

		@Getter private String name;
		@Getter private int nr;
	}
	
	@AllArgsConstructor
	public enum Commands {
	    RESERVE ("reserve",0), 
	    IN ("imelding",1),
	    UIT ("uitmelding",2),
	    VOOR ("vooraankondiging",3);

		@Getter private String name;
		@Getter private int nr;
	}// end enum Commands

	@AllArgsConstructor
	public enum VehicleType {
	  	NO_INFORMATION ("No information", 0),
	    BUS ("Bus", 1), 
	    TRAM ("Tram", 2),
	    POLICE ("Politie", 3), 
	    BRANDWEER ("Brandweer", 4),
	    AMBULANCE ("Ambulance", 5),
	    CVV ("CVV", 6),
	    TAXI ("Taxi", 7),
	    UNKNOWN("Unknown", 8);

		@Getter private String name;
		@Getter private int nr;
	}

	@AllArgsConstructor
	public enum VehicleStatus {
	    GEENINFO ("Geen info", 0), 
	    ONDERWEG ("Onderweg", 1),
	    STOP1 ("Stop bij halte", 2),
	    EINDE ("Einde", 3),
	    STOP2 ("Stop niet bij halte", 4);

		@Getter private String name;
		@Getter private int nr;
	}

	@AllArgsConstructor
	public enum PriorityClass {
	    GEENINFO ("Geen info",0), 
	    GEEN ("Geen",1),
	    CONDITIONEEL ("Conditioneel",2),
	    ABSOLUUT ("Absoluut",3),
	    ALARM ("Alarm",4);

		@Getter private String name;
		@Getter private int nr;
	}
	
	@AllArgsConstructor
	public enum PunctualityClass {
	    GEENINFO ("Geen info",0), 
	    TELAAT ("Te laat",1),
	    OPTIJD ("Op tijd",2),
	    TEVROEG ("Te vroeg",3),
	    BUITENDIENST ("Geen dienst",4),
	    NORMAL ("Vecom normaal",5),
	    SYSTEM ("Vecom system",6),
	    RESERVE1 ("Vecom reseve1",7),
	    RESERVE2 ("Vecom reseve2",7);

		@Getter private String name;
		@Getter private int nr;
	}

	@AllArgsConstructor
	public enum JourneyType {
	    DIENST ("dienst",10), 
	    DEADRUN ("dead run",11),
	    REMISE_IN ("remise in",12),
	    REMISE_UIT ("remise uit",13),
	    RESERVED ("gereserveer",14);

		@Getter private String name;
		@Getter private int nr;
	}

	@AllArgsConstructor
	public enum Direction {
	    UNKNOWN ("unknown",0), 
	    FORWARD ("forwards",1),
	    RESERVED ("reserved",2),
	    BACKWARDS ("backwards",3);

		@Getter private String name;
		@Getter private int nr;
	}

}
