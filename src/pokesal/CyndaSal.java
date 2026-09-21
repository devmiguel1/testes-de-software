package pokesal;

import pokesal.enums.TipoElemental;

/**
 * Representa o Pokesal CyndaSal.
 *
 * <p>Define os atributos iniciais do CyndaSal, como nome, HP,
 * ataque, defesa, velocidade, tipo elemental e estado de envenenamento.</p>
 */
public class CyndaSal extends Pokesal {
  /**
   * Cria um CyndaSal e define seus atributos iniciais.
   *
   * <p>O CyndaSal é definido como do tipo Fogo e começa a batalha
   * sem estar envenenado.</p>
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