package batalha;

public class NotificadorConsole implements NotificadorBatalha {

  @Override
  public void notificarResultado(ResultadoBatalha resultado) {

    if (resultado == null) {
      throw new IllegalArgumentException(
          "O resultado da batalha nao pode ser nulo."
      );
    }

    System.out.println("\n===== NOTIFICACAO DA BATALHA =====");
    System.out.println("Vencedor: " + resultado.getVencedor());
    System.out.println("Perdedor: " + resultado.getPerdedor());
    System.out.println("Turnos: " + resultado.getTurnos());
    System.out.println("Pokesal vencedor: " + resultado.getPokesalVencedor());
    System.out.println("Pokesal perdedor: " + resultado.getPokesalPerdedor());
    System.out.println("==================================");
  }
}