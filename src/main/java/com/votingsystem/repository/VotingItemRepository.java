package com.votingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.votingsystem.entity.VotingItem;

public interface VotingItemRepository extends JpaRepository<VotingItem, Integer> {
	
	//檢查是否存在項目
	boolean existsByItemName(String itemName);

}
