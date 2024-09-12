package br.com.insurepricecalculator.exceptions;

public class ProdutoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ProdutoException() {
        super();
    }

    public ProdutoException(String message) {
        super(message);
    }

    public ProdutoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProdutoException(Throwable cause) {
        super(cause);
    }

    protected ProdutoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}