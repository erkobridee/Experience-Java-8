package academy.elqoo.java8.defaultmethods;

import java.lang.UnsupportedOperationException;

import java.util.Collection;

public interface Shape {

    int getXPos();

    int getYPos();

    void setXPos(int xPOs);

    void setYPos(int yPos);

    default String getName(){
        return "";
    }

    default void move(int x, int y) {
        setXPos(x);
        setYPos(y);
    }

    default void doSomething(){
        throw new UnsupportedOperationException();
    }

    static void moveXPosWith10(Collection<Shape> shapes) {
        shapes.forEach(shape -> shape.setXPos(shape.getXPos() + 10));
    }

}
