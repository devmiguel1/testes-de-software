package pokesal;

import pokesal.enums.TipoElemental;

/**
 * Representa o Pokesal BulbaSal.
 *
 * <p>Define os atributos iniciais do BulbaSal, como nome, HP,
 * ataque, defesa, velocidade, tipo elemental e estado de envenenamento.</p>
 */
public class BulbaSal extends Pokesal {
  /**
   * Cria um BulbaSal e define seus atributos iniciais.
   *
   * <p>O BulbaSal é definido como do tipo Planta e começa a batalha
   * sem estar envenenado.</p>
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