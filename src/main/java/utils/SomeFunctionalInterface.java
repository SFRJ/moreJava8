package utils;

//This annotation is not mandatory but will tell the compiler to strictly verify that this interface is in fact a functional interface.
@FunctionalInterface
public interface SomeFunctionalInterface {

    public void uniqueMethod(String parameter);
}
