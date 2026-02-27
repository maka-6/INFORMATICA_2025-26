/*
* Autore: Makaoui Youness
* Classe: 4G
* Luogo: Lab 53bis
* Data: 27/02/2026
* Descrizione:  Classe BarraMetallica, estende la classe Cilindro
*/
import java.io.*;

class BarraMetallica extends Cilindro {
    
    private String type; // tipo di metallo
    private double density; // densita' del materiale calcolata
    private double cost; // costo al metro cubo
    
    public BarraMetallica (double length, double diameter, String type, double density, double cost) throws NonValidDataException {
        
        super(length, diameter);
        
        if ( cost < 0 )
            throw new NonValidDataException("Costo negativo!!");
        
        if ( density <= 0 )
            throw new NonValidDataException("Densita' nulla!!");
        
        this.type = type;
        this.density = density;
        this.cost = cost;
    }
    
    public BarraMetallica() {
        super();
        this.type = "Acciaio";
        this.density = 7850;
        this.cost = 2500;
    }
    
    // getter 
    double getCost(){
        return cost;
    }
    double getDensity(){
        return density;
    }
    String getType(){
        return type;
    }
    
    // costo totale
    double totalCost(){
        return this.cost * getVolume();
    }
    // peso totale
    double totalWeight(){
        return getVolume() * density;
    }
    
    // salvataggio su file CSV
    void saveOnCSV(String fileName) {
        
        try (FileWriter fw = new FileWriter(fileName, true)) {
            // da completare
            String line = this.type + "," + this.cost + "," + this.density + "," + getVolume() + "," + getLength() + "," + getDiameter() + "\n";
            fw.write(line);
            
        } catch ( IOException e ) {
            System.out.println("Errore apertura file: " + fileName);
        }
    }
    
    @Override
    public String toString() {
        return "Barra di " + type + " \nLunghezza: " + getLength() + " \nDiametro: " + getDiameter() + "\nDensita: " + getDensity() +" \nVolume: " + getVolume() + " \nArea Totale: " + getTotalArea() + " \nCost Totale: " + totalCost() + "$ \nPeso Totale: " + totalWeight() + "kg ";
    }
}