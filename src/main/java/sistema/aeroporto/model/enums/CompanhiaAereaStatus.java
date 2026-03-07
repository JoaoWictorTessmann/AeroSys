package sistema.aeroporto.model.enums;

public enum CompanhiaAereaStatus {
    ATIVA,
    INATIVA,
    SUSPENSA,
    CANCELADA;

     public static CompanhiaAereaStatus fromString(String status) {
        return CompanhiaAereaStatus.valueOf(status.toUpperCase());
    }
}