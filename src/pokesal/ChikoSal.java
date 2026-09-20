package pokesal;

import pokesal.enums.TipoElemental;

/**
 * Classe do sistema.
 */

public class ChikoSal extends Pokesal {
  /**
   * Classe do sistema.
   */

  public ChikoSal() {
    setNome("ChikoSal");
    setHp(45);
    setHpMax(45);
    setAtk(14);
    setDef(6);
    setSpd(45);
    setTipo(TipoElemental.PLANTA);
    setEnvenenado(false);
  }
}
