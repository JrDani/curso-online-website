package br.com.fabricaon.cursos.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.fabricaon.cursos.model.Curso;

public class CursoValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {		
		return Curso.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "categoria.id", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "curso_dificuldade", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "valor", "field.required");
	
		Curso curso = (Curso) target;
		
		if(!curso.getTitulo().isEmpty())
			if(!curso.getTitulo().matches("[\\p{L}\\s]+"))
				errors.rejectValue("titulo", "typeMismatch");	
		
		if(curso.getValor() != null)
		if(curso.getValor().doubleValue() < 0.0)
			errors.rejectValue("valor", "typeMismatch");
	}
}
