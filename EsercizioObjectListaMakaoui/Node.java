public class Node {

    Object o;
    Object pointNext;

    public Node(Object o ) {
        this.o = o;
        pointNext = null;
    }

    public Node() {
        o = new Object();
    }

    Point getPunto(){
        return ((Point) o);
    }
    String getPuntoNome(){
        return ((Node) o).getPunto().getName();
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
    public String toString(){
        Node node = (Node) o;
        return  "[ Punto: " + node.toString() + " ]\n" +
                "[ Next: " + isPointNextNULL() + " ]";
    }
}