package itens;

import pokesal.Pokesal;

public class Antidote implements Item {

    private boolean efeito = false;

    public void usar(Pokesal p) {

        if (!efeito && p.isEnvenenado()) {
            p.setEnvenenado(false);
            efeito = true;
        }
    }
}