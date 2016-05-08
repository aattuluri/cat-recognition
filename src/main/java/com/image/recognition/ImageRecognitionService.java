package com.image.recognition;

import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.debug.DebugScreen.enableDebugScreen;
import spark.Request;
import spark.Response;
import spark.Route;

import com.image.recognition.handlers.CatImageRecognitionHandler;

public class ImageRecognitionService {
	
    public static void main( String[] args) {
    	
    	port (9001);
       
    	get("/ping", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "pong";
            }
        });
    	
        post("/cat-image-recognition", new CatImageRecognitionHandler());
        
        enableDebugScreen(); 
        
    }
}
