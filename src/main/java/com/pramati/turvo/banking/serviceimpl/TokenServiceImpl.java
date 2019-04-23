package com.pramati.turvo.banking.serviceimpl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.pramati.turvo.banking.dao.TokenDAO;
import com.pramati.turvo.banking.errors.TurvoException;
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

	public ResponseEntity<? extends Object> createToken(Token token) {

		if (token.getCustomerid() == null || token.getServiceid() == null) {
			String message = "Request Body is missing required customerid and serviceid as follows {customerid:int, serviceid:int}";
			TurvoException turvoException = new TurvoException();
			turvoException.setStatus(HttpStatus.BAD_REQUEST.value());
			turvoException.setMessage(message);
			turvoException.setError(HttpStatus.BAD_REQUEST);
			turvoException.setException(turvoException.getClass().toString());
			return ResponseEntity.ok().body(turvoException);
		}
		Long customerid;
		Long tokenid;
		Integer selectedCounterid;
		Long serviceid;
		String status;

		Token tokenToAssign = new Token();

		customerid = token.getCustomerid();
		Customer customer = customerService.getCustomerById(customerid);

		if (customer == null) {
			TurvoException turvoException = new TurvoException();
			turvoException.setStatus(HttpStatus.BAD_REQUEST.value());
			turvoException.setMessage("No Customer Existed with Customer Id:" + customerid);
			turvoException.setError(HttpStatus.BAD_REQUEST);
			turvoException.setException(turvoException.getClass().toString());
			return ResponseEntity.ok().body(turvoException);
		}

		tokenid = tokenDAO.getMaxTokenid();
		if (tokenid == null) {
			tokenid = Long.valueOf(1);
		}

		String customerServiceType;
		customerServiceType = customer.getServicetype();

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

		serviceid = token.getServiceid();

		// default status of the token which might be updated as complete or cancel
		// later
		status = "progress";

		tokenToAssign.seTokenid(tokenid);
		tokenToAssign.setCustomerid(customerid);
		tokenToAssign.setCounterid(selectedCounterid);
		tokenToAssign.setServiceid(serviceid);
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
		return ResponseEntity.ok().body(tokenDAO.save(tokenToAssign));
	}

	public ResponseEntity<? extends Object> getAllTokens() {
		 List<Token> listOfTokens =  tokenDAO.findAll();

		 if (listOfTokens.size() == 0) {
				String errorMessage = "No Tokens Avaialable";
				TurvoException turvoException = new TurvoException();
				turvoException.setStatus(HttpStatus.OK.value());
				turvoException.setMessage(errorMessage);
				turvoException.setError(null);
				turvoException.setException(null);
				return ResponseEntity.ok().body(turvoException);
			}

			return ResponseEntity.ok().body(listOfTokens);

	}

	public Token getTokenById(Long tokenId) {

		return tokenDAO.findOne(tokenId);
	}

	public ResponseEntity<? extends Object> getToken(Long tokenId) {

		Token tokenToFind = this.getTokenById(tokenId);

		if (tokenToFind == null) {
			String errorMessage = "Invalid Token-Id: " + tokenId;
			TurvoException turvoException = new TurvoException();
			turvoException.setStatus(HttpStatus.BAD_REQUEST.value());
			turvoException.setMessage(errorMessage);
			turvoException.setError(HttpStatus.BAD_REQUEST);
			turvoException.setException(turvoException.getClass().toString());
			return ResponseEntity.ok().body(turvoException);
		}

		return ResponseEntity.ok().body(tokenToFind);
	}

	public ResponseEntity<? extends Object> markToken(Long tokenId, Token requestedToken) {

		Token tokenToUpdate = this.getTokenById(tokenId);

		if (tokenToUpdate == null) {
			String errorMessage = "Invalid Token-Id: " + tokenId;
			TurvoException turvoException = new TurvoException();
			turvoException.setStatus(HttpStatus.BAD_REQUEST.value());
			turvoException.setMessage(errorMessage);
			turvoException.setError(HttpStatus.BAD_REQUEST);
			turvoException.setException(turvoException.getClass().toString());
			return ResponseEntity.ok().body(turvoException);
		}

		if (requestedToken == null) {
			String errorMessage = "Request Body missing token as follows\n"
					+ "{\"tokenid\": int,\"status\": \"progress or complete or cancel\"}";
			TurvoException turvoException = new TurvoException();
			turvoException.setStatus(HttpStatus.BAD_REQUEST.value());
			turvoException.setMessage(errorMessage);
			turvoException.setError(HttpStatus.BAD_REQUEST);
			turvoException.setException(turvoException.getClass().toString());
			return ResponseEntity.ok().body(turvoException);
		}

		if (requestedToken.getTokenid() != tokenToUpdate.getTokenid()) {
			String errorMessage = "Requested Token-Id in the URL and RequestBody are Not Same";
			TurvoException turvoException = new TurvoException();
			turvoException.setStatus(HttpStatus.BAD_REQUEST.value());
			turvoException.setMessage(errorMessage);
			turvoException.setError(HttpStatus.BAD_REQUEST);
			turvoException.setException(turvoException.getClass().toString());
			return ResponseEntity.ok().body(turvoException);
		}

		String statusOfToken = requestedToken.getStatus();

		if (statusOfToken.equalsIgnoreCase("complete")) {
			tokenToUpdate = this.markTokenAsComplete(tokenId);
			if (tokenToUpdate == null) {
				String errorMessage = "Token-Id: " + tokenId + " Not in the Queue";
				TurvoException turvoException = new TurvoException();
				turvoException.setStatus(HttpStatus.BAD_REQUEST.value());
				turvoException.setMessage(errorMessage);
				turvoException.setError(HttpStatus.BAD_REQUEST);
				turvoException.setException(turvoException.getClass().toString());
				return ResponseEntity.ok().body(turvoException);
			}
			return ResponseEntity.ok().body(tokenToUpdate);
		} else if (statusOfToken.equalsIgnoreCase("cancel")) {
			tokenToUpdate = this.markTokenAsCancel(tokenId);
			if (tokenToUpdate == null) {
				String errorMessage = "Token-Id: " + tokenId + " Not in the Queue";
				TurvoException turvoException = new TurvoException();
				turvoException.setStatus(HttpStatus.BAD_REQUEST.value());
				turvoException.setMessage(errorMessage);
				turvoException.setError(HttpStatus.BAD_REQUEST);
				turvoException.setException(turvoException.getClass().toString());
				return ResponseEntity.ok().body(turvoException);
			}
			return ResponseEntity.ok().body(tokenToUpdate);
		} else if (statusOfToken.equalsIgnoreCase("progress")) {
			String errorMessage = "Nothing is Updated";
			TurvoException turvoException = new TurvoException();
			turvoException.setStatus(HttpStatus.BAD_REQUEST.value());
			turvoException.setMessage(errorMessage);
			turvoException.setError(HttpStatus.BAD_REQUEST);
			turvoException.setException(turvoException.getClass().toString());
			return ResponseEntity.ok().body(turvoException);
		} else {
			String errorMessage = "Invalid Token Status, Should Be progress|complete|cancel ";
			TurvoException turvoException = new TurvoException();
			turvoException.setStatus(HttpStatus.BAD_REQUEST.value());
			turvoException.setMessage(errorMessage);
			turvoException.setError(HttpStatus.BAD_REQUEST);
			turvoException.setException(turvoException.getClass().toString());
			return ResponseEntity.ok().body(turvoException);
		}
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

	public ResponseEntity<? extends Object> getCounterById(Integer counterid) {

		Counter counter = counterService.getCounterById(counterid);
		if (counter == null) {
			TurvoException turvoException = new TurvoException();
			turvoException.setStatus(HttpStatus.BAD_REQUEST.value());
			turvoException.setMessage("Invalid Counter-ID : " + counterid);
			turvoException.setError(HttpStatus.BAD_REQUEST);
			turvoException.setException(turvoException.getClass().toString());
			return ResponseEntity.ok().body(turvoException);
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

		return ResponseEntity.ok().body(counterQueue);
	}

	public ResponseEntity<? extends Object> getAllCounters() {

		List<CounterQueue> listOfAllCounters = new ArrayList<CounterQueue>();
		Long countOfCounters = counterService.getCountOfCounters();
		if (countOfCounters == null) {
			TurvoException turvoException = new TurvoException();
			turvoException.setStatus(HttpStatus.OK.value());
			turvoException.setMessage("No Counters Are Available");
			turvoException.setError(null);
			turvoException.setException(null);
			return ResponseEntity.ok().body(turvoException);
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
		return ResponseEntity.ok().body(listOfAllCounters);
	}
}
