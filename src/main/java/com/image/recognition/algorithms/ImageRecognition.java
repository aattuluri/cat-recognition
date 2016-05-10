package com.image.recognition.algorithms;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.image.recognition.handlers.Match;
import com.image.recognition.utils.Utils;

public class ImageRecognition {
	
	private static final Logger logger = Logger.getLogger(ImageRecognition.class.getCanonicalName());
	
	final static String PERFECT_CAT_IMAGE_BASE64 = "KyAgICAgICAgICAgICArCisrKyAgICAgICAgICsrKwogKysrKysrKysrKysrKwogKysgICAgICAgICArKworKyAgKyAgI"
			+ "CAgKyAgKysKKysgKysrICAgKysrICsrCisrICAgICAgICAgICArKwogKysgICArKysgICArKwogKysgICAgICAgICArKwogI"
			+ "CsrICsgICArICsrCiAgKysgICsrKyAgKysKICAgKysgICAgICsrCiAgICAgKysrKysKCiAgICAgICAgICAgICAgIAo=";
	
	private static HashMap<TemplateImageType, Byte [][]> templateImages;
	
	static {
		templateImages = new HashMap<TemplateImageType, Byte [][]> ();
		templateImages.put(TemplateImageType.CAT, Utils.getTextMatrixFromBase64(PERFECT_CAT_IMAGE_BASE64));
	}
	
	private Double threshold = 100.0;
	private Byte [][] targetImageMatrix;
	
	public ImageRecognition (Byte [][] targetImage, 
			Double threshold) {
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

	public Byte[][] getTargetImageMatrix() {
		return targetImageMatrix;
	}

	public void setTargetImageMatrix(Byte[][] targetImageMatrix) {
		this.targetImageMatrix = targetImageMatrix;
	}

	public List<Match> find (TemplateImageType templateImageType) {
		
		Byte [][] srcImage = templateImages.get(templateImageType);
		Byte [][] targetImage = getTargetImageMatrix();
		
		List<Match> matchPositions = new LinkedList<Match> ();
		
		int totalMatchesPossible = srcImage.length * srcImage[0].length;
		
		int numMismatchesToSkipFrame = (int) (totalMatchesPossible * (1.0 - (getThreshold() / 100.0)));
		
		// loop through the search image
		for ( int x = 0; x <= targetImage.length - srcImage.length; x++ ) {
		    for ( int y = 0; y <= targetImage[0].length - srcImage[0].length; y++ ) {
			    Double confidence = 0.0;
			    int numMatches = 0;
			    int numMismatches = 0;
				// loop through the perfect image
				for ( int j = 0; j < srcImage[0].length; j++ ) {
		            for ( int i = 0; i < srcImage.length; i++ ) {
		            	byte targetImgVal = targetImage[x+i][y+j];
		                byte srcImageVal = srcImage[i][j];
		                if (targetImgVal == srcImageVal) {
		                	numMatches++;
		                } else {
		                	numMismatches++;
		                }
		                if (numMismatches > numMismatchesToSkipFrame) {
		                	break;
		                }
		            }
		            if (numMismatches > numMismatchesToSkipFrame) {
	                	break;
	                }
			    }
				
				//calculate confidence so far
				confidence = ((1.0 * numMatches) / totalMatchesPossible) * 100.0;
				
				//check if the confidence is greater than the threshold, if so add the match
				if (confidence >= getThreshold()) {
					matchPositions.add(new Match (x, y, Math.round(confidence *100.0)/100.0));
				}
		    }
		}
		
		logger.info("Match count: " + matchPositions.size());
		
		return matchPositions;
	}
	
}
