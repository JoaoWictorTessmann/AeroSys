CREATE DATABASE IF NOT EXISTS sistemaaeroporto;

USE sistemaaeroporto;

CREATE TABLE IF NOT EXISTS companhia_aerea (
    id              BIGINT          NOT NULL AUTO_INCREMENT,
    nome            VARCHAR(150)    NOT NULL,
    cnpj            VARCHAR(20)     NOT NULL,
    data_fundacao   DATE            NOT NULL,
    seguro_aeronave BOOLEAN         NOT NULL DEFAULT FALSE,
    status          ENUM('ATIVA', 'INATIVA') NOT NULL DEFAULT 'ATIVA',

    PRIMARY KEY (id),
    CONSTRAINT uq_companhia_cnpj UNIQUE (cnpj),
    CONSTRAINT uq_companhia_nome UNIQUE (nome)
);

CREATE TABLE IF NOT EXISTS piloto (
    id             BIGINT       NOT NULL AUTO_INCREMENT,
    nome           VARCHAR(150) NOT NULL,
    idade          INT          NOT NULL,
    genero         CHAR(1)      NOT NULL,
    cpf            CHAR(11)     NOT NULL,
    data_renovacao DATE         NOT NULL,
    matricula      VARCHAR(50)  NOT NULL,
    habilitacao    VARCHAR(50)  NOT NULL,
    status         ENUM('ATIVO', 'INATIVO') NOT NULL DEFAULT 'ATIVO',

    PRIMARY KEY (id),
    CONSTRAINT uq_piloto_cpf       UNIQUE (cpf),
    CONSTRAINT uq_piloto_matricula UNIQUE (matricula),
    CONSTRAINT chk_piloto_idade    CHECK (idade >= 18)
);

CREATE TABLE IF NOT EXISTS voo (
    id                        BIGINT       NOT NULL AUTO_INCREMENT,
    piloto_id                 BIGINT       NOT NULL,
    companhia_id              BIGINT       NOT NULL,
    codigo                    VARCHAR(10)  NOT NULL,
    origem                    CHAR(4)      NOT NULL,
    destino                   CHAR(4)      NOT NULL,
    horario_partida_previsto  DATETIME     NOT NULL,
    horario_chegada_previsto  DATETIME     NOT NULL,
    horario_partida_real      DATETIME     NULL,
    horario_chegada_real      DATETIME     NULL,
    motivo_cancelamento       VARCHAR(255) NOT NULL DEFAULT '',
    status                    ENUM('AGENDADO', 'VOANDO', 'CONCLUIDO', 'CANCELADO') NOT NULL DEFAULT 'AGENDADO',

    PRIMARY KEY (id),
    CONSTRAINT uq_voo_codigo          UNIQUE (codigo),
    CONSTRAINT chk_voo_origem_destino CHECK (origem <> destino),
    CONSTRAINT fk_voo_piloto          FOREIGN KEY (piloto_id)    REFERENCES piloto (id),
    CONSTRAINT fk_voo_companhia       FOREIGN KEY (companhia_id) REFERENCES companhia_aerea (id)
);