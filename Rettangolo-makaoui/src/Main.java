/*
* Autore: Makaoui Youness
* Classe: 4G
* Descrizione: Classe di un rettangolo
*/

// classe rettangolo
class Rettangolo{

    private double base, height;

    // costruttore 1
    public Rettangolo(){
        base = 4;
        height = 6;
    }

    // costruttore 2
    public Rettangolo(double base, double height){
        this.base = base;
        this.height = height;
    }

    double AreaCalc(){
        return (base * height);
    }

    double PerimetroCalc(){
        return (base + height)*2;
    }

    double getBase(){
        return base;
    }
    double getHeight(){
        return height;
    }

    @Override
    public String toString(){
        return "\nBase: "+getBase()+"\nAltezza: "+getHeight();
    }
}

public class Main {
    public static void main(String[] args) {

        // istanzio i due rettangoli
        Rettangolo rec1 = new Rettangolo(34,56);
        Rettangolo rec2 = new Rettangolo(3,10);

        // stampo le informazioni dei rettangoli
        System.out.println("Rettangolo 1: " + rec1.toString() + "\nArea: " + rec1.AreaCalc()+ "\nPerimetro: " + rec1.PerimetroCalc());
        System.out.println("\nRettangolo 2: " + rec2.toString() + "\nArea: " + rec2.AreaCalc()+ "\nPerimetro: " + rec2.PerimetroCalc());

    }
}