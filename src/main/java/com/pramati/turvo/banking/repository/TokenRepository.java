package com.pramati.turvo.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pramati.turvo.banking.model.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

	@Query("select max(t.tokenid)+1 from Token t")
	public Long getMaxTokenid();
}
