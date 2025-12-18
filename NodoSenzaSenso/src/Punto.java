/*
 * Autore: Makaoui Youness
 * Data: 17/12/2025
 * Classe: 4G
 * Luogo: xx
 * Descrizione: Lista dinamica in java
 */

class Point {

    private double x,y;
    public String name;

    Point(){
        y = 0;
        x = 0;
        name = "Origine";
    }

    Point ( double x, double y, String name ){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    double getX() {
        return this.x;
    }
    double getY() {
        return this.y;
    }
    String getName(){
        return name;
    }

    double Distance ( double x, double y ){
        return (double) Math.sqrt(Math.pow( this.x - x, 2) + Math.pow( this.y - y, 2));
    }

    @Override
    public String toString(){
        return getName()+" ("+getX()+";"+getY()+")";
    }
}
