package batalha;

import java.time.Clock;
import java.time.LocalTime;

import batalha.enums.TipoTerreno;
import pokesal.Pokesal;
import pokesal.enums.TipoElemental;

public class ServicoTerreno {

    private final Clock clock;

    public ServicoTerreno(Clock clock) {
        this.clock = clock;
    }

    public double bonusTerreno(TipoTerreno terreno, Pokesal pokeAtacante) {

        LocalTime horario = LocalTime.now(clock);

        boolean duranteODia = horario.isAfter(LocalTime.of(6, 0))
                && horario.isBefore(LocalTime.of(18, 0));

        if (terreno == TipoTerreno.ASFALTO_QUENTE
                && pokeAtacante.getTipo() == TipoElemental.FOGO
                && duranteODia) {
            return 1.15;
        }

        if (terreno == TipoTerreno.PISO_ESCORREGADIO
                && pokeAtacante.getTipo() == TipoElemental.AGUA) {
            return 1.10;
        }

        return 1.0;
    }

    public void efeitoFimDeTurno(TipoTerreno terreno, Pokesal pokesal) {

        if (terreno == TipoTerreno.CANTEIRO_CENTRAL
                && pokesal.getTipo() == TipoElemental.PLANTA
                && pokesal.getHp() > 0) {

            pokesal.setHp(
                    pokesal.getHp() + pokesal.getHpMax() * 0.05
            );
        }
    }
}
