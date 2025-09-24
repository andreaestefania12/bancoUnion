package com.gaei.clientManager.infrastructure.exception;

import com.gaei.clientManager.domain.exception.ClientValidationException;
import com.gaei.clientManager.infrastructure.exception.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClientValidationException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponseDTO> handlerClientValidationException(ClientValidationException ex){
        String idTx = ex.getIdTx();
        String errorMessage = ex.getMessage();
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(idTx, errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
