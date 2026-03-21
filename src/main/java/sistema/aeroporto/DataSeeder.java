package sistema.aeroporto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import sistema.aeroporto.model.CompanhiaAerea;
import sistema.aeroporto.model.Piloto;
import sistema.aeroporto.model.Voo;
import sistema.aeroporto.model.enums.CompanhiaAereaStatus;
import sistema.aeroporto.model.enums.PilotoStatus;
import sistema.aeroporto.model.enums.VooStatus;
import sistema.aeroporto.repository.CompanhiaAereaRepository;
import sistema.aeroporto.repository.PilotoRepository;
import sistema.aeroporto.repository.VooRepository;

@Component
public class DataSeeder implements ApplicationRunner {

    private final CompanhiaAereaRepository companhiaRepository;
    private final PilotoRepository pilotoRepository;
    private final VooRepository vooRepository;

    public DataSeeder(CompanhiaAereaRepository companhiaRepository,
                      PilotoRepository pilotoRepository,
                      VooRepository vooRepository) {
        this.companhiaRepository = companhiaRepository;
        this.pilotoRepository    = pilotoRepository;
        this.vooRepository       = vooRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (companhiaRepository.count() > 0) {
            System.out.println(">>> Banco já possui dados. Seed ignorado.");
            return;
        }

        System.out.println(">>> Banco vazio. Iniciando seed...");
        seedCompanhias();
        seedPilotos();
        seedVoos();
        System.out.println(">>> Seed concluído com sucesso.");
    }

    // ── COMPANHIAS ────────────────────────────────────────────────────────────

    private void seedCompanhias() {
        List<CompanhiaAerea> companhias = List.of(
            companhia("Azul Linhas Aereas",      "47001880000184", "2008-05-15", true,  CompanhiaAereaStatus.ATIVA),
            companhia("LATAM Airlines Brasil",    "85993975000110", "1999-03-20", true,  CompanhiaAereaStatus.ATIVA),
            companhia("Gol Linhas Aereas",        "54651591000196", "2001-01-09", true,  CompanhiaAereaStatus.ATIVA),
            companhia("Passaredo Transportes",    "23966200000107", "1995-07-11", true,  CompanhiaAereaStatus.ATIVA),
            companhia("Voepass Linhas Aereas",    "43503897000189", "2012-11-30", true,  CompanhiaAereaStatus.ATIVA),
            companhia("Itapemirim Transportes",   "98474249000133", "2020-03-01", false, CompanhiaAereaStatus.ATIVA),
            companhia("Rico Linhas Aereas",       "39770436000103", "2003-06-18", true,  CompanhiaAereaStatus.ATIVA),
            companhia("Sideral Linhas Aereas",    "84843343000108", "2006-09-25", true,  CompanhiaAereaStatus.ATIVA),
            companhia("Total Linhas Aereas",      "89781379000128", "2010-04-10", true,  CompanhiaAereaStatus.ATIVA),
            companhia("Abaete Linhas Aereas",     "96989996000189", "1997-12-05", true,  CompanhiaAereaStatus.ATIVA),
            companhia("BRA Transportes Aereos",   "33021859000136", "2004-08-14", true,  CompanhiaAereaStatus.ATIVA),
            companhia("Webjet Linhas Aereas",     "61340056000180", "2005-02-28", false, CompanhiaAereaStatus.ATIVA),
            companhia("Meta Linhas Aereas",       "89550492000100", "2009-07-07", true,  CompanhiaAereaStatus.ATIVA),
            companhia("Pantanal Linhas Aereas",   "86479249000147", "2002-11-19", true,  CompanhiaAereaStatus.ATIVA),
            companhia("Avianca Brasil",           "00143563000139", "1998-09-25", false, CompanhiaAereaStatus.INATIVA),
            companhia("Map Linhas Aereas",        "82299941000132", "2015-04-10", false, CompanhiaAereaStatus.INATIVA),
            companhia("Ocean Air Transportes",    "21015996000189", "2000-12-05", true,  CompanhiaAereaStatus.INATIVA),
            companhia("Trip Linhas Aereas",       "46061903000183", "1993-05-22", true,  CompanhiaAereaStatus.INATIVA),
            companhia("Noar Linhas Aereas",       "92383138000135", "2007-03-15", false, CompanhiaAereaStatus.INATIVA),
            companhia("Cruiser Linhas Aereas",    "48813147000127", "2011-10-30", false, CompanhiaAereaStatus.INATIVA)
        );
        companhiaRepository.saveAll(companhias);
        System.out.println(">>> " + companhias.size() + " companhias inseridas.");
    }

