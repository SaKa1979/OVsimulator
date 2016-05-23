package model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CVNAttribute {
	@Getter private int cvnNumber;
	@Getter private List<CVNField> cvnField;
	
	public CVNAttribute(int cvnNumber, CVNField cvnField) {
		this.cvnNumber = cvnNumber;
		this.cvnField = new ArrayList<CVNField>();
		this.cvnField.add(cvnField);
	}
}
