package itens;

import pokesal.Pokesal;

/**
 * Representa o item Antidote, utilizado para remover o envenenamento
 * de um Pokesal durante a batalha.
 *
 * <p>O item verifica se o Pokesal está envenenado e se o efeito
 * ainda não foi utilizado. Quando essas condições são atendidas,
 * o envenenamento é removido e o item passa a ser considerado usado.</p>
 */
public class Antidote implements Item {

  private boolean efeito = false;
  private String nome = "Antidote";

  public String getNome() {
    return nome;
  }

  /**
   * Utiliza o Antidote em um Pokesal.
   *
   * <p>O método remove o envenenamento do Pokesal caso o item ainda
   * não tenha sido utilizado e o Pokesal esteja envenenado.
   * Depois de aplicar o efeito, o item não poderá ser utilizado
   * novamente.</p>
   *
   * @param pokesal Pokesal que receberá o efeito do Antidote
   */
  public void usar(Pokesal pokesal) {

    if (!efeito && pokesal.isEnvenenado()) {
      pokesal.setEnvenenado(false);
      efeito = true;
    }
  }
}