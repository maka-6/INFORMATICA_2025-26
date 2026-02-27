/*
* Autore: Makaoui Youness
* Classe: 4G
* Luogo: Lab 53bis
* Data: 27/02/2026
* Descrizione:  Classe Exception per i dati del cilindro/barra metallica
*/

class NonValidDataException extends Exception{
    
    public NonValidDataException () {
        super();
    }
    
    public NonValidDataException (String message) {
        super(message);
    }
}