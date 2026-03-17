package sistema.aeroporto.exception;

public class PilotoObrigatorioException extends RuntimeException {

    public PilotoObrigatorioException() {
        super("Piloto é obrigatório");
    }
}