public class Libro {
    private String titolo;
    private int numeroCopie;
    private double costo;
    private Data dataUscita;

    public Libro(String titolo, int numeroCopie, double costo, Data dataUscita) {
        this.titolo = titolo;
        this.numeroCopie = numeroCopie;
        this.costo = costo;
        this.dataUscita = dataUscita;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getNumeroCopie() {
        return numeroCopie;
    }

    public double getCosto() {
        return costo;
    }

    public Data getDataUscita() {
        return dataUscita;
    }
}
