package pokesal;

import pokesal.enums.TipoElemental;

/**
 * Classe do sistema.
 */

public class SquirtSal extends Pokesal {
  /**
   * Classe do sistema.
   */

  public SquirtSal() {
    setNome("SquirtSal");
    setHp(44);
    setHpMax(44);
    setAtk(10);
    setDef(4);
    setSpd(43);
    setTipo(TipoElemental.AGUA);
    setEnvenenado(false);
  }
}
