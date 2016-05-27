package model.kar;

import java.io.Serializable;

import org.apache.commons.lang3.Range;

import com.google.common.collect.BiMap;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import model.Encodings.Encoding;

@RequiredArgsConstructor
public class KarField implements Serializable{
	private static final long serialVersionUID = 5648904267893701445L;
	
	@Getter private final String fieldName;
	@Getter private final int sizeInBytes;
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
