package database;

import model.MutableNode;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public class Database implements IDatabase {

    private IRepository repository;

    public Database(Repository repository) {
        this.repository=repository;
    }

    @Override
    public MutableNode load() {
        return repository.getSchema();
    }

    @Override
    public List<Row> readData(String tableName) {
        return repository.get(tableName);
    }

    @Override
    public List<Row> filterData(List<String> filters, List<String> sorts, List<String> orders){
        return repository.filter(filters, sorts, orders);
    }

    @Override
    public void insertData(List<String> columns, List<Object> values){
        repository.insert(columns, values);
    }

    @Override
    public void updateData(List<String> columns, List<String> values){
        repository.update(columns, values);
    }

    @Override
    public void deleteData(){
        repository.delete();
    }

    @Override
    public void buildSearchQuery(String column, String operation, String value, String concat, int commit){
        repository.buildSearch(column, operation, value, concat, commit);
    }

    @Override
    public void addColumnAggregation(JButton report, String col){
        repository.addAggregation(report, col);
    }

    @Override
    public void addColumnGrouping(List<String> columns){
        repository.addGrouping(columns);
    }

    @Override
    public List<Row> setColumns(String source, String pk, String val){ return repository.set(source, pk, val); }
}
