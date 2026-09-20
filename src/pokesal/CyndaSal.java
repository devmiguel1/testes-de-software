package pokesal;

import pokesal.enums.TipoElemental;

public class CyndaSal extends Pokesal{
    public CyndaSal() {
        setNome("CyndaSal");
        setHp(39);
        setAtk(15);
        setDef(5);
        setSpd(65);
        setTipo(TipoElemental.FOGO);
    }
}
