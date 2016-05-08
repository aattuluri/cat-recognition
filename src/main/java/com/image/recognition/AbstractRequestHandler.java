package com.image.recognition;

import java.io.IOException;
import java.util.Map;

import spark.Request;
import spark.Response;
import spark.Route;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public abstract class AbstractRequestHandler <P extends Validable> implements RequestHandler<P>, Route {
	
    private Class<P> valueClass;

    private static final int HTTP_BAD_REQUEST = 400;

    public AbstractRequestHandler(Class<P> valueClass){
        this.valueClass = valueClass;
    }

    public static String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writeValueAsString(data);
        } catch (IOException e){
            throw new RuntimeException("IOException from a StringWriter?");
        }
    }

    public final Result process(P value, Map<String, String> urlParams) {
        if (value != null && !value.isValid()) {
            return new Result(HTTP_BAD_REQUEST);
        } else {
            return processImpl(value, urlParams);
        }
    }

    protected abstract Result processImpl(P value, Map<String, String> urlParams);


    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            P value = null;
            value = objectMapper.readValue(request.body(), valueClass);
            Map<String, String> urlParams = request.params();
            Result result = process(value, urlParams);
            response.status(result.getCode());
            response.type("application/json");
            response.body(result.getBody());
            return result.getBody();
        } catch (JsonMappingException e) {
            response.status(400);
            response.body(e.getMessage());
            return e.getMessage();
        }
    }

}
