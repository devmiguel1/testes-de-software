package pokesal;

import pokesal.enums.TipoElemental;

/**
 * Classe do sistema.
 */

public class TotoSal extends Pokesal {
  /**
   * Classe do sistema.
   */

  public TotoSal() {
    setNome("TotoSal");
    setHp(50);
    setHpMax(50);
    setAtk(11);
    setDef(5);
    setSpd(43);
    setTipo(TipoElemental.AGUA);
    setEnvenenado(false);
  }
}

