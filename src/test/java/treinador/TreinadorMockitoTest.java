package treinador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pokesal.CharSal;
import pokesal.Pokesal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TreinadorMockitoTest {

    @Mock
    private GeradorAleatorioInterface gerador;

    private Treinador treinador;
    private Pokesal inimigo;

    @BeforeEach
    void setUp() {
        treinador = new Treinador(
                gerador,
                "Eduardo",
                new CharSal()
        );

        inimigo = new CharSal();
    }

    @Test
    void testAcertoCriticoComGeradorMockado() {

        when(gerador.gerarAletorio())
                .thenReturn(0.0, 0.0);

        treinador.ataquePokesal(inimigo, 1.0);

        // Dano normal: 17 - 6 = 11
        // Dano crítico: 11 * 1.5 = 16.5

        assertEquals(22.5, inimigo.getHp(), 0.001);

        verify(gerador, times(2)).gerarAletorio();
    }

    @Test
    void testGolpeErraQuandoPrecisaoFalha() {

        double hpInicial = inimigo.getHp();

        treinador.getPokesal().getGolpe().setPrecisao(0);

        when(gerador.gerarAletorio())
                .thenReturn(0.99);

        treinador.ataquePokesal(inimigo, 1.0);

        assertEquals(hpInicial, inimigo.getHp(), 0.001);

        verify(gerador, times(1)).gerarAletorio();
    }
}
