
package batalha;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import treinador.Treinador;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TorneioMockitoTest {

    @Mock
    private Batalha batalha;

    @Mock
    private RepositorioResultados repositorio;

    @Mock
    private Terreno terreno;

    @Mock
    private Treinador t1;

    @Mock
    private Treinador t2;

    @Mock
    private Treinador t3;

    @Mock
    private Treinador t4;

    @InjectMocks
    private Torneio torneio;

    private List<Treinador> participantes;

    @BeforeEach
    void setUp() {
        participantes = Arrays.asList(t1, t2, t3, t4);
    }

    private ResultadoBatalha resultado(
            String vencedor,
            String perdedor,
            int turnos
    ) {
        return new ResultadoBatalha(
                vencedor,
                perdedor,
                turnos,
                "CharSal",
                "CharSal"
        );
    }

    private void configurarBatalhas() {

        when(t1.getNome()).thenReturn("Eduardo");
        when(t3.getNome()).thenReturn("Lucas");

        when(batalha.iniciarBatalha(eq(t1), eq(t2), any(Terreno.class)))
                .thenReturn(resultado("Eduardo", "Miguel", 4));

        when(batalha.iniciarBatalha(eq(t3), eq(t4), any(Terreno.class)))
                .thenReturn(resultado("Lucas", "Pedro", 6));

        when(batalha.iniciarBatalha(eq(t1), eq(t3), any(Terreno.class)))
                .thenReturn(resultado("Eduardo", "Lucas", 8));
    }

    @Test
    void testResultadoSalvoAoFimDaBatalha() {

        configurarBatalhas();

        torneio.executar(participantes);

        ArgumentCaptor<ResultadoBatalha> captor =
                ArgumentCaptor.forClass(ResultadoBatalha.class);

        verify(repositorio, times(3))
                .salvarResultado(captor.capture());

        List<ResultadoBatalha> resultados =
                captor.getAllValues();

        ResultadoBatalha primeiro = resultados.get(0);

        assertEquals("Eduardo", primeiro.getVencedor());
        assertEquals("Miguel", primeiro.getPerdedor());
        assertEquals(4, primeiro.getTurnos());
    }

    @Test
    void testFalhaNoRepositorioNaoInvalidaBatalha() {

        configurarBatalhas();

        doThrow(new IllegalStateException("Erro no banco"))
                .when(repositorio)
                .salvarResultado(any(ResultadoBatalha.class));

        Treinador campeao = torneio.executar(participantes);

        assertSame(t1, campeao);

        verify(repositorio, atLeastOnce())
                .salvarResultado(any(ResultadoBatalha.class));
    }

    @Test
    void testRepositorioNaoChamadoSeBatalhaNaoTerminou() {

        when(batalha.iniciarBatalha(
                eq(t1),
                eq(t2),
                any(Terreno.class)
        )).thenThrow(new IllegalStateException("Batalha interrompida"));

        assertThrows(
                IllegalStateException.class,
                () -> torneio.executar(participantes)
        );

        verify(repositorio, never())
                .salvarResultado(any(ResultadoBatalha.class));
    }

    @Test
    void testChaveamentoTorneioAvancaVencedores() {

        configurarBatalhas();

        Treinador campeao = torneio.executar(participantes);

        assertSame(t1, campeao);

        verify(batalha).iniciarBatalha(t1, t2, terreno);

        verify(batalha).iniciarBatalha(t3, t4, terreno);

        verify(batalha).iniciarBatalha(t1, t3, terreno);

        verify(batalha, times(3))
                .iniciarBatalha(
                        any(Treinador.class),
                        any(Treinador.class),
                        eq(terreno)
                );
    }
}
