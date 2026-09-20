package pokesal;

import pokesal.enums.TipoElemental;

public class TotoSal extends Pokesal{
    public TotoSal() {
        setNome("TotoSal");
        setHp(50);
        setAtk(11);
        setDef(5);
        setSpd(43);
        setTipo(TipoElemental.AGUA);
    }
}
