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
	
	@Getter private final String fieldName;
	@Getter private final int sizeInBytes;
	@Getter private final Range<Integer> range;
	@Getter private final Class<? extends Encoding> encoding;
	@Getter private int value;
	
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
