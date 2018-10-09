package com.revature.pojos.files;

public class GradeFile extends NormalFile {
	
	public enum Type{
		PRESENTATION, GRADE
	}
	
	private Type gradeType;
	
	public GradeFile() {}

	public Type getGradeType() {
		return gradeType;
	}

	public void setGradeType(Type gradeType) {
		this.gradeType = gradeType;
	}

}
