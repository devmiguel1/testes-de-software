package batalha;

import java.util.Scanner;
import treinador.Treinador;

/**
 * Representa e gerencia uma batalha entre dois treinadores.
 *
 * <p>Controla a ordem dos turnos, os ataques, o uso de itens,
 * a aplicação do bônus do terreno e a verificação das condições
 * de vitória e derrota.</p>
 */
public class Batalha {

  /**
   * Inicia uma batalha entre dois treinadores.
   *
   * <p>A ordem dos ataques é determinada pela velocidade dos
   * Pokésais de cada treinador. A batalha continua enquanto
   * nenhum dos dois treinadores for derrotado.</p>
   *
   * @param t1 treinador que participa da batalha
   * @param t2 segundo treinador que participa da batalha
   * @param terreno terreno utilizado durante a batalha
   */
  public void iniciarBatalha(Treinador t1, Treinador t2, Terreno terreno) {
    boolean vida = true;
    while (vida) {
      if (t1
          .getPokesal()
          .getSpd() < t2
          .getPokesal()
          .getSpd()) {
        vida = executarTurno(t2, t1, terreno);
      } else {
        vida = executarTurno(t1, t2, terreno);
      }
    }
  }

  /**
   * Executa um turno completo da batalha entre dois treinadores.
   *
   * <p>Antes de cada ataque, verifica se o treinador precisa
   * utilizar uma Potion. Em seguida, realiza o ataque e verifica
   * se o treinador defensor foi derrotado. Caso nenhum treinador
   * seja derrotado, o segundo treinador também realiza seu ataque.</p>
   *
   * @param t1 treinador que realiza o primeiro ataque
   * @param t2 treinador que recebe o primeiro ataque
   * @param terreno terreno utilizado para calcular o bônus do ataque
   * @return {@code false} quando um dos treinadores é derrotado,
   *         ou {@code true} quando a batalha deve continuar
   */
  public boolean executarTurno(Treinador t1, Treinador t2, Terreno terreno) {
    curarSeNecessario(t1);
    ataqueTurno(t1, t2, terreno);
    if (t2
        .getPokesal()
        .getHp() <= 0) {
      System.out.println(t2
          .getPokesal()
          .getNome() + " nao aguentou mais!");
      System.out.println(t1.getNome() + " ganhou a batalha!");
      return false;
    }

    curarSeNecessario(t2);
    ataqueTurno(t2, t1, terreno);
    if (t1
        .getPokesal()
        .getHp() <= 0) {
      System.out.println(t1
          .getPokesal()
          .getNome() + " nao aguentou mais!");
      System.out.println(t2.getNome() + " ganhou a batalha!");
      return false;
    }

    return true;
  }

  /**
   * Permite que o treinador escolha um item da lista de itens
   * para ser utilizado durante a batalha.
   *
   * @param treinador treinador que irá escolher e utilizar o item
   */
  private void escolhaItem(Treinador treinador) {
    Scanner sc = new Scanner(System.in);
    treinador.listarItens();

    System.out.println("Ecolha o Item: ");
    int escolha = Integer.parseInt(sc
        .nextLine()
        .trim());
    treinador.usarItem(escolha - 1);
  }

  /**
   * Executa o ataque de um treinador contra outro.
   *
   * <p>Primeiro calcula o bônus fornecido pelo terreno e registra
   * o HP do defensor antes do ataque. Depois realiza o ataque,
   * calcula o dano causado e exibe as informações do ataque
   * e do HP restante.</p>
   *
   * @param atacante treinador responsável por realizar o ataque
   * @param defensor treinador que recebe o ataque
   * @param terreno terreno utilizado para calcular o bônus do ataque
   */
  private void ataqueTurno(Treinador atacante, Treinador defensor, Terreno terreno) {
    double bonus = terreno.bonus(atacante, defensor);
    double hpAntes = defensor
        .getPokesal()
        .getHp();

    System.out.println(atacante
        .getPokesal()
        .getNome() + " atacou " + defensor
        .getPokesal()
        .getNome());
    atacante.ataquePokesal(defensor.getPokesal(), bonus);

    double dano = hpAntes - defensor
        .getPokesal()
        .getHp();
    System.out.printf("dano: %.2f \n", dano);
    System.out.printf("HP de %s: %.2f%n", defensor
        .getPokesal()
        .getNome(), defensor
        .getPokesal()
        .getHp());
    System.out.println();
  }

  /**
   * Verifica se o Pokésal do treinador precisa ser curado.
   *
   * <p>A cura é realizada quando o HP atual é maior que zero
   * e menor ou igual à metade do HP máximo. Nesse caso, o método
   * tenta utilizar o primeiro item disponível. Caso o item não
   * possa ser utilizado, a mensagem da exceção é exibida.</p>
   *
   * @param treinador treinador que terá o HP verificado
   */
  private void curarSeNecessario(Treinador treinador) {
    double metade = treinador
        .getPokesal()
        .getHpMax() / 2;
    if (treinador
        .getPokesal()
        .getHp() > 0 && treinador
        .getPokesal()
        .getHp() <= metade) {
      try {
        treinador.usarItem(0);
        System.out.println(treinador.getNome() + " usou uma Potion em " + treinador
            .getPokesal()
            .getNome());
        System.out.printf("HP de %s agora: %.2f%n", treinador
            .getPokesal()
            .getNome(), treinador
            .getPokesal()
            .getHp());
      } catch (IllegalStateException e) {
        System.out.println(e.getMessage());
      }
    }
  }
}