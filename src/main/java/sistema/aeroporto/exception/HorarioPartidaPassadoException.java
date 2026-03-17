package sistema.aeroporto.exception;

public class HorarioPartidaPassadoException extends RuntimeException {

    public HorarioPartidaPassadoException() {
        super("Horário de partida não pode ser no passado");
    }
}
