package sistema.aeroporto.exception;

public class NotFoundVooException extends RuntimeException {

    public NotFoundVooException() {
        super("Voo não encontrado");
    }
}
