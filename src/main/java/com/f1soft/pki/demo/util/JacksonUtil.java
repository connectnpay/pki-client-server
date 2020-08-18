package com.f1soft.pki.demo.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author santosh
 */
@Slf4j
public class JacksonUtil {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T get(String content, Class clazz) throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {
        return (T) objectMapper.readValue(content, clazz);
    }

    public <T> List<T> getList(String content, Class target) throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException {
        List<T> t = objectMapper.readValue(content, objectMapper.getTypeFactory().constructCollectionType(List.class, target));
        return t;
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static String getString(Object object) {
        try {
            //Object to JSON in String
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            log.error("Exception ", e);
            return "";
        }
    }
}
