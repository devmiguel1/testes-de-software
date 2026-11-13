package batalha;

import batalha.enums.TipoTerreno;
import pokesal.Pokesal;
import treinador.Treinador;

public class Terreno {

  private TipoTerreno tipoterreno;
  private final ServicoTerreno servicoTerreno;

  public Terreno(ServicoTerreno servicoTerreno) {
    this.servicoTerreno = servicoTerreno;
  }

  public double bonus(Treinador atacante, Treinador defensor) {

    Pokesal pokeAtacante = atacante.getPokesal();
    Pokesal pokeDefensor = defensor.getPokesal();

    double bonusTipo = 1.0;

    switch (pokeAtacante.getTipo()) {

      case FOGO:
        if (pokeDefensor.getTipo() == pokesal.enums.TipoElemental.PLANTA) {
          bonusTipo = 2.0;
        } else if (pokeDefensor.getTipo() == pokesal.enums.TipoElemental.AGUA) {
          bonusTipo = 0.5;
        }
        break;

      case AGUA:
        if (pokeDefensor.getTipo() == pokesal.enums.TipoElemental.FOGO) {
          bonusTipo = 2.0;
        } else if (pokeDefensor.getTipo() == pokesal.enums.TipoElemental.PLANTA) {
          bonusTipo = 0.5;
        }
        break;

      case PLANTA:
        if (pokeDefensor.getTipo() == pokesal.enums.TipoElemental.AGUA) {
          bonusTipo = 2.0;
        } else if (pokeDefensor.getTipo() == pokesal.enums.TipoElemental.FOGO) {
          bonusTipo = 0.5;
        }
        break;

      default:
        break;
    }

    return bonusTipo
            * servicoTerreno.bonusTerreno(tipoterreno, pokeAtacante);
  }

  public void efeitoFimDeTurno(Pokesal pokesal) {
    servicoTerreno.efeitoFimDeTurno(tipoterreno, pokesal);
  }

  public TipoTerreno getTerreno() {
    return tipoterreno;
  }

  public void setTerreno(TipoTerreno terreno) {
    this.tipoterreno = terreno;
  }
}