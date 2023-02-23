package ejercicio1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        Methods methods = new Methods();

        try {
            FileReader fr = new FileReader("files.txt");
            methods.OpenFile(fr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        methods.readytofilter();
    }
}
