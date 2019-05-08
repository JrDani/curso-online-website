package br.com.fabricaon.cursos.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import br.com.fabricaon.cursos.model.Usuario;

public class NewUserValidation extends UserValidation{
	@Override
	public void validate(Object target, Errors errors) {
		
		super.validate(target, errors);	
		
		Usuario usuario = (Usuario) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "field.required");
		if(!usuario.getPasswordConfirm().isEmpty())
			if(!(usuario.getPassword().equals(usuario.getPasswordConfirm())))
				errors.rejectValue("password", "notmatch.password");
	} 
}
