USE sistemaaeroporto;

-- ========================================
-- 20 Companhias (14 ATIVAS, 6 INATIVAS)
-- ========================================
INSERT INTO companhia_aerea (nome, cnpj, data_fundacao, seguro_aeronave, status) VALUES
('Azul Linhas Aereas',         '47001880000184', '2008-05-15', TRUE,  'ATIVA'),
('LATAM Airlines Brasil',      '85993975000110', '1999-03-20', TRUE,  'ATIVA'),
('Gol Linhas Aereas',          '54651591000196', '2001-01-09', TRUE,  'ATIVA'),
('Passaredo Transportes',      '23966200000107', '1995-07-11', TRUE,  'ATIVA'),
('Voepass Linhas Aereas',      '43503897000189', '2012-11-30', TRUE,  'ATIVA'),
('Itapemirim Transportes',     '98474249000133', '2020-03-01', FALSE, 'ATIVA'),
('Rico Linhas Aereas',         '39770436000103', '2003-06-18', TRUE,  'ATIVA'),
('Sideral Linhas Aereas',      '84843343000108', '2006-09-25', TRUE,  'ATIVA'),
('Total Linhas Aereas',        '89781379000128', '2010-04-10', TRUE,  'ATIVA'),
('Abaeté Linhas Aereas',       '96989996000189', '1997-12-05', TRUE,  'ATIVA'),
('BRA Transportes Aereos',     '33021859000136', '2004-08-14', TRUE,  'ATIVA'),
('Webjet Linhas Aereas',       '61340056000180', '2005-02-28', FALSE, 'ATIVA'),
('Meta Linhas Aereas',         '89550492000100', '2009-07-07', TRUE,  'ATIVA'),
('Pantanal Linhas Aereas',     '86479249000147', '2002-11-19', TRUE,  'ATIVA'),
('Avianca Brasil',             '00143563000139', '1998-09-25', FALSE, 'INATIVA'),
('Map Linhas Aereas',          '82299941000132', '2015-04-10', FALSE, 'INATIVA'),
('Ocean Air Transportes',      '21015996000189', '2000-12-05', TRUE,  'INATIVA'),
('Trip Linhas Aereas',         '46061903000183', '1993-05-22', TRUE,  'INATIVA'),
('Noar Linhas Aereas',         '92383138000135', '2007-03-15', FALSE, 'INATIVA'),
('Cruiser Linhas Aereas',      '48813147000127', '2011-10-30', FALSE, 'INATIVA');

-- ========================================
-- 20 Pilotos (16 ATIVOS, 4 INATIVOS)
-- ========================================
INSERT INTO piloto (nome, idade, genero, cpf, data_renovacao, matricula, habilitacao, status) VALUES
('Carlos Eduardo Souza',      42, 'M', '91580778060', '2025-06-10', 'PIL20240001', 'ATPL-A', 'ATIVO'),
('Fernanda Lima Rocha',       37, 'F', '00785783024', '2026-01-15', 'PIL20240002', 'ATPL-A', 'ATIVO'),
('Ricardo Mendes Alves',      55, 'M', '83309047091', '2024-08-22', 'PIL20240003', 'ATPL-H', 'ATIVO'),
('Juliana Martins Costa',     29, 'F', '93692024045', '2027-03-05', 'PIL20240004', 'CPL-A',  'ATIVO'),
('Marcos Vinicius Pereira',   48, 'M', '10030365031', '2025-11-20', 'PIL20240005', 'ATPL-A', 'ATIVO'),
('Patricia Oliveira Nunes',   34, 'F', '53946107036', '2026-07-08', 'PIL20240006', 'ATPL-A', 'ATIVO'),
('Anderson Luiz Ferreira',    51, 'M', '92128564034', '2024-12-30', 'PIL20240007', 'ATPL-H', 'ATIVO'),
('Camila Souza Ribeiro',      27, 'F', '74694967002', '2027-09-14', 'PIL20240008', 'CPL-A',  'ATIVO'),
('Thiago Henrique Cardoso',   44, 'M', '21752147022', '2025-04-18', 'PIL20240009', 'ATPL-A', 'ATIVO'),
('Renata Cristina Barros',    39, 'F', '09622833047', '2026-10-22', 'PIL20240010', 'ATPL-A', 'ATIVO'),
('Felipe Augusto Moreira',    33, 'M', '51502105012', '2027-01-09', 'PIL20240011', 'CPL-A',  'ATIVO'),
('Larissa Beatriz Cunha',     46, 'F', '89846570007', '2025-08-03', 'PIL20240012', 'ATPL-H', 'ATIVO'),
('Eduardo Pires Monteiro',    52, 'M', '25357609080', '2024-11-27', 'PIL20240013', 'ATPL-A', 'ATIVO'),
('Vanessa Rodrigues Lima',    31, 'F', '68199038047', '2027-05-16', 'PIL20240014', 'CPL-A',  'ATIVO'),
('Gustavo Alves Nogueira',    43, 'M', '61309469024', '2025-09-30', 'PIL20240015', 'ATPL-A', 'ATIVO'),
('Aline Fonseca Teixeira',    36, 'F', '47510792045', '2026-03-11', 'PIL20240016', 'ATPL-A', 'ATIVO'),
('Bruno Henrique Teixeira',   61, 'M', '91569800006', '2023-12-01', 'PIL20240017', 'ATPL-A', 'INATIVO'),
('Roberto Carlos Dias',       58, 'M', '16290861000', '2022-05-17', 'PIL20240018', 'ATPL-A', 'INATIVO'),
('Sergio Luiz Nascimento',    63, 'M', '05532097011', '2021-08-09', 'PIL20240019', 'ATPL-H', 'INATIVO'),
('Marcia Helena Vieira',      57, 'F', '16523651096', '2022-11-25', 'PIL20240020', 'ATPL-A', 'INATIVO');

