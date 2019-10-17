package com.stackroute.muzixapp.exceptions;

import com.stackroute.muzixapp.response.ResponseForError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TrackGlobalExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {TrackAlreadyExistsException.class})
    public ResponseEntity<ResponseForError> globalTrackAlreadyExistsException(TrackAlreadyExistsException ex) throws Exception{
        ResponseForError responseForError = new ResponseForError();
        responseForError.setErrorID(HttpStatus.CONFLICT.value());
        responseForError.setErrorMessageInformation(ex.getMessage());
        return new ResponseEntity<>(responseForError,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {TrackNotFoundException.class})
    public ResponseEntity<ResponseForError> globalTrackNotFoundException(TrackNotFoundException ex) throws Exception{
        ResponseForError responseForError = new ResponseForError();
        responseForError.setErrorID(HttpStatus.NOT_FOUND.value());
        responseForError.setErrorMessageInformation(ex.getMessage());
        return new ResponseEntity<>(responseForError,HttpStatus.NOT_FOUND);
    }
}