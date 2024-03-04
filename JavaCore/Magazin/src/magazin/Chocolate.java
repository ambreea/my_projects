package magazin;

public class Chocolate extends Product {

    public String tipProdus;
    public int greutate;

    public Chocolate(String tipProdus, String numeProdus, long codBare, double pretBaza, int greutate) {
        super(numeProdus, codBare, pretBaza);
        this.tipProdus = "ciocolata";
        this.greutate = greutate;
    }

    public String getInfo() {
        return "Tip produs: " + tipProdus + "\n" +
                super.getInfo() + "\n" +
                "Grame: " + greutate + " g\n" +
                "Pret: " + super.pret() + " Lei\n" +
                "TVA 20%";

    }
}
