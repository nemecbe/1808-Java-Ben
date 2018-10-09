package com.revature.pojos.files;

import java.io.File;

public class NormalFile {
	
	//File?
	private File curFile;
	
	private String type;
	
	public NormalFile() {}

	public File getFile() {
		return curFile;
	}

	public void setFile(File curFile) {
		this.curFile = curFile;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
