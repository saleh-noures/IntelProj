package com.inteliment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason=" Please select a number form (5, 10, 20, 30)!")
public class NotInRangeException  extends RuntimeException {

    public NotInRangeException(int topNo)
    {
        super( "Invalid TopNo: " + topNo);
    }

}



