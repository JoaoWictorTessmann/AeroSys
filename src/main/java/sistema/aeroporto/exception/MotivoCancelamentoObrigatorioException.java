package sistema.aeroporto.exception;

public class MotivoCancelamentoObrigatorioException extends RuntimeException {

    public MotivoCancelamentoObrigatorioException() {
        super("Motivo do cancelamento é obrigatório");
    }
}
