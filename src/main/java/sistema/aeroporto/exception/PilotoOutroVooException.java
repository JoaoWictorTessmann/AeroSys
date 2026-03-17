package sistema.aeroporto.exception;

public class PilotoOutroVooException extends RuntimeException {

    public PilotoOutroVooException() {
        super("Piloto já está escalado para outro voo nesse horário");
    }
}
