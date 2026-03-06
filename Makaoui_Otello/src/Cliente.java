/*
* Autore: Makaoui youness
* Classe: 4G
* Data: 06/03/2026
* Versione: 1.0
* Luogo: Lab 53bis
* Descrizione:
*/

public class Cliente {

    private final String name, surname;

    public Cliente( String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Cliente() {
        name = "Mario";
        surname = "Rossi";
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
