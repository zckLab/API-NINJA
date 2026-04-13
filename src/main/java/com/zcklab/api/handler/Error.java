package com.zcklab.api.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Error {

    private String property; //This indicates the JSON property that caused the error.
    private String message; // Error message
}
