package com.farmacia.lojavirtual.service;

public class NegocioException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NegocioException(String msg){
		super(msg);
	}

	public NegocioException(String msg, Throwable t) {
		super(msg, t);
	}

}
