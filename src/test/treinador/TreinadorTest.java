package treinador;
import org.junit.jupiter.api.BeforeEach;
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

  @org.junit.jupiter.api.Test
  void escolhaDePerk() {
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

}