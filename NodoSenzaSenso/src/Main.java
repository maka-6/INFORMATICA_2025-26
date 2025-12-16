public class Main {
    public static void main(String[] args) {

        Node head = new Node();
        List list = new List( "Points", head );
        Node node1 = new Node();
        list.insertNode( node1 );
        Node node2 = new Node();
        list.insertNode( node2 );
        Node node3 = new Node();
        list.insertNode( node3 );
        Node node4 = new Node();
        list.insertNode( node4 );
        list.printList();

    }
}