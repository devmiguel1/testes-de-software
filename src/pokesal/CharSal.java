package pokesal;

import pokesal.enums.TipoElemental;

public class CharSal extends Pokesal{
    public CharSal() {
        setNome("CharSal");
        setHp(39);
        setAtk(52);
        setDef(43);
        setSpd(65);
        setTipo(TipoElemental.FOGO);
    }
}
