package academy.elqoo.java8.defaultmethods;

import org.junit.Test;
import java.lang.UnsupportedOperationException;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class DefaultMethodsTest {

    @Test
    public void shouldMoveShape(){
        Shape shape = new Rectangle();
        // implement a default move method without changing the Rectangle class
        shape.move(10,10);
        assertThat(10, equalTo(shape.getXPos()));
        assertThat(10, equalTo(shape.getYPos()));
    }

    @Test
    public void shouldMoveXposWith10(){
        Rectangle rectangle = new Rectangle();
        Triangle triangle = new Triangle();
        List<Shape> shapes = asList(rectangle, triangle);
        // write a static method on shape that add 10 to each xPos of a shape
        Shape.moveXPosWith10(shapes);
        assertThat(10, equalTo(rectangle.getXPos()));
        assertThat(10, equalTo(triangle.getXPos()));

    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowNotImplementedException(){
        // add an optional method to the shape method
        Shape triangle = new Triangle();
        triangle.doSomething();
    }

    @Test
    public void shouldReturnNameForTriangle(){
        Shape shape = new Triangle();
        assertThat("Triangle",equalTo(shape.getName()));
    }

    @Test
    public void shouldReturnNameForRectangle(){
        AbstractShape shape = new Rectangle();
        assertThat("Hello",equalTo(shape.getName()));
    }

    @Test
    public void shouldProvideName(){
        // make rectangle implement NamedObject
        NamedObject namedObject = new Rectangle();
        assertThat("Hello",equalTo(namedObject.getName()));
    }


}
