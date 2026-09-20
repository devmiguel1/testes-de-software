import batalha.Batalha;
import batalha.Terreno;
import batalha.enums.TipoTerreno;
import itens.Item;
import itens.Potion;
import pokesal.CharSal;
import pokesal.SquirtSal;
import treinador.Treinador;

public class Main {

    public static void main(String[] args) {
        Treinador t1 = new Treinador();
        t1.setNome("Ronaldo");
        t1.setPokesal(new CharSal());
        t1.escolhaDePerk(1);
        t1.setItens(new Item[]{new Potion(10), new Potion(10)});

        Treinador t2 = new Treinador();
        t2.setNome("Rival");
        t2.setPokesal(new SquirtSal());
        t2.escolhaDePerk(2);
        t2.setItens(new Item[]{new Potion(10), new Potion(10)});

        Terreno terreno = new Terreno();
        terreno.setTerreno(TipoTerreno.ASFALTO_QUENTE);

        new Batalha().iniciarBatalha(t1, t2, terreno);
    }
}