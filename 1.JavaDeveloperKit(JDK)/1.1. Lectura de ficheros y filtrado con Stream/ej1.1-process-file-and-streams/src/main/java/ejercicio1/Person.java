package ejercicio1;

import java.util.Optional;

public class Person {


    private String name;
    private String town;
    private Integer ageInt;

    private Optional<Integer> age;



    public Person() {

    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Integer getAgeInt() {
        return ageInt;
    }

    public void setAgeInt(Integer ageInt) {
        this.ageInt = ageInt;
    }

    public Optional<Integer> getAge() {
        return age;
    }

    public void setAge(Optional<Integer> age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", town='" + town + '\'' +
                ", ageInt=" + ageInt +
                ", age=" + age +
                '}';
    }

}
