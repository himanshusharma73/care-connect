package org.careconnect.careconnectbooking.exception;

import org.careconnect.careconnectbooking.responce.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(DoctorExitException.class)
    public ResponseEntity<ApiResponse> exitException(DoctorExitException e){
        BookingException bookingException =new BookingException("1000",e.getMessage(),
                "These details are already taken");
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setError(bookingException);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> validationError(MethodArgumentNotValidException e){
        BookingException bookingException =new BookingException("1001",e.getFieldError().getDefaultMessage(),
                "You are entering wrong details please check them ");
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setError(bookingException);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceError(ResourceNotFoundException e){
        BookingException bookingException =new BookingException("1002",e.getMessage(),
                "You are entering wrong details please check them ");
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setError(bookingException);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookingDtoException.class)
    public ResponseEntity<ApiResponse> bookingDto(BookingDtoException e){
        BookingException bookingException=new BookingException("1003","At least the specialty or doctor ID must be filled in","");
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setError(bookingException);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}