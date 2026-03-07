package sistema.aeroporto.model.enums;

public enum VooStatus {
    AGENDADO,
    CONFIRMADO,
    EMBARQUE,
    PRONTO,
    TAXIANDO,
    DECOLADO,
    EM_VOO,
    POUSADO,
    EM_GATE,
    INALIZADO,
    ATRASADO,
    CANCELADO,
    DESVIADO,
    EM_HOLD,
    CONCLUIDO;

     public static VooStatus fromString(String status) {
        return VooStatus.valueOf(status.toUpperCase());
    }
}
