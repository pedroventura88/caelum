package br.com.alura.forum.controller;

import br.com.alura.forum.dto.output.MessageErrorOutputDto;
import br.com.alura.forum.dto.output.ValidationErrorOutputDto;
import br.com.alura.forum.exception.InvalidOperationException;
import br.com.alura.forum.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.InjectService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@AllArgsConstructor
@RestControllerAdvice
public class ExceptionHandleController {

    private MessageSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorOutputDto hadleValidationError (MethodArgumentNotValidException exception) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = exception.getBindingResult().getGlobalErrors();

        ValidationErrorOutputDto validationErrorOutputDto = new ValidationErrorOutputDto();

        fieldErrors.forEach(error -> validationErrorOutputDto.addFieldError(error.getField(), getErrorMessage(error)));
        globalErrors.forEach(error -> validationErrorOutputDto.addGlobalError(getErrorMessage(error)));
        return validationErrorOutputDto;
    }

    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }


    /** Existe a necessidade de Parâmetros para os metodos abaixo? */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MessageErrorOutputDto handleNotFoundException (Exception exception, WebRequest webRequest) {
        return new MessageErrorOutputDto("Resource not found", HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(InvalidOperationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public MessageErrorOutputDto handleInvalidOperationException (Exception exception, WebRequest webRequest) {
        return new MessageErrorOutputDto("Operation not valid", HttpStatus.UNAUTHORIZED.value());
    }


}
