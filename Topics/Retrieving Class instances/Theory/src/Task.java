// You can experiment here, it won’t be checked

public class Task {
  public static void main(String[] args) throws ClassNotFoundException {
    System.out.println(Class.forName(String.class.getClass().getName()));
    Class voidClass = void.class;

    Class intClass = int.class;

    Class stringClass = String.class;

    Class integerClass = Integer.class;

    Class objectClass = Object.class;
  }
}
