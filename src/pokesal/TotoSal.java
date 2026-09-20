package pokesal;

import pokesal.enums.TipoElemental;

public class TotoSal extends Pokesal{
    public TotoSal() {
        setHp(50);
        setAtk(65);
        setDef(64);
        setSpd(43);
        setTipo(TipoElemental.AGUA);
    }
}
