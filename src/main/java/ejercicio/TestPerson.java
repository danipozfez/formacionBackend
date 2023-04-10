package ejercicio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestPerson {
    public static void main(String[] args) throws InvalidLineFormatException {

        String rutaFichero = "src/main/java/FICHEROS/people.csv";
        List<Person> persons = new ArrayList<>();
        try {
             System.out.println(devuelveListaDesdeCSV(rutaFichero));
           // devuelveListaDesdeCSV(rutaFichero);
        } catch (InvalidLineFormatException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("\nIMPRIMIENDO CON FILTRO POR EDAD\n");

        filtraPersonasPorEdad(rutaFichero);
        System.out.println("\nIMPRIMIENDO CON FILTRO POR LETRA\n");

        filtraPersonasPorNombre(rutaFichero);

        System.out.println("\nIMPRIMIENDO CON FILTRO POR EDAD Y CIUDAD \n");

       filtraPersonasPorEdadYCiudad(rutaFichero);

        System.out.println("\nIMPRIMIENDO CON FILTRO EDAD Y BARCELONA \n");

        filtraPersonasPorCiudad(rutaFichero);

    }

    private static void filtraPersonasPorEdad(String rutaFichero) throws InvalidLineFormatException {
        devuelveListaDesdeCSV(rutaFichero).stream()
                .filter(person -> person.getAge() < 25 && person.getAge()!=0)
                .map(Person::toString)
                .forEach(System.out::println);
    }

    private static void filtraPersonasPorNombre(String rutaFichero) throws InvalidLineFormatException {
        devuelveListaDesdeCSV(rutaFichero).stream()
                .filter(person -> person.getName().charAt(0) != 'A' && person.getName().charAt(0) != 'Á')
                .map(Person::toString)
                .forEach(System.out::println);
    }

    private static void filtraPersonasPorEdadYCiudad(String rutaFichero) throws InvalidLineFormatException {

        System.out.println(
        devuelveListaDesdeCSV(rutaFichero).stream()
                .filter(person -> person.getAge() < 25 && person.getAge()!=0 && person.getTown().equals("Madrid"))
                .map(Person::toString)
                .findFirst()
        );
    }
    private static void filtraPersonasPorCiudad(String rutaFichero) throws InvalidLineFormatException {
        devuelveListaDesdeCSV(rutaFichero).stream()
                .filter(person -> person.getAge() < 25 && person.getAge()!=0 && person.getTown().equals("Barcelona"))
                .map(Person::toString)
                .forEach(System.out::println);
    }
    public static List<Person> devuelveListaDesdeCSV(String ruta) throws InvalidLineFormatException {
        List<Person> persons = new ArrayList<>();
        List<Person> errores = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(ruta))) {
            while (scanner.hasNextLine()) {


                try {

                    String line = scanner.nextLine();
                    String[] values = line.split(":");

                    String name = values[0].trim();
                    if (name.isEmpty()) {
                        throw new InvalidLineFormatException("El nombre es obligatorio. Hay 3 espacios en el campo y esto se considera como blank. " + line);

                    } else if (countCharacter(line, ':')==1 ){
                        throw new InvalidLineFormatException("Falta el último delimitador de campo (:) " + line);

                    } else if (countCharacter(line, ':')==0 ) {
                        throw new InvalidLineFormatException("Faltan dos delimitadores de campo (:) " + line);
                    } else {
                        String town = values.length > 1 ? values[1].trim() : "desconocido";
                        int age = values.length > 2 ? Integer.parseInt((values[2].trim())) : 0;

                        Person person = new Person(name, town, age);
                        //System.out.println(person);
                        persons.add(person);
                    }
                } catch (NumberFormatException e) {
                    throw new RuntimeException(e);
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return persons;
    }

    public static int countCharacter(String line, char character) {
        int posicion, contador = 0;
        posicion = line.indexOf(character);
        while (posicion != -1) {
            contador++;
            posicion = line.indexOf(character, posicion + 1);
        }
        return contador;

    }

    /*public static void imprimirPersonasPorEdad(ArrayList<Person>persons,Person person){

        persons.stream()
                .filter(persona -> person.getEdad() > 20)
                .map(persons::getNombre)
                .forEach(System.out::println);
    }*/

}
