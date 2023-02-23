package ejercicio1;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Methods
{
    List<Person> peopleList = new ArrayList<>();
    public List<Person> OpenFile(FileReader fr) throws IOException {


        try (BufferedReader bufferedReader = new BufferedReader(fr)) {


            String line = bufferedReader.readLine();


            while (line != null) {
                System.out.println(line);
                String[] person = line.split(":");
                Person p = new Person();

                if (person.length > 0) {
                    p.setName((person[0]));

                }
                if (person.length >= 2) {
                    p.setTown(person[1]);
                }
                try {
                    if (person[1].equals("")) {
                        person[1] = null;
                    }
                    Optional<String> op = Optional.ofNullable(person[1]);
                    String City = op.orElse("unknow city");
                    p.setTown(City);
                } catch (Exception e) {
                    Optional<String> op = Optional.empty();
                }
                p.setAgeInt(person.length > 2
                        ? Integer.parseInt(person[2]) : 0);
                peopleList.add(p);
                line = bufferedReader.readLine();
            }
            System.out.println(" ===========INFORMACION DEL FICHERO ==============");
            peopleList.forEach(p -> System.out.println("Name: " + p.getName() + "  City:  " + p.getTown() + " Age:  " + p.getAgeInt()));
        } catch (IOException e) {

            e.printStackTrace();

        }
        return peopleList;
    }

    public void readytofilter(){
        System.out.println("==============menor a 25=================");
        peopleList.stream().filter(age -> age.getAgeInt() < 25).filter((c)->c.getAgeInt()!=0).forEach(System.out::println);
        System.out.println("=================people starting with the letter A =========");
        peopleList.stream().filter((p)->!p.getName().startsWith("A")).forEach(System.out::println);
        System.out.println("========================Madrid city=============");
        Optional<Person> personMadrid = peopleList.stream().filter((p)-> p.getAgeInt() <25).filter((p)->p.getAgeInt()>0).filter((p)->p.getTown().equals("Madrid")).findFirst();
        if(personMadrid.isPresent()){
            System.out.println(personMadrid.get());
        }
        else{
            System.out.println("non-existent");
        }
        System.out.println("Filtrar por menor de 25 y ciudad de Barcelona");
        System.out.println("====================================");
        Optional<Person> personOptionalBarcelona = peopleList.stream().filter((p)->p.getAgeInt()<25).filter((p)->p.getAgeInt()>0).filter((p)->p.getTown().equals("Barcelona")).findFirst();
        if(personOptionalBarcelona.isPresent()){
            System.out.println(personOptionalBarcelona.get());
        }else{
            System.out.println("Non exist");
        }
    }



}



