package pokesal;

import pokesal.enums.TipoElemental;

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

    public void setHp(double hp) {
        if (hpMax == 0) {
            hpMax = hp;
        }
        this.hp = hp;
    }
    public double getHpMax() {
        return hpMax;
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
