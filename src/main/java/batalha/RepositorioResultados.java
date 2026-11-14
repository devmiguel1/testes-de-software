package batalha;

import java.util.ArrayList;
import java.util.List;

public class RepositorioResultados {
  private String vencedor;
  private String perdedor;
  private List<RegistroBatalha> turnos;
  private String pokesalVencedor;
  private String pokesalPerdedor;
  private static String erro;

  public RepositorioResultados(String vencedor, String perdedor, List<RegistroBatalha> turnos, String pokesalVencedor,
                          String pokesalPerdedor) {
    if (vencedor == null || perdedor == null || pokesalVencedor == null ||
        pokesalPerdedor == null) {
      erro = "Houve um erro no registro da batlha entre " + vencedor + " e " + perdedor;
    } else {
      this.vencedor = vencedor;
      this.perdedor = perdedor;
      this.turnos = turnos;
      this.pokesalVencedor = pokesalVencedor;
      this.pokesalPerdedor = pokesalPerdedor;
    }
  }

  public static String getErro() {
    return erro;
  }

  public String getVencedor() {
    return vencedor;
  }

  public String getPerdedor() {
    return perdedor;
  }

  public List<RegistroBatalha> getTurnos() {
    return turnos;
  }

  public String getPokesalVencedor() {
    return pokesalVencedor;
  }

  public String getPokesalPerdedor() {
    return pokesalPerdedor;
  }

  public void getRegistro() {
    System.out.println("Vencedor: " + vencedor);
    System.out.println("Perdedor: " + perdedor);
    System.out.println("Turnos: " + turnos);
    System.out.println("Pokesal do vencedor: " + pokesalVencedor);
    System.out.println("Pokesal do perdedor: " + pokesalPerdedor);
  }
}
