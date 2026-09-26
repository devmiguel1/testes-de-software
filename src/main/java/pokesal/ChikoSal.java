package pokesal;

import pokesal.enums.TipoElemental;

/**
 * Representa o Pokesal ChikoSal.
 *
 * <p>Define os atributos iniciais do ChikoSal, como nome, HP,
 * ataque, defesa, velocidade, tipo elemental e estado de envenenamento.</p>
 */
public class ChikoSal extends Pokesal {
  /**
   * Cria um ChikoSal e define seus atributos iniciais.
   *
   * <p>O ChikoSal é definido como do tipo Planta e começa a batalha
   * sem estar envenenado.</p>
   */
  public ChikoSal() {
    setNome("ChikoSal");
    setHp(45);
    setHpMax(45);
    setAtk(14);
    setDef(6);
    setSpd(45);
    setTipo(TipoElemental.PLANTA);
    setEnvenenado(false);
  }
}