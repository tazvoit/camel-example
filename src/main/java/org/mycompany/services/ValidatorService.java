package org.mycompany.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mycompany.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.io.IOException;

@Service
public class ValidatorService {
    protected static final Logger LOGGER = LoggerFactory.getLogger(ValidatorService.class);
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private Validator validator;


        public Errors isValidSyntax(Usuario usuarioRequest) {
            BeanPropertyBindingResult usuarioRequestResult = new BeanPropertyBindingResult(new Usuario(), "usuarioRequest");
            BeanPropertyBindingResult errors = null;

            validator.validate(usuarioRequest, usuarioRequestResult);
            if (!usuarioRequestResult.hasErrors()) {
                LOGGER.debug("[usuarioRequestResult]: [OK]");
                LOGGER.debug("[MandatoryFields]: [OK]");
            } else {
                LOGGER.error("[applicationRequestResult]: Total Errors [" + usuarioRequestResult.getErrorCount() + "]" + " Descripcion [" + usuarioRequestResult.getFieldError() + "]");
                errors = usuarioRequestResult;
            }
            return errors;
        }


    public static boolean isValidJson(final String json) throws IOException {
        boolean valid = true;
        try{
            objectMapper.readTree(json);
        } catch(JsonProcessingException e) {
            LOGGER.error("Exception:"+e);
            valid = false;
        }
        return valid;
    }


    }
