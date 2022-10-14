package ku.cs.app.models;

public interface CheckIfExistAndReturnObject<T> {
    boolean checkIfExist(String s);
    T returnObject(String s);
}
