/*
 * Autore: Youness Makaoui
 * Classe: 4G
 * Data: 22/02/2026
 * Luogo: xx
 * Versione: 1.0
 * Descrizione: Esercitazione Triangoli
 */

import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;

public class Triangolo {

    private Punto[] points = new Punto[3];
    private String name;

    Triangolo( Punto[] points ) throws TrianglePositionException{
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

    double area () {
        return ( points[0].distance(points[2]) * points[0].distance( points[0].medianPoint(points[1]) ) ) / 2;
    }

    double perimeter () {
        return points[0].distance(points[1]) + points[1].distance(points[2]) + points[2].distance(points[0]);
    }

    boolean isDegenerate () {
        return points[0].equals(points[1]) || points[1].equals(points[2]) || points[0].equals(points[2]);
    }

    void translate ( double deltaX, double deltaY ) {
        name = "Triangolo traslato";
        for (Punto p : points){
            p.setX(p.getX()+deltaX);
            p.setY(p.getY()+deltaY);
        }
    }

    void saveOnCsv ( String filename ) {
        try ( FileWriter fw = new FileWriter(filename, true) ) {
            String line = name + ","
                    + points[0].getX() + "," + points[0].getY() + ","
                    + points[1].getX() + "," + points[1].getY() + ","
                    + points[2].getX() + "," + points[2].getY() + "\n";
            fw.write(line);
            fw.flush();
        } catch (Exception e) {
            System.out.println("Errore nella scrittura del file");
        }
    }

    void buildFromCsv ( String filename ) {
        try ( BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // separa le colonne
                if (parts.length != 7) {
                    System.out.println("Riga CSV malformata");
                    return;
                }
                Punto[] pts = new Punto[3];
                pts[0] = new Punto(Double.parseDouble(parts[1]), Double.parseDouble(parts[2]));
                pts[1] = new Punto(Double.parseDouble(parts[3]), Double.parseDouble(parts[4]));
                pts[2] = new Punto(Double.parseDouble(parts[5]), Double.parseDouble(parts[6]));
                points = pts;
                name = "Triangolo CSV";
            }
        } catch (Exception e) {
            System.out.println("Errore nella lettura del file");
        }
    }

    @Override
    public String toString () {
        return name + " [\n\t" + points[0] + ";\n\t" + points[1] + ";\n\t" + points[2] + "\n]\n"
                + "Area: " + area() + "\nPerimetro: " + perimeter();
    }
}