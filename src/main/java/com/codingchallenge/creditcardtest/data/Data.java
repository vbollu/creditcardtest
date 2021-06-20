package com.codingchallenge.creditcardtest.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;
@Service
public class Data {

	private Map<String, String> beginsWithMap = new HashMap<>();
	private Map<String, List<Integer>> lengthMap = new HashMap<>();
	
	private Integer maxBeginsWithStringLen = 0;

	public Data() {


		//map all begins with digits to card types
		addNewStartingDegits(Arrays.asList("34", "37"), "AMEX");
		addNewStartingDegits("6011", "Discover");
		addNewStartingDegits(IntStream.range(51, 55).mapToObj(String::valueOf).collect(Collectors.toList()), "MasterCard");
		addNewStartingDegits("4", "VISA");
		
		//map cards to lengths
		lengthMap.put("AMEX", Arrays.asList(15));
		lengthMap.put("Discover", Arrays.asList(16));
		lengthMap.put("MasterCard", Arrays.asList(16));
		lengthMap.put("VISA", Arrays.asList(13, 16));

	}
	
	public void addNewStartingDegits(List<String> beginsWithList, String cardType) {
		beginsWithList.forEach(beginsWith->this.addNewStartingDegits(beginsWith, cardType));
	}
	
	public void addNewStartingDegits(String beginsWith, String cardType) {
		beginsWithMap.put(beginsWith, cardType);
		maxBeginsWithStringLen = Math.max(maxBeginsWithStringLen, beginsWith.length());
	}

	public Map<String, String> getBeginsWithMap() {
		return beginsWithMap;
	}

	public void setBeginsWithMap(Map<String, String> beginsWithMap) {
		this.beginsWithMap = beginsWithMap;
	}

	public Map<String, List<Integer>> getLengthMap() {
		return lengthMap;
	}

	public void setLengthMap(Map<String, List<Integer>> lengthMap) {
		this.lengthMap = lengthMap;
	}

	public Integer getMaxBeginsWithStringLen() {
		return maxBeginsWithStringLen;
	}

	public void setMaxBeginsWithStringLen(Integer maxBeginsWithStringLen) {
		this.maxBeginsWithStringLen = maxBeginsWithStringLen;
	}

	
	
}
