/*
* Autore: Makaoui Youness
* Classe: 4G
* Luogo: Lab 53bis
* Data: 27/02/2026
* Descrizione:  Classe Main
*/

import java.util.*;


public class Main {
    
    public static void main (String[] Argv) {
        BarraMetallica bm = new BarraMetallica();
        System.out.println(bm.toString());
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Inserisci dimensione Array delle barre: ");
        int dim = sc.nextInt();
        BarraMetallica[] bmArray = new BarraMetallica[dim];
        
        
        int ch;
        // numero di barre inserite
        int index = 0;
        do {
            System.out.println("1 - Inserisci una barra di metallo");
            System.out.println("2 - Visualizza lista barre");
            System.out.println("3 - Salva su file la lista");
            System.out.println("4 - Esci" + "\n: ");
            ch = sc.nextInt();
            sc.nextLine();
            
            switch (ch) {
                case 1:
                    if (index == dim) {
                        System.out.println("Array pieno");
                        break;
                    }
                    try {
                        BarraMetallica bmUtente;
                        
                        System.out.println("Inserisci tipo di metallo: ");
                        String type = sc.nextLine();
                        
                        System.out.println("Inserisci lunghezza: ");
                        double lenght = sc.nextDouble();
                        
                        System.out.println("Inserisci diametro: ");
                        double diameter = sc.nextDouble();
                        
                        System.out.println("Inserisci costo: ");
                        double cost = sc.nextDouble();
                        
                        System.out.println("Inserisci densita: ");
                        double density = sc.nextDouble();
                        
                        bmUtente = new BarraMetallica(lenght, diameter, type, density, cost);
                        bmArray[index] = bmUtente;
                        index++;
                    
                    } catch (NonValidDataException e){
                        System.out.println("Inserisci dati validi");
                    }
                    break;
                
                case 2:
                    for (int i = 0; i < index;  i++){
                        System.out.println(bmArray[i].toString());
                    }
                    break;
                    
                case 3:
                    System.out.println("Inserisci Nome file: ");
                    String fileName = sc.nextLine();
                    for (int i = 0; i < index;  i++){
                        bmArray[i].saveOnCSV(fileName);
                    }
                    break;
                
                case 4:
                    System.out.println("Arrivederci");
                    break;
                
                default:
                    System.out.println("Scegli un opzione valida scimmia");
                    break;
            }
            
        } while (ch!=4);
    }
}