package pokesal;

import pokesal.enums.TipoElemental;

/**
 * Representa o Pokesal SquirtSal.
 *
 * <p>Define os atributos iniciais do SquirtSal, como nome, HP,
 * ataque, defesa, velocidade, tipo elemental e estado de envenenamento.</p>
 */
public class SquirtSal extends Pokesal {
  /**
   * Cria um SquirtSal e define seus atributos iniciais.
   *
   * <p>O SquirtSal é definido como do tipo Água e começa a batalha
   * sem estar envenenado.</p>
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