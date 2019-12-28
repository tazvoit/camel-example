package org.mycompany.processors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mycompany.model.ErrorResponse;
import org.mycompany.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class GenerateResponseError  implements Processor {
    protected static final Logger LOGGER = LoggerFactory.getLogger(GenerateResponseError.class);

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();
    private ErrorResponse errorResponse = new ErrorResponse();

    @Override
    public void process(Exchange exchange) throws Exception {
        exchange.getIn().setHeader("Content-Type","application/json");
        switch (exchange.getIn().getHeader("CamelHttpResponseCode").toString()){
            case "400":{
                exchange.getIn().setBody( generateMessage(Constants.ERROR_400_DES));
                break;
            }
            case "500":{
                exchange.getIn().setBody( generateMessage(Constants.ERROR_500_DESC));
                break;
            }
            case "202":{
                exchange.getIn().setBody( generateMessage(Constants.ERROR_202_DES));
                break;
            }
            default:{
                exchange.getIn().setBody( generateMessage(Constants.ERROR_501_DESC));
                break;
            }
        }
        LOGGER.error("Exception Generated:"+exchange);
    }

    private String generateMessage(String descripcion) throws JsonProcessingException {
        errorResponse.setDescripcion(descripcion);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(errorResponse);
    }
}
