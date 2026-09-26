package pokesal;

import pokesal.enums.TipoElemental;

/**
 * Representa a classe base dos Pokesais utilizados no sistema.
 *
 * <p>Armazena as características básicas de um Pokesal, como HP,
 * ataque, defesa, velocidade e tipo elemental, além de controlar
 * o estado de envenenamento.</p>
 */
public abstract class Pokesal {
  private String nome;
  private double hp;
  private double hpMax;
  private double atk;
  private double def;
  private double spd;
  private double erro = 5;
  private double desvio = 5;
  private double critico = 5;
  private TipoElemental tipo;
  private boolean envenenado;

  /**
   * Realiza um ataque básico contra outro Pokesal.
   *
   * <p>O dano causado é igual ao valor de ataque do Pokesal
   * que está realizando o ataque.</p>
   *
   * @param poke Pokesal que receberá o ataque
   */
  public void atacar(Pokesal poke) {
    poke.setHp(poke.getHp() - atk);
  }

  public boolean isEnvenenado() {
    return envenenado;
  }

  public void setEnvenenado(boolean envenenado) {
    this.envenenado = envenenado;
  }

  public TipoElemental getTipo() {
    return tipo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public void setTipo(TipoElemental tipo) {
    this.tipo = tipo;
  }

  public double getSpd() {
    return spd;
  }

  public void setSpd(double spd) {
    this.spd = spd;
  }

  public double getDef() {
    return def;
  }

  public void setDef(double def) {
    this.def = def;
  }

  public double getAtk() {
    return atk;
  }

  public void setAtk(double atk) {
    this.atk = atk;
  }

  public double getHp() {
    return hp;
  }

  /**
   * Define o HP atual do Pokesal.
   *
   * <p>O valor do HP não pode ser menor que zero nem maior que
   * o HP máximo. Caso o valor informado ultrapasse esses limites,
   * ele é ajustado automaticamente.</p>
   *
   * @param hp novo valor do HP
   */
  public void setHp(double hp) {
    if (hp < 0) {
      hp = 0;
    } else if (hp > hpMax) {
      hp = hpMax;
    }
    this.hp = hp;
  }

  public double getHpMax() {
    return hpMax;
  }

  /**
   * Define o HP máximo do Pokesal.
   *
   * <p>Ao definir o HP máximo, o HP atual também é definido com
   * o mesmo valor.</p>
   *
   * @param hpMax novo valor do HP máximo
   */
  protected void setHpMax(double hpMax) {
    this.hpMax = hpMax;
    this.hp = hpMax;
  }

  public double getDesvio() {
    return desvio;
  }

  public void setDesvio(double desvio) {
    this.desvio = desvio;
  }

  public double getErro() {
    return erro;
  }

  public void setErro(double erro) {
    this.erro = erro;
  }

  public double getCritico() {
    return critico;
  }

  public void setCritico(double critico) {
    this.critico = critico;
  }
}