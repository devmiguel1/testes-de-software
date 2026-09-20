package pokesal;

import pokesal.enums.TipoElemental;

/**
 * Classe do sistema.
 */

public class CyndaSal extends Pokesal {
  /**
   * Classe do sistema.
   */

  public CyndaSal() {
    setNome("CyndaSal");
    setHp(39);
    setHpMax(39);
    setAtk(15);
    setDef(5);
    setSpd(65);
    setTipo(TipoElemental.FOGO);
    setEnvenenado(false);
  }
}
