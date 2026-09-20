package itens;

import pokesal.Pokesal;

public class Potion implements Item {

    private int qtdCura;
    private String nome = "Potion";

    public String getNome() {
        return nome;
    }


    public Potion(int qtdCura) {
        this.qtdCura = qtdCura;
    }

    public void usar(Pokesal p) {
        p.setHp(Math.min(p.getHp() + qtdCura, p.getHpMax()));
    }

    public int getQtdCura() {
        return qtdCura;
    }

    public void setQtdCura(int qtdCura) {
        this.qtdCura = qtdCura;
    }
}