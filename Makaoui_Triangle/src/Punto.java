/*
* Autore: Youness Makaoui
* Classe: 4G
* Data: 16/02/2026
* Luogo: xx
* Versione: 3.0
* Descrizione: Esercitazione classe punto
*/

public class Punto {

    private double x, y;
    private String name;

    // costruttore base
    Punto(){
        y = 0;
        x = 0;
        name = "Origine";
    }

    Punto ( double x, double y ){
        this.x = x;
        this.y = y;
        name = "Punto utente";
    }

    Punto ( double x, double y, String name ){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    // distanza tra due punti
    double distance(Punto p ){
        return Math.sqrt(Math.pow( this.x - p.x, 2 ) + Math.pow( this.y - p.y, 2 ));
    }

    // comparazione distanza dall'origine'
    // trova anche se i due punti sono uguali
    int compareTo(Punto p){
        double d1, d2;
        d1 = distance(new Punto());
        d2 = p.distance(new Punto());

        // caso secondo punto piu vicino all'Origine
        if ( d1 < d2 )
            return -1;

        // caso primo punto piu vicino all'Origine'
        else if ( d1 > d2 )
            return 1;

        // entrambi i punti sono equidistanti dall'Origine e quindi uguali
        else
            return 0;
    }

    Punto medianPoint(Punto p){
        return new Punto( (this.x + p.x)/2 , (this.y + p.y)/2, "Punto Medio" );
    }

    @Override
    public String toString() {
        return name + " [" + x + ";" + y + "]";
    }

    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setName(String name){
        this.name = name;
    }

    public double getY() {
        return y;
    }
    public double getX() {
        return x;
    }

    public String getName() {
        return this.name;
    }
}
