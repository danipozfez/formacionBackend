package teoria;

import java.util.function.BiConsumer;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("Hello world!");

        //EJEMPLO DE OPERADOR TERNARIO

        String nombre = "dani";

        boolean compruebaNombre = nombre.equals("dani") ? true : false;

        System.out.println(compruebaNombre);

        int x = 6;
        x = x > 5 ? 1 : 0;
        System.out.println(x);

        //EJEMPLO DE FUNCION LAMDA CON DOS ARGUMENTOS

        BiConsumer<String, Integer> biConsumidor = (name, edad) -> {
            System.out.println(name + ", tiene " + edad + " años!");
        };

        biConsumidor.accept("daniel", 37);
    }
}