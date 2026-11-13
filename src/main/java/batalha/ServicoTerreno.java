package batalha;

import java.time.Clock;
import java.time.LocalTime;

import batalha.enums.TipoClima;
import batalha.enums.TipoTerreno;
import pokesal.Pokesal;
import pokesal.enums.TipoElemental;

public class ServicoTerreno {

    private final Clock clock;
    private final TipoClima clima;

    public ServicoTerreno(Clock clock, TipoClima clima) {
        this.clock = clock;
        this.clima = clima;
    }

    public double bonusTerreno(TipoTerreno terreno, Pokesal pokeAtacante) {

        LocalTime horario = LocalTime.now(clock);

        boolean duranteODia = horario.isAfter(LocalTime.of(6, 0))
                && horario.isBefore(LocalTime.of(18, 0));

        if (terreno == TipoTerreno.ASFALTO_QUENTE
                && pokeAtacante.getTipo() == TipoElemental.FOGO
                && duranteODia
                && clima == TipoClima.ENSOLARADO) {
            return 1.15;
        }

        if (terreno == TipoTerreno.PISO_ESCORREGADIO
                && pokeAtacante.getTipo() == TipoElemental.AGUA
                && clima == TipoClima.CHUVOSO) {
            return 1.10;
        }

        return 1.0;
    }

    public void efeitoFimDeTurno(TipoTerreno terreno, Pokesal pokesal) {

        if (terreno == TipoTerreno.CANTEIRO_CENTRAL
                && pokesal.getTipo() == TipoElemental.PLANTA
                && pokesal.getHp() > 0
                && clima != TipoClima.TEMPESTADE) {

            pokesal.setHp(
                    pokesal.getHp() + pokesal.getHpMax() * 0.05
            );
        }
    }
}