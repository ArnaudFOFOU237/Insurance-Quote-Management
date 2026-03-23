package com.accenture.quote.exception;


import com.accenture.quote.exceptions.BadRequestException;
import com.accenture.quote.exceptions.InvalidInputException;
import com.accenture.quote.exceptions.ResourceNotFoundException;
import com.accenture.quote.exceptions.ServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;


@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions of type {@link ResourceNotFoundException}.
     * <p>
     * This method is triggered whenever a {@link ResourceNotFoundException} is thrown in the application.
     * It constructs a {@link ProblemDetail} object with the HTTP status code 404 (NOT_FOUND) and the exception's message.
     * Additional details such as a title ("Ressource introuvable") and a timestamp are included in the response.
     * </p>
     *
     * @param ex the {@link ResourceNotFoundException} that was thrown
     * @return a {@link ProblemDetail} object containing details about the error
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ProblemDetail handleNotFound(ResourceNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );

        problemDetail.setTitle("Ressource introuvable");
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }

    /**
     * Handles exceptions of type {@link BadRequestException}.
     * <p>
     * This method is triggered whenever a {@link BadRequestException} is thrown in the application.
     * It constructs a {@link ProblemDetail} object with the HTTP status code 400 (BAD_REQUEST) and the exception's message.
     * Additional details such as a title ("Mauvaise requête") and a timestamp are included in the response.
     * </p>
     *
     * @param ex the {@link BadRequestException} that was thrown
     * @return a {@link ProblemDetail} object containing details about the error
     */
    @ExceptionHandler(BadRequestException.class)
    public ProblemDetail handleBadRequestException(ResourceNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );

        problemDetail.setTitle("Mauvaise requête");
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }

    /**
     * Handles exceptions of type {@link InvalidInputException}.
     * <p>
     * This method is triggered whenever an {@link InvalidInputException} is thrown in the application.
     * It constructs a {@link ProblemDetail} object with the HTTP status code 400 (BAD_REQUEST) and the exception's message.
     * Additional details such as a title ("Requête invalide") and a timestamp are included in the response.
     * </p>
     *
     * @param ex the {@link InvalidInputException} that was thrown
     * @return a {@link ProblemDetail} object containing details about the error
     */
    @ExceptionHandler(InvalidInputException.class)
    public ProblemDetail handleInvalidInputException(InvalidInputException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );

        problemDetail.setTitle("Requête invalide");
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }

    /**
     * Handles exceptions of type {@link ServiceUnavailableException}.
     * <p>
     * This method is triggered whenever a {@link ServiceUnavailableException} is thrown in the application.
     * It constructs a {@link ProblemDetail} object with the HTTP status code 503 (SERVICE_UNAVAILABLE) and the exception's message.
     * Additional details such as a title ("Service temporairement indisponible") and a timestamp are included in the response.
     * </p>
     *
     * @param ex the {@link ServiceUnavailableException} that was thrown
     * @return a {@link ProblemDetail} object containing details about the error
     */
    @ExceptionHandler(ServiceUnavailableException.class)
    public ProblemDetail handleServiceUnavailableException(ServiceUnavailableException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.SERVICE_UNAVAILABLE,
                ex.getMessage()
        );

        problemDetail.setTitle("Service temporairement indisponible");
        problemDetail.setProperty("timestamp", Instant.now());

        return problemDetail;
    }


    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGlobalException(Exception ex) {
        return ProblemDetail.forStatusAndDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "une erreur est survenue"
        );
    }
}
