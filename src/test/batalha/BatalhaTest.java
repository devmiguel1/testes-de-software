package batalha;

import static org.junit.jupiter.api.Assertions.assertEquals;

import batalha.enums.TipoTerreno;
import org.junit.jupiter.api.BeforeEach;
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
  }

  @Test
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
  public void testOrdemDeAtaquePorVelocidade() {
    // 1. Preparação (Arrange): Crie dois Pokesal, um com SPD (velocidade) alta e outro com SPD
    // baixa.
    // 2. Ação (Act): Inicie um turno de batalha.
    // 3. Verificação (Assert): Verifique através do histórico da batalha ou estado final qual
    // Pokesal realizou a ação primeiro.
  }

}
