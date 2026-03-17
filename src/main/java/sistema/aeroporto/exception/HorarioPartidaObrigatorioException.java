package sistema.aeroporto.exception;

public class HorarioPartidaObrigatorioException extends RuntimeException {

    public HorarioPartidaObrigatorioException() {
        super("Horário de partida é obrigatório");
    }
}
