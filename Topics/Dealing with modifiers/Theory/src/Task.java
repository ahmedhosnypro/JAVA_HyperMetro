// You can experiment here, it won’t be checked

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

public class Task {
  public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    System.class
            .getField("out").getType()
            .getMethod("println", String.class)
            .invoke(new PrintStream(System.err), "Hello!");
  }
}
