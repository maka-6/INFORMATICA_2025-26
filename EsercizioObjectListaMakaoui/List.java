public class List {

    Object head;
    String name;

    List() {
        name = "lista vuota";
    }

    List(String name, Object head) {
        this.name = name;
        this.head = head;
    }

    void printList() {
        System.out.println(" --------- " + name + " --------- ");
        Node tmp = (Node) head;
        while (tmp != null) {
            System.out.println(tmp.toString());
            tmp = tmp.getPointNext();
        }
    }

    void insertNodeHead( Node node ) {
        node.insertNext(head);
        head = node;
    }

    void insertNodeTail( Node node ) {
        if (head == null) head = node;

        Node tmp = (Node) head;
        while (tmp.getPointNext() != null) tmp = tmp.getPointNext();
        tmp.insertNext(node);
    }

    void insertNodeSorted( Node node ) {
        Node tmp = (Node) head;
        String name = node.getPuntoNome();
        while (tmp != null && tmp.getPuntoNome().compareTo(name) < 0)
            tmp = tmp.getPointNext();

        node.insertNext(tmp.getPointNext());
    }

    void deleteNodeName( String name ) {
        if (head == null) return;
        Node tmp = (Node) head;
        while (tmp != null ) {
            if (tmp.getPuntoNome().equals(name)){
                tmp.insertNext(tmp.getPointNext());
            }
            tmp = tmp.getPointNext();
        }
    }

    Node findTail( List list ){
        if (list.head == null) return null;
        Node tmp = (Node) list.head;
        while (tmp.getPointNext() != null) tmp = tmp.getPointNext();
        return tmp;
    }

    int countNodes(){
        int count = 0;
        Node tmp = (Node) head;
        while (tmp != null) {
            count++;
            tmp = tmp.getPointNext();
        }
        return count;
    }

    // Elimina tutti i nodi con nome uguale a name
    void deleteNode( String name, List list2 ) {

        Node tmp = (Node) list2.head;

        // elimina tutte le teste che matchano
        while (((Node) head) != null && tmp.getPuntoNome().equals(name)) {
            head = ((Node) head).getPointNext();
        }

        // elimina i nodi successivi
        tmp = (Node) list2.head;

        while (tmp != null && tmp.getPointNext() != null) {
            if (tmp.getPointNext().getPuntoNome().equals(name)) {
                tmp.insertNext(tmp.getPointNext().getPointNext());
            } else {
                tmp = tmp.getPointNext();
            }
        }
    }


    public static void main(String[] args) {

        Node head = new Node();
        List list = new List( "Points", head );
        Node node1 = new Node();
        list.insertNodeHead( node1 );
        Node node2 = new Node();
        list.insertNodeHead( node2 );
        Node node3 = new Node();
        list.insertNodeHead( node3 );
        Node node4 = new Node();
        list.insertNodeHead( node4 );
        Node node5 = new Node( new Point(4.1, 2.0, "Centro") );
        list.insertNodeSorted( node5 );

        list.printList();
        list.deleteNode( "Origine", list );
        //System.out.println("Nodi eliminati: " + list.deleteNode( "Galactus", list ));
        list.printList();


    }
}