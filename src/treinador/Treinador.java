package treinador;

import pokesal.Pokesal;

import java.util.Random;

public class Treinador {
    private nome String;
    private Pokesal pokesal;
    private Item[] itens;
    private void melhoresCriticos(){
        pokesal.setCritico(pokesal.getCritico() + 10);
    };
    private void melhorDesvio(){
        pokesal.setDesvio(pokesal.getDesvio() + 5);
    };
    private void melhorDefesa(){
        pokesal.setDef(pokesal.getDef() + 5);
    };
    public void ataquePokesal(Pokesal inimigo){
        Random random = new Random();
        if( random.nextInt(1, 100) <= ( inimigo.getDesvio() + pokesal.getErro() ) ){
            System.out.println("errou o golpe");
        }else if(random.nextInt() <= pokesal.getCritico()){
            inimigo.setHp(inimigo.getHp() - (pokesal.getAtk() * 2));
        }else {
            inimigo.setHp(inimigo.getHp() - pokesal.getAtk());
        }
    }
}
