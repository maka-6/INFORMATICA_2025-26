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

        System.out.println("M segmento A: "+segmentoA.coefficienteAngolare());
        System.out.println("M segmento B: "+segmentoB.coefficienteAngolare());

        System.out.println("Punto medio del segmento A: "+segmentoA.puntoMedio().toString());
        System.out.println("Punto medio del segmento B: "+segmentoB.puntoMedio().toString());

        System.out.println("Distanza dal punto medio del segmento A all'origine: "+segmentoA.distanceOrigin());
        System.out.println("Distanza dal punto medio del segmento B all'origine: "+segmentoB.distanceOrigin());

        System.out.println("Inclinazione rispetto all'Asse delle ascisse del segmento A: "+segmentoA.inclinazioneAscisse()+" gradi");
        System.out.println("Inclinazione rispetto all'Asse delle ascisse del segmento B: "+segmentoB.inclinazioneAscisse()+" gradi");

        if ( segmentoA.segmentoDegenere() )
            System.out.println("Segmento A e' degenerato");
        else
            System.out.println("Segmento A non e' degenerato");


        if ( segmentoB.segmentoDegenere() )
            System.out.println("Segmento B e' degenerato");
        else
            System.out.println("Segmento B non e' degenerato");

        if ( segmentoA.segmentiConsecutivi(segmentoB) == true ) {
            System.out.println("Segmento A e' consecutivo al segmento B");
        } else {
            System.out.println("Segmento A non e' consecutivo al segmento B");
        }

        System.out.println("Lunghezza del segmento A: "+segmentoA.lunghezza());
        System.out.println("Lunghezza del segmento B: "+segmentoB.lunghezza());
    }
}