package treinador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pokesal.CharSal;
import pokesal.Pokesal;
import treinador.Treinador;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TreinadorTest {

  private Pokesal pokesal;
  private Treinador treinador;

  @BeforeEach
  void setUp() {
    treinador = new Treinador();
    pokesal = new CharSal();
    treinador.setPokesal(pokesal);
  }

  @ParameterizedTest
  @ValueSource(ints = {5, 0, -3})
  @DisplayName("Numeros invalidos não podem mudar os atributos")
  void numerosInvalidos(int num){

    treinador.setPokesal(pokesal);

    double criticoIncial = pokesal.getCritico();
    double desvioInicial = pokesal.getDesvio();
    double defesaInicial = pokesal.getDef();

    treinador.escolhaDePerk(num);

    assertEquals(criticoIncial, pokesal.getCritico());
    assertEquals(desvioInicial, pokesal.getDesvio());
    assertEquals(defesaInicial, pokesal.getDef());
  }

  @Test
  @DisplayName("Teste do limite de hp")
  void testCalculoDanoBoundaryValues(){

    pokesal.setHp(100);
    pokesal.setDef(100);
    pokesal.setAtk(100);

    assertEquals(pokesal.getHp(), pokesal.getHpMax());
    assertEquals(pokesal.getDef(), pokesal.getDefMax());
    assertEquals(pokesal.getAtk(), pokesal.getAtkMax());
  }


  @Test
  @DisplayName("Perk 1 deve aumentar o crítico em 10")
  void testeCritico() {
    double criticoInicial = pokesal.getCritico();

    treinador.escolhaDePerk(1);

    assertEquals(criticoInicial + 10, pokesal.getCritico());
  }

  @Test
  @DisplayName("Perk 2 deve aumentar o desvio em 5")
  void testeDesvio() {
    double desvioInicial = pokesal.getDesvio();

    treinador.escolhaDePerk(2);

    assertEquals(desvioInicial + 5, pokesal.getDesvio());
  }

  @Test
  @DisplayName("Perk 3 deve aumentar a defesa em 5")
  void testeDefesa() {
    double defesaInicial = pokesal.getDef();

    treinador.escolhaDePerk(3);

    assertEquals(defesaInicial + 5, pokesal.getDef());
  }
}