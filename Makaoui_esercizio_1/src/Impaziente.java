/*
 * Autore: Makaoui Youness
 * Data: 23/03/2026
 * Luogo: Lab 53bis
 * Versione: 1.0
 * Descrizione:
 */

public class Impaziente extends Thread{

    public Impaziente(String name){
        super(name);
    }

    @Override
    public void run(){

        System.out.println(getName() + ": FATEMI LAVORARE!!");

        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + ": STO LAVORANDO...");
            Thread.yield();
        }
    }
}
