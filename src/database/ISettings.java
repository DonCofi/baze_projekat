package database;

public interface ISettings {
    Object getParameter(String parameter);

    void addParameter(String parameter, Object value);
}
