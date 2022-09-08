package com.pruebas.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;


@Data
@Builder
@Getter
@AllArgsConstructor
public class ErrorModel 
{
    
	@JsonProperty("app-name")
    private String applicationName;

    @JsonProperty("status")
    private int status;

    @JsonProperty("error-message")
    private String message;

}
