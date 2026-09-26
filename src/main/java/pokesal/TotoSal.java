package pokesal;

import pokesal.enums.TipoElemental;

/**
 * Representa o Pokesal TotoSal.
 *
 * <p>Define os atributos iniciais do TotoSal, como nome, HP,
 * ataque, defesa, velocidade, tipo elemental e estado de envenenamento.</p>
 */
public class TotoSal extends Pokesal {
  /**
   * Cria um TotoSal e define seus atributos iniciais.
   *
   * <p>O TotoSal é definido como do tipo Água e começa a batalha
   * sem estar envenenado.</p>
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