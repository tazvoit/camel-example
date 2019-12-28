package org.mycompany.processors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mycompany.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class GenerateObject implements Processor {
    protected static final Logger LOGGER = LoggerFactory.getLogger(GenerateObject.class);

    @Autowired
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void process(Exchange exchange) throws Exception {
        HashMap usuarioBD = exchange.getIn().getBody(HashMap.class);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Usuario usuario = new Usuario(new Integer(usuarioBD.get("id").toString()), usuarioBD.get("nombre").toString(), usuarioBD.get("puesto").toString(), simpleDateFormat.parse(usuarioBD.get("fechanacimento").toString()));
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(usuario);
        exchange.getIn().setBody(json);
    }
}
