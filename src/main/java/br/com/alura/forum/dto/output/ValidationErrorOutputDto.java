package br.com.alura.forum.dto.output;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationErrorOutputDto {
    private List<FieldOutputErrorDto> fieldError = new ArrayList<>();
    private List<String> globalErrors = new ArrayList<>();

    public void addFieldError(String field, String message) {
        fieldError.add(new FieldOutputErrorDto(field, message));
    }

    public void addGlobalError(String message) {
        globalErrors.add(message);
    }

    public int getNumberOfErrors(){
        return this.fieldError.size() + this.globalErrors.size();
    }


}