    // ── PILOTOS ───────────────────────────────────────────────────────────────

    private void seedPilotos() {
        List<Piloto> pilotos = List.of(
            piloto("Carlos Eduardo Souza",    42, "M", "91580778060", "2025-06-10", "PIL20240001", "ATPL-A", PilotoStatus.ATIVO),
            piloto("Fernanda Lima Rocha",     37, "F", "00785783024", "2026-01-15", "PIL20240002", "ATPL-A", PilotoStatus.ATIVO),
            piloto("Ricardo Mendes Alves",    55, "M", "83309047091", "2024-08-22", "PIL20240003", "ATPL-H", PilotoStatus.ATIVO),
            piloto("Juliana Martins Costa",   29, "F", "93692024045", "2027-03-05", "PIL20240004", "CPL-A",  PilotoStatus.ATIVO),
            piloto("Marcos Vinicius Pereira", 48, "M", "10030365031", "2025-11-20", "PIL20240005", "ATPL-A", PilotoStatus.ATIVO),
            piloto("Patricia Oliveira Nunes", 34, "F", "53946107036", "2026-07-08", "PIL20240006", "ATPL-A", PilotoStatus.ATIVO),
            piloto("Anderson Luiz Ferreira",  51, "M", "92128564034", "2024-12-30", "PIL20240007", "ATPL-H", PilotoStatus.ATIVO),
            piloto("Camila Souza Ribeiro",    27, "F", "74694967002", "2027-09-14", "PIL20240008", "CPL-A",  PilotoStatus.ATIVO),
            piloto("Thiago Henrique Cardoso", 44, "M", "21752147022", "2025-04-18", "PIL20240009", "ATPL-A", PilotoStatus.ATIVO),
            piloto("Renata Cristina Barros",  39, "F", "09622833047", "2026-10-22", "PIL20240010", "ATPL-A", PilotoStatus.ATIVO),
            piloto("Felipe Augusto Moreira",  33, "M", "51502105012", "2027-01-09", "PIL20240011", "CPL-A",  PilotoStatus.ATIVO),
            piloto("Larissa Beatriz Cunha",   46, "F", "89846570007", "2025-08-03", "PIL20240012", "ATPL-H", PilotoStatus.ATIVO),
            piloto("Eduardo Pires Monteiro",  52, "M", "25357609080", "2024-11-27", "PIL20240013", "ATPL-A", PilotoStatus.ATIVO),
            piloto("Vanessa Rodrigues Lima",  31, "F", "68199038047", "2027-05-16", "PIL20240014", "CPL-A",  PilotoStatus.ATIVO),
            piloto("Gustavo Alves Nogueira",  43, "M", "61309469024", "2025-09-30", "PIL20240015", "ATPL-A", PilotoStatus.ATIVO),
            piloto("Aline Fonseca Teixeira",  36, "F", "47510792045", "2026-03-11", "PIL20240016", "ATPL-A", PilotoStatus.ATIVO),
            piloto("Bruno Henrique Teixeira", 61, "M", "91569800006", "2023-12-01", "PIL20240017", "ATPL-A", PilotoStatus.INATIVO),
            piloto("Roberto Carlos Dias",     58, "M", "16290861000", "2022-05-17", "PIL20240018", "ATPL-A", PilotoStatus.INATIVO),
            piloto("Sergio Luiz Nascimento",  63, "M", "05532097011", "2021-08-09", "PIL20240019", "ATPL-H", PilotoStatus.INATIVO),
            piloto("Marcia Helena Vieira",    57, "F", "16523651096", "2022-11-25", "PIL20240020", "ATPL-A", PilotoStatus.INATIVO)
        );
        pilotoRepository.saveAll(pilotos);
        System.out.println(">>> " + pilotos.size() + " pilotos inseridos.");
    }

