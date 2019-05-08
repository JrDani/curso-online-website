package br.com.fabricaon.cursos.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.fabricaon.cursos.model.Comentario;

public class ComentarioValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Comentario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "comentario", "field.required");
		
	}

}
