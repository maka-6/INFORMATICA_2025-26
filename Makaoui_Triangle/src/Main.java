
void main() {

    Triangolo t = new Triangolo();

    System.out.println(t.toString());

    if ( t.isDegenerate() )
        System.out.println("Triangolo degenerato");
    else
        System.out.println("Triangolo non e' degenerato");

    t.translate(10,10);
    System.out.println(t.toString());
}