package br.com.fabricaon.cursos.validation;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.fabricaon.cursos.model.Usuario;

public class UserValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {		
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "username", "field.required");		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "field.required");		
		
		Usuario usuario = (Usuario) target;
		
		// Regex to restrict no. of characters in top level domain
		String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";	 
		Pattern pattern = Pattern.compile(regex);
		
		if(!usuario.getUsername().isEmpty())
			if(!pattern.matcher(usuario.getUsername()).matches())
				errors.rejectValue("username", "typeMismatch");		
	} 
	
}
