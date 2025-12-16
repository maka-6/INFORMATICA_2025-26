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
}
