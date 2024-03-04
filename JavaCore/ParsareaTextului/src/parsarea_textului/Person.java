package parsarea_textului;

public class Person {

    private String prenume;
    private String nume;
    private String loculNasterii;


    public Person(String prenume, String nume, String loculNasterii) {
        this.prenume = prenume;
        this.nume = nume;
        this.loculNasterii = loculNasterii;
    }

    public String getInfo() {
        return nume + " " + prenume + " " + loculNasterii;
    }
}


