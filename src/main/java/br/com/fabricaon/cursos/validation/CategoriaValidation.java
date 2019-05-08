package br.com.fabricaon.cursos.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.fabricaon.cursos.model.Categoria;

public class CategoriaValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Categoria.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "nome", "field.required");
		
		Categoria categoria = (Categoria) target;	
		
		if(!categoria.getNome().isEmpty())
			if(!categoria.getNome().matches("[\\p{L}\\s]+")){
				errors.rejectValue("nome", "typeMismatch");
			}
	}
}
