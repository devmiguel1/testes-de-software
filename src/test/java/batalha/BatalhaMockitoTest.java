
package batalha;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pokesal.Pokesal;
import treinador.Treinador;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BatalhaMockitoTest {

    @Mock
    private ServicoRecompensa servicoRecompensa;

    @Mock
    private ServicoNotificacao servicoNotificacao;

    @Mock
    private RegistroBatalha registro;

    @Mock
    private Terreno terreno;

    @Mock
    private Treinador atacante;

    @Mock
    private Treinador defensor;

    @Mock
    private Pokesal pokeAtacante;

    @Mock
    private Pokesal pokeDefensor;

    @InjectMocks
    private Batalha batalha;

    @BeforeEach
    void setUp() {

        when(atacante.getPokesal())
                .thenReturn(pokeAtacante);

        when(defensor.getPokesal())
                .thenReturn(pokeDefensor);

        when(terreno.bonus(atacante, defensor))
                .thenReturn(1.0);

        when(pokeAtacante.getHp())
                .thenReturn(100.0);

        when(pokeAtacante.getHpMax())
                .thenReturn(100.0);

        when(pokeDefensor.getHp())
                .thenReturn(100.0);

        when(pokeDefensor.isEnvenenado())
                .thenReturn(false);

        when(atacante.getItem(0))
                .thenReturn(null);

        when(defensor.getItem(0))
                .thenReturn(null);
    }

    @Test
    void testSequenciaDeEventosNoTurno() {

        batalha.executarTurno(
                atacante,
                defensor,
                terreno
        );

        InOrder ordem = inOrder(
                terreno,
                registro,
                atacante
        );

        ordem.verify(terreno)
                .bonus(atacante, defensor);

        ordem.verify(registro)
                .registrarAtaque(atacante, defensor);

        ordem.verify(atacante)
                .ataquePokesal(pokeDefensor, 1.0);

        ordem.verify(registro)
                .registrarDano(pokeDefensor, 0.0);

        ordem.verify(terreno)
                .efeitoFimDeTurno(pokeAtacante);
    }
}
