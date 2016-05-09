package com.image.recognition.algorithms;

import com.image.recognition.utils.Utils;

public class CatImageRecognition extends ImageRecognition {
	
	final static String PERFECT_CAT_IMAGE_FILENAME = "perfect_cat_image.txt";
	
	final static String PERFECT_CAT_IMAGE_BASE64 = "KyAgICAgICAgICAgICArCisrKyAgICAgICAgICsrKwogKysrKysrKysrKysrKwogKysgICAgICAgICArKworKyAgKyAgI"
			+ "CAgKyAgKysKKysgKysrICAgKysrICsrCisrICAgICAgICAgICArKwogKysgICArKysgICArKwogKysgICAgICAgICArKwogI"
			+ "CsrICsgICArICsrCiAgKysgICsrKyAgKysKICAgKysgICAgICsrCiAgICAgKysrKysKCiAgICAgICAgICAgICAgIAo=";
	
	static Byte [][] perfectCatImage;
	
	static {
		
		perfectCatImage = Utils.getTextMatrixFromBase64(PERFECT_CAT_IMAGE_BASE64);
		
	}
	
	/**
	 * @param targetImage	Base64 encoded text image file
	 * @param threshold		The threshold with which the images are matched
	 */
	public CatImageRecognition (String targetImage, Double threshold) {
		super(perfectCatImage, Utils.getTextMatrixFromBase64(targetImage), threshold);
	}

}
