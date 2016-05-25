package model;

import java.io.Serializable;

import org.apache.commons.lang3.Range;

import com.google.common.collect.BiMap;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class KarField implements Serializable{
	private static final long serialVersionUID = 5648904267893701445L;
	
	@Getter private final String fieldName;
	@Getter private final int sizeInBytes;
	@Getter private final Range<Integer> range;
	@Getter private final BiMap<Integer, String> encoding;
	@Getter @Setter private int value;
}