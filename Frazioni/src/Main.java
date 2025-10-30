public class Main {
    public static void main(String[] args) {

        Frazioni a = new Frazioni(1, 2);
        Frazioni b = new Frazioni( 4, 7 );
        Frazioni c = new Frazioni( 1, 3 );

        Frazioni d = a.sumFractions(b, c);

        System.out.println(d.num + "/" + d.den);
    }
}