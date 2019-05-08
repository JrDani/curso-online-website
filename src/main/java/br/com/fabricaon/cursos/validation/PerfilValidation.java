package br.com.fabricaon.cursos.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.fabricaon.cursos.model.Perfil;

public class PerfilValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Perfil.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "categoria.id", "field.required");
		
		Perfil perfil = (Perfil) target;
		
		if(!perfil.getNome().isEmpty()) 
			if(!perfil.getNome().matches("[\\p{L}\\s]+")) {
				errors.rejectValue("nome", "typeMismatch");
			}
	}

}
