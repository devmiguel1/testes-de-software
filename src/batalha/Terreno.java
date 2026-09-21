package batalha;

import static batalha.enums.TipoTerreno.ASFALTO_QUENTE;
import static batalha.enums.TipoTerreno.CANTEIRO_CENTRAL;
import static batalha.enums.TipoTerreno.PISO_ESCORREGADIO;

import batalha.enums.TipoTerreno;
import pokesal.Pokesal;
import pokesal.enums.TipoElemental;
import treinador.Treinador;

/**
 * Representa o terreno onde a batalha está acontecendo.
 *
 * <p>É responsável por calcular os bônus de ataque de acordo com
 * o tipo do Pokesal e o terreno, além de aplicar efeitos específicos
 * ao final de cada turno.</p>
 */
public class Terreno {
  private TipoTerreno terreno;

  /**
   * Calcula o bônus de ataque considerando o tipo do Pokesal
   * atacante e o tipo do Pokesal defensor.
   *
   * <p>O bônus pode aumentar ou diminuir de acordo com a vantagem
   * ou desvantagem entre os tipos elementais. Depois disso, o bônus
   * específico do terreno também é aplicado ao resultado.</p>
   *
   * @param atacante treinador que está realizando o ataque
   * @param defensor treinador que está recebendo o ataque
   * @return multiplicador final do bônus do ataque
   */
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

  /**
   * Aplica o efeito do terreno ao final do turno.
   *
   * <p>Quando o terreno é um canteiro central e o Pokesal é do tipo
   * Planta e ainda possui HP, ele recupera 5% do seu HP máximo.</p>
   *
   * @param pokesal Pokesal que receberá o efeito do terreno
   */
  public void efeitoFimDeTurno(Pokesal pokesal) {
    if (terreno == CANTEIRO_CENTRAL && pokesal.getTipo() == TipoElemental.PLANTA
        &&
        pokesal.getHp() > 0) {
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