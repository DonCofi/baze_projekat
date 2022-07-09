package database;

import model.MutableNode;

import javax.swing.*;
import java.util.List;

public interface IDatabase {
    MutableNode load();

    List<Row> readData(String tableName);

    List<Row> filterData(List<String> filters, List<String> sorts, List<String> orders);

    void insertData(List<String> columns, List<Object> values);

    void updateData(List<String> columns, List<String> values);

    void deleteData();

    void buildSearchQuery(String column, String operation, String value, String concat, int commit);

    void addColumnAggregation(JButton report, String col);

    void addColumnGrouping(List<String> columns);

    List<Row> setColumns(String source, String pk, String val);
}
