/*
* Autore: Makaoui Youness
* Classe: 4G
* Luogo: Lab 53bis
* Data: 27/02/2026
* Descrizione:  Classe Cilindro
*/

class Cilindro {
    private double diameter;
    private double length;
    private double volume;
    
    public Cilindro(double length, double diameter) throws NonValidDataException{
        
        if ( length < 0 || diameter < 0 )
            throw new NonValidDataException("Dati non validi per il cilindro");
        
        this.diameter = diameter;
        this.length = length;
        this.volume = Math.PI * Math.pow(diameter/2, 2) * length;
    }
    
    public Cilindro() {
        this.length = 1;
        this.diameter = 9;
        this.volume = Math.PI * Math.pow(diameter/2, 2) * length;
    }
    
    public double getLength(){
        return this.length;
    }
    
    public double getDiameter(){
        return this.diameter;
    }
    public double getVolume(){
        return volume;
    }
    double getTotalArea(){
        double r = diameter / 2;
        return 2 * Math.PI * r * r + 2 * Math.PI * r * length;
    }
}