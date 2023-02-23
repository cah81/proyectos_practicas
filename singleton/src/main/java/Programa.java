import java.util.function.Supplier;

public class Programa {
    public static  void main(String[] args){
        System.out.println("Hola");
        System.out.println("Es singleton  " + SingletonTester.isSingleton(Singleton::getInstance));

        System.out.println("Es singleton "+ SingletonTester.isSingleton(NoSingleton::new));
    }
}

class Singleton{
    private static  Singleton INSTANCE = new Singleton();
    String hola;

    private  Singleton(){
        System.out.println("Inicializado Singleton");
    }

    public static Singleton getInstance(){
        return INSTANCE;
    }
}

class NoSingleton{
    public NoSingleton(){
        System.out.println("Inicializado no singleton");
    }
    String hola;
}

class SingletonTester{
        public static boolean isSingleton(Supplier<Object> func){
            Object i1 = func.get();
            Object i2 = func.get();
            return i2==i1;
        }
        }
