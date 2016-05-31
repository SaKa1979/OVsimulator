package model;

import lombok.AllArgsConstructor;
import lombok.Getter;

//TODO split in kar and vecom encoding/ what about common encodings?
public class Encodings {
	
	public interface Encoding {
		public String getName();
		public int getValue();
	}
	
	@AllArgsConstructor
	public enum ManualControl implements Encoding  {
	    NOMANUALCONTROL ("Geen info",0), 
	    TURNLEFT ("Linksaf",1),
	    TURNRIGHT ("Rechtsaf",2),
	    FORWARD ("Voorwaarts",3),
	    READYTOSTART ("Startklaar", 4),
	    RTS_TR ("Startklaar + rechtsaf", 5),
	    RTS_TL ("Startklaar + linksaf", 6),
	    RTS_F ("Startklaar + voorwaarts", 7);

		@Getter private String name;
		@Getter private int value;
	}
	
	@AllArgsConstructor
	public enum OverLoop implements Encoding  {
		DEPARTURE ("Vertrek", 0),
	    ARRIVAL ("Aankomst", 1);

		@Getter private String name;
		@Getter private int value;
	}
	
	@AllArgsConstructor
	public enum Command implements Encoding  {
	    RESERVE ("Reserve",0), 
	    IN ("Imelding", 1),
	    UIT ("Uitmelding", 2),
	    VOOR ("Vooraankondiging", 3);

		@Getter private String name;
		@Getter private int value;
	}

	@AllArgsConstructor
	public enum KarVehicleType implements Encoding {
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
		@Getter private int value;
	}
	
	@AllArgsConstructor
	public enum VecomVehicleType implements Encoding  {
	  	POLICE ("Politie", 0x10),
	    FIRE_BRIGADE ("Brandweer", 0x08), 
	    AMBULANCE ("Ambulance", 0x0C),
	    TAXI ("Taxi", 0x04),
//	    SECOND_TRANSPONDER ("Tweede reageerder", 0x00),
//	    RESERVED1 ("Reserve 1", 0x00),
//	    RESERVED2 ("Reserve 2", 0x00),
	    CITY_TRAM ("Stads tram", 0x80),
	    CITY_BUS ("Stads bus", 0xA0),
	    REGIONAL_TRAM ("Regionale tram", 0xC0),
	    REGIONAL_BUS ("Regionale bus", 0xE0);

		@Getter private String name;
		@Getter private int value;
	}
	
	@AllArgsConstructor
	public enum CategoryType implements Encoding  {
	  	LIJN_DIENST ("Lijn dienst", 0),
	    UITRUK ("Uitruk", 1), 
	    INRUK ("Inruk", 2),
	    GEEN_DIENST ("Geen dienst", 3); 

		@Getter private String name;
		@Getter private int value;
	}

	@AllArgsConstructor
	public enum VehicleStatus implements Encoding  {
	    GEENINFO ("Geen info", 0), 
	    ONDERWEG ("Onderweg", 1),
	    STOP1 ("Stop bij halte", 2),
	    EINDE ("Einde", 3),
	    STOP2 ("Stop niet bij halte", 4);

		@Getter private String name;
		@Getter private int value;
	}

	@AllArgsConstructor
	public enum PriorityClass implements Encoding  {
	    GEENINFO ("Geen info",0), 
	    GEEN ("Geen",1),
	    CONDITIONEEL ("Conditioneel",2),
	    ABSOLUUT ("Absoluut",3),
	    ALARM ("Alarm",4);

		@Getter private String name;
		@Getter private int value;
	}
	
	@AllArgsConstructor
	public enum PunctualityClass implements Encoding  {
	    GEENINFO ("Geen info",0), 
	    TELAAT ("Te laat",1),
	    OPTIJD ("Op tijd",2),
	    TEVROEG ("Te vroeg",3),
	    BUITENDIENST ("Geen dienst",4);

		@Getter private String name;
		@Getter private int value;
	}
	
	@AllArgsConstructor
	public enum Inteli implements Encoding  {
	    NORMAL ("Normaal", 0),
	    SYSTEM ("Systeem", 1);

		@Getter private String name;
		@Getter private int value;
	}

	@AllArgsConstructor
	public enum JourneyType implements Encoding  {
	    DIENST ("Dienst", 10), 
	    DEADRUN ("Dead run", 11),
	    REMISE_IN ("Remise in", 12),
	    REMISE_UIT ("Remise uit", 13),
	    RESERVED ("Gereserveerd", 14);

		@Getter private String name;
		@Getter private int value;
	}

	@AllArgsConstructor
	public enum Direction implements Encoding  {
	    UNKNOWN ("Onbekend", 0), 
	    FORWARD ("Voorwaarts", 1),
	    RESERVED ("Gereserveerd", 2),
	    BACKWARDS ("Achteruit", 3);

		@Getter private String name;
		@Getter private int value;
	}
	
	public static Encoding getTypeByValue(Class<? extends Encoding> encoding, int value) {
		for (Encoding e : encoding.getEnumConstants()) {
			if (value == e.getValue()) {
				return e;
			}
		}
		return null;
	}
	
	public static Encoding getTypeByName(Class<? extends Encoding> encoding, String name) {
		for (Encoding e : encoding.getEnumConstants()) {
			if (name.equals(e.getName())) {
				return e;
			}
		}
		return null;
	}
	
	public static String getNameByNr(Class<? extends Encoding> encoding, int value) {
		for (Encoding e : encoding.getEnumConstants()) {
			if (value == e.getValue()) {
				return e.getName();
			}
		}
		return "";
	}
	
	public static int getValueByName(Class<? extends Encoding> encoding, String name) {
		for (Encoding e : encoding.getEnumConstants()) {
			if (name == e.getName()) {
				return e.getValue();
			}
		}
		return 0;
	}
	
	public static String[] getStringArray(Class<? extends Encoding> encoding) {
		Encoding[] encodings = encoding.getEnumConstants();
		String[] array = new String[encodings.length];
		for (int i = 0; i < encodings.length; i++) {
			array[i] = encodings[i].getName();
		}
		return array;
	}
}

	