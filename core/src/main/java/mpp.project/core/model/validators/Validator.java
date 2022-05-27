package mpp.project.core.model.validators;


import mpp.project.core.exceptions.ValidatorException;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}