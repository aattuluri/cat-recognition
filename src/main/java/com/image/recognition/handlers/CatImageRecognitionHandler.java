package com.image.recognition.handlers;

import java.util.List;
import java.util.Map;

import com.image.recognition.AbstractRequestHandler;
import com.image.recognition.Result;
import com.image.recognition.algorithms.CatImageRecognition;

public class CatImageRecognitionHandler extends AbstractRequestHandler <ImageRecognitionPayload> {
	
	public CatImageRecognitionHandler() {
		super(ImageRecognitionPayload.class);
	}

	@Override
	protected Result processImpl (ImageRecognitionPayload value, Map<String, String> urlParams) {
		CatImageRecognition cir = new CatImageRecognition(value.getImage(), value.getThreshold());
		List<Match> matches = cir.find();
		ImageRecognitionPayload irp = new ImageRecognitionPayload();
		irp.setMatches(matches);
		String responseBody = dataToJson(irp);
		return new Result (200, responseBody);
	}
}
