package sistema.aeroporto.exception;

public class MenorIdadeException extends RuntimeException {
    public MenorIdadeException() {
        super("Piloto deve ter no mínimo 18 anos");
    }
}