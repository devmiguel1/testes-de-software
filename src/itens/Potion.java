package itens;

import pokesal.Pokesal;

/**
 * Classe do sistema.
 */

public class Potion implements Item {

  private int qtdCura;
  private String nome = "Potion";

  public String getNome() {
    return nome;
  }

  /**
   * Classe do sistema.
   */

  public Potion(int qtdCura) {
    this.qtdCura = qtdCura;
  }

  /**
   * Classe do sistema.
   */

  public void usar(Pokesal pokesal) {
    pokesal.setHp(Math.min(pokesal.getHp() + qtdCura, pokesal.getHpMax()));
  }

  public int getQtdCura() {
    return qtdCura;
  }

  public void setQtdCura(int qtdCura) {
    this.qtdCura = qtdCura;
  }
}