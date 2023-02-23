package com.bosonit.SBB1_Ej5_1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class ThirdClass implements CommandLineRunner {

    public void run(String... args) throws Exception {
        System.out.println("3.=======Hello From the Third Class : ThirdClass.=======");
        System.out.println("4.==modificando funcion ==== Impresion de Argumentos"+ "  "  + Arrays.deepToString(args));
    }


    @Bean
    public CommandLineRunner imprimir(){
     return p ->{
         System.out.println("5.=======Se ejecuta Bean.=======");

     };
    }

}
