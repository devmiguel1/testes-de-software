package pokesal;

import pokesal.enums.TipoElemental;

public class SquirtSal extends Pokesal{
    public SquirtSal() {
        setHp(44);
        setAtk(48);
        setDef(65);
        setSpd(43);
        setTipo(TipoElemental.AGUA);
    }
}
