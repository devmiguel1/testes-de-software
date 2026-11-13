package application;

import batalha.Batalha;
import batalha.RepositorioResultados;
import batalha.Terreno;
import batalha.enums.TipoTerreno;
import itens.Item;
import itens.Potion;
import java.util.ArrayList;
import java.util.List;
import pokesal.CharSal;
import pokesal.SquirtSal;
import treinador.GeradorAleatorio;
import treinador.Treinador;

/**
 * Classe principal responsável por iniciar a execução do sistema.
 */
public class Main {

  /**
   * Inicia a execução da aplicação e configura os treinadores,
   * seus Pokesal, perks, itens e o terreno da batalha.
   *
   * @param args argumentos recebidos pela linha de comando
   */
  public static void main(String[] args) {

    List<RepositorioResultados> batalhas = new ArrayList<RepositorioResultados>();

    GeradorAleatorio gerador = new GeradorAleatorio();
    Treinador t1 = new Treinador(gerador, "Ronaldo", new CharSal(),
        new Item[] {new Potion(10), new Potion(10)});
    t1.escolhaDePerk(1);

    Treinador t2 = new Treinador(gerador, "Rival", new SquirtSal(),
        new Item[] {new Potion(10), new Potion(10)});
    t2.escolhaDePerk(2);

    Terreno terreno = new Terreno();
    terreno.setTerreno(TipoTerreno.ASFALTO_QUENTE);

    batalhas.add(new Batalha().iniciarBatalha(t1, t2, terreno));
    batalhas.get(0).getRegistro();
  }
}