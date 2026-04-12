import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Campionato camp = new Campionato(100); // max 100 partite
        camp.loadFromCSV("Partite.csv");

        System.out.println("Partite caricate:");
        System.out.println(camp);

        System.out.print("Squadra casa: ");
        String casa = sc.nextLine();
        System.out.print("Squadra ospite: ");
        String ospite = sc.nextLine();
        System.out.print("Gol casa: ");
        int golC = sc.nextInt();
        System.out.print("Gol ospite: ");
        int golO = sc.nextInt();
        System.out.print("Data (gg mm aaaa): ");
        int g = sc.nextInt();
        int m = sc.nextInt();
        int a = sc.nextInt();

        Partita nuova = new Partita(new Data(g, m, a), new Squadra(casa), new Squadra(ospite), golC, golO);
        camp.addPartita(nuova);
        camp.saveToCSV("Partite.csv");

        System.out.println("Partite aggiornate:");
        System.out.println(camp);
    }
}