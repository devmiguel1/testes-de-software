package pokesal;

import pokesal.enums.TipoElemental;

public class CyndaSal extends Pokesal{
    public CyndaSal() {
        setNome("CyndaSal");
        setHp(39);
        setAtk(52);
        setDef(43);
        setSpd(65);
        setTipo(TipoElemental.FOGO);
    }
}
