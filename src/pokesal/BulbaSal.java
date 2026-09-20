package pokesal;

import pokesal.enums.TipoElemental;

/**
 * Classe do sistema.
 */

public class BulbaSal extends Pokesal {
  /**
   * Classe do sistema.
   */

  public BulbaSal() {
    setNome("BulbaSal");
    setHp(45);
    setHpMax(45);
    setAtk(12);
    setDef(4);
    setSpd(45);
    setTipo(TipoElemental.PLANTA);
    setEnvenenado(false);
  }
}
