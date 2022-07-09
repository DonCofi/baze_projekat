package app;

import database.*;
import model.*;
import observer.IModelObserver;
import observer.IViewObserver;
import observer.ObserverEventType;
import observer.ObserverNotification;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Init implements IModelObserver {

    private List<IViewObserver> viewObservers = new ArrayList<IViewObserver>();
    private IDatabase db;
    private ISettings settings;
    private TableModel tm;

    public Init(){
        this.settings=initSettings();
        this.db=new Database(new Repository(this.settings));
        tm=new TableModel();
    }

    private ISettings initSettings() {
        ISettings settingsImplementation = new Settings();
        settingsImplementation.addParameter("mssql_ip", Constants.MSSQL_IP);
        settingsImplementation.addParameter("mssql_database", Constants.MSSQL_DATABASE);
        settingsImplementation.addParameter("mssql_username", Constants.MSSQL_USERNAME);
        settingsImplementation.addParameter("mssql_password", Constants.MSSQL_PASSWORD);
        return settingsImplementation;
    }

    public void loadResource(){
        InformationResource ir=(InformationResource)this.db.load();
        this.notifyObservers(new ObserverNotification(this, ObserverEventType.ADD));
    }

    public void readData(String table){
        if(this.db.readData(table)==null){
            return;
        }
        tm.setRows(this.db.readData(table));
    }

    public void filterData(List<String> filters, List<String> sorts, List<String> orders){
        if(this.db.filterData(filters, sorts, orders)==null){
            return;
        }
        tm.setRows(this.db.filterData(filters, sorts, orders));
    }

    public void insertData(List<String> columns, List<Object> values){
        this.db.insertData(columns, values);
    }

    public void updateData(List<String> columns, List<String> values){
        this.db.updateData(columns, values);
    }

    public void deleteData(){
        this.db.deleteData();
    }

    public void buildSearchQuery(String column, String operation, String value, String concat, int commit){this.db.buildSearchQuery(column, operation, value, concat, commit);}

    public  void addColumnAggregation(JButton report, String col){this.db.addColumnAggregation(report, col);}

    public void addColumnGrouping(List<String> columns){this.db.addColumnGrouping(columns);}

    public void setColumns(String source, String pk, String val){
        if(this.db.setColumns(source, pk, val)==null){
            return;
        }
        tm.setRows(this.db.setColumns(source,pk, val));
    }

    public TableModel getTableModel(){
        return tm;
    }

    public void setTableModel(TableModel tm){
        this.tm=tm;
    }

    private void createObserverList() {
        if(viewObservers==null) {
            viewObservers = new ArrayList<IViewObserver>();
        }
    }

    @Override
    public void addObserver(IViewObserver viewObserver) {
        createObserverList();
        if(viewObserver==null) {
            return;
        }
        if(this.viewObservers.contains(viewObserver)) {
            return;
        }
        this.viewObservers.add(viewObserver);
    }

    @Override
    public void removeObserver(IViewObserver viewObserver) {
        if(viewObserver==null || !viewObservers.contains(viewObserver)) {
            return;
        }
        viewObservers.remove(viewObserver);
    }

    @Override
    public void notifyObservers(ObserverNotification event) {
        createObserverList();
        for(IViewObserver viewObserver : viewObservers) {
            viewObserver.update(event);
        }
    }
}
