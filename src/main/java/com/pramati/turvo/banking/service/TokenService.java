package com.pramati.turvo.banking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.pramati.turvo.banking.model.Token;

public interface TokenService {
	public ResponseEntity<? extends Object> createToken(Token token);

	public ResponseEntity<? extends Object> markToken(Long tokenId, Token requestedToken);

	public List<Token> getAllTokens();

	public Token getTokenById(Long tokenId);

	public ResponseEntity<? extends Object> getCounterById(Integer counterid);

	public ResponseEntity<? extends Object> getAllCounters();

}
