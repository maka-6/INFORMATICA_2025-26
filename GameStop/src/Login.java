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

public class Login {

    private String email;
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private boolean checkEmail(String email) {
        return email.contains("@");
    }
    private boolean checkPassword(String password) {
        return password.length() >= 8;
    }

    public User loginVerify() {

        if (!checkEmail(email) || !checkPassword(password)) {
            return null;
        }
        try ( BufferedReader br = new BufferedReader(new FileReader("data/Users.csv"))) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length < 3) continue;

                String username = parts[0];
                String email = parts[1];
                String password = parts[2];

                if (this.email.equals(email) && this.password.equals(password)) {
                    return new User(username, email, password, "docs/user-icon-base.png");
                }
            }

        } catch (Exception e) {
            System.out.println( "Errore durante il lettura del file: " + e.getMessage());
        }
        return null;
    }

}