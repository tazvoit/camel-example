package org.mycompany.processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.mycompany.model.Usuario;
import org.mycompany.services.ValidatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;


public class ValidateData implements Processor{

	protected static final Logger LOGGER = LoggerFactory.getLogger(ValidateData.class);

	@Autowired
	private ValidatorService validatorService;
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public void process(Exchange exchange) throws Exception {

		String jsonObject = exchange.getIn().getBody(String.class);
		try {
			if(validatorService.isValidJson(jsonObject)){
				Usuario usuarioRequest = this.objectMapper.readValue(jsonObject, Usuario.class);
				Errors errors = validatorService.isValidSyntax(usuarioRequest);
				if(errors == null){
					exchange.getIn().setBody(usuarioRequest);
					exchange.getIn().setHeader("validated", "pass");
				}else{
					exchange.getIn().setHeader("CamelHttpResponseCode","400");
					throw new Exception("Sintaxis incorrecta: Errors[" + errors.getErrorCount() + "] Desc: " + errors.getFieldError());
				}
			}else{
				exchange.getIn().setHeader("CamelHttpResponseCode","400");
				throw new Exception("Json formado incorrectamente.");
			}

		} catch(Exception m) {
			LOGGER.error("ErrorResponse al leer los datos de entrada",m);
		}

	}
}
