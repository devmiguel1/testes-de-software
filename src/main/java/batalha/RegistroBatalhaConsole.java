package batalha;

import pokesal.Pokesal;
import treinador.Treinador;

/**
 * Registro de batalha que escreve os eventos no console.
 */
public class RegistroBatalhaConsole implements RegistroBatalha {

  @Override
  public void registrarAtaque(Treinador atacante, Treinador defensor) {
    System.out.println(atacante.getPokesal().getNome() + " atacou "
        + defensor.getPokesal().getNome());
  }

  @Override
  public void registrarDano(Pokesal defensor, double dano) {
    System.out.printf("dano: %.2f | HP de %s: %.2f%n", dano, defensor.getNome(), defensor.getHp());
  }

  @Override
  public void registrarEfeitoStatus(Pokesal pokesal, String efeito) {
    System.out.println(pokesal.getNome() + " sofreu " + efeito);
  }

  @Override
  public void registrarEfeitoTerreno(Pokesal pokesal, String efeito) {
    System.out.println(pokesal.getNome() + ": " + efeito);
  }

  @Override
  public void registrarUsoItem(Treinador treinador, String item) {
    System.out.println(treinador.getNome() + " usou " + item + " em "
        + treinador.getPokesal().getNome());
  }
}
