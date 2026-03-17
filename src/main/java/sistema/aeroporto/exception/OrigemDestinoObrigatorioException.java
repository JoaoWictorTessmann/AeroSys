package sistema.aeroporto.exception;

public class OrigemDestinoObrigatorioException extends RuntimeException {

    public OrigemDestinoObrigatorioException() {
        super("Origem e destino são obrigatórios");
    }
}
