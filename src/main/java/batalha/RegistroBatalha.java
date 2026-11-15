package batalha;

import pokesal.Pokesal;
import treinador.Treinador;

/**
 * Registra os eventos de um turno de batalha.
 *
 * <p>Os eventos são registrados na ordem em que acontecem no turno:
 * ataque, dano, efeito de status, efeito de terreno e uso de item.</p>
 */
public interface RegistroBatalha {

  /**
   * Registra o ataque de um treinador contra outro.
   *
   * @param atacante treinador que ataca
   * @param defensor treinador que recebe o ataque
   */
  void registrarAtaque(Treinador atacante, Treinador defensor);

  /**
   * Registra o dano causado pelo ataque.
   *
   * @param defensor Pokésal que recebeu o dano
   * @param dano     quantidade de HP perdida
   */
  void registrarDano(Pokesal defensor, double dano);

  /**
   * Registra um efeito de status, como o envenenamento.
   *
   * @param pokesal Pokésal afetado
   * @param efeito  descrição do efeito
   */
  void registrarEfeitoStatus(Pokesal pokesal, String efeito);

  /**
   * Registra um efeito causado pelo terreno.
   *
   * @param pokesal Pokésal afetado
   * @param efeito  descrição do efeito
   */
  void registrarEfeitoTerreno(Pokesal pokesal, String efeito);

  /**
   * Registra o uso de um item.
   *
   * @param treinador treinador que usou o item
   * @param item      nome do item usado
   */
  void registrarUsoItem(Treinador treinador, String item);
}
