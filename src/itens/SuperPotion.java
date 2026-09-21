package itens;

import pokesal.Pokesal;

/**
 * Representa o item SuperPotion, utilizado para recuperar o HP de um Pokesal.
 */
public class SuperPotion implements Item {

  private int qtdCura;
  private String nome = "SuperPotion";

  public String getNome() {
    return nome;
  }

  /**
   * Cria uma SuperPotion com uma quantidade de cura definida.
   *
   * @param qtdCura quantidade de HP que a SuperPotion recupera
   */
  public SuperPotion(int qtdCura) {
    this.qtdCura = qtdCura;
  }

  /**
   * Utiliza a SuperPotion para recuperar o HP do Pokesal.
   *
   * <p>A quantidade de cura é adicionada diretamente ao HP atual
   * do Pokesal.</p>
   *
   * @param pokesal Pokesal que receberá o efeito da SuperPotion
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