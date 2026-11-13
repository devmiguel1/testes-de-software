package treinador;

public class GeradorAleatorio implements GeradorAleatorioInterface {

  @Override
  public double gerarAletorio() {
    return (double) (Math.random() * 100);
  }
}
