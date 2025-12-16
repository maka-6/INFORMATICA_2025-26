class List {

    Node head;
    String name;

    List (){
        name = "lista vuota";
    }

    List ( String name , Node head ){
        this.name = name;
        this.head = head;
    }

    void printList(){
        Node tmp = head;
        while ( tmp != null ) {
            System.out.println( tmp.toString() );
            tmp = tmp.getPointNext();
        }
    }

    void insertNode( Node node ){
        node.insertNext( head );
        head = node;
    }
}
