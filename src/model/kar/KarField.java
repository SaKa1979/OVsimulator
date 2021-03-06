package model.kar;

import java.io.Serializable;

import org.apache.commons.lang3.Range;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import model.Encodings;
import model.Encodings.Encoding;

@RequiredArgsConstructor
public class KarField implements Serializable{
	private static final long serialVersionUID = 5648904267893701445L;
	
	@Getter private final int fieldIndex;
	@Getter private final String fieldName;
	@Getter private final int sizeInBytes;
	@Getter private final Range<Integer> range;
	@Getter private final Class<? extends Encoding> encoding;
	@Getter private int value;
	
	public KarField(String fieldName, int sizeInBytes, Range<Integer> range,
			Class<? extends Encoding> encoding) {
		super();
		this.fieldIndex = 0;
		this.fieldName = fieldName;
		this.sizeInBytes = sizeInBytes;
		this.range = range;
		this.encoding = encoding;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public void setValue(Encoding enumValue) {
		this.value = enumValue.getValue();
	}
	
	public void setValue(String name) {
		this.value = Encodings.getValueByName(encoding, name);
	}
}
