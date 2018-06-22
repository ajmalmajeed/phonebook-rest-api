package com.ajmal.phonebook.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonServiceUtil {

    private JsonServiceUtil(){

    }

    /**
     * Converts a String to a JsonNode
     * @param string the String to be converted
     * @return
     * @throws IOException
     */
    public static JsonNode toJsonNode(String string) throws IOException {
        ObjectMapper om = new ObjectMapper();
        JsonNode n = om.readTree(string);
        om=null;
        return n;
    }

    /**
     * Converts an object to a JsonNode
     * @param object the object to be converted
     * @return
     */
    public static JsonNode toJsonNode(Object object){
        ObjectMapper om = new ObjectMapper();
        JsonNode n = om.convertValue(object, JsonNode.class);
        om=null;
        return n;

    }

}