    // ── VOOS ──────────────────────────────────────────────────────────────────

    private void seedVoos() {
        List<CompanhiaAerea> c = companhiaRepository.findAll();
        List<Piloto>         p = pilotoRepository.findAll();

        List<Voo> voos = List.of(
            // AGENDADOS
            voo(p.get(0),  c.get(0),  "AD1001", "SBGR", "SBBR", ldt("2026-04-01T06:00"), ldt("2026-04-01T07:30"), null,                    null,                    "",                                      VooStatus.AGENDADO),
            voo(p.get(1),  c.get(1),  "LA2002", "SBSP", "SBRF", ldt("2026-04-02T08:00"), ldt("2026-04-02T11:00"), null,                    null,                    "",                                      VooStatus.AGENDADO),
            voo(p.get(2),  c.get(2),  "G33003", "SBCT", "SBPA", ldt("2026-04-03T10:00"), ldt("2026-04-03T11:30"), null,                    null,                    "",                                      VooStatus.AGENDADO),
            voo(p.get(3),  c.get(3),  "PP4004", "SBFL", "SBGR", ldt("2026-04-04T14:00"), ldt("2026-04-04T15:45"), null,                    null,                    "",                                      VooStatus.AGENDADO),
            voo(p.get(4),  c.get(4),  "VP5005", "SBSV", "SBCF", ldt("2026-04-05T07:00"), ldt("2026-04-05T08:30"), null,                    null,                    "",                                      VooStatus.AGENDADO),
            voo(p.get(5),  c.get(5),  "IT6006", "SBMO", "SBFZ", ldt("2026-04-06T09:00"), ldt("2026-04-06T10:15"), null,                    null,                    "",                                      VooStatus.AGENDADO),
            voo(p.get(6),  c.get(6),  "RC7007", "SBMQ", "SBBV", ldt("2026-04-07T11:00"), ldt("2026-04-07T12:45"), null,                    null,                    "",                                      VooStatus.AGENDADO),
            // VOANDO
            voo(p.get(7),  c.get(7),  "SD8008", "SBBH", "SBVT", ldt("2026-03-19T06:00"), ldt("2026-03-19T07:00"), ldt("2026-03-19T06:05"), null,                    "",                                      VooStatus.VOANDO),
            voo(p.get(8),  c.get(8),  "TT9009", "SBCG", "SBCY", ldt("2026-03-19T07:30"), ldt("2026-03-19T09:00"), ldt("2026-03-19T07:35"), null,                    "",                                      VooStatus.VOANDO),
            voo(p.get(9),  c.get(9),  "AB0010", "SBGO", "SBLO", ldt("2026-03-19T08:00"), ldt("2026-03-19T09:00"), ldt("2026-03-19T08:00"), null,                    "",                                      VooStatus.VOANDO),
            voo(p.get(10), c.get(10), "BR1011", "SBKP", "SBSP", ldt("2026-03-19T09:30"), ldt("2026-03-19T10:30"), ldt("2026-03-19T09:32"), null,                    "",                                      VooStatus.VOANDO),
            voo(p.get(11), c.get(11), "WJ2012", "SBRF", "SBSG", ldt("2026-03-19T10:00"), ldt("2026-03-19T11:15"), ldt("2026-03-19T10:08"), null,                    "",                                      VooStatus.VOANDO),
            // CONCLUIDOS
            voo(p.get(12), c.get(12), "MT3013", "SBPA", "SBSP", ldt("2026-03-18T06:00"), ldt("2026-03-18T07:30"), ldt("2026-03-18T06:05"), ldt("2026-03-18T07:35"), "",                                      VooStatus.CONCLUIDO),
            voo(p.get(13), c.get(13), "PL4014", "SBBR", "SBGR", ldt("2026-03-17T12:00"), ldt("2026-03-17T13:30"), ldt("2026-03-17T12:10"), ldt("2026-03-17T13:40"), "",                                      VooStatus.CONCLUIDO),
            voo(p.get(14), c.get(0),  "AD5015", "SBFZ", "SBSV", ldt("2026-03-16T14:00"), ldt("2026-03-16T15:20"), ldt("2026-03-16T14:02"), ldt("2026-03-16T15:18"), "",                                      VooStatus.CONCLUIDO),
            voo(p.get(15), c.get(1),  "LA6016", "SBCF", "SBCT", ldt("2026-03-15T08:00"), ldt("2026-03-15T09:30"), ldt("2026-03-15T08:15"), ldt("2026-03-15T09:45"), "",                                      VooStatus.CONCLUIDO),
            voo(p.get(0),  c.get(2),  "G37017", "SBVT", "SBBH", ldt("2026-03-14T10:00"), ldt("2026-03-14T11:00"), ldt("2026-03-14T10:00"), ldt("2026-03-14T11:05"), "",                                      VooStatus.CONCLUIDO),
            // CANCELADOS
            voo(p.get(1),  c.get(3),  "PP8018", "SBLO", "SBGO", ldt("2026-03-20T18:00"), ldt("2026-03-20T19:00"), null,                    null,                    "Falha tecnica na aeronave",              VooStatus.CANCELADO),
            voo(p.get(2),  c.get(4),  "VP9019", "SBBV", "SBMQ", ldt("2026-03-21T07:00"), ldt("2026-03-21T08:45"), null,                    null,                    "Condicoes climaticas adversas na rota", VooStatus.CANCELADO),
            voo(p.get(3),  c.get(5),  "IT0020", "SBSG", "SBRF", ldt("2026-03-22T15:00"), ldt("2026-03-22T16:15"), null,                    null,                    "Greve dos controladores de voo",        VooStatus.CANCELADO)
        );
        vooRepository.saveAll(voos);
        System.out.println(">>> " + voos.size() + " voos inseridos.");
    }

