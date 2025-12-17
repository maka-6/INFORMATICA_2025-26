
class Node {

    private Point point;
    private Node pointNext;
    private String nome;

    Node ( Point point, String nome ){
        this.point = point;
        this.nome = nome;
    }

    Node (){
        point = new Point();
        nome = "Galactus";
    }

    Point getPunto(){
        return point;
    }
    String getNome(){
        return nome;
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
}
