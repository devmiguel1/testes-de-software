package itens;

import pokesal.Pokesal;

/**
 * Define o comportamento básico que os itens utilizados na batalha
 * devem possuir.
 *
 * <p>Cada item deve implementar um método para ser utilizado em um
 * Pokesal e outro método para informar o nome do item.</p>
 */
public interface Item {

  /**
   * Utiliza o item em um Pokesal.
   *
   * @param pokesal Pokesal que receberá o efeito do item
   */
  public void usar(Pokesal pokesal);

  public String getNome();
}