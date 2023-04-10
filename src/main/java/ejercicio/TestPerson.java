package ejercicio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestPerson {
    public static void main(String[] args) throws InvalidLineFormatException {

        String rutaFichero= "src/main/java/FICHEROS/people.csv";
        List<Person> persons = new ArrayList<>();

        System.out.println(devuelveListaDesdeCSV(rutaFichero));

    }

    public static List <Person> devuelveListaDesdeCSV (String ruta) throws InvalidLineFormatException{
        List<Person> persons = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(ruta))) {
            while (scanner.hasNextLine()){

                String line = scanner.nextLine();
                String [] values = line.split(":");
                if (values.length <1 || values.length>3){
                    throw  new InvalidLineFormatException("formato de linea inválido "+line);
                }
                String name = values[0].trim();
                String town = values.length >1 ? values[1].trim() : "";
                int age = values.length > 2 ? Integer.parseInt(values[2].trim()) : 0;
                Person person = new Person(name,town,age);
                persons.add(person);
            }
            // System.out.println(persons.toString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return persons;
    }
}
