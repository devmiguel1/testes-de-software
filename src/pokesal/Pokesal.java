package pokesal;

import pokesal.enums.TipoElemental;

public abstract class Pokesal {
    private double hp;
    private double atk;
    private double def;
    private double spd;
    private TipoElemental tipo;
    private boolean envenenado;

    public void atacar(Pokesal poke){
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
        this.hp = hp;
    }

}
