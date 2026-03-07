package sistema.aeroporto.model.enums;

public enum PilotoStatus {
    ATIVO,
    INATIVO,
    SUSPENSO,
    VENCIDO,
    APOSENTADO,
    DESLIGADO;

     public static PilotoStatus fromString(String status) {
        return PilotoStatus.valueOf(status.toUpperCase());
    }
}
