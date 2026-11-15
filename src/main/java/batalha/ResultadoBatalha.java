package batalha;

/**
 * Resultado de uma batalha: vencedor, perdedor, número de turnos
 * e Pokésal utilizados.
 */
public class ResultadoBatalha {
  private final String vencedor;
  private final String perdedor;
  private final int turnos;
  private final String pokesalVencedor;
  private final String pokesalPerdedor;

  /**
   * Cria o resultado de uma batalha.
   *
   * @param vencedor        nome do treinador vencedor
   * @param perdedor        nome do treinador perdedor
   * @param turnos          número de turnos da batalha
   * @param pokesalVencedor nome do Pokésal do vencedor
   * @param pokesalPerdedor nome do Pokésal do perdedor
   * @throws IllegalArgumentException se algum nome for nulo
   */
  public ResultadoBatalha(String vencedor, String perdedor, int turnos, String pokesalVencedor,
      String pokesalPerdedor) {
    if (vencedor == null || perdedor == null || pokesalVencedor == null
        || pokesalPerdedor == null) {
      throw new IllegalArgumentException("Resultado de batalha com dados nulos");
    }
    this.vencedor = vencedor;
    this.perdedor = perdedor;
    this.turnos = turnos;
    this.pokesalVencedor = pokesalVencedor;
    this.pokesalPerdedor = pokesalPerdedor;
  }

  public String getVencedor() {
    return vencedor;
  }

  public String getPerdedor() {
    return perdedor;
  }

  public int getTurnos() {
    return turnos;
  }

  public String getPokesalVencedor() {
    return pokesalVencedor;
  }

  public String getPokesalPerdedor() {
    return pokesalPerdedor;
  }

  @Override
  public String toString() {
    return "Vencedor: " + vencedor + " (" + pokesalVencedor + ") | Perdedor: "
        + perdedor + " (" + pokesalPerdedor + ") | Turnos: " + turnos;
  }
}