-- ========================================
-- 20 Voos (7 AGENDADOS, 5 EM_VOO, 5 CONCLUIDOS, 3 CANCELADOS)
-- ========================================
INSERT INTO voo (piloto_id, companhia_id, codigo, origem, destino, horario_partida_previsto, horario_chegada_previsto, horario_partida_real, horario_chegada_real, motivo_cancelamento, status) VALUES
-- AGENDADOS
(1,  1,  'AD1001', 'SBGR', 'SBBR', '2026-04-01 06:00:00', '2026-04-01 07:30:00', NULL,                  NULL,                  '', 'AGENDADO'),
(2,  2,  'LA2002', 'SBSP', 'SBRF', '2026-04-02 08:00:00', '2026-04-02 11:00:00', NULL,                  NULL,                  '', 'AGENDADO'),
(3,  3,  'G33003', 'SBCT', 'SBPA', '2026-04-03 10:00:00', '2026-04-03 11:30:00', NULL,                  NULL,                  '', 'AGENDADO'),
(4,  4,  'PP4004', 'SBFL', 'SBGR', '2026-04-04 14:00:00', '2026-04-04 15:45:00', NULL,                  NULL,                  '', 'AGENDADO'),
(5,  5,  'VP5005', 'SBSV', 'SBCF', '2026-04-05 07:00:00', '2026-04-05 08:30:00', NULL,                  NULL,                  '', 'AGENDADO'),
(6,  6,  'IT6006', 'SBMO', 'SBFZ', '2026-04-06 09:00:00', '2026-04-06 10:15:00', NULL,                  NULL,                  '', 'AGENDADO'),
(7,  7,  'RC7007', 'SBMQ', 'SBBV', '2026-04-07 11:00:00', '2026-04-07 12:45:00', NULL,                  NULL,                  '', 'AGENDADO'),
-- EM VOO
(8,  8,  'SD8008', 'SBBH', 'SBVT', '2026-03-19 06:00:00', '2026-03-19 07:00:00', '2026-03-19 06:05:00', NULL,                  '', 'VOANDO'),
(9,  9,  'TT9009', 'SBCG', 'SBCY', '2026-03-19 07:30:00', '2026-03-19 09:00:00', '2026-03-19 07:35:00', NULL,                  '', 'VOANDO'),
(10, 10, 'AB0010', 'SBGO', 'SBLO', '2026-03-19 08:00:00', '2026-03-19 09:00:00', '2026-03-19 08:00:00', NULL,                  '', 'VOANDO'),
(11, 11, 'BR1011', 'SBKP', 'SBSP', '2026-03-19 09:30:00', '2026-03-19 10:30:00', '2026-03-19 09:32:00', NULL,                  '', 'VOANDO'),
(12, 12, 'WJ2012', 'SBRF', 'SBSG', '2026-03-19 10:00:00', '2026-03-19 11:15:00', '2026-03-19 10:08:00', NULL,                  '', 'VOANDO'),
-- CONCLUIDOS
(13, 13, 'MT3013', 'SBPA', 'SBSP', '2026-03-18 06:00:00', '2026-03-18 07:30:00', '2026-03-18 06:05:00', '2026-03-18 07:35:00', '', 'CONCLUIDO'),
(14, 14, 'PL4014', 'SBBR', 'SBGR', '2026-03-17 12:00:00', '2026-03-17 13:30:00', '2026-03-17 12:10:00', '2026-03-17 13:40:00', '', 'CONCLUIDO'),
(15, 1,  'AD5015', 'SBFZ', 'SBSV', '2026-03-16 14:00:00', '2026-03-16 15:20:00', '2026-03-16 14:02:00', '2026-03-16 15:18:00', '', 'CONCLUIDO'),
(16, 2,  'LA6016', 'SBCF', 'SBCT', '2026-03-15 08:00:00', '2026-03-15 09:30:00', '2026-03-15 08:15:00', '2026-03-15 09:45:00', '', 'CONCLUIDO'),
(1,  3,  'G37017', 'SBVT', 'SBBH', '2026-03-14 10:00:00', '2026-03-14 11:00:00', '2026-03-14 10:00:00', '2026-03-14 11:05:00', '', 'CONCLUIDO'),
-- CANCELADOS
(2,  4,  'PP8018', 'SBLO', 'SBGO', '2026-03-20 18:00:00', '2026-03-20 19:00:00', NULL,                  NULL,                  'Falha tecnica na aeronave',              'CANCELADO'),
(3,  5,  'VP9019', 'SBBV', 'SBMQ', '2026-03-21 07:00:00', '2026-03-21 08:45:00', NULL,                  NULL,                  'Condicoes climaticas adversas na rota',  'CANCELADO'),
(4,  6,  'IT0020', 'SBSG', 'SBRF', '2026-03-22 15:00:00', '2026-03-22 16:15:00', NULL,                  NULL,                  'Greve dos controladores de voo',         'CANCELADO');