import batalha.Terreno;
import pokesal.CyndaSal;
import pokesal.SquirtSal;
import treinador.Treinador;

public class Main {
    public static void main(String[] args) {
        Treinador t1 = new Treinador();
        t1.setNome("Ash");
        t1.setPokesal(new CyndaSal());

        Treinador t2 = new Treinador();
        t2.setNome("Misty");
        t2.setPokesal(new SquirtSal());

        Terreno terreno = new Terreno() {};

        // quem tem mais velocidade comeca
        Treinador atacante = t1.getPokesal().getSpd() >= t2.getPokesal().getSpd() ? t1 : t2;
        Treinador defensor = atacante == t1 ? t2 : t1;

        while (true) {
            double bonus = terreno.bonus(atacante, defensor);
            double vidaAntes = defensor.getPokesal().getHp();

            System.out.println(atacante.getNome() + " ataca com " + atacante.getPokesal().getNome());
            atacante.ataquePokesal(defensor.getPokesal(), bonus);

            double vidaDepois = defensor.getPokesal().getHp();
            System.out.println("bonus de tipo: " + bonus);
            System.out.println("dano: " + (vidaAntes - vidaDepois));
            System.out.println("vida do " + defensor.getPokesal().getNome() + ": " + Math.max(0, vidaDepois));
            System.out.println();

            if (vidaDepois <= 0) {
                System.out.println(defensor.getPokesal().getNome() + " morreu...");
                System.out.println(atacante.getNome() + " ganhou a batalha!");
                break;
            }

            Treinador troca = atacante;
            atacante = defensor;
            defensor = troca;
        }
    }
}