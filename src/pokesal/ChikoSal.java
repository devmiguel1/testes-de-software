package pokesal;

import pokesal.enums.TipoElemental;

public class ChikoSal extends Pokesal{
    public ChikoSal() {
        setHp(45);
        setAtk(49);
        setDef(65);
        setSpd(45);
        setTipo(TipoElemental.PLANTA);
    }
}
