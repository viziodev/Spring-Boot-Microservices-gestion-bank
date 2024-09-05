package org.training.transactions.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.training.transactions.exception.GlobalException;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Slf4j
public class FeignClientErrorDecoder implements ErrorDecoder {

    /**
     * Decodes the response and returns an Exception based on the status code.
     *
     * @param s        The string representation of the response.
     * @param response The HTTP response.
     * @return An Exception based on the status code.
     */
    @Override
    public Exception decode(String s, Response response) {

        GlobalException globalException = extractGlobalException(response);

        switch (response.status()) {
            case 404, 400 -> {
                return globalException;
            }
            default -> {
                return new Exception("common feign exception");
            }
        }
    }

    /**
     * Extracts a GlobalException object from the response body.
     *
     * @param response the response object
     * @return the GlobalException object extracted from the response body
     */
    private GlobalException extractGlobalException(Response response) {

        GlobalException globalException = null;
        Reader reader = null;

        try {
            reader = response.body().asReader(StandardCharsets.UTF_8);
            String result = IOUtils.toString(reader);
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            globalException = mapper.readValue(result, GlobalException.class);
        } catch (IOException e) {
            log.error("IO exception on reading exception message", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("IO exception on reading exception message", e);
                }
            }
        }
        return globalException;
    }
}