package pokesal;

import pokesal.enums.TipoElemental;

/**
 * Classe do sistema.
 */

public class CharSal extends Pokesal {
  /**
   * Classe do sistema.
   */

  public CharSal() {
    setNome("CharSal");
    setHp(39);
    setHpMax(39);
    setAtk(17);
    setDef(6);
    setSpd(65);
    setTipo(TipoElemental.FOGO);
    setEnvenenado(false);
  }
}
