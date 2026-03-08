package sistema.aeroporto.dto;

public record VooDTO(
        String id,
        PilotoDTO piloto,
        CompanhiaAereaDTO companhia,
        String codigo,
        String origem,
        String destino,
        String motivoCancelamento,
        String horarioPartidaPrevisto,
        String horarioChegadaPrevisto,
        String horarioPartidaReal,
        String horarioChegadaReal,
        String status) {
    public VooDTO(
        PilotoDTO piloto, CompanhiaAereaDTO companhia, String codigo, String origem, String destino,
        String motivoCancelamento, String horarioPartidaPrevisto, String horarioChegadaPrevisto, 
        String horarioPartidaReal, String horarioChegadaReal, String status
    ) {
        this(
            null, piloto, companhia, codigo, origem, destino, motivoCancelamento, horarioPartidaPrevisto,
            horarioChegadaPrevisto, horarioPartidaReal, horarioChegadaReal, status
        );
}
}
