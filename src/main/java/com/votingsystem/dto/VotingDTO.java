package com.votingsystem.dto;

import java.util.List;

public class VotingDTO {
	
	private List<String> itemId;
	private String votingName;
		
	public VotingDTO() {

	}

	
	public List<String> getItemId() {
		return itemId;
	}


	public void setItemId(List<String> itemId) {
		this.itemId = itemId;
	}


	public String getVotingName() {
		return votingName;
	}
	public void setVotingName(String votingName) {
		this.votingName = votingName;
	}
	
	
}
