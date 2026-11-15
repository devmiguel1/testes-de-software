
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServicoTerrenoMockitoTest {

    @Mock
    private Clock clock;

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
}
