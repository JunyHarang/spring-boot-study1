package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
       HelloLombok helloLombok = new HelloLombok();
       helloLombok.setName("lombok");
       helloLombok.setAge(20);

        String name = helloLombok.getName();
        int age = helloLombok.getAge();

        System.out.println("age = " + age);
        System.out.println("name = " + name);

        System.out.println("helloLombok ToString Call = " + helloLombok);
    }
}
