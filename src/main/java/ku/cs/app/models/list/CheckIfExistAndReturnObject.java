package ku.cs.app.models.list;

public interface CheckIfExistAndReturnObject<T> {
    boolean checkIfExist(String s);
    T returnObject(String s);
}
