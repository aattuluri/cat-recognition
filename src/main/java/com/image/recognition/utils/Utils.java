package com.image.recognition.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.LinkedList;
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
		logger.info(fileContent);
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
        Byte [][] matrix = null;
        try {
            is = new ByteArrayInputStream(fileContent);
            bfReader = new BufferedReader(new InputStreamReader(is));
            String temp = null;
            int numLines = 0;
            int maxLineLength = 0;
            LinkedList<String> rows = new LinkedList<String> ();
            while((temp = bfReader.readLine()) != null){
            	rows.add(temp);
            	if (temp.length() > maxLineLength) {
            		maxLineLength = temp.length();
            	}
            	numLines++;
            }
            matrix = new Byte [numLines][maxLineLength];
            int row = 0;
            for (String s: rows) {
            	int p = 0;
            	for (p = 0; p < s.length(); p++) {
            		matrix[row][p] = (byte) s.charAt(p);
            	}
            	while (p < maxLineLength) {
            		matrix[row][p] = (byte) ' ';
            		p++;
            	}
            	row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                if(is != null) is.close();
            } catch (Exception ex){
                 
            }
        }
		return matrix;
	}
	
}
