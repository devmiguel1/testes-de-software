package itens;

import pokesal.Pokesal;

public class Antidote implements Item {

    private boolean efeito = false;
    private String nome = "Antidote";

    public String getNome() {
        return nome;
    }


    public void usar(Pokesal p) {

        if (!efeito && p.isEnvenenado()) {
            p.setEnvenenado(false);
            efeito = true;
        }
    }
}