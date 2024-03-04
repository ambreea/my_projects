package parsarea_textului;

public class ParsareaTextului {

    public static void main(String[] args) {

        String text = "John.Davidson/Belgrade Michael.Barton/Krakow Ivan.Perkinson/Moscow";
        String[] date = text.split("[ ]");
        String[] Person = new String[date.length];

        for (int i = 0; i < date.length; i++) {

            String name = date[i].split("[/]")[0];

                String nume = name.split("[.]")[0];
                String prenume = name.split("[.]")[1];

            String loculNasterii = date[i].split("[/]")[1];

            Person person = new Person(prenume, nume, loculNasterii);
            Person[i]= person.getInfo();

            //System.out.println(Person[i]);
        }

        for (String value:Person) {
            System.out.println(value);
        }
    }
}
