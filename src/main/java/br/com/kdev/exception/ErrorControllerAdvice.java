package br.com.kdev.exception;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import br.com.kdev.dto.ErrorDTO;
import br.com.kdev.exception.CategoryNotFoundException;
import br.com.kdev.exception.ProductNotFoundException;

@ControllerAdvice(basePackages = "br.com.kdev.controller")
public class ErrorControllerAdvice {

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ProductNotFoundException.class)
	public ErrorDTO handleUserNotFound(ProductNotFoundException userNotFoundException) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
		errorDTO.setMessage("Produto não encontrado.");
		errorDTO.setTimestamp(new Date());
		return errorDTO;

	}

	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CategoryNotFoundException.class)
	public ErrorDTO handleCategoryNotFound(CategoryNotFoundException categoryNotFoundException) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
		errorDTO.setMessage("Categoria não encontrada.");
		errorDTO.setTimestamp(new Date());
		return errorDTO;
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorDTO processValidationError(MethodArgumentNotValidException ex) {
		ErrorDTO errorDTO = new ErrorDTO();
		errorDTO.setStatus(HttpStatus.BAD_REQUEST.value());
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		StringBuilder sb = new StringBuilder("Valor inválido para o(s) campo(s):");

		for (FieldError fieldError : fieldErrors) {
			sb.append(" ");
			sb.append(fieldError.getField());
		}
		errorDTO.setMessage(sb.toString());
		errorDTO.setTimestamp(new Date());
		return errorDTO;
	}

}
