/*
* Autore: Youness Makaoui
* Classe: 4G
* Data: 16/02/2026
* Luogo: Home
* Versione: 2.0
* Descrizione: Esercitazione classe punto
*/

public class Punto {

    private float x, y;
    private String name;

    // costruttore base
    Punto(){
        y = 0;
        x = 0;
        name = "Origine";
    }


    Punto ( float x, float y ){
        this.x = x;
        this.y = y;
        name = "Punto";
    }

    Punto ( float x, float y, String name ){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    Punto ( String name ){
        this.name = name;
        x = 0;
        y = 0;
    }

    // distanza tra due punti
    float distance(Punto p ){
        return (float) Math.sqrt(Math.pow( this.x - p.x, 2 ) + Math.pow( this.y - p.y, 2 ));
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

    Punto puntoMedio(Punto p){
        return new Punto( (this.x + p.x)/2 , (this.y + p.y)/2, "Punto Medio" );
    }

    @Override
    public String toString() {
        return name + " [" + x + ";" + y + "]";
    }

    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public void setName(String name){
        this.name = name;
    }
}
