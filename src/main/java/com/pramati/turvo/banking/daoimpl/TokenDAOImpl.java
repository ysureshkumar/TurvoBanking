package com.pramati.turvo.banking.daoimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.pramati.turvo.banking.dao.TokenDAO;
import com.pramati.turvo.banking.model.Token;
import com.pramati.turvo.banking.repository.TokenRepository;

@Service
public class TokenDAOImpl implements TokenDAO {

	@Autowired
	TokenRepository tokenRepository;
	
	public void settokenRepository(TokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
	}

	public Token save(Token token) {
		return tokenRepository.saveAndFlush(token);
	}

	public Token update(Token token) {
		return tokenRepository.saveAndFlush(token);
	}

	public List<Token> findAll() {
		return tokenRepository.findAll();
	}

	public Token findOne(Long tokenId) {
		return tokenRepository.findOne(tokenId);
	}
	
	public Long getMaxTokenid() {
		return tokenRepository.getMaxTokenid();
	}
}
