package sistema.aeroporto.exception;

public class CompanhiaNaoAtivaException extends RuntimeException {

    public CompanhiaNaoAtivaException() {
        super("Companhia não está ativa");
    }
}