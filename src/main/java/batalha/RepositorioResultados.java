package batalha;

import java.util.ArrayList;
import java.util.List;

public class RepositorioResultados {
  public List<ResultadoBatalha> batalhas = new ArrayList<ResultadoBatalha>();

  public void salvarResultado(ResultadoBatalha resultado) {
    batalhas.add(resultado);
  }
}
