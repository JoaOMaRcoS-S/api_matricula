package com.joaomarcos.matricula.model;
@SuppressWarnings( "serial" )

public class MatriculaInvalidaException extends RuntimeException{
	public MatriculaInvalidaException(String mensagem) {
		super(mensagem);
	}
}
