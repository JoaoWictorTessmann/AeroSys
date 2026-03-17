package sistema.aeroporto.exception;

public class NotFoundCompanhiaAereaException extends RuntimeException {

    public NotFoundCompanhiaAereaException() {
        super("Companhia não encontrada");
    }
}
