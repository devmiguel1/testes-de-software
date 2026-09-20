package pokesal;

import pokesal.enums.TipoElemental;

public class CharSal extends Pokesal{
    public CharSal() {
        setNome("CharSal");
        setHp(39);
        setAtk(17);
        setDef(6);
        setSpd(65);
        setTipo(TipoElemental.FOGO);
        setEnvenenado(false);
    }
}
