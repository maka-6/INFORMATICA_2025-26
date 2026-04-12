/*
 * Autore: Makaoui Youness
 * Data: 10/4/2026
 * Luogo: Lab 53bis
 * Versione: 1.0
 * Descrizione:
 */

import java.io.File;

public class Country {

    public static String name;
    public static File flag;

    public Country() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public File getFlagFile() {
        return flag;
    }
    public void setFlagFile(File flag) {
        this.flag = flag;
    }
}
