/*
 * Autore: Makaoui Youness
 * Data: 17/12/2025
 * Classe: 4G
 * Luogo: xx
 * Descrizione: Lista dinamica in java
 */

public class Point {

    private float x;
    private float y;
    public String name;

    Point(){
        y = 0;
        x = 0;
        name = "Origine";
    }

    Point ( float x, float y, String name ){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    float getX() {
        return this.x;
    }
    float getY() {
        return this.y;
    }
    String getName(){
        return name;
    }

    float Distance ( float x, float y ){
        return (float) Math.sqrt(Math.pow( this.x - x, 2) + Math.pow( this.y - y, 2));
    }

    @Override
    public String toString(){
        return getName()+" ("+getX()+";"+getY()+")";
    }
}
