package com.votingsystem.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.votingsystem.entity.Vote;

public interface VoteRepository extends JpaRepository<Vote, Integer> {

	
	public List<Vote> findByName(String name);
	

}
