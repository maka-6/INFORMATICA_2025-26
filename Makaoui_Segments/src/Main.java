public class Main {
    public static void main(String[] args) {

        Segmento segmentoA = new Segmento();
        Segmento segmentoB = new Segmento();
        segmentoA.compileSegment(); // utente compila il segmento A
        segmentoB.compileSegment();

        System.out.println(segmentoA.toString());
        System.out.println(segmentoB.toString());

        if ( segmentoA.compareTo(segmentoB) == 1 ){
            System.out.println("Il segmento B e' il piu vicino all'origine'");

        } else if ( segmentoA.compareTo(segmentoB) == -1 ){
            System.out.println("Il segmento A e' il piu vicino all'origine'");

        } else {
            System.out.println("I due segmenti distano ugualmente dall'origine'");
        }

        if ( segmentoA.equalSegments(segmentoB) == 1 ){
            System.out.println("I segmenti sono completamente diversi");

        } else if ( segmentoA.equalSegments(segmentoB) == -1 ){
            System.out.println("I segmenti sono ugualmente lunghi ma sono in posizioni diverse'");

        } else {
            System.out.println("I due segmenti sono uguali e nella stessa posizione");
        }
    }
}