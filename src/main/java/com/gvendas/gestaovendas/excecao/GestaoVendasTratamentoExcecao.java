package com.gvendas.gestaovendas.excecao;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GestaoVendasTratamentoExcecao extends ResponseEntityExceptionHandler {

    public static final String CONTANT_VALIDATION_NOT_BLANK = "NotBlank";
    public static final String CONTANT_VALIDATION_NOT_NULL = "NotNull";
    public static final String CONTANT_VALIDATION_LENGTH = "Length";
    public static final String CONTANT_VALIDATION_PATTERN = "Pattern";



    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatusCode status, WebRequest request) {
        List<Erro> erros = gerarListaDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex,erros,headers, HttpStatus.BAD_REQUEST, request);
    }

    // Quero tratar uma exceção, porém náo tem uma classe para sobrescrever, então acesso à exceção
    // que apareceu no postman ou insmonia no trace

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
        String msgUsuario = "Recurso não encontrado";
        String msgDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(msgUsuario,msgDesenvolvedor));
        return handleExceptionInternal(ex,erros,new HttpHeaders(),HttpStatus.NOT_FOUND,request);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
        String msgUsuario = "Recurso não encontrado";
        String msgDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(msgUsuario,msgDesenvolvedor));
        return handleExceptionInternal(ex,erros,new HttpHeaders(),HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Object> handleRegraNegocioException(RegraNegocioException ex , WebRequest request){
        String msgUsuario = ex.getMessage();
        String msgDesenvolvedor = ex.getMessage();
        List<Erro> erros = Arrays.asList(new Erro(msgUsuario,msgDesenvolvedor));
        return handleExceptionInternal(ex,erros,new HttpHeaders(),HttpStatus.BAD_REQUEST,request);

    }

    private List<Erro> gerarListaDeErros(BindingResult bindingResult){
        List<Erro> erros = new ArrayList<Erro>();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String msgUsuario = tratarMensageDeErroParaUsuario(fieldError);
            String msgDesenvolvedor = fieldError.toString();
            erros.add(new Erro(msgUsuario,msgDesenvolvedor));

        });
        return erros;

    }
    private String tratarMensageDeErroParaUsuario(FieldError fildError){
        if(fildError.getCode().equals(CONTANT_VALIDATION_NOT_BLANK)){
            return fildError.getDefaultMessage().concat(" é obrigatório.");
        }
        if(fildError.getCode().equals(CONTANT_VALIDATION_NOT_NULL)){
            return fildError.getDefaultMessage().concat(" é obrigatório.");
        }
        if(fildError.getCode().equals(CONTANT_VALIDATION_LENGTH)){
            return fildError.getDefaultMessage().concat(String.format(" deve ter entre %s e %s caracteres.",
                    fildError.getArguments()[2],fildError.getArguments()[1]));
        } if(fildError.getCode().equals(CONTANT_VALIDATION_PATTERN)){
            return fildError.getDefaultMessage().concat(" formado inválido.");
        }
        return fildError.toString();

    }

}
