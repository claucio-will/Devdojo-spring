package com.claucio.dev.devdojospring.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDatails{

    private final String fields;
    private final String fieldsMessage;
}
