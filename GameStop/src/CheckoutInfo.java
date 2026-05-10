/*
 * Autore: Makaoui Youness, Alessio Fabrizio
 * Data: 10/05/2026
 * Classe: 4G
 * Luogo: xx
 * Versione: 1.0
 * Descrizione:
 */


public class CheckoutInfo {

    private String fullName;
    private String address;
    private String city;
    private String zipCode;
    private String country;


    public CheckoutInfo(String fullName, String address, String city, String zipCode, String country) {
        this.fullName = fullName;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCountry() {
        return country;
    }

    public boolean isComplete() {
        return fullName != null && !fullName.trim().isEmpty()
                && address != null && !address.trim().isEmpty()
                && city != null && !city.trim().isEmpty()
                && zipCode != null && !zipCode.trim().isEmpty()
                && country != null && !country.trim().isEmpty();
    }

    @Override
    public String toString() {
        return fullName + ";" +
                address + ";" +
                city + ";" +
                zipCode + ";" +
                country;
    }
}
