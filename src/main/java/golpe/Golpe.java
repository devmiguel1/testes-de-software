package golpe;

public class Golpe {
  private final String nome;
  private int precisao;

  public Golpe(String nome, int precisao) {
    if (precisao < 0 || precisao > 100) {
      throw new IllegalArgumentException("Precisão deve estar entre 0 e 100");
    }
    this.nome = nome;
    this.precisao = precisao;
  }

  public String getNome() {
    return nome;
  }

  public int getPrecisao() {
    return precisao;
  }

  public void setPrecisao(int precisao) {
    if (precisao < 0 || precisao > 100) {
      throw new IllegalArgumentException("Precisão deve estar entre 0 e 100");
    }
    this.precisao = precisao;
  }
}
