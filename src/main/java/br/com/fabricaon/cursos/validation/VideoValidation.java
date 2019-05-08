package br.com.fabricaon.cursos.validation;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.fabricaon.cursos.model.Video;

public class VideoValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {	
		 return Video.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "url", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "duracao_minuto", "field.required");
		
		Video video = (Video) target;
		if(video.getDuracao_minuto() != null)
			if(video.getDuracao_minuto() <= 0) {
				errors.rejectValue("duracao_minuto", "typeMismatch");
			}
		
		Pattern p = Pattern.compile("(?i)^(?:(?:https?|ftp)://)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*(?:\\.(?:[a-z\\u00a1-\\uffff]{2,}))\\.?)(?::\\d{2,5})?(?:[/?#]\\S*)?$");
		if(!video.getUrl().isEmpty())			
			if(!p.matcher(video.getUrl()).matches())
				errors.rejectValue("url", "typeMismatch");	
	}

}
