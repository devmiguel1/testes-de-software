package treinador;

import itens.Item;
import java.util.Random;
import pokesal.Pokesal;

/**
 * Representa um treinador que participa das batalhas.
 *
 * <p>O treinador possui um Pokesal, itens que podem ser utilizados
 * durante a batalha e opções de melhorias que alteram algumas
 * características do seu Pokesal.</p>
 */
public class Treinador {
  private String nome;
  private Pokesal pokesal;
  private Item[] itens = new Item[2];
  private int itensUsados = 0;

  private void perkCritico() {
    pokesal.setCritico(pokesal.getCritico() + 10);
  }

  private void perkDesvio() {
    pokesal.setDesvio(pokesal.getDesvio() + 5);
  }

  private void perkDefesa() {
    pokesal.setDef(pokesal.getDef() + 5);
  }

  /**
   * Escolhe uma melhoria para aplicar ao Pokesal do treinador.
   *
   * <p>O número informado define qual característica será aumentada:
   * crítico, desvio ou defesa.</p>
   *
   * @param numero número correspondente à melhoria escolhida
   */
  public void escolhaDePerk(int numero) {
    switch (numero) {
      case 1:
        perkCritico();
        break;
      case 2:
        perkDesvio();
        break;
      case 3:
        perkDefesa();
        break;
      default:
        break;
    }
  }

  /**
   * Realiza um ataque do Pokesal do treinador contra um Pokesal inimigo.
   *
   * <p>O método verifica primeiro se o ataque erra com base no desvio
   * do inimigo e no erro do atacante. Caso acerte, calcula o dano
   * considerando o ataque, o bônus recebido e a defesa do inimigo.
   * Também verifica a possibilidade de um ataque crítico.</p>
   *
   * @param inimigo Pokesal que receberá o ataque
   * @param bonus bônus aplicado ao ataque
   */
  public void ataquePokesal(Pokesal inimigo, double bonus) {
    Random random = new Random();
    if (random.nextInt(1, 100) <= (inimigo.getDesvio() + pokesal.getErro())) {
      System.out.println("errou o golpe");
      return;
    }

    double dano = Math.max(1, pokesal.getAtk() * bonus) - inimigo.getDef();

    if (random.nextInt(1, 100) <= pokesal.getCritico()) {
      System.out.println("Acertou um ataque critico");
      dano = dano * 2;
    }
    inimigo.setHp(inimigo.getHp() - dano);
  }

  /**
   * Lista os itens disponíveis para o treinador.
   *
   * <p>Somente as posições que possuem um item são exibidas,
   * juntamente com o número que pode ser utilizado para selecionar
   * o item.</p>
   */
  public void listarItens() {
    for (int index = 0; index < itens.length; index++) {
      if (itens[index] != null) {
        System.out.println("[" + (index + 1) + "] " + itens[index].getNome());
      }
    }
  }

  /**
   * Utiliza um item do treinador no seu Pokesal.
   *
   * <p>O método verifica se o limite de dois itens utilizados na
   * batalha já foi atingido. Caso o limite tenha sido atingido,
   * uma exceção é lançada.</p>
   *
   * @param indice posição do item que será utilizado
   * @throws IllegalStateException quando o treinador já utilizou
   *         o limite de itens permitido na batalha
   */
  public void usarItem(int indice) {
    if (itensUsados >= 2) {
      throw new IllegalStateException(nome + " ja usou o limite de itens nesta batalha");
    }
    itens[indice].usar(pokesal);
    itensUsados++;
  }

  /**
   * Retorna os itens que pertencem ao treinador.
   *
   * @return array contendo os itens do treinador
   */
  public Item[] getItens() {
    return itens;
  }

  /**
   * Retorna um item específico pela posição informada.
   *
   * @param numero posição do item no array
   * @return item localizado na posição informada
   */
  public Item getItem(int numero) {
    return itens[numero];
  }

  public void setItens(Item[] itens) {
    this.itens = itens;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Pokesal getPokesal() {
    return pokesal;
  }

  public void setPokesal(Pokesal pokesal) {
    this.pokesal = pokesal;
  }
}