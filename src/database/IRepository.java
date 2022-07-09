package database;

import model.MutableNode;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public interface IRepository {
    MutableNode getSchema();

    List<Row> get(String source);

    List<Row> filter(List<String> filters, List<String> sorts, List<String> orders);

    void insert(List<String> columns, List<Object> values);

    void update(List<String> columns, List<String> values);

    void delete();

    void buildSearch(String column, String operation, String value, String concat, int commit);

    void addAggregation(JButton report, String col);

    void addGrouping(List<String> columns);

    List<Row> set(String source, String pk, String val);
}
