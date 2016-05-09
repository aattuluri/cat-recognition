package com.image.recognition.algorithms;

import java.util.List;
import java.util.logging.Logger;

import com.image.recognition.handlers.Match;

abstract public class ImageRecognition {
	
	private static final Logger logger = Logger.getLogger(ImageRecognition.class.getCanonicalName());
	
	private Double threshold = 100.0;
	private Byte [][] sourceImageMatrix;
	private Byte [][] targetImageMatrix;
	
	public ImageRecognition (Byte [][] sourceImage, Byte [][] targetImage, 
			Double threshold) {
		sourceImageMatrix = sourceImage;
		targetImageMatrix = targetImage;
		if (null != threshold) {
			if (threshold >= 50.0 && threshold <= 100.0) {
				this.threshold = threshold;
			}
		}
	}
	
	public Double getThreshold() {
		return threshold;
	}

	public void setThreshold(Double threshold) {
		this.threshold = threshold;
	}

	public Byte[][] getSourceImageMatrix() {
		return sourceImageMatrix;
	}

	public void setSourceImageMatrix(Byte[][] sourceImageMatrix) {
		this.sourceImageMatrix = sourceImageMatrix;
	}

	public Byte[][] getTargetImageMatrix() {
		return targetImageMatrix;
	}

	public void setTargetImageMatrix(Byte[][] targetImageMatrix) {
		this.targetImageMatrix = targetImageMatrix;
	}

	abstract public List<Match> find ();
	
}
