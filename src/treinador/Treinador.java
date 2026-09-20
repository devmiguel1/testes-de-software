package treinador;

import itens.Item;
import pokesal.Pokesal;

import java.util.Random;

public class Treinador {
    private String nome;
    private Pokesal pokesal;
    private Item[] itens = new Item[2];
    private int itensUsados = 0;

    private void perkCritico() {
        pokesal.setCritico(pokesal.getCritico() + 10);
    }


    private void perkDesvio() {
        pokesal.setDesvio(pokesal.getDesvio() + 5);
    }


    private void perkDefesa() {
        pokesal.setDef(pokesal.getDef() + 5);
    }

    public void escolhaDePerk(int i) {
        switch (i) {
            case 1:
                perkCritico();
                break;
            case 2:
                perkDesvio();
                break;
            case 3:
                perkDefesa();
                break;
        }
    }

    public void ataquePokesal(Pokesal inimigo, double bonus) {
        Random random = new Random();
        if (random.nextInt(1, 100) <= (inimigo.getDesvio() + pokesal.getErro())) {
            System.out.println("errou o golpe");
            return;
        }

        double dano = Math.max(1, pokesal.getAtk() * bonus) - inimigo.getDef();

        if (random.nextInt(1, 100) <= pokesal.getCritico()) {
            System.out.println("Acertou um ataque critico");
            dano = dano * 2;
        }
        inimigo.setHp(inimigo.getHp() - dano);
    }

    public void listarItens() {
        for (int i = 0; i < itens.length; i++) {
            if (itens[i] != null) {
                System.out.println("[" + (i + 1) + "] " + itens[i].getNome());
            }
        }
    }

    public void usarItem(int indice) {
        if (itensUsados >= 2) {
            throw new IllegalStateException(nome + " ja usou o limite de itens nesta batalha");
        }
        itens[indice].usar(pokesal);
        itensUsados++;
    }

    public Item[] getItens() {
        return itens;
    }

    public Item getItem(int n) {
        return itens[n];
    }

    public void setItens(Item[] itens) {
        this.itens = itens;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pokesal getPokesal() {
        return pokesal;
    }

    public void setPokesal(Pokesal pokesal) {
        this.pokesal = pokesal;
    }
}
