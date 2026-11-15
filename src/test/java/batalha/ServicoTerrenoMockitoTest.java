package batalha;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import batalha.enums.TipoClima;
import batalha.enums.TipoTerreno;
import pokesal.CharSal;
import pokesal.enums.TipoElemental;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServicoTerrenoMockitoTest {

    @Mock
    private Clock clock;

    private void fixarHorario(String instante) {

        when(clock.getZone()).thenReturn(ZoneOffset.UTC);

        when(clock.instant()).thenReturn(
            Instant.parse(instante)
        );
    }

    @Test
    void testAsfaltoQuenteApenasDuranteODia() {

        when(clock.getZone()).thenReturn(ZoneOffset.UTC);

        when(clock.instant()).thenReturn(
            Instant.parse("2026-11-15T12:00:00Z"),
            Instant.parse("2026-11-15T20:00:00Z")
        );

        ServicoTerreno servico = new ServicoTerreno(
            clock,
            TipoClima.ENSOLARADO
        );

        CharSal poke = new CharSal();

        double bonusDia = servico.bonusTerreno(
            TipoTerreno.ASFALTO_QUENTE,
            poke
        );

        double bonusNoite = servico.bonusTerreno(
            TipoTerreno.ASFALTO_QUENTE,
            poke
        );

        assertEquals(1.15, bonusDia, 0.001);
        assertEquals(1.0, bonusNoite, 0.001);

        verify(clock, times(2)).instant();
        verify(clock, atLeastOnce()).getZone();
    }

    @Test
    void testAsfaltoQuenteNaoValeAntesDasSeis() {

        fixarHorario("2026-11-15T05:00:00Z");

        ServicoTerreno servico = new ServicoTerreno(
            clock,
            TipoClima.ENSOLARADO
        );

        double bonus = servico.bonusTerreno(
            TipoTerreno.ASFALTO_QUENTE,
            new CharSal()
        );

        assertEquals(1.0, bonus, 0.001);
    }

    @Test
    void testAsfaltoQuenteNaoBeneficiaOutroTipo() {

        fixarHorario("2026-11-15T12:00:00Z");

        ServicoTerreno servico = new ServicoTerreno(
            clock,
            TipoClima.ENSOLARADO
        );

        CharSal poke = new CharSal();
        poke.setTipo(TipoElemental.AGUA);

        double bonus = servico.bonusTerreno(
            TipoTerreno.ASFALTO_QUENTE,
            poke
        );

        assertEquals(1.0, bonus, 0.001);
    }

    @Test
    void testAsfaltoQuenteNaoValeSemSol() {

        fixarHorario("2026-11-15T12:00:00Z");

        ServicoTerreno servico = new ServicoTerreno(
            clock,
            TipoClima.CHUVOSO
        );

        double bonus = servico.bonusTerreno(
            TipoTerreno.ASFALTO_QUENTE,
            new CharSal()
        );

        assertEquals(1.0, bonus, 0.001);
    }

    @Test
    void testPisoEscorregadioNaoBeneficiaSemAguaOuSemChuva() {

        fixarHorario("2026-11-15T12:00:00Z");

        ServicoTerreno servico = new ServicoTerreno(
            clock,
            TipoClima.ENSOLARADO
        );

        CharSal fogo = new CharSal();

        CharSal agua = new CharSal();
        agua.setTipo(TipoElemental.AGUA);

        double bonusFogo = servico.bonusTerreno(
            TipoTerreno.PISO_ESCORREGADIO,
            fogo
        );

        double bonusAgua = servico.bonusTerreno(
            TipoTerreno.PISO_ESCORREGADIO,
            agua
        );

        assertEquals(1.0, bonusFogo, 0.001);
        assertEquals(1.0, bonusAgua, 0.001);
    }

    @Test
    void testCanteiroCentralCuraPokesalDePlanta() {

        ServicoTerreno servico = new ServicoTerreno(
            clock,
            TipoClima.ENSOLARADO
        );

        CharSal poke = new CharSal();
        poke.setTipo(TipoElemental.PLANTA);
        poke.setHp(10);

        servico.efeitoFimDeTurno(
            TipoTerreno.CANTEIRO_CENTRAL,
            poke
        );

        assertEquals(10 + poke.getHpMax() * 0.05, poke.getHp(), 0.001);
    }

    @Test
    void testCanteiroCentralNaoCuraOutroTipo() {

        ServicoTerreno servico = new ServicoTerreno(
            clock,
            TipoClima.ENSOLARADO
        );

        CharSal poke = new CharSal();
        poke.setHp(10);

        servico.efeitoFimDeTurno(
            TipoTerreno.CANTEIRO_CENTRAL,
            poke
        );

        assertEquals(10, poke.getHp(), 0.001);
    }

    @Test
    void testOutroTerrenoNaoCura() {

        ServicoTerreno servico = new ServicoTerreno(
            clock,
            TipoClima.ENSOLARADO
        );

        CharSal poke = new CharSal();
        poke.setTipo(TipoElemental.PLANTA);
        poke.setHp(10);

        servico.efeitoFimDeTurno(
            TipoTerreno.ASFALTO_QUENTE,
            poke
        );

        assertEquals(10, poke.getHp(), 0.001);
    }

    @Test
    void testCanteiroCentralNaoCuraPokesalDerrotado() {

        ServicoTerreno servico = new ServicoTerreno(
            clock,
            TipoClima.ENSOLARADO
        );

        CharSal poke = new CharSal();
        poke.setTipo(TipoElemental.PLANTA);
        poke.setHp(0);

        servico.efeitoFimDeTurno(
            TipoTerreno.CANTEIRO_CENTRAL,
            poke
        );

        assertEquals(0, poke.getHp(), 0.001);
    }

    @Test
    void testCanteiroCentralNaoCuraEmTempestade() {

        ServicoTerreno servico = new ServicoTerreno(
            clock,
            TipoClima.TEMPESTADE
        );

        CharSal poke = new CharSal();
        poke.setTipo(TipoElemental.PLANTA);
        poke.setHp(10);

        servico.efeitoFimDeTurno(
            TipoTerreno.CANTEIRO_CENTRAL,
            poke
        );

        assertEquals(10, poke.getHp(), 0.001);
    }
}
