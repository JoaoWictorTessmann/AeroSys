package sistema.aeroporto.exception;

public class CodigoVooExistenteException extends RuntimeException {

    public CodigoVooExistenteException() {
        super("Código de voo já existente");
    }
}