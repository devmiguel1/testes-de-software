package batalha;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import batalha.enums.TipoTerreno;
import itens.Item;
import itens.Potion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pokesal.CharSal;
import pokesal.SquirtSal;
import treinador.Treinador;

public class BatalhaTest {
  private Treinador t1;
  private Treinador t2;
  private Terreno terreno;

  @BeforeEach
  public void configurarCenario() {
    t1 = new Treinador();
    t1.setNome("Ronaldo");
    t1.setPokesal(new CharSal());


    t2 = new Treinador();
    t2.setNome("Rival");
    t2.setPokesal(new SquirtSal());

    terreno = new Terreno();

    t1
        .getPokesal()
        .setErro(0);
    t1
        .getPokesal()
        .setDesvio(0);

    t1
        .getPokesal()
        .setCritico(0);

    t2
        .getPokesal()
        .setErro(0);
    t2
        .getPokesal()
        .setDesvio(0);

    t2
        .getPokesal()
        .setCritico(0);
  }

  @Test
  @DisplayName("Teste sobre a vantangem elemental")
  public void testVantagemElemental() {
    double bonus = terreno.bonus(t1, t2);
    t1.ataquePokesal(t2.getPokesal(), bonus);

    double vidaEsperada = 39.5;
    double vidaAtual = t2
        .getPokesal()
        .getHp();

    assertEquals(vidaEsperada, vidaAtual,
        "O dano deveria ser metade devido à desvantagem do tipo Fogo contra Agua.");
  }

  @Test
  @DisplayName("Teste sobre a vantangem de terreno")
  public void testEfeitoTerrenoEstacionamentoUCSal() {
    terreno.setTerreno(TipoTerreno.ASFALTO_QUENTE);

    double bonus = terreno.bonus(t1, t2);
    t1.ataquePokesal(t2.getPokesal(), bonus);

    double vidaEsperada = 38.225;
    double vidaAtual = t2
        .getPokesal()
        .getHp();

    assertEquals(vidaEsperada, vidaAtual,
        "O dano deveria ter um multiplicador de 0.575 devido à desvantagem do tipo Fogo contra " +
            "Agua e a vantagem do tipo de terreno.");
  }

  @Test
  @DisplayName("Teste sobre a prioridade de ataque de acordo com a velocidade de ataque dos " +
      "pokesals")
  public void testOrdemDeAtaquePorVelocidade() {
    Batalha batalha = new Batalha();
    terreno.setTerreno(TipoTerreno.CANTEIRO_CENTRAL);

    t1
        .getPokesal()
        .setSpd(100);
    t1
        .getPokesal()
        .setAtkMax(1000);

    t1
        .getPokesal()
        .setAtk(1000);

    t2
        .getPokesal()
        .setSpd(10);


    batalha.iniciarBatalha(t1, t2, terreno);

    double vidaT1 = t1
        .getPokesal()
        .getHp();
    double vidaT2 = t2
        .getPokesal()
        .getHp();

    assertEquals(39.0, vidaT1,
        "O T1 era mais rápido, deveria ter atacado primeiro e saído sem sofrer dano.");
    assertTrue(vidaT2 <= 0.0,
        "O T2 era mais lento e deveria ter sido derrotado antes de conseguir atacar.");
  }

  @Test
  @DisplayName("Teste sobre o excesso de itens usados")
  public void testUsoLimiteDeItensExcedido() {
    t1.setItens(new Item[] {new Potion(10), new Potion(10)});
    t1.usarItem(0);
    t1.usarItem(1);

    Exception excecao = assertThrows(IllegalStateException.class, () -> {
      t1.usarItem(0);
    });

    assertEquals("Ronaldo ja usou o limite de itens nesta batalha", excecao.getMessage());
  }
}
