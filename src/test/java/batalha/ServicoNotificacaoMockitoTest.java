package batalha;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServicoNotificacaoMockitoTest {

    @Mock
    private NotificadorBatalha notificador;

    @InjectMocks
    private ServicoNotificacao servico;

    @Test
    void testResultadoEncaminhadoAoNotificador() {

        ResultadoBatalha resultado = new ResultadoBatalha(
            "Eduardo",
            "Miguel",
            5,
            "CharSal",
            "CharSal"
        );

        servico.notificar(resultado);

        ArgumentCaptor<ResultadoBatalha> captor =
            ArgumentCaptor.forClass(ResultadoBatalha.class);

        verify(notificador).notificarResultado(captor.capture());

        assertSame(resultado, captor.getValue());

        verify(notificador, atLeastOnce())
            .notificarResultado(any(ResultadoBatalha.class));
    }

    @Test
    void testFalhaNoNotificadorPropagaExcecao() {

        ResultadoBatalha resultado = new ResultadoBatalha(
            "Eduardo",
            "Miguel",
            5,
            "CharSal",
            "CharSal"
        );

        doThrow(new IllegalStateException("Falha na notificação"))
            .when(notificador)
            .notificarResultado(eq(resultado));

        assertThrows(
            IllegalStateException.class,
            () -> servico.notificar(resultado)
        );

        verify(notificador).notificarResultado(resultado);
    }

    @Test
    void testResultadoNuloLancaExcecaoESemNotificar() {

        assertThrows(
            IllegalArgumentException.class,
            () -> servico.notificar(null)
        );

        verifyNoInteractions(notificador);
    }
}
