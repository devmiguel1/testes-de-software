package batalha;

import batalha.enums.TipoTerreno;
import pokesal.Pokesal;
import pokesal.enums.TipoElemental;
import treinador.Treinador;

import static batalha.enums.TipoTerreno.*;

public abstract class Terreno {
    private TipoTerreno terreno;

    public double bonus(Treinador atacante, Treinador defensor) {

        Pokesal pokeAtacante = atacante.getPokesal();
        Pokesal pokeDefensor = defensor.getPokesal();

        switch (pokeAtacante.getTipo()) {

            case FOGO:
                if (pokeDefensor.getTipo() == TipoElemental.PLANTA) {
                    return 2.0;
                }

                if (pokeDefensor.getTipo() == TipoElemental.AGUA) {
                    return 0.5;
                }

                return 1.0;

            case AGUA:
                if (pokeDefensor.getTipo() == TipoElemental.FOGO) {
                    return 2.0;
                }

                if (pokeDefensor.getTipo() == TipoElemental.PLANTA) {
                    return 0.5;
                }

                return 1.0;

            case PLANTA:
                if (pokeDefensor.getTipo() == TipoElemental.AGUA) {
                    return 2.0;
                }

                if (pokeDefensor.getTipo() == TipoElemental.FOGO) {
                    return 0.5;
                }

                return 1.0;
        }

        return 1.0;
    }

    public TipoTerreno getTerreno() {
        return terreno;
    }

    public void setTerreno(TipoTerreno terreno) {
        this.terreno = terreno;
    }
}
