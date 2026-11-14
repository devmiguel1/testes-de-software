package batalha;

import java.util.ArrayList;
import java.util.List;

public class RegistroList {
  private List<RepositorioResultados> batalhas = new ArrayList<RepositorioResultados>();


  public void salvarResultado(RepositorioResultados resultado) {
    batalhas.add(resultado);
  }

  public List<RepositorioResultados> getBatalhas(){
    return batalhas;
  }

}
