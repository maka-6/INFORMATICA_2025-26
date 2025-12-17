/*
 * Autore: Makaoui Youness
 * Data: 17/12/2025
 * Classe: 4G
 * Luogo: xx
 * Descrizione: Lista dinamica in java
 */

class Node {

    private Point point;
    private Node pointNext;
    private String name;

    Node ( Point point, String nome ){
        this.point = point;
        this.name = nome;
    }

    Node (){
        point = new Point();
        name = "Galactus";
    }

    Point getPunto(){
        return point;
    }
    String getNome(){
        return name;
    }
    Node getPointNext(){
        return pointNext;
    }

    void insertNext( Node pointNext ){
        this.pointNext = pointNext;
    }

    String isPointNextNULL(){
        if ( pointNext == null )
            return "nullo";
        else
            return "presente";
    }

    @Override
    public String toString(){
        return  "[ Nome: " + getNome() + " ]\n" +
                "[ Punto: " + point.toString() + " ]\n" +
                "[ Next: " + isPointNextNULL() + " ]";
    }

    void setName( String name ){
        this.name = name;
    }
}
