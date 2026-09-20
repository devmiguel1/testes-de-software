package treinador;

import itens.Item;
import pokesal.Pokesal;

import java.util.Random;

public class Treinador {
    private String nome;
    private Pokesal pokesal;
    private Item[] itens = new Item[2];

    private void melhoresCriticos() {
        pokesal.setCritico(pokesal.getCritico() + 10);
    }

    ;

    private void melhorDesvio() {
        pokesal.setDesvio(pokesal.getDesvio() + 5);
    }

    ;

    private void melhorDefesa() {
        pokesal.setDef(pokesal.getDef() + 5);
    }

    ;

    public void ataquePokesal(Pokesal inimigo, Double bonus) {
        Random random = new Random();
        if (random.nextInt(1, 100) <= (inimigo.getDesvio() + pokesal.getErro())) {
            System.out.println("errou o golpe");
        } else if (random.nextInt() <= pokesal.getCritico()) {
            inimigo.setHp(inimigo.getHp() - (pokesal.getAtk() * 2 + bonus));
        } else {
            inimigo.setHp(inimigo.getHp() - pokesal.getAtk() + bonus);
        }
    }

    public void listarItens() {
        System.out.println("[1] " +itens[0] +" [2] "+ itens[1]);
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
