package com.revature.pojos.files;

public class ApprovalFile extends NormalFile {
	
	public enum ApprovalType{
		DIRECT_SUPERVISOR, DEPARTMENT_HEAD, BEN_CO
	}
	
	private ApprovalType typeOfApproval;
	
	public ApprovalFile() {}

	public ApprovalType getTypeOfApproval() {
		return typeOfApproval;
	}

	public void setTypeOfApproval(ApprovalType typeOfApproval) {
		this.typeOfApproval = typeOfApproval;
	}

}
