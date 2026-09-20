package pokesal;

import pokesal.enums.TipoElemental;

public class SquirtSal extends Pokesal{
    public SquirtSal() {
        setNome("SquirtSal");
        setHp(44);
        setAtk(10);
        setDef(4);
        setSpd(43);
        setTipo(TipoElemental.AGUA);
        setEnvenenado(false);
    }
}
