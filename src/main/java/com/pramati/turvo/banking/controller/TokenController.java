package com.pramati.turvo.banking.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pramati.turvo.banking.model.Counter;
import com.pramati.turvo.banking.model.CounterQueue;
import com.pramati.turvo.banking.model.Customer;
import com.pramati.turvo.banking.model.Token;
import com.pramati.turvo.banking.service.CounterService;
import com.pramati.turvo.banking.service.CustomerService;
import com.pramati.turvo.banking.service.TokenService;

@RestController
@RequestMapping("/turvo/tokens")
public class TokenController {

	@Autowired
	TokenService tokenService;

	public TokenService gettokenService() {
		return tokenService;
	}

	public void setTokenService(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@PostMapping("/createtoken")
	public Token createToken(@Valid @RequestBody Token token) {
		return tokenService.createToken(token); // tokenService.createToken(token);
	}

	@PutMapping("/marktokenascomplete/{tokenId}")
	public ResponseEntity<Token> markTokenAsComplete(@PathVariable(value = "tokenId") Long tokenId) {

		Token tokenToUpdate = tokenService.markTokenAsComplete(tokenId);

		if (tokenToUpdate == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(tokenToUpdate);
	}

	@PutMapping("/marktokenascancel/{tokenId}")
	public ResponseEntity<Token> markTokenAsCancel(@PathVariable(value = "tokenId") Long tokenId) {

		Token tokenToUpdate = tokenService.markTokenAsCancel(tokenId);

		if (tokenToUpdate == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(tokenToUpdate);
	}

	@GetMapping("/getalltokens")
	public List<Token> getAllTokens() {
		return tokenService.getAllTokens();
	}

	@GetMapping("/gettoken/{tokenId}")
	public ResponseEntity<Token> createToken(@PathVariable(value = "tokenId") Long tokenId) {
		Token token = tokenService.getTokenById(tokenId);
		if (token == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(token);
	}

	@GetMapping("/counters/{counterid}")
	public ResponseEntity<CounterQueue> getCounter(@PathVariable(value = "counterid") Integer counterid) {

		CounterQueue counterQueue = tokenService.getCounterById(counterid);
		if (counterQueue == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(counterQueue);
	}

	@GetMapping("/counters/getallcounters")
	public List<CounterQueue> getAllCounters() {

		List<CounterQueue> listOfAllCounters = tokenService.getAllCounters();
		if (listOfAllCounters.size() == 0) {
			return null;
		}
		return listOfAllCounters;
	}

}
