package itens;

import pokesal.Pokesal;

/**
 * Classe do sistema.
 */

public class SuperPotion implements Item {

  private int qtdCura;
  private String nome = "SuperPotion";

  public String getNome() {
    return nome;
  }

  /**
   * Classe do sistema.
   */

  public SuperPotion(int qtdCura) {
    this.qtdCura = qtdCura;
  }

  /**
   * Classe do sistema.
   */

  public void usar(Pokesal pokesal) {
    pokesal.setHp(pokesal.getHp() + qtdCura);
  }

  public int getQtdCura() {
    return qtdCura;
  }

  public void setQtdCura(int qtdCura) {
    this.qtdCura = qtdCura;
  }
}