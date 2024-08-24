package com.votingsystem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.votingsystem.dto.VotingDTO;
import com.votingsystem.entity.VotingItem;
import com.votingsystem.service.VotingService;

@Controller
public class VotingController {
	@Autowired
    private VotingService votingService;
	
	//後台---------------------
	@GetMapping("/BackVotingItemView")
	public String backvotingItemView() {
		return "Back";
	}

	
	//後台新增項目
	@PostMapping("/AddItem")
	public ResponseEntity<String> addItem(@RequestBody VotingItem votingItem){
		boolean result = votingService.existsByItemName(votingItem.getItemName());
		
		if (result) {
			return ResponseEntity.badRequest().body("項目已存在");
		}else {
			votingService.createVotingItem(votingItem);
			return ResponseEntity.ok("新增成功");			
		}
	}
	//後台刪除項目
	@PostMapping("/DeleteItem")
	public ResponseEntity<String> deleteItem(@RequestBody VotingItem votingItem){
		votingService.deleteVotingItem(votingItem.getId());
		return ResponseEntity.ok("刪除成功");	
	}
	
	//前後台共同使用-------------
	//前後台看見所有項目
	@ResponseBody
    @GetMapping("/items")
    public List<VotingItem> getAllVotingItems() {
        return votingService.getAllVotingItems();    
    }
	//前後台共同使用-------------
	
	//前台-------------------------
	@GetMapping("/FrontVotingItemView")
	public String votingItemView() {
		return "AllVotingItem";
	}
	
	//前台投票
	@PostMapping("/Voting")
	public ResponseEntity<String> voting(@RequestBody VotingDTO votingDTO) {
		String name = votingDTO.getVotingName();
		List<String> itemIds = votingDTO.getItemId();
		
		for (String itemId : itemIds) {
			int transferId = Integer.valueOf(itemId);
			
		}
		
		votingService.voting(name, itemIds);		
		return ResponseEntity.ok("投票成功");	
	}	
}
