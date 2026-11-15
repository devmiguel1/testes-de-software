package batalha;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import treinador.Treinador;

/**
 * Torneio eliminatório com 4 ou 8 treinadores.
 *
 * <p>Os treinadores se enfrentam em pares e os vencedores avançam
 * para a próxima rodada até sobrar um campeão. O resultado de cada
 * batalha é salvo no repositório; se o repositório falhar, o erro
 * é registrado e o torneio continua.</p>
 */
public class Torneio {
  private static final Logger LOG = Logger.getLogger(Torneio.class.getName());

  private final Batalha batalha;
  private final RepositorioResultados repositorio;
  private final Terreno terreno;

  /**
   * Cria um torneio com os colaboradores necessários.
   *
   * @param batalha     responsável por executar cada batalha
   * @param repositorio onde os resultados são salvos
   * @param terreno     terreno usado nas batalhas
   */
  public Torneio(Batalha batalha, RepositorioResultados repositorio, Terreno terreno) {
    this.batalha = batalha;
    this.repositorio = repositorio;
    this.terreno = terreno;
  }

  /**
   * Executa o torneio até restar um campeão.
   *
   * @param participantes lista com 4 ou 8 treinadores, com nomes diferentes
   * @return treinador campeão
   * @throws IllegalArgumentException se a quantidade não for 4 nem 8
   */
  public Treinador executar(List<Treinador> participantes) {
    if (participantes.size() != 4 && participantes.size() != 8) {
      throw new IllegalArgumentException("O torneio aceita apenas 4 ou 8 treinadores");
    }

    List<Treinador> rodada = new ArrayList<>(participantes);
    while (rodada.size() > 1) {
      List<Treinador> vencedores = new ArrayList<>();
      for (int i = 0; i < rodada.size(); i += 2) {
        Treinador a = rodada.get(i);
        Treinador b = rodada.get(i + 1);

        ResultadoBatalha resultado = batalha.iniciarBatalha(a, b, terreno);
        salvarComSeguranca(resultado);

        vencedores.add(resultado.getVencedor().equals(a.getNome()) ? a : b);
      }
      rodada = vencedores;
    }
    return rodada.get(0);
  }

  private void salvarComSeguranca(ResultadoBatalha resultado) {
    try {
      repositorio.salvarResultado(resultado);
    } catch (RuntimeException e) {
      LOG.warning("Falha ao salvar resultado: " + e.getMessage());
    }
  }
}
