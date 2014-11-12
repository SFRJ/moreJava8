package part3.other;

public interface AnInterfaceThatUsesDefaultKeyword {

    default void method() {
        System.out.println("This is a default implementation of method()");
    }
}
