package batalha;

import pokesal.Pokesal;
import treinador.Treinador;

/**
 * Representa e gerencia uma batalha entre dois treinadores.
 *
 * <p>Controla a ordem dos turnos, os ataques, os efeitos de status
 * e de terreno, o uso de itens e a verificação das condições
 * de vitória e derrota.</p>
 */
public class Batalha {

  /** Fração do HP máximo perdida a cada turno por um Pokésal envenenado. */
  private static final double DANO_VENENO = 1.0 / 16;

  private final ServicoRecompensa servicoRecompensa;
  private final ServicoNotificacao servicoNotificacao;
  private final RegistroBatalha registro;

  /**
   * Cria uma batalha com os serviços de recompensa, notificação e registro.
   *
   * @param servicoRecompensa  serviço que concede pontos ao vencedor
   * @param servicoNotificacao serviço que notifica o resultado da batalha
   * @param registro           registro dos eventos de cada turno
   */
  public Batalha(ServicoRecompensa servicoRecompensa,
      ServicoNotificacao servicoNotificacao, RegistroBatalha registro) {
    this.servicoRecompensa = servicoRecompensa;
    this.servicoNotificacao = servicoNotificacao;
    this.registro = registro;
  }

  /**
   * Inicia uma batalha entre dois treinadores.
   *
   * <p>A ordem dos ataques é determinada pela velocidade dos
   * Pokésais de cada treinador. A batalha continua enquanto
   * nenhum dos dois treinadores for derrotado. Ao final, o vencedor
   * recebe a recompensa e o resultado é notificado.</p>
   *
   * @param t1      treinador que participa da batalha
   * @param t2      segundo treinador que participa da batalha
   * @param terreno terreno utilizado durante a batalha
   * @return resultado com vencedor, perdedor, turnos e Pokésal utilizados
   */
  public ResultadoBatalha iniciarBatalha(Treinador t1, Treinador t2, Terreno terreno) {
    int turnos = 0;
    boolean continua = true;

    while (continua) {
      turnos += 2;
      if (t1.getPokesal().getSpd() < t2.getPokesal().getSpd()) {
        continua = executarTurno(t2, t1, terreno);
      } else {
        continua = executarTurno(t1, t2, terreno);
      }
    }

    Treinador vencedor = t1.getPokesal().getHp() > 0 ? t1 : t2;
    Treinador perdedor = vencedor == t1 ? t2 : t1;
    System.out.println(vencedor.getNome() + " ganhou a batalha!");

    int pontos = servicoRecompensa.concederRecompensa(vencedor);
    System.out.println(vencedor.getNome() + " recebeu " + pontos + " pontos de recompensa!");

    ResultadoBatalha resultado = new ResultadoBatalha(
        vencedor.getNome(),
        perdedor.getNome(),
        turnos,
        vencedor.getPokesal().getNome(),
        perdedor.getPokesal().getNome());

    servicoNotificacao.notificar(resultado);

    return resultado;
  }

  /**
   * Executa um turno completo: o primeiro treinador age e, se o
   * adversário continuar de pé, o segundo treinador age.
   *
   * @param t1      treinador que age primeiro
   * @param t2      treinador que age em seguida
   * @param terreno terreno utilizado durante a batalha
   * @return {@code false} quando um dos treinadores é derrotado,
   *     ou {@code true} quando a batalha deve continuar
   */
  public boolean executarTurno(Treinador t1, Treinador t2, Terreno terreno) {
    turnoDoTreinador(t1, t2, terreno);
    if (t2.getPokesal().getHp() <= 0) {
      return false;
    }

    turnoDoTreinador(t2, t1, terreno);
    return t1.getPokesal().getHp() > 0;
  }

  /**
   * Executa a vez de um treinador, na ordem exigida pela regra 2.4:
   * ataque, dano, efeito de status, efeito de terreno e uso de item.
   *
   * @param atacante treinador que realiza o ataque
   * @param defensor treinador que recebe o ataque
   * @param terreno  terreno utilizado durante a batalha
   */
  private void turnoDoTreinador(Treinador atacante, Treinador defensor, Terreno terreno) {
    Pokesal alvo = defensor.getPokesal();

    // 1. Ataque
    double bonus = terreno.bonus(atacante, defensor);
    double hpAntes = alvo.getHp();
    registro.registrarAtaque(atacante, defensor);
    atacante.ataquePokesal(alvo, bonus);

    // 2. Dano
    registro.registrarDano(alvo, hpAntes - alvo.getHp());

    // 3. Efeito de status
    if (alvo.isEnvenenado() && alvo.getHp() > 0) {
      alvo.setHp(alvo.getHp() - alvo.getHpMax() * DANO_VENENO);
      registro.registrarEfeitoStatus(alvo, "dano de veneno");
    }

    // 4. Efeito de terreno
    Pokesal proprio = atacante.getPokesal();
    double hpAntesTerreno = proprio.getHp();
    terreno.efeitoFimDeTurno(proprio);
    if (proprio.getHp() != hpAntesTerreno) {
      registro.registrarEfeitoTerreno(proprio, "recuperou HP no terreno");
    }

    // 5. Uso de item
    curarSeNecessario(atacante);
  }

  /**
   * Usa o primeiro item do treinador quando o HP do Pokésal está
   * entre zero e a metade do HP máximo.
   *
   * @param treinador treinador que terá o HP verificado
   */
  private void curarSeNecessario(Treinador treinador) {
    Pokesal pokesal = treinador.getPokesal();
    boolean precisaCurar = pokesal.getHp() > 0 && pokesal.getHp() <= pokesal.getHpMax() / 2;
    if (!precisaCurar || treinador.getItem(0) == null) {
      return;
    }
    try {
      treinador.usarItem(0);
      registro.registrarUsoItem(treinador, treinador.getItem(0).getNome());
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
    }
  }
}
