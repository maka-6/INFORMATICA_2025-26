import java.io.*;
import java.util.*;

public class Libreria {
    private List<Libro> catalogo;
    private static String FILE_CSV = "Data/Inventario_Libri.csv";

    public Libreria() {
        catalogo = new ArrayList<>();
        caricaLibri();
    }

    // Carica i libri dal CSV
    private void caricaLibri() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_CSV))) {

            String line;
            boolean primaRiga = true;

            while ((line = br.readLine()) != null) {
                if (primaRiga) {
                    primaRiga = false;
                    continue;
                }

                StringTokenizer st = new StringTokenizer(line, ";");

                String titolo = st.nextToken();

                int copie = Integer.parseInt(st.nextToken());

                double costo = Double.parseDouble(st.nextToken());

                String[] dataSplit = st.nextToken().split("/");

                int giorno = Integer.parseInt(dataSplit[0]);

                int mese = Integer.parseInt(dataSplit[1]);

                int anno = Integer.parseInt(dataSplit[2]);

                catalogo.add(new Libro(titolo, copie, costo, new Data(giorno, mese, anno)));
            }

        } catch (FileNotFoundException e) {
            System.out.println("File non trovato");
        } catch (IOException e) {
            System.out.println("Errore lettura file");
        }
    }

    // Visualizza l'inventario
    public void visualizzaCatalogo() {
        System.out.printf("%-50s %-10s %-10s %-15s%n", "Titolo libro", "n° copie", "Costo $", "Data di uscita");
        System.out.println("-------------------------------------------------------------------------------------");

        for (Libro libro : catalogo) {
            System.out.printf("%-50s %-10d $%-10.2f %-15s%n",
                    libro.getTitolo(),
                    libro.getNumeroCopie(),
                    libro.getCosto(),
                    libro.getDataUscita().toString());
        }
    }

    // Restituisce il numero di libri presenti
    public int numeroLibri() {
        return catalogo.size();
    }

    // Ricerca un libro per titolo
    public Libro ricercaTitolo(String titolo) {
        for (Libro libro : catalogo) {
            if (libro.getTitolo().equalsIgnoreCase(titolo)) {
                return libro;
            }
        }
        return null; // non trovato
    }


    // Aggiunge un libro solo se non esiste un libro con lo stesso titolo
    public boolean aggiungiLibro(Libro libro) {
        if (ricercaTitolo(libro.getTitolo()) != null) {
            System.out.println("Errore: questo libro esiste già.");
            return false;
        }

        catalogo.add(libro);

        try (PrintWriter out = new PrintWriter(new FileWriter(FILE_CSV, true))) {
            Data data = libro.getDataUscita();

            out.println(libro.getTitolo() + "," + libro.getNumeroCopie() + "," +
                    libro.getCosto() + "," + String.format("%02d/%02d/%04d",
                    data.getGiorno(), data.getMese(), data.getAnno()));
            System.out.println("Libro aggiunto correttamente!");
            return true;
        } catch (IOExceptionlibro);
            return false;
        }
    }
}

class Main{
    public static void main(String[] args) {
        Libreria libreria = new Libreria();
        libreria.visualizzaCatalogo();
    }
}