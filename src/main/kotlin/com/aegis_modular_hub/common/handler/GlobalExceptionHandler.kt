package com.aegis_modular_hub.common.handler

import com.aegis_modular_hub.common.error.ApiError
import com.aegis_modular_hub.common.error.FieldErrorItem
import com.aegis_modular_hub.common.exception.ApiException
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import org.slf4j.LoggerFactory
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.AuthenticationException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class GlobalExceptionHandler {

    private val logger = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(ApiException::class)
    fun handleApiException(exception: ApiException, request: HttpServletRequest): ResponseEntity<ApiError> =
        buildResponse(exception.status, exception.message, request.requestURI)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        val fieldErrors = exception.bindingResult.fieldErrors.map {
            FieldErrorItem(it.field, it.defaultMessage ?: "Valor inválido")
        }
        return buildResponse(HttpStatus.BAD_REQUEST, "Error de validación en los datos", request.requestURI, fieldErrors)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(
        exception: ConstraintViolationException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> {
        val fieldErrors = exception.constraintViolations.map {
            FieldErrorItem(it.propertyPath.toString(), it.message)
        }
        return buildResponse(HttpStatus.BAD_REQUEST, "Validación de parámetros fallida", request.requestURI, fieldErrors)
    }

    @ExceptionHandler(
        MethodArgumentTypeMismatchException::class,
        HttpMessageNotReadableException::class
    )
    fun handleBadRequest(exception: Exception, request: HttpServletRequest): ResponseEntity<ApiError> =
        buildResponse(HttpStatus.BAD_REQUEST, "Formato de solicitud inválido", request.requestURI)

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolation(exception: DataIntegrityViolationException, request: HttpServletRequest): ResponseEntity<ApiError> {
        logger.warn("Violación de integridad de datos: {}", exception.message)
        return buildResponse(HttpStatus.CONFLICT, "El registro ya existe o viola una restricción de integridad", request.requestURI)
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDenied(exception: AccessDeniedException, request: HttpServletRequest): ResponseEntity<ApiError> =
        buildResponse(HttpStatus.FORBIDDEN, "No tienes permisos para realizar esta acción", request.requestURI)

    @ExceptionHandler(AuthenticationException::class)
    fun handleAuthentication(exception: AuthenticationException, request: HttpServletRequest): ResponseEntity<ApiError> =
        buildResponse(HttpStatus.UNAUTHORIZED, "Debes estar autenticado para acceder", request.requestURI)

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun handleMethodNotAllowed(
        exception: HttpRequestMethodNotSupportedException,
        request: HttpServletRequest
    ): ResponseEntity<ApiError> = buildResponse(
        status = HttpStatus.METHOD_NOT_ALLOWED,
        message = "El método ${request.method} no está permitido para esta ruta",
        path = request.requestURI
    )

    @ExceptionHandler(Exception::class)
    fun handleGenericException(exception: Exception, request: HttpServletRequest): ResponseEntity<ApiError> {
        logger.error("Error no controlado en ${request.requestURI}", exception)
        return buildResponse(
            status = HttpStatus.INTERNAL_SERVER_ERROR,
            message = "Ha ocurrido un error interno inesperado",
            path = request.requestURI
        )
    }

    private fun buildResponse(
        status: HttpStatus,
        message: String,
        path: String,
        fieldErrors: List<FieldErrorItem> = emptyList()
    ): ResponseEntity<ApiError> {
        val body = ApiError(
            status = status.value(),
            error = status.reasonPhrase,
            message = message,
            path = path,
            fieldErrors = fieldErrors
        )
        return ResponseEntity.status(status).body(body)
    }
}