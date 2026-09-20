package batalha;


import treinador.Treinador;

import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);
        String usarItem;


        System.out.println(treinador1.getNome() +"quer usar um dos itens? [S/N]");
        usarItem = sc.nextLine();

        if(usarItem.equalsIgnoreCase("S")){
            escolhaItem(t1);
        }else{
            ataqueTurno(t1,t2);
            if (t2.getPokesal().getHp() <= 0){
                System.out.println(t2.getPokesal().getNome() +"morreu...");
                System.out.println(t1.getPokesal().getNome() +"ganhou a batalha...");
                return false;
            }
        }

        System.out.println(treinador2.getNome() +"quer usar um dos itens? [S/N]");
        usarItem = sc.nextLine();

        if(usarItem.equalsIgnoreCase("S")){
            escolhaItem(t2);
        }else{
            ataqueTurno(t2,t1);
            if (t2.getPokesal().getHp() <= 0){
                System.out.println(t2.getPokesal().getNome() +"morreu...");
                System.out.println(t1.getPokesal().getNome() +"ganhou a batalha...");
                return false;
            }
        }

        return true;
    }

    private void escolhaItem(Treinador t){
        Scanner sc = new Scanner(System.in);
        t.listarItens();
        System.out.println("Ecolha o Item: ");
        int escolha = sc.nextInt();
        if (escolha == 1){
            t.getItem(0).usar(t.getPokesal());
        }
    }

    private void ataqueTurno(Treinador tacante, Treinador defensor) {
        System.out.println("Ataque do " +tacante.getPokesal().getNome());
        double bonus = terreno.bonus(treinador1,treinador2);
        tacante.ataquePokesal(defensor.getPokesal(),bonus);
    }
}
