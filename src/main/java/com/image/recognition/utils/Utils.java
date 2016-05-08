package com.image.recognition.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.logging.Logger;

import com.image.recognition.handlers.CatImageRecognitionHandler;

public class Utils {
	
	private static final Logger logger = Logger.getLogger(CatImageRecognitionHandler.class.getCanonicalName());
	
	/**
	 * 
	 * Converts a base64 encoded text file to 2D matrix of Characters
	 * 
	 * @param base64EncodedFile
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static Byte [][] getTextMatrixFromBase64 (String base64EncodedFile) 
		throws IllegalArgumentException {
		byte [] decodedFileContent = Base64.getDecoder().decode(base64EncodedFile.getBytes());
		return getTextMatrix (decodedFileContent);
	}
	
	/**
	 * Converts a text file to 2D matrix of Characters
	 * 
	 * @param fileContent
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static Byte [][] getTextMatrix (String fileContent) 
		throws IllegalArgumentException {
		return getTextMatrix (fileContent.getBytes());
	}
	
	/**
	 * Converts a stream of bytes to 2D matrix of Characters
	 * 
	 * @param fileContent
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static Byte [][] getTextMatrix (byte [] fileContent) 
		throws IllegalArgumentException {
		InputStream is = null;
        BufferedReader bfReader = null;
        try {
            is = new ByteArrayInputStream(fileContent);
            bfReader = new BufferedReader(new InputStreamReader(is));
            String temp = null;
            while((temp = bfReader.readLine()) != null){
            	logger.info(temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if(is != null) is.close();
            } catch (Exception ex){
                 
            }
        }
		return new Byte [10][10];
	}
	
}
