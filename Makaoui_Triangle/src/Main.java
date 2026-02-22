import java.util.*;
void main() {

    Scanner sc = new Scanner(System.in);

    Punto[] punti = new Punto[3];

    System.out.println("Inserisci i punti del triangolo: ");
    for (int i = 0; i < 3; i++) {
        System.out.println("Inserisci il punto " + (i+1) + " x y :");
        punti[i] = new Punto(sc.nextDouble(), sc.nextDouble());
    }

    Triangolo t;

    try {
        t = new Triangolo(punti);
        t.saveOnCsv("triangoli.csv");

        System.out.println(t.toString());

        if ( t.isDegenerate() )
            System.out.println("Triangolo degenerato");
        else
            System.out.println("Triangolo non e' degenerato");

        t.translate(10,10);
        System.out.println(t.toString());

        t.saveOnCsv("triangoli.csv");
        t.buildFromCsv("triangoli.csv");
        System.out.println(t.toString());

    }catch (TrianglePositionException e){
        System.out.println("Triangolo non valido");
    }
}