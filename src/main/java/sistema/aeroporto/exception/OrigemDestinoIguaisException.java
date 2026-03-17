package sistema.aeroporto.exception;

public class OrigemDestinoIguaisException extends RuntimeException {

    public OrigemDestinoIguaisException() {
        super("Origem e destino não podem ser iguais");
    }
}