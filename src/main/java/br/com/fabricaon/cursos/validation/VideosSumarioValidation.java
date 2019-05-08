package br.com.fabricaon.cursos.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.fabricaon.cursos.model.VideosSumario;

public class VideosSumarioValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return VideosSumario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "capitulo", "field.required");
		
		VideosSumario videosSumario = (VideosSumario) target;
		if(videosSumario.getCapitulo() != null)
			if(videosSumario.getCapitulo() <= 0)
				errors.rejectValue("capitulo", "typeMismatch");
	}

}
