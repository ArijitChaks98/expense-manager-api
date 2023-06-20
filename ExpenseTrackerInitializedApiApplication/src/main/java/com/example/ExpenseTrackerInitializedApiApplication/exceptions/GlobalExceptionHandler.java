package com.example.ExpenseTrackerInitializedApiApplication.exceptions;

import com.example.ExpenseTrackerInitializedApiApplication.entity.ErrorObject;
//import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // In this Class we will create the custom error message
    @ExceptionHandler(ResourceNotFoundException.class)
    // Here We are creating a method for custom exceptions
    public ResponseEntity<ErrorObject> handleExpenseNotFoundException (ResourceNotFoundException ex, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimeStamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.NOT_FOUND);
    }

    // This is for Argument Type Mismatch Exception (Bad Request)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)

    public ResponseEntity<ErrorObject> handleArgumentMismatchException(MethodArgumentTypeMismatchException ex, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimeStamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.BAD_REQUEST);
    }

    // This is for handle all type of Internal server error exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> handleGeneralException(Exception ex, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimeStamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // This for handle the Customize error message
    //@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // Creating a Map
        Map<String, Object> body = new HashMap<String, Object>();

        // Adding properties to this Map
        body.put("TimeStamp", new Date());
        body.put("StatusCode", HttpStatus.BAD_REQUEST.value());
        // Creating a list for storing the error message
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(msg -> msg.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("Messages", errors );
        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);

    }

    // This is for handle Item Already Exists exception
    @ExceptionHandler(ItemAlreadyExistsExceptions.class)
    public ResponseEntity<ErrorObject> handleItemExistsException (ItemAlreadyExistsExceptions ex, WebRequest request){
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpStatus.CONTINUE.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimeStamp(new Date());
        return new ResponseEntity<ErrorObject>(errorObject, HttpStatus.CONFLICT);
    }
}
