/*
 * Autore: Makaoui Youness
 * Data: 30/10/2025
 * Classe: 4G
 * Luogo: xx
 * Descrizione: Dado con memoria dei lanci
 */

import java.io.*;

public class IMieiPensieri {

    FileWriter file;
    public IMieiPensieri(String nomeFile) {

        try {
            file = new FileWriter(nomeFile);
        } catch (IOException e) {
        throw new RuntimeException(e);
        }
    }

    public void scriviPensiero(String pensiero){

        PrintWriter fileOut;
        fileOut = new PrintWriter(file);

        fileOut.println(pensiero);
        try{
            fileOut.close();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        IMieiPensieri pensieri = new IMieiPensieri("diario.txt");
        pensieri.scriviPensiero("Oggi mangio poi mangio poi mangio poi mangio");
    }
}