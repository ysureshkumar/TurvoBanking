package com.pramati.turvo.banking.dao;

import java.util.List;

import com.pramati.turvo.banking.model.Token;

public interface TokenDAO {
	public Token save(Token token);

	public Token update(Token token);

	public List<Token> findAll();

	public Token findOne(Long tokenId);
	
	public Long getMaxTokenid();	 
}
