/*
 * Autore: Youness Makaoui
 * Classe: 4G
 * Data: 22/02/2026
 * Luogo: xx
 * Versione: 1.0
 * Descrizione: Esercitazione Triangoli
 */

import java.awt.*;

public class Triangolo {

    private Punto[] points = new Punto[3];
    private String name;

    Triangolo(Punto[] points ) throws TrianglePositionException{
        this.points = points;
        // con un for each controllo che tutt i siano nel primo quadrante
        for (Punto p : points) {
            if ( p.getX() < 0 || p.getY() < 0 )
                throw new TrianglePositionException("Triangolo non valido");
        }
        name = "Triangolo utente";
    }
    // il triangolo base rispetta i requisiti
    Triangolo(){
        points[0] = new Punto(0,0);
        points[1] = new Punto(1,0);
        points[2] = new Punto(0,1);
        name = "Triangolo nell'origine";
    }

    double area (){
        return ( points[0].distance(points[2]) * points[0].distance( points[0].medianPoint(points[1]) ) ) / 2;
    }

    double perimeter (){
        return points[0].distance(points[1]) + points[1].distance(points[2]) + points[2].distance(points[0]);
    }

    boolean isDegenerate(){
        return points[0].equals(points[1]) || points[1].equals(points[2]) || points[0].equals(points[2]);
    }

    void translate( double deltaX, double deltaY ){
        name = "Triangolo traslato";
        for (Punto p : points){
            p.setX(p.getX()+deltaX);
            p.setY(p.getY()+deltaY);
        }
    }

    void saveOnCsv(){

    }

    @Override
    public String toString() {
        return name + " [\n\t" + points[0] + ";\n\t" + points[1] + ";\n\t" + points[2] + "\n]\n"
                + "Area: " + area() + "\nPerimetro: " + perimeter();
    }
}