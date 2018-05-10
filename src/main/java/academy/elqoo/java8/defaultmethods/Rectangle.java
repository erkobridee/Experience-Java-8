package academy.elqoo.java8.defaultmethods;

public class Rectangle extends AbstractShape implements NamedObject {

    @Override
    public String getName(){
        return "Hello";
    }

    @Override
    public void doSomething() {
        System.out.println("I'm doing something");
    }
}
