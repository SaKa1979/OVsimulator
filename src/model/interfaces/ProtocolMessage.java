package model.interfaces;

import java.io.Serializable;

import model.Encodings.VehicleType;

public interface ProtocolMessage extends Serializable {
	public Attribute getAttribute(AttributeID id);
	public VehicleType getVehicleType();
	public String toShortString();
}
