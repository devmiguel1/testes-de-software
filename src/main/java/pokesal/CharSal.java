package pokesal;

import pokesal.enums.TipoElemental;

/**
 * Representa o Pokesal CharSal.
 *
 * <p>Define os atributos iniciais do CharSal, como nome, HP,
 * ataque, defesa, velocidade, tipo elemental e estado de envenenamento.</p>
 */
public class CharSal extends Pokesal {
  /**
   * Cria um CharSal e define seus atributos iniciais.
   *
   * <p>O CharSal é definido como do tipo Fogo e começa a batalha
   * sem estar envenenado.</p>
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