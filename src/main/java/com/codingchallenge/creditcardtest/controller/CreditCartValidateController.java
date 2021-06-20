package com.codingchallenge.creditcardtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingchallenge.creditcardtest.data.Constants;
import com.codingchallenge.creditcardtest.data.Data;

@RestController
@RequestMapping("creditcard/validate")
public class CreditCartValidateController {
	
	@Autowired
	private Data data;
	
	@GetMapping("/{cardNumber}")
	  public String validate(@PathVariable String cardNumber) {
		
		String cardType="";
		//Check starting digits and card number length
		for(int index=0;index<cardNumber.length();index++) {
			cardType = data.getBeginsWithMap().get(cardNumber.substring(0,index));
			//No need to continue if card is identified or 
			//when length reaches to current max begins with string length
			if(!(cardType==null || cardType.isEmpty()) || index == data.getMaxBeginsWithStringLen()) {
				break;
			}
		}
		if(cardType==null || cardType.isEmpty()) {
			return Constants.UNKNOWN+":"+cardNumber+Constants.INVALID;
		}
		
		//After card is identified, check if card length is valid
		if(!data.getLengthMap().get(cardType).stream().anyMatch(len->len==cardNumber.length())) {
			return cardType+":"+cardNumber+Constants.INVALID;
		};
		
		//run Luhn Algorithm
		if(this.runLuhnAlgorithm(cardNumber)) {
			return cardType+":"+cardNumber+Constants.VALID;
		}else {
			return cardType+":"+cardNumber+Constants.INVALID;
		}
	  }
	
	/**
	 * Check against Luhn Algorithm and return true or false
	 * @param cardNumber
	 * @return
	 */
	public boolean runLuhnAlgorithm(String cardNumber) {
		char[] digits = cardNumber.toCharArray();
		
		int sum = 0;
		
		//Starting with the next to last digit  and continuing with every other digit going back to
		// the beginning of the card, double the digit and sum all double digits
		for(int index=digits.length-2;index>=0;index-=2) {
			int digit = Integer.parseInt(String.valueOf(digits[index]));
			digit = digit + digit;
			//For digits greater than 9, split and sum them
			if(digit>9) {
				sum = sum + digit%10;
				sum = sum + digit/10;
			}else {
				sum = sum + digit;
			}
		}
		
		//sum untouched digits
		for(int index=digits.length-1;index>=0;index-=2) {
			int digit = Integer.parseInt(String.valueOf(digits[index]));
			sum = sum + digit;
		}
		
		//Check multiple of 10
		if(sum % 10 ==0) {
			return true;
		}else {
			return false;
		}
		
	}
	
}
