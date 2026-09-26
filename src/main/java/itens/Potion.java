package itens;

import pokesal.Pokesal;

/**
 * Representa o item Potion, utilizado para recuperar o HP de um Pokesal.
 *
 * <p>A quantidade de cura é definida quando o item é criado e não pode
 * ultrapassar o HP máximo do Pokesal.</p>
 */
public class Potion implements Item {

  private int qtdCura;
  private String nome = "Potion";

  public String getNome() {
    return nome;
  }

  /**
   * Cria uma Potion com uma quantidade de cura definida.
   *
   * @param qtdCura quantidade de HP que a Potion pode recuperar
   */
  public Potion(int qtdCura) {
    this.qtdCura = qtdCura;
  }

  /**
   * Utiliza a Potion para recuperar o HP do Pokesal.
   *
   * <p>A cura é adicionada ao HP atual, mas o resultado é limitado
   * ao HP máximo do Pokesal.</p>
   *
   * @param pokesal Pokesal que receberá a cura
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