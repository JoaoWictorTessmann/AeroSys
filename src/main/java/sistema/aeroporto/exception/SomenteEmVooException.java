package sistema.aeroporto.exception;

public class SomenteEmVooException extends RuntimeException {
    public SomenteEmVooException() {
        super("Somente voos iniciados podem ser concluidos");
    }
}
