package com.image.recognition.handlers;

import java.util.List;
import java.util.Map;

import com.image.recognition.AbstractRequestHandler;
import com.image.recognition.Result;
import com.image.recognition.algorithms.ImageRecognition;
import com.image.recognition.algorithms.TemplateImageType;
import com.image.recognition.utils.Utils;

public class CatImageRecognitionHandler extends AbstractRequestHandler <ImageRecognitionPayload> {
	
	public CatImageRecognitionHandler() {
		super(ImageRecognitionPayload.class);
	}

	@Override
	protected Result processImpl (ImageRecognitionPayload value, Map<String, String> urlParams) {
		Byte [][] targetImage = null;
		//throw a 400 if the image is not a valid base64 encoded string
		try {
			targetImage = Utils.getTextMatrixFromBase64(value.getImage());
		} catch (IllegalArgumentException iae) {
			return new Result (400);
		}
		ImageRecognition cir = new ImageRecognition(targetImage, value.getThreshold());
		List<Match> matches = cir.find(TemplateImageType.CAT);
		ImageRecognitionPayload irp = new ImageRecognitionPayload();
		irp.setMatches(matches);
		String responseBody = dataToJson(irp);
		return new Result (200, responseBody);
	}
}
