package pokesal;

import pokesal.enums.TipoElemental;

public class BulbaSal extends Pokesal{
    public BulbaSal() {
        setNome("BulbaSal");
        setHp(45);
        setAtk(49);
        setDef(49);
        setSpd(45);
        setTipo(TipoElemental.PLANTA);
    }
}
