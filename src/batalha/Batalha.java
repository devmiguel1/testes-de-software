package batalha;


import treinador.Treinador;

import java.util.Scanner;

public class Batalha {

    public void iniciarBatalha(Treinador t1, Treinador t2,Terreno terreno){
        boolean vida = true;
        while (vida){
            if (t1.getPokesal().getSpd() < t2.getPokesal().getSpd()) {
                vida = executarTurno(t2, t1,terreno);
            }
            else{
                vida = executarTurno(t1, t2,terreno);
            }
        }
    }

    public boolean executarTurno(Treinador t1, Treinador t2, Terreno terreno){
        curarSeNecessario(t1);
        ataqueTurno(t1, t2, terreno);
        if (t2.getPokesal().getHp() <= 0){
            System.out.println(t2.getPokesal().getNome() + " nao aguentou mais!");
            System.out.println(t1.getNome() + " ganhou a batalha!");
            return false;
        }

        curarSeNecessario(t2);
        ataqueTurno(t2, t1, terreno);
        if (t1.getPokesal().getHp() <= 0){
            System.out.println(t1.getPokesal().getNome() + " nao aguentou mais!");
            System.out.println(t2.getNome() + " ganhou a batalha!");
            return false;
        }

        return true;
    }

    private void escolhaItem(Treinador t){
        Scanner sc = new Scanner(System.in);
        t.listarItens();
        System.out.println("Ecolha o Item: ");
        int escolha = Integer.parseInt(sc.nextLine().trim());
        t.usarItem(escolha - 1);
    }

    private void ataqueTurno(Treinador atacante, Treinador defensor, Terreno terreno) {
        double bonus = terreno.bonus(atacante, defensor);
        double hpAntes = defensor.getPokesal().getHp();

        System.out.println(atacante.getPokesal().getNome() + " atacou " + defensor.getPokesal().getNome());
        atacante.ataquePokesal(defensor.getPokesal(), bonus);

        double dano = hpAntes - defensor.getPokesal().getHp();
        System.out.printf("dano: %.2f \n",dano);
        System.out.printf("HP de %s: %.2f%n", defensor.getPokesal().getNome(), defensor.getPokesal().getHp());
        System.out.println();
    }

    private void curarSeNecessario(Treinador treinador) {
        double metade = treinador.getPokesal().getHpMax() / 2;
        if (treinador.getPokesal().getHp() > 0 && treinador.getPokesal().getHp() <= metade) {
            try {
                treinador.usarItem(0);
                System.out.println(treinador.getNome() + " usou uma Potion em " + treinador.getPokesal().getNome());
                System.out.printf("HP de %s agora: %.2f%n", treinador.getPokesal().getNome(), treinador.getPokesal().getHp());
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
