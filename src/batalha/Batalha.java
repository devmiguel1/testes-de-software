package batalha;

public class Batalha {
    public Terreno terreno;
    public Treinador treinador1;
    public Treinador treinador2;


    public void iniciarBatalha(Treinador t1, Treinador t2){
        boolean vida = true;
        while (vida){
            if (t1.Pokesal.spd < t2.Pokesal.spd) {
                executarTurno(t2, t1);
            }
            else{

            }
        }


    }

    public void executarTurno(Treinador t1, Treinador t2){

    }
}
