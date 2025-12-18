/*
 * Autore: Makaoui Youness
 * Data: 17/12/2025
 * Classe: 4G
 * Luogo: xx
 * Descrizione: Lista dinamica in java
 */

public class Main {
    public static void main(String[] args) {

        Node head = new Node();
        List list = new List( "Points", head );
        Node node1 = new Node( new Point(3.3,3.9,"Arco"),"Hello" );
        list.insertNode( node1 );
        Node node2 = new Node();
        list.insertNode( node2 );
        Node node3 = new Node();
        list.insertNode( node3 );
        Node node4 = new Node();
        list.insertNode( node4 );
        list.printList();
        list.deleteNode( "Galactus", list );
        //System.out.println("Nodi eliminati: " + list.deleteNode( "Galactus", list ));
        list.printList();
    }
}