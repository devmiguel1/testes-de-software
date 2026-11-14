package batalha;

import java.util.ArrayList;
import java.util.List;
import pokesal.enums.TipoElemental;

public class RegistroBatalha {
  private String ataque;
  private double dano;
  private boolean efeitoDeStatus;
  private TipoElemental terreno;
  private boolean itemUsado;

  public RegistroBatalha(String ataque, double dano, boolean efeitoDeStatus, TipoElemental terreno, boolean itemUsado){
    this.ataque = ataque;
    this.dano = dano;
    this.efeitoDeStatus = efeitoDeStatus;
    this.terreno = terreno;
    this.itemUsado = itemUsado;
  }

  public String getAtaque() {
    return ataque;
  }

  public double getDano() {
    return dano;
  }

  public boolean isEfeitoDeStatus() {
    return efeitoDeStatus;
  }

  public TipoElemental getTerreno() {
    return terreno;
  }
}
