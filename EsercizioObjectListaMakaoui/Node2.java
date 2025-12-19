class Nodo2 {

    Object o;
    Object pointNext;

    public Nodo2 ( Object o ) {
        this.o = o;
        pointNext = null;
    }

    public Nodo2 () {
        o = new Object();
    }

    Point getPunto(){
        return ((Point) o);
    }
    String getPuntoNome(){
        return ((Nodo2) o).getPunto().getName();
    }
    Nodo2 getPointNext(){
        return (Nodo2) pointNext;
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
        Nodo2 node = (Nodo2) o;
        return  "[ Punto: " + node.toString() + " ]\n" +
                "[ Next: " + isPointNextNULL() + " ]";
    }
}

class List2 {

    Object head;
    String name;

    List2() {
        name = "lista vuota";
    }

    List2(String name, Object head) {
        this.name = name;
        this.head = head;
    }

    void printList() {
        System.out.println(" --------- " + name + " --------- ");
        Nodo2 tmp = (Nodo2) head;
        while (tmp != null) {
            System.out.println(tmp.toString());
            tmp = tmp.getPointNext();
        }
    }

    void insertNode( Nodo2 node ) {
        node.insertNext(head);
        head = node;
    }

    // Elimina tutti i nodi con nome uguale a name
    void deleteNode( String name, List2 list2 ) {

        Nodo2 tmp = (Nodo2) list2.head;

        // elimina tutte le teste che matchano
        while (((Nodo2) head) != null && tmp.getPuntoNome().equals(name)) {
            head = ((Nodo2) head).getPointNext();
        }

        // elimina i nodi successivi
        tmp = (Nodo2) list2.head;

        while (tmp != null && tmp.getPointNext() != null) {
            if (tmp.getPointNext().getPuntoNome().equals(name)) {
                tmp.insertNext(tmp.getPointNext().getPointNext());
            } else {
                tmp = tmp.getPointNext();
            }
        }
    }

    public static void main(String[] args) {

        Nodo2 head = new Nodo2();
        List2 list2 = new List2( "Points", head );
        Nodo2 node1 = new Nodo2();
        list2.insertNode( node1 );
        Nodo2 node2 = new Nodo2();
        list2.insertNode( node2 );
        Nodo2 node3 = new Nodo2();
        list2.insertNode( node3 );
        Nodo2 node4 = new Nodo2();
        list2.insertNode( node4 );
        list2.printList();
        list2.deleteNode( "maka", list2 );
        //System.out.println("Nodi eliminati: " + list.deleteNode( "Galactus", list ));
        list2.printList();
    }
}
