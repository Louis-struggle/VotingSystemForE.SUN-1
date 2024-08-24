package com.votingsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.votingsystem.entity.Vote;
import com.votingsystem.entity.VotingItem;
import com.votingsystem.repository.VoteRepository;
import com.votingsystem.repository.VotingItemRepository;

import jakarta.transaction.Transactional;

@Service
public class VotingService {
	@Autowired
	private VotingItemRepository votingItemRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate; //用來存取預存程序
	
	//後台
	//新增項目
	@Transactional
	public VotingItem createVotingItem(VotingItem votingItem) {
		return votingItemRepository.save(votingItem);
	}
	
	//查詢項目
	public boolean existsByItemName(String itemName) {
		return votingItemRepository.existsByItemName(itemName);
	}
	
	//查詢所有項目
    public List<VotingItem> getAllVotingItems() {
        return votingItemRepository.findAll();
    }
    
    //刪除項目
    public void deleteVotingItem(Integer itemId) {
    	votingItemRepository.deleteById(itemId);
    }
    
    //查詢所有投票資料
    public List<Vote> getAllVote(){
    	return voteRepository.findAll();
    }
    
    //前台
    //使用者投票
    public void voting(String name, List<String> itemIds) {
    	
    	//使用預存程序取值拿出來會是泛型為Map的陣列
    	List<Map<String, Object>> queryForList = jdbcTemplate.queryForList("EXEC GetAllVote ?", name);
    	List<Vote> findByName = new ArrayList<>();
    	for (Map<String, Object> row : queryForList) {
			Vote vote = new Vote();
			vote.setId((Integer) row.get("id"));
			vote.setName((String)row.get("name"));
			vote.setItemId((Integer)row.get("itemId"));
			findByName.add(vote);
		} 	
    	
//    	List<Vote> findByName = voteRepository.findByName(name);

    	List<Vote> voteList = new ArrayList<>();
    	for (String itemId : itemIds) {
    		int transfer = Integer.valueOf(itemId);
    		boolean alreadyVoted = false; 
    		for (Vote compare : findByName) {
				if (compare.getItemId().equals(transfer)) { //如果有投過就設定為true
					alreadyVoted = true;
					break;
				}
			}
    		if(!alreadyVoted) {
    			Vote vote = new Vote();
    			vote.setName(name);
    			vote.setItemId(transfer);
    			voteList.add(vote);
    			//找到項目並且將票數+1
    			Optional<VotingItem> result = votingItemRepository.findById(transfer);
    			VotingItem votingItem = result.get();
    			votingItem.setNumberOfVotes(votingItem.getNumberOfVotes() + 1);
    			votingItemRepository.save(votingItem);					  			
    		}
		}
    	voteRepository.saveAll(voteList);
    }
}
