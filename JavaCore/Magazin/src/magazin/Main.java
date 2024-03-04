package magazin;

public class Main {

    public static void main(String[] args) {

        Product product1 = new Product("Murfatlar", 213455, 10.2);

        System.out.println(product1.getInfo() + "\n");

        // tipul de produs este preluat din clasa din care face parte, indiferent de ce valoare introduce utilizatorul

        Wine wine1 = new Wine("", "Murfatlar", 213455, 10.2, 0.7);
        Wine wine2 = new Wine("", "Jidvei", 1565556, 15.5, 0.75);

        System.out.println(wine1.getInfo() + "\n");
        System.out.println(wine2.getInfo() + "\n");

        Chocolate chocolate1 = new Chocolate("", "Heidi", 12348, 7.2, 100);
        Chocolate chocolate2 = new Chocolate("", "Lindt", 698436, 10.7, 100);

        System.out.println(chocolate1.getInfo() + "\n");
        System.out.println(chocolate2.getInfo() + "\n");
    }

}
