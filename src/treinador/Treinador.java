package treinador;

import itens.Item;
import pokesal.Pokesal;

import java.util.Random;

public class Treinador {
    private String nome;
    private Pokesal pokesal;
    private Item[] itens = new Item[2];
    private void melhoresCriticos(Pokesal pokesal){
        pokesal.setCritico(pokesal.getCritico() + 10);
    };
    private void melhorDesvio(Pokesal pokesal){
        pokesal.setDesvio(pokesal.getDesvio() + 5);
    };
    private void melhorDefesa(Pokesal pokesal){
        pokesal.setDef(pokesal.getDef() + 5);
    };
    public void ataquePokesal(Pokesal inimigo){
        Random random = new Random();
        if( random.nextInt(1, 100) <= ( inimigo.getDesvio() + pokesal.getErro() ) ){
            System.out.println(inimigo.getNome() +" errou o golpe!");
        }else if(random.nextInt() <= pokesal.getCritico()){
            inimigo.setHp(inimigo.getHp() - (pokesal.getAtk() * 2));
        }else {
            inimigo.setHp(inimigo.getHp() - pokesal.getAtk());
        }
    }
    public Pokesal getPokesal(){
        return this.pokesal;
    }

}
