import java.util.Random;

public class DadoTest {

    // Classe Dado con memoria
    static class Dado {
        private int facce;
        private int[] memoria;
        private int indice;
        private Random rand;

        // Costruttore: definisce il numero di facce e la capacità di memoria
        public Dado(int facce, int capacitaMemoria) {
            this.facce = facce;
            this.memoria = new int[capacitaMemoria];
            this.indice = 0;
            this.rand = new Random();
        }

        // Lancia il dado e memorizza il risultato (se c'è spazio)
        public int lancia() {
            int risultato = rand.nextInt(facce) + 1;

            if (indice < memoria.length) {
                memoria[indice] = risultato;
                indice++;
            } else {
                System.out.println("⚠️ Memoria piena, non posso memorizzare altri lanci!");
            }

            return risultato;
        }

        // Restituisce una stringa con tutti i lanci effettuati
        public String getStorico() {
            if (indice == 0) {
                return "Nessun lancio effettuato.";
            }

            StringBuilder sb = new StringBuilder();
            sb.append("Lanci effettuati: ");
            for (int i = 0; i < indice; i++) {
                sb.append(memoria[i]);
                if (i < indice - 1) {
                    sb.append(", ");
                }
            }
            return sb.toString();
        }

        // Azzera la memoria dei lanci
        public void resetMemoria() {
            indice = 0;
            System.out.println("✅ Memoria azzerata.");
        }

        // Restituisce il numero di lanci effettuati finora
        public int getNumeroLanci() {
            return indice;
        }
    }

    // Metodo main per testare il Dado con memoria
    public static void main(String[] args) {
        // Creo un dado a 6 facce con memoria per 10 lanci
        Dado d = new Dado(6, 10);

        // Faccio 5 lanci
        for (int i = 0; i < 5; i++) {
            int valore = d.lancia();
            System.out.println("Lancio " + (i + 1) + ": " + valore);
        }

        // Stampo lo storico dei lanci
        System.out.println(d.getStorico());

        // Numero di lanci
        System.out.println("Numero di lanci memorizzati: " + d.getNumeroLanci());

        // Reset memoria
        d.resetMemoria();

        // Controllo dopo il reset
        System.out.println(d.getStorico());
        System.out.println("Numero di lanci dopo reset: " + d.getNumeroLanci());
    }
}
