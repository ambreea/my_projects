package magazin;

public class Wine extends Product {

    public String tipProdus;
    public double volumSticla;
    public final double TVA_VIN = 0.1;

    public Wine(String tipProdus, String numeProdus, long codBare, double pretBaza, double volumSticla) {
        super(numeProdus, codBare, pretBaza);
        this.tipProdus = "vin";
        this.volumSticla = volumSticla;
    }

    public double pretVin() {
        return super.pret() * (1 + TVA_VIN);
    }

    public String getInfo() {
        return "Tip produs: " + tipProdus + "\n" +
                super.getInfo() + "\n" +
                "Volum sticla: " + volumSticla + " ml\n" +
                "Pret vin: " + pretVin() + " Lei\n" +
                "TVA vin 10% suplimentar pentru bauturi alcoolice";
    }

}
