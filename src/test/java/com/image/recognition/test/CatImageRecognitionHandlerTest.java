package com.image.recognition.test;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.image.recognition.Result;
import com.image.recognition.algorithms.ImageRecognition;
import com.image.recognition.handlers.CatImageRecognitionHandler;
import com.image.recognition.handlers.ImageRecognitionPayload;
import com.image.recognition.handlers.Match;

public class CatImageRecognitionHandlerTest {
	
	private static final Logger logger = Logger.getLogger(ImageRecognition.class.getCanonicalName());
	
	public String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writeValueAsString(data);
        } catch (IOException e){
            throw new RuntimeException("IOException from a StringWriter?");
        }
    }
	
    @Test
    public void aNullImageBadRequest() {
        ImageRecognitionPayload payload = new ImageRecognitionPayload();
        payload.setThreshold(75.0);;
        CatImageRecognitionHandler handler = new CatImageRecognitionHandler();
        assertEquals(new Result(400), handler.process(payload, Collections.emptyMap()));
    }

    @Test
    public void anEmptyImageBadRequest() {
        ImageRecognitionPayload payload = new ImageRecognitionPayload();
        payload.setImage("");
        payload.setThreshold(75.0);;
        CatImageRecognitionHandler handler = new CatImageRecognitionHandler();
        assertEquals(new Result(400), handler.process(payload, Collections.emptyMap()));
    }
    
    @Test
    public void anInvalidBase64ImageBadRequest() {
        ImageRecognitionPayload payload = new ImageRecognitionPayload();
        payload.setImage("This is not valid Base64");
        CatImageRecognitionHandler handler = new CatImageRecognitionHandler();
        Result result = handler.process(payload, Collections.emptyMap());
        assertEquals(new Result(400), result);
    }

    @Test
    public void aCatImageIsRecognized() {
    	ImageRecognitionPayload payload = new ImageRecognitionPayload();
        payload.setImage("KyAgICAgICAgICAgICArCisrKyAgICAgICAgICsrKwogKysrKysrKysrKysrKwogKysgICAgICAgICArKworKyAgKyAgICAgKyAgKysKKysgKy"
        		+ "srICAgKysrICsrCisrICAgICAgICAgICArKwogKysgICArKysgICArKwogKysgICAgICAgICArKwogICsrICsgICArICsrCiAgKysgICsrKyAgKysKICA"
        		+ "gKysgICAgICsrCiAgICAgKysrKysKCiAgICAgICAgICAgICAgIAo=");
        payload.setThreshold(100.0);;
        CatImageRecognitionHandler handler = new CatImageRecognitionHandler();
        ImageRecognitionPayload irp = new ImageRecognitionPayload();
        List<Match> matches = new LinkedList<Match> ();
        Match match = new Match (0, 0, 100.0);
        matches.add(match);
        irp.setMatches(matches);
        assertEquals(new Result(200, dataToJson(irp)), 
        		handler.process(payload, Collections.emptyMap()));
    }

}
