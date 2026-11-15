
package batalha;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pokesal.CharSal;
import treinador.GeradorAleatorioInterface;
import treinador.Treinador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServicoRecompensaMockitoTest {

    @Mock
    private GeradorRecompensa gerador;

    @Mock
    private GeradorAleatorioInterface geradorTreinador;

    @InjectMocks
    private ServicoRecompensa servico;

    private Treinador vencedor;

    @BeforeEach
    void setUp() {

        vencedor = new Treinador(
                geradorTreinador,
                "Eduardo",
                new CharSal()
        );
    }

    @Test
    void testRecompensaAdicionadaAoVencedor() {

        when(gerador.gerarPontos())
                .thenReturn(75);

        int pontos = servico.concederRecompensa(vencedor);

        assertEquals(75, pontos);
        assertEquals(75, vencedor.getPontos());

        verify(gerador, times(1))
                .gerarPontos();
    }

    @Test
    void testRecompensaNegativaGeraExcecao() {

        when(gerador.gerarPontos())
                .thenReturn(-10);

        assertThrows(
                IllegalStateException.class,
                () -> servico.concederRecompensa(vencedor)
        );

        assertEquals(0, vencedor.getPontos());

        verify(gerador).gerarPontos();
    }
}
