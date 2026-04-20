/*
 * Autore: Makaoui Youness, Alessio Fabrizio
 * Data: 19/04/2026
 * Classe: 4G
 * Luogo: xx
 * Versione: 1.0
 * Descrizione:
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class SingUp {

    private String username;
    private String email;
    private String password;

    public SingUp(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    private boolean checkEmail(String email) {
        return email.contains("@");
    }
    private boolean checkPassword(String password) {
        return password.length() >= 8;
    }

    public User singUpVerify() {

        if (!checkEmail(email) || !checkPassword(password)) {
            return null;
        }

        try (BufferedReader br = new BufferedReader(new FileReader("data/Users.csv"))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length < 3) continue;

                if (parts[1].equals(email)) {
                    return null; // email già esistente
                }
            }

        } catch (Exception e) {
            System.out.println("Errore lettura: " + e.getMessage());
        }

        try (FileWriter fr = new FileWriter("data/Users.csv", true)) {

            fr.write(username + "," + email + "," + password + "\n");
            fr.flush();
            return new User(username, email, password);

        } catch (Exception e) {
            System.out.println("Errore scrittura: " + e.getMessage());
        }

        return null;
    }
}