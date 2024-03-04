package magazin;

import java.security.PrivateKey;

public class Product {

    private String numeProdus;
    private long codBare;
    private double pretBaza;
    public final double TVA = 0.2;


    public String getNumeProdus() {
        return numeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public long getCodBare() {
        return codBare;
    }

    public void setCodBare(long codBare) {
        this.codBare = codBare;
    }

    public double getPretBaza() {
        return pretBaza;
    }

    public void setPretBaza(double pretBaza) {
        this.pretBaza = pretBaza;
    }

    public Product(String numeProdus, long codBare, double pretBaza) {

        this.numeProdus = numeProdus;
        this.codBare = codBare;
        this.pretBaza = pretBaza;
    }

    public double pret() {
        return pretBaza * (1 + TVA);
    }

    String getInfo() {
        return "Nume produs: " + numeProdus + "\n" +
                "Cod bare: " + codBare + "\n" +
                "Pret baza: " + pretBaza + " Lei";
    }

}
