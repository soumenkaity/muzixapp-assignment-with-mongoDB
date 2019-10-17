package com.stackroute.muzixapp.response;

import lombok.Data;

@Data
public class ResponseForError {
    private int errorID;
    private String errorMessageInformation;
}