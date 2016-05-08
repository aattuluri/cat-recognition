package com.image.recognition.handlers;

public class Match {
	
	public Integer x;
	public Integer y;
	public Double confidence;
	
	public Match (Integer x, Integer y, Double confidence) {
		this.x = x;
		this.y = y;
		this.confidence = confidence;
	}
	
}
