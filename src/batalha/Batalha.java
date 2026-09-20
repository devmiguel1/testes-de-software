package batalha;


import treinador.Treinador;

public class Batalha {
    public Terreno terreno;
    public Treinador treinador1;
    public Treinador treinador2;


    public void iniciarBatalha(Treinador t1, Treinador t2){
        boolean vida = true;
        while (vida){
            if (t1.getPokesal().getSpd() < t2.getPokesal().getSpd()) {
                vida = executarTurno(t2, t1);
            }
            else{
                vida = executarTurno(t1, t2);
            }
        }


    }

    public boolean executarTurno(Treinador t1, Treinador t2){

        System.out.println("Ataque do " +t1.Pokesal.nome);
        t1.ataque(t2);

        if (t2.Pokesal.hp <= 0){
            System.out.println(t2.Pokesal.nome +"morreu...");
            System.out.println(t1.Pokesal.nome +"ganhou a batalha...");
            return false;
        }

        t2.ataque(t1);
        System.out.println("Ataque do " +t2.Pokesal.nome);

        if (t1.Pokesal.hp <= 0){
            System.out.println(t1.Pokesal.nome +"morreu...");
            System.out.println(t2.Pokesal.nome +"ganhou a batalha...");
            return false;
        }
    }


}