    // ── HELPERS ───────────────────────────────────────────────────────────────

    private CompanhiaAerea companhia(String nome, String cnpj, String dataFundacao,
                                     boolean seguro, CompanhiaAereaStatus status) {
        CompanhiaAerea c = new CompanhiaAerea();
        c.setNome(nome);
        c.setCnpj(cnpj);
        c.setDataFundacao(LocalDate.parse(dataFundacao));
        c.setSeguroAeronave(seguro);
        c.setStatus(status);
        return c;
    }

    private Piloto piloto(String nome, int idade, String genero, String cpf,
                          String dataRenovacao, String matricula,
                          String habilitacao, PilotoStatus status) {
        Piloto p = new Piloto();
        p.setNome(nome);
        p.setIdade(idade);
        p.setGenero(genero);
        p.setCpf(cpf);
        p.setDataRenovacao(LocalDate.parse(dataRenovacao));
        p.setMatricula(matricula);
        p.setHabilitacao(habilitacao);
        p.setStatus(status);
        return p;
    }

    private Voo voo(Piloto piloto, CompanhiaAerea companhia, String codigo,
                    String origem, String destino,
                    LocalDateTime partida, LocalDateTime chegada,
                    LocalDateTime partidaReal, LocalDateTime chegadaReal,
                    String motivoCancelamento, VooStatus status) {
        Voo v = new Voo();
        v.setPiloto(piloto);
        v.setCompanhia(companhia);
        v.setCodigo(codigo);
        v.setOrigem(origem);
        v.setDestino(destino);
        v.setHorarioPartidaPrevisto(partida);
        v.setHorarioChegadaPrevisto(chegada);
        v.setHorarioPartidaReal(partidaReal);
        v.setHorarioChegadaReal(chegadaReal);
        v.setMotivoCancelamento(motivoCancelamento);
        v.setStatus(status);
        return v;
    }

    private LocalDateTime ldt(String iso) {
        return LocalDateTime.parse(iso);
    }
}