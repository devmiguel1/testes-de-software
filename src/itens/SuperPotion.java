package itens;

import pokesal.Pokesal;

public class SuperPotion implements Item {

    private int qtdCura;
    private String nome = "SuperPotion";

    public String getNome() {
        return nome;
    }


    public SuperPotion(int qtdCura) {
        this.qtdCura = qtdCura;
    }

    public void usar(Pokesal p) {
        p.setHp(p.getHp() + qtdCura);
    }

    public int getQtdCura() {
        return qtdCura;
    }

    public void setQtdCura(int qtdCura) {
        this.qtdCura = qtdCura;
    }
}