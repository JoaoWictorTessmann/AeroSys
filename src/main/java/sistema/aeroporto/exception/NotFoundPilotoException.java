package sistema.aeroporto.exception;

public class NotFoundPilotoException extends RuntimeException {

    public NotFoundPilotoException() {
        super("Piloto não encontrado");
    }
}
