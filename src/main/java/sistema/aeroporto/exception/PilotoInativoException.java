package sistema.aeroporto.exception;

public class PilotoInativoException extends RuntimeException {

    public PilotoInativoException() {
        super("Piloto não pode iniciar o voo");
    }
}
