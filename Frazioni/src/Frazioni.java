public class Frazioni {

    double num, den;

    public Frazioni(double n, double d) {
        num = n;
        den = d;
    }

    Frazioni sumFractions(Frazioni b, Frazioni c) {
        int i = 1;

        while (i % this.den != 0 || i % b.den != 0 || i % c.den != 0) {
            i++;
        }

        this.num = this.num * (i / this.den);
        b.num = b.num * (i / b.den);
        c.num = c.num * (i / c.den);

        return new Frazioni(this.num + b.num + c.num, i);
    }

    Frazioni mulFractions(Frazioni b, Frazioni c) {
        return new Frazioni(this.num * b.num * c.num, this.den * b.den * c.den);

    }

    Frazioni divFraction(Frazioni b, Frazioni c) {
        Frazioni temp = new Frazioni(this.num * b.den * c.num, this.den * b.num * c.den);

        int i = 2;
        while ( temp.den % i== 0 && temp.num % i== 0 ) {
            i++;
        }

        temp.num = temp.num / i;
        temp.den = temp.den / i;

        return temp;
    }

    public static void main(String[] args) {

        Frazioni a = new Frazioni(1, 2);
        Frazioni b = new Frazioni(4, 7);
        Frazioni c = new Frazioni(1, 3);

        Frazioni d = a.sumFractions(b, c);
        System.out.println("Somma: " + d.num + "/" + d.den);

        d = a.mulFractions(b, c);
        System.out.println("Prodotto: " +  d.num + "/" +  d.den);

        d = a.divFraction(b, c);
        System.out.println("Divisione: " +  d.num + "/" +  d.den);
    }

}