/*
 * Autore: Makaoui Youness
 * Data: 23/03/2026
 * Luogo: Lab 53bis
 * Versione: 1.0
 * Descrizione:
 */

public class Assemblaggio extends Thread{


    public Assemblaggio(String name){
        super(name);
    }

    @Override
    public void run(){

        System.out.println(getName() + ": Assemblaggio die pezzi...");

    }
}