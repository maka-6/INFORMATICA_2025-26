/*
* Autore: Makaoui Youness
* Data: 17/12/2025
* Classe: 4G
* Luogo: xx
* Descrizione: Lista dinamica in java
*/

class List {

    Node head;
    String name;

    List() {
        name = "lista vuota";
    }

    List(String name, Node head) {
        this.name = name;
        this.head = head;
    }

    void printList() {
        System.out.println(" --------- " + name + " --------- ");
        Node tmp = head;
        while (tmp != null) {
            System.out.println(tmp.toString());
            tmp = tmp.getPointNext();
        }
    }

    void insertNode( Node node ) {
        node.insertNext(head);
        head = node;
    }

    // Elimina tutti i nodi con nome uguale a name
    void deleteNode( String name, List list ) {

        // elimina tutte le teste che matchano
        while (list.head != null && list.head.getNome().equals(name)) {
            list.head = list.head.getPointNext();
        }

        // elimina i nodi successivi
        Node tmp = list.head;

        while (tmp != null && tmp.getPointNext() != null) {
            if (tmp.getPointNext().getNome().equals(name)) {
                tmp.insertNext(tmp.getPointNext().getPointNext());
            } else {
                tmp = tmp.getPointNext();
            }
        }
    }
}