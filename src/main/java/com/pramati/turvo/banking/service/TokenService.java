package com.pramati.turvo.banking.service;

import java.util.List;

import com.pramati.turvo.banking.model.CounterQueue;
import com.pramati.turvo.banking.model.Token;

public interface TokenService {
	public Token createToken(Token token);

	public Token markTokenAsComplete(Long tokenId);

	public Token markTokenAsCancel(Long tokenId);

	public List<Token> getAllTokens();

	public Token getTokenById(Long tokenId);
	
	public CounterQueue getCounterById(Integer counterid);

	public List<CounterQueue> getAllCounters();

}
