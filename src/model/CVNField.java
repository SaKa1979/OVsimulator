package model;

import org.apache.commons.lang3.Range;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CVNField {
	@Getter private String fieldName;
	@Getter private int sizeInBytes;
	@Getter private Range<Integer> range;
}
