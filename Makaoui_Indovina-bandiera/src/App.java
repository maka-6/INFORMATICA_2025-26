/*
 * Autore: Makaoui Youness
 * Data: 10/4/2026
 * Luogo: Lab 53bis
 * Versione: 1.0
 * Descrizione:
 */
import javax.swing.*;
import java.io.File;

public class App extends JFrame {

    private String[] countries = new String[102];
    private Country[] countriesList = new Country[102];

    public App() {
        setTitle("");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadCountries();

        setVisible(true);
    }


    private void loadCountries() {
        File cartella = new File("resources/w2560-jpeg");

        int i = 0;
        for (File file : cartella.listFiles()) {
            if (file.getName().endsWith(".jpg")) {
                countriesList[i] = new Country();
                countriesList[i].setName( file.getName().replace(".jpg", "") );
                countriesList[i].setFlagFile(file);
                countries[i] = countriesList[i].getName();
                System.out.println(countriesList[i].getName());
                i++;
            }
        }

    }


    public static void main(String[] args) {
        new App();
    }
}
