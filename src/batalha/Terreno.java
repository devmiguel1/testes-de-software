package batalha;

import batalha.enums.TipoTerreno;
import pokesal.Pokesal;
import pokesal.enums.TipoElemental;
import treinador.Treinador;

import static batalha.enums.TipoTerreno.*;

public class Terreno {
    private TipoTerreno terreno;

    public double bonus(Treinador atacante, Treinador defensor) {

        Pokesal pokeAtacante = atacante.getPokesal();
        Pokesal pokeDefensor = defensor.getPokesal();
        double bonusTipo = 1.0;

        switch (pokeAtacante.getTipo()) {

            case FOGO:
                if (pokeDefensor.getTipo() == TipoElemental.PLANTA) {
                    bonusTipo = 2.0;
                } else if (pokeDefensor.getTipo() == TipoElemental.AGUA) {
                    bonusTipo = 0.5;
                }
                break;

            case AGUA:
                if (pokeDefensor.getTipo() == TipoElemental.FOGO) {
                    bonusTipo = 2.0;
                } else if (pokeDefensor.getTipo() == TipoElemental.PLANTA) {
                    bonusTipo = 0.5;
                }
                break;

            case PLANTA:
                if (pokeDefensor.getTipo() == TipoElemental.AGUA) {
                    bonusTipo = 2.0;
                } else if (pokeDefensor.getTipo() == TipoElemental.FOGO) {
                    bonusTipo = 0.5;
                }
                break;

            default:
                break;
        }

        return bonusTipo * bonusTerreno(pokeAtacante);
    }

    private double bonusTerreno(Pokesal pokeAtacante) {
        if (terreno == ASFALTO_QUENTE && pokeAtacante.getTipo() == TipoElemental.FOGO) {
            return 1.15;
        }
        if (terreno == PISO_ESCORREGADIO && pokeAtacante.getTipo() == TipoElemental.AGUA) {
            return 1.10;
        }
        return 1.0;
    }

    public void efeitoFimDeTurno(Pokesal pokesal) {
        if (terreno == CANTEIRO_CENTRAL && pokesal.getTipo() == TipoElemental.PLANTA && pokesal.getHp() > 0) {
            pokesal.setHp(pokesal.getHp() + pokesal.getHpMax() * 0.05);
        }
    }

    public TipoTerreno getTerreno() {
        return terreno;
    }

    public void setTerreno(TipoTerreno terreno) {
        this.terreno = terreno;
    }
}
