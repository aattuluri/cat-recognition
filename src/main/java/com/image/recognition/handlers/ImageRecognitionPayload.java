package com.image.recognition.handlers;

import java.util.List;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.image.recognition.Validable;

@Data
@JsonInclude(Include.NON_NULL)
public class ImageRecognitionPayload implements Validable {
	
	private String image;				//base64 encoded text matrix
	private Double threshold;			//the threshold for matching
	private List<Match> matches;		//the list of matches for the cat with confidence
	
	@JsonIgnore
	public boolean isValid () {
		return (image != null && !image.isEmpty());
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Double getThreshold() {
		return threshold;
	}
	public void setThreshold(Double threshold) {
		this.threshold = threshold;
	}
	public List<Match> getMatches() {
		return matches;
	}
	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
	
}
