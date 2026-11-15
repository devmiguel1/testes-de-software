package batalha;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import itens.Item;
import pokesal.Pokesal;
import treinador.Treinador;

import static org.junit.jupiter.api.Assertions.*;
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

    @Mock
    private Item item;

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
            .thenReturn(10.0);

        when(pokeAtacante.getHpMax())
            .thenReturn(100.0);

        when(pokeDefensor.getHp())
            .thenReturn(100.0);

        when(pokeDefensor.isEnvenenado())
            .thenReturn(false);

        when(atacante.getItem(0))
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

        ordem.verify(atacante).getItem(0);

    }

    @Test
    void testVenenoRegistraEfeitoDeStatus() {

        when(pokeDefensor.isEnvenenado())
            .thenReturn(true);

        batalha.executarTurno(
            atacante,
            defensor,
            terreno
        );

        verify(registro)
            .registrarEfeitoStatus(pokeDefensor, "dano de veneno");
    }

    @Test
    void testVenenoNaoAfetaPokesalDerrotado() {

        when(pokeDefensor.isEnvenenado())
            .thenReturn(true);

        when(pokeDefensor.getHp())
            .thenReturn(0.0);

        boolean continua = batalha.executarTurno(
            atacante,
            defensor,
            terreno
        );

        assertFalse(continua);

        verify(registro, never())
            .registrarEfeitoStatus(any(Pokesal.class), anyString());
    }

    @Test
    void testEfeitoDeTerrenoQueMudaHpEhRegistrado() {

        when(pokeAtacante.getHp())
            .thenReturn(10.0, 20.0, 10.0);

        batalha.executarTurno(
            atacante,
            defensor,
            terreno
        );

        verify(registro)
            .registrarEfeitoTerreno(pokeAtacante, "recuperou HP no terreno");
    }

    @Test
    void testItemUsadoQuandoHpEstaBaixo() {

        when(atacante.getItem(0))
            .thenReturn(item);

        when(item.getNome())
            .thenReturn("Potion");

        batalha.executarTurno(
            atacante,
            defensor,
            terreno
        );

        verify(atacante)
            .usarItem(0);

        verify(registro)
            .registrarUsoItem(atacante, "Potion");
    }
}
