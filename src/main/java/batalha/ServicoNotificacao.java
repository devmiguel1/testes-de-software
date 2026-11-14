package batalha;

public class ServicoNotificacao {

  private final NotificadorBatalha notificador;

  public ServicoNotificacao(NotificadorBatalha notificador) {
    this.notificador = notificador;
  }

  public void notificar(RepositorioResultados resultado) {

    if (resultado == null) {
      throw new IllegalArgumentException(
          "O resultado da batalha nao pode ser nulo."
      );
    }

    notificador.notificarResultado(resultado);
  }
}