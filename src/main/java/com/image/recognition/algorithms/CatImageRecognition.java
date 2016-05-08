package com.image.recognition.algorithms;

import java.util.List;
import java.util.logging.Logger;

import com.image.recognition.handlers.CatImageRecognitionHandler;
import com.image.recognition.handlers.Match;
import com.image.recognition.utils.Utils;

public class CatImageRecognition extends ImageRecognition {
	
	private static final Logger logger = Logger.getLogger(CatImageRecognitionHandler.class.getCanonicalName());
	
	final static String PERFECT_CAT_IMAGE_FILENAME = "perfect_cat_image.txt";
	
	final static String PERFECT_CAT_IMAGE_BASE64 = "KyAgICAgICAgICAgICArCisrKyAgICAgICAgICsrKwogKysrKysrKysrKysrKwogKysgICAgICAgICArKworKyAgKyAgI"
			+ "CAgKyAgKysKKysgKysrICAgKysrICsrCisrICAgICAgICAgICArKwogKysgICArKysgICArKwogKysgICAgICAgICArKwogI"
			+ "CsrICsgICArICsrCiAgKysgICsrKyAgKysKICAgKysgICAgICsrCiAgICAgKysrKysKCiAgICAgICAgICAgICAgIAo=";
	
	static Byte [][] perfectCatImage;
	
	static {
		
		perfectCatImage = Utils.getTextMatrixFromBase64(PERFECT_CAT_IMAGE_BASE64);
		
//		TODO: Load it from a resource file
//		try {
//			String file = Files.toString(new File (PERFECT_CAT_IMAGE_FILENAME), Charsets.UTF_8);
//			perfectCatImage = Utils.getTextMatrix(file);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
	}
	
	/**
	 * @param targetImage	Base64 encoded text image file
	 * @param threshold		The threshold with which the images are matched
	 */
	public CatImageRecognition (String targetImage, Double threshold) {
		super(perfectCatImage, Utils.getTextMatrix(targetImage), threshold);
	}

	@Override
	public List<Match> find () {
		// TODO Auto-generated method stub
		return null;
	}

}
