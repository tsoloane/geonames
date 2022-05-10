package dev.tsoloane.medium.geonames.config

import dev.tsoloane.medium.geonames.model.ErrorResponse
import dev.tsoloane.medium.geonames.model.FieldError
import kotlin.streams.toList
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException


@RestControllerAdvice(annotations = [RestController::class])
class RestExceptionHandler {

    @ExceptionHandler(ResponseStatusException::class)
    fun handleNotFound(exception: ResponseStatusException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse()
        errorResponse.httpStatus = exception.status.value()
        errorResponse.exception = exception::class.simpleName
        errorResponse.message = exception.message
        return ResponseEntity(errorResponse, exception.status)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(exception: MethodArgumentNotValidException):
            ResponseEntity<ErrorResponse> {
        val bindingResult: BindingResult = exception.bindingResult
        val fieldErrors: List<FieldError> = bindingResult.fieldErrors
                .stream()
                .map { error ->
                    var fieldError = FieldError()
                    fieldError.errorCode = error.code
                    fieldError.field = error.field
                    fieldError
                }
                .toList()
        val errorResponse = ErrorResponse()
        errorResponse.httpStatus = HttpStatus.BAD_REQUEST.value()
        errorResponse.exception = exception::class.simpleName
        errorResponse.fieldErrors = fieldErrors
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Throwable::class)
    fun handleThrowable(exception: Throwable): ResponseEntity<ErrorResponse> {
        exception.printStackTrace()
        val errorResponse = ErrorResponse()
        errorResponse.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value()
        errorResponse.exception = exception::class.simpleName
        return ResponseEntity(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

}
