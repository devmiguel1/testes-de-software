package pokesal;

import pokesal.enums.TipoElemental;

public class ChikoSal extends Pokesal{
    public ChikoSal() {
        setNome("ChikoSal");
        setHp(45);
        setAtk(14);
        setDef(6);
        setSpd(45);
        setTipo(TipoElemental.PLANTA);
    }
}
