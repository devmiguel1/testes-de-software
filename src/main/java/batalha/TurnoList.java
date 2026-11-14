package batalha;

import java.util.ArrayList;
import java.util.List;

public class TurnoList {

  private List<RegistroBatalha> turnos = new ArrayList<>();

  public void salvarTurnos(RegistroBatalha resultado) {
    turnos.add(resultado);
  }

  public List<RegistroBatalha> getBatalhas(){
    return turnos;
  }
}
