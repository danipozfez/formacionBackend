package ejercicio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestPerson {
    public static void main(String[] args) {

        String rutaFichero = "src/main/java/FICHEROS/people.csv";
        List<Person> persons = new ArrayList<>();
        try {
            // System.out.println(devuelveListaDesdeCSV(rutaFichero));
            devuelveListaDesdeCSV(rutaFichero);
        } catch (InvalidLineFormatException e) {
            System.err.println(e.getMessage());
        }

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
                        System.out.println(person);
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

}
