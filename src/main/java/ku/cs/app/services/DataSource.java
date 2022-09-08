package ku.cs.app.services;


public interface DataSource <T>{

    T readData();
    void writeData(T t);
    void clearData();
}
