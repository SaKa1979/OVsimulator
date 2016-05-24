package model;

import org.apache.commons.lang3.Range;

import com.google.common.collect.BiMap;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class KarField {
	@Getter private final String fieldName;
	@Getter private final int sizeInBytes;
	@Getter private final Range<Integer> range;
	@Getter private final BiMap<Integer, String> encoding;
	@Getter @Setter private int value;
}
