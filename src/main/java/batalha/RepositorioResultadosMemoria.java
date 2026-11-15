package batalha;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Repositório que guarda os resultados das batalhas em memória.
 */
public class RepositorioResultadosMemoria implements RepositorioResultados {
  private final List<ResultadoBatalha> resultados = new ArrayList<>();

  @Override
  public void salvarResultado(ResultadoBatalha resultado) {
    resultados.add(resultado);
  }

  /**
   * Retorna os resultados salvos, sem permitir alteração.
   *
   * @return lista somente leitura dos resultados
   */
  public List<ResultadoBatalha> getResultados() {
    return Collections.unmodifiableList(resultados);
  }
}
