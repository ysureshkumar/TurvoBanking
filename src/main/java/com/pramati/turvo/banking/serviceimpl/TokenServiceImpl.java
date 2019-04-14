package com.pramati.turvo.banking.serviceimpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.pramati.turvo.banking.dao.TokenDAO;
import com.pramati.turvo.banking.model.Counter;
import com.pramati.turvo.banking.model.CounterQueue;
import com.pramati.turvo.banking.model.Customer;
import com.pramati.turvo.banking.model.Token;
import com.pramati.turvo.banking.service.CounterService;
import com.pramati.turvo.banking.service.CustomerService;
import com.pramati.turvo.banking.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	TokenDAO tokenDAO;

	@Autowired
	CustomerService customerService;

	@Autowired
	CounterService counterService;

	int queueSizeLimit = 5;

	// Counter Queues
	Queue<Long> counter1Q = new LinkedList<Long>(); // regular counter
	Queue<Long> counter2Q = new LinkedList<Long>(); // regular counter
	Queue<Long> counter3Q = new LinkedList<Long>(); // regular counter
	Queue<Long> counter4Q = new LinkedList<Long>(); // prime counter
	Queue<Long> counter5Q = new LinkedList<Long>(); // prime counter
	Queue<Long> counter6Q = new LinkedList<Long>(); // regular counter

	public TokenDAO getTokenDAO() {
		return tokenDAO;
	}

	public void setTokenDAO(TokenDAO tokenDAO) {
		this.tokenDAO = tokenDAO;
	}

	public Token createToken(Token token) {

		Long customerid;
		Long tokenid;
		Integer selectedCounterid;
		String services;
		String status;
		Token tokenToAssign = new Token();

		customerid = token.getCustomerid();
		Customer customer = customerService.getCustomerById(customerid);

		if (customer != null) {

			tokenid = tokenDAO.getMaxTokenid();
			if (tokenid == null) {
				tokenid = Long.valueOf(1);
			}

			String customerServiceType;
			customerServiceType = customer.getServiceType();

			selectedCounterid = 0;
			if (customerServiceType.equals("prime")) {

				if (counter4Q.size() <= 10) {
					selectedCounterid = 4;
				} else if (counter5Q.size() <= 10) {
					selectedCounterid = 5;
				}

			} else if (customerServiceType.equals("regular")) {

				if (counter1Q.size() <= 10) {
					selectedCounterid = 1;
				} else if (counter2Q.size() <= 10) {
					selectedCounterid = 2;
				} else if (counter3Q.size() <= 10) {
					selectedCounterid = 3;
				} else if (counter6Q.size() <= 10) {
					selectedCounterid = 6;
				}
			}

			services = token.getServices();

			// default status of the token which might be updated as complete or cancel
			// later
			status = "progress";

			tokenToAssign.seTokenid(tokenid);
			tokenToAssign.setCustomerid(customerid);
			tokenToAssign.setCounterid(selectedCounterid);
			tokenToAssign.setServices(services);
			tokenToAssign.setStatus(status);

			tokenDAO.save(tokenToAssign);

			switch (selectedCounterid) {
			case 1:
				counter1Q.add(tokenid);
				break;
			case 2:
				counter2Q.add(tokenid);
				break;
			case 3:
				counter3Q.add(tokenid);
				break;
			case 4:
				counter4Q.add(tokenid);
				break;
			case 5:
				counter5Q.add(tokenid);
				break;
			case 6:
				counter6Q.add(tokenid);
				break;
			}
		}
		return tokenDAO.save(tokenToAssign);
	}

	public List<Token> getAllTokens() {
		return tokenDAO.findAll();
	}

	public Token getTokenById(Long tokenId) {
		// TODO Auto-generated method stub
		return tokenDAO.findOne(tokenId);
	}

	public Token markTokenAsComplete(Long tokenId) {

		Token tokenToUpdate = tokenDAO.findOne(tokenId);

		Integer counterid = tokenToUpdate.getCounterid();
		Long tokenid = tokenToUpdate.getTokenid();

		switch (counterid) {
		case 1:
			if (counter1Q.contains(tokenId)) {
				tokenToUpdate.setStatus("complete");
				counter1Q.remove(tokenid);
			} else {
				return null;
			}
			break;
		case 2:
			if (counter2Q.contains(tokenId)) {
				tokenToUpdate.setStatus("complete");
				counter2Q.remove(tokenid);
			} else {
				return null;
			}
			break;
		case 3:
			if (counter3Q.contains(tokenId)) {
				tokenToUpdate.setStatus("complete");
				counter3Q.remove(tokenid);
			} else {
				return null;
			}
			break;
		case 4:
			if (counter4Q.contains(tokenId)) {
				tokenToUpdate.setStatus("complete");
				counter4Q.remove(tokenid);
			} else {
				return null;
			}
			break;
		case 5:
			if (counter5Q.contains(tokenId)) {
				tokenToUpdate.setStatus("complete");
				counter5Q.remove(tokenid);
			} else {
				return null;
			}
			break;
		case 6:
			if (counter6Q.contains(tokenId)) {
				tokenToUpdate.setStatus("complete");
				counter6Q.remove(tokenid);
			} else {
				return null;
			}
		}
		return tokenDAO.update(tokenToUpdate);
	}

	public Token markTokenAsCancel(Long tokenId) {
		Token tokenToUpdate = tokenDAO.findOne(tokenId);

		Integer counterid = tokenToUpdate.getCounterid();
		Long tokenid = tokenToUpdate.getTokenid();

		switch (counterid) {
		case 1:
			if (counter1Q.contains(tokenId)) {
				tokenToUpdate.setStatus("cancel");
				counter1Q.remove(tokenid);
			} else {
				return null;
			}
			break;
		case 2:
			if (counter2Q.contains(tokenId)) {
				tokenToUpdate.setStatus("cancel");
				counter2Q.remove(tokenid);
			} else {
				return null;
			}
			break;
		case 3:
			if (counter3Q.contains(tokenId)) {
				tokenToUpdate.setStatus("cancel");
				counter3Q.remove(tokenid);
			} else {
				return null;
			}
			break;
		case 4:
			if (counter4Q.contains(tokenId)) {
				tokenToUpdate.setStatus("cancel");
				counter4Q.remove(tokenid);
			} else {
				return null;
			}
			break;
		case 5:
			if (counter5Q.contains(tokenId)) {
				tokenToUpdate.setStatus("cancel");
				counter5Q.remove(tokenid);
			} else {
				return null;
			}
			break;
		case 6:
			if (counter6Q.contains(tokenId)) {
				tokenToUpdate.setStatus("cancel");
				counter6Q.remove(tokenid);
			} else {
				return null;
			}
		}
		return tokenDAO.update(tokenToUpdate);
	}

	public CounterQueue getCounterById(Integer counterid) {

		Counter counter = counterService.getCounterById(counterid);
		if (counter == null) {
			return null;
		}

		CounterQueue counterQueue = new CounterQueue();
		counterQueue.setCounterId(counterid);
		counterQueue.setCounterType(counter.getCountertype());

		switch (counterid) {
		case 1:
			counterQueue.setCounterQ(counter1Q);
			break;
		case 2:
			counterQueue.setCounterQ(counter2Q);
			break;
		case 3:
			counterQueue.setCounterQ(counter3Q);
			break;
		case 4:
			counterQueue.setCounterQ(counter4Q);
			break;
		case 5:
			counterQueue.setCounterQ(counter5Q);
			break;
		case 6:
			counterQueue.setCounterQ(counter6Q);
			break;
		}

		return counterQueue;
	}

	public List<CounterQueue> getAllCounters() {

		List<CounterQueue> listOfAllCounters = new ArrayList<CounterQueue>();
		Long countOfCounters = counterService.getCountOfCounters();
		if (countOfCounters == null) {
			return null;
		}

		Integer counterid = 1;
		while (counterid <= countOfCounters) {
			Counter counter = counterService.getCounterById(counterid);
			CounterQueue counterQueue = new CounterQueue();
			counterQueue.setCounterId(counterid);
			counterQueue.setCounterType(counter.getCountertype());

			switch (counterid) {
			case 1:
				counterQueue.setCounterQ(counter1Q);
				break;
			case 2:
				counterQueue.setCounterQ(counter2Q);
				break;
			case 3:
				counterQueue.setCounterQ(counter3Q);
				break;
			case 4:
				counterQueue.setCounterQ(counter4Q);
				break;
			case 5:
				counterQueue.setCounterQ(counter5Q);
				break;
			case 6:
				counterQueue.setCounterQ(counter6Q);
				break;
			}
			listOfAllCounters.add(counterQueue);
			counterid++;
		}

		return listOfAllCounters;
	}
}
