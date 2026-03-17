package sistema.aeroporto.exception;

public class CodigoVooObrigatorioException extends RuntimeException {

    public CodigoVooObrigatorioException() {
        super("Código do voo é obrigatório");
    }
}
