package itens;

import pokesal.Pokesal;

/**
 * Classe do sistema.
 */

public class Antidote implements Item {

  private boolean efeito = false;
  private String nome = "Antidote";

  public String getNome() {
    return nome;
  }

  /**
   * Classe do sistema.
   */

  public void usar(Pokesal pokesal) {

    if (!efeito && pokesal.isEnvenenado()) {
      pokesal.setEnvenenado(false);
      efeito = true;
    }
  }
}