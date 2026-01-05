public class Node {

    Object o;
    Object pointNext;

    public Node(Object o ) {
        this.o = o;
        pointNext = null;
    }

    public Node() {
        this.o = new Point();
        this.pointNext = null;
    }


    Point getPunto(){
        return ((Point) o);
    }
    String getPuntoNome() {
        if (((Point) o).getName() == null) return "null";
        return ((Point) o).getName();
    }
    Node getPointNext(){
        return (Node) pointNext;
    }

    void insertNext( Object pointNext ){
        this.pointNext = pointNext;
    }

    String isPointNextNULL(){
        if ( pointNext == null )
            return "nullo";
        else
            return "presente";
    }

    @Override
    public String toString() {
        return "[ Nodo: " +  o.toString() + " ]\n" +
                "[ Next: " + isPointNextNULL() + " ]";
    }
}