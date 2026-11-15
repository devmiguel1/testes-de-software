package batalha;

/**
 * Armazena os resultados das batalhas do torneio.
 */
public interface RepositorioResultados {

  /**
   * Salva o resultado de uma batalha.
   *
   * @param resultado resultado a ser salvo
   */
  void salvarResultado(ResultadoBatalha resultado);
}
