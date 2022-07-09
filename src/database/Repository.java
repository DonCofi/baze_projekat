package database;

import com.sun.tools.javac.Main;
import enumerations.AttributeType;
import enumerations.ConstraintType;
import exceptions.ExceptionHandler;
import model.*;
import view.MainFrame;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository {

    private static int commitFlag=0;
    private static String searchQuery=" WHERE ";

    private static int countFFlag=0;
    private static int countSFlag=0;
    private static int avarageFFlag=0;
    private static int avarageSFlag=0;
    private static String countFQuery=" ";
    private static String countSQuery=" ";
    private static String avarageFQuery=" ";
    private static String avarageSQuery=" ";

    private static int groupFlag=0;
    private static String groupQuery=" GROUP BY ";


    private ISettings settings;
    private Connection connection;

    public Repository(ISettings settings){
        this.settings=settings;
    }

    private void initConnection() throws SQLException, ClassNotFoundException{
        String url = "jdbc:jtds:sqlserver://147.91.175.155/tim_1_bp2020";
        String username = "tim_1_bp2020";
        String password = "qDMV3TBe";
        connection = DriverManager.getConnection(url, username, password);
    }

    private void closeConnection(){
        try{
            connection.close();
        } catch (Exception e){
            ExceptionHandler.createDialog(e);
        } finally {
            connection=null;
        }
    }

    @Override
    public MutableNode getSchema() {
        try{
            this.initConnection();
            System.out.println("Povezan na bazu");
            DatabaseMetaData dmd = connection.getMetaData();
            InformationResource ir=InformationResource.getInstance();
            ir.setName("Information resource");
            String tableType[]={"TABLE"};
            ResultSet tables = dmd.getTables(connection.getCatalog(), null, null, tableType);
            while(tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                Entity table = new Entity(tableName);
                if(table.toString().contains("trace")) {
                    continue;
                }
                table.setParent(ir);
                ir.addChild(table);
                ResultSet columns = dmd.getColumns(connection.getCatalog(), null, tableName, null);
                while (columns.next()) {
                    String columnName = columns.getString("COLUMN_NAME");
                    String columnType = columns.getString("TYPE_NAME");
                    String isNull = columns.getString("IS_NULLABLE");
                    int columnSize = Integer.parseInt(columns.getString("COLUMN_SIZE"));
                    Attribute attribute = new Attribute(columnName, table, AttributeType.valueOf(columnType.toUpperCase()), columnSize);
                    if(!isNull.equals("YES")){
                        Constraint constraint=new Constraint(ConstraintType.NOT_NULL);
                        constraint.setParent(attribute);
                        attribute.addChild(constraint);
                    }
                    table.addChild(attribute);
                    ResultSet pk = dmd.getPrimaryKeys(connection.getCatalog(), null, table.toString());
                    ResultSet fk = dmd.getImportedKeys(connection.getCatalog(), null, table.toString());
                    while(pk.next()){
                        String pkCol=pk.getString("COLUMN_NAME");//?
                        if(pkCol.equals(attribute.toString())){
                            Constraint constraint=new Constraint(ConstraintType.PRIMARY_KEY);
                            constraint.setParent(attribute);
                            attribute.addChild(constraint);
                        }
                    }
                    while(fk.next()){
                        String fkCol=fk.getString("FKCOLUMN_NAME");
                        if(fkCol.equals(attribute.toString())){
                            Constraint constraint=new Constraint(ConstraintType.FOREIGN_KEY);
                            constraint.setParent(attribute);
                            attribute.addChild(constraint);
                        }
                    }
                }
            }
            return ir;
        } catch (Exception e){
            ExceptionHandler.createDialog(e);
        } finally {
            this.closeConnection();
        }
        return null;
    }

    @Override
    public List<Row> get(String tableName) {
        List<Row> rows=new ArrayList<>();
        try{
            this.initConnection();
            String query="SELECT * FROM "+tableName;
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                Row row=new Row();
                row.setName(tableName);
                ResultSetMetaData rsm=rs.getMetaData();
                for(int i=1;i<=rsm.getColumnCount();i++){
                    row.addField(rsm.getColumnName(i), rs.getString(i));
                }
                rows.add(row);
            }
        } catch (Exception e){
            ExceptionHandler.createDialog(e);
            return null;
        } finally {
            this.closeConnection();
        }
        return rows;
    }

    @Override
    public List<Row> filter(List<String> filters, List<String> sorts, List<String> orders){
        String query="SELECT ";
        if(filters.size()!=0) {
            for (int i = 0; i < filters.size(); i++) {
                query += filters.get(i);
                if (i < filters.size() - 1) {
                    query += ",";
                }
            }
        }
        query+=" ";
        int all=0;
        if(countFFlag==1 || avarageFFlag==1){
            all=1;
            if(filters.size()!=0) {
                query += ", ";
            }
        }
        if(countFFlag==1){
            query+=countFQuery;
            countFFlag=0;
            countFQuery=" ";
        }
        if(avarageFFlag==1){
            query+=avarageFQuery;
            avarageFFlag=0;
            avarageFQuery=" ";
        }
        if(filters.size()==0 && all==0){
            query+="*";
        }else{
            query=query.substring(0, query.length()-1);
        }
        query+=" FROM ";
        InformationResource ir= (InformationResource) MainFrame.getInstance().getTm().getRoot();
        Entity entity=(Entity) ir.getChildWithName(MainFrame.getInstance().getTp().getSelectedComponent().getName());
        query+= entity.toString();
        if(commitFlag==1){
            query+=searchQuery;
            commitFlag=0;
            searchQuery=" WHERE ";
        }
        if(groupFlag==1){
            query+=groupQuery;
            groupFlag=0;
            groupQuery=" GROUP BY ";
        }
        int zarez=0;
        if(sorts.size()!=0){
            query+=" ORDER BY ";
            for(int i=0;i<sorts.size();i++){
                query+=sorts.get(i);
                if(!(orders.get(i).equals("/"))){
                    query=query+" "+orders.get(i);
                }
                if(i<sorts.size()-1){
                    query+=",";
                }
            }
            query+=" ";
        }else if(countSFlag==1 || avarageSFlag==1){
            query+=" ORDER BY ";
            zarez=1;
        }
        if(countSFlag==1){
            query+=countSQuery;
            countSFlag=0;
            countSQuery=" ";
        }
        if(avarageSFlag==1){
            query+=avarageSQuery;
            avarageSFlag=0;
            avarageSQuery=" ";
        }
        if(zarez==1){
            query=query.substring(0, query.length()-1);
        }
        List<Row> rows=new ArrayList<>();
        System.out.print(query+"\n");
        try{
            this.initConnection();
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                Row row=new Row();
                row.setName(MainFrame.getInstance().getTp().getSelectedComponent().getName());
                ResultSetMetaData rsm=rs.getMetaData();
                for(int i=1;i<=rsm.getColumnCount();i++){
                    row.addField(rsm.getColumnName(i), rs.getString(i));
                }
                rows.add(row);
            }
        } catch (Exception e){
            ExceptionHandler.createDialog(e);
            return null;
        } finally {
            this.closeConnection();
        }
        return rows;
    }

    @Override
    public void insert(List<String> columns, List<Object> values){
        InformationResource ir= (InformationResource) MainFrame.getInstance().getTm().getRoot();
        Entity entity=(Entity) ir.getChildWithName(MainFrame.getInstance().getTp().getSelectedComponent().getName());
        String query="INSERT INTO ";
        query+=entity.toString();
        query+="(";
        for(int i=0;i<columns.size();i++){
            query+=columns.get(i);
            if(i<columns.size()-1){
                query+=",";
            }
        }
        query+=") VALUES(";
        for(int i=0;i<values.size();i++){
            if(values.get(i) instanceof Integer){
                query+=values.get(i).toString();
            }else{
                query+=values.get(i);
            }
            if(i<values.size()-1){
                query+=",";
            }
        }
        query+=")";
        System.out.print(query+"\n");
        try {
            this.initConnection();
            PreparedStatement statement=connection.prepareStatement(query);
            statement.execute();
        } catch (Exception e){
            ExceptionHandler.createDialog(e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void update(List<String> columns, List<String> values){
        String query="UPDATE ";
        InformationResource ir= (InformationResource) MainFrame.getInstance().getTm().getRoot();
        Entity entity=(Entity) ir.getChildWithName(MainFrame.getInstance().getTp().getSelectedComponent().getName());
        query+= entity.toString();
        query+=" SET ";
        for(int i=0;i<columns.size();i++){
            query+=columns.get(i);
            query+=" = (";
            query+=values.get(i);
            query+=")";
            if(i<columns.size()-1){
                query+=",";
            }
        }
        if(commitFlag==1){
            query+=searchQuery;
            commitFlag=0;
            searchQuery=" WHERE ";
        }
        System.out.print(query+"\n");
        try {
            this.initConnection();
            PreparedStatement statement=connection.prepareStatement(query);
            statement.execute();
        } catch (Exception e){
            ExceptionHandler.createDialog(e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void delete(){
        String query="DELETE FROM ";
        InformationResource ir= (InformationResource) MainFrame.getInstance().getTm().getRoot();
        Entity entity=(Entity) ir.getChildWithName(MainFrame.getInstance().getTp().getSelectedComponent().getName());
        query+=entity.toString();
        if(commitFlag==1){
            query+=searchQuery;
            commitFlag=0;
            searchQuery=" WHERE ";
        }
        System.out.print(query+"\n");
        try {
            this.initConnection();
            PreparedStatement statement=connection.prepareStatement(query);
            statement.execute();
        } catch (Exception e){
            ExceptionHandler.createDialog(e);
        } finally {
            this.closeConnection();
        }
    }

    @Override
    public void buildSearch(String column, String operation, String value, String concat, int commit){
        searchQuery=searchQuery+column+" "+operation+" ";
        char split[]=value.toCharArray();
        if(split[0]>='0' && split[0]<='9'){
            searchQuery=searchQuery+value+" ";
        }else{
            searchQuery=searchQuery+"'"+value+"'"+" ";
        }
        if(commit==0){
            searchQuery+=concat;
        }
        searchQuery+=" ";
        if(commit==1){
            commitFlag=1;
        }
    }

    @Override
    public void addAggregation(JButton report, String col){
        if(report.getName().equals("Fcount")){
            countFFlag=1;
            countFQuery=countFQuery+"COUNT("+col+") AS "+col+"_count,";
        }else if(report.getName().equals("Favarage")){
            avarageFFlag=1;
            avarageFQuery=avarageFQuery+"AVG("+col+") AS "+col+"_avarage,";
        }else if(report.getName().equals("Scount")){
            countSFlag=1;
            countSQuery=countFQuery+"COUNT("+col+"),";
        }else{
            avarageSFlag=1;
            avarageSQuery=avarageFQuery+"AVG("+col+"),";
        }
    }

    @Override
    public void addGrouping(List<String> columns){
        groupFlag=1;
        for(int i=0;i<columns.size();i++){
            groupQuery=groupQuery+columns.get(i);
            if(i<columns.size()-1){
                groupQuery+=",";
            }
        }
    }

    @Override
    public List<Row> set(String tableName, String pk, String val) {
        List<Row> rows=new ArrayList<>();
        try{
            this.initConnection();
            String query="SELECT * FROM "+tableName;
            char split[]=val.toCharArray();
            if(!(split[0]>='0' && split[0]<='9')){
                val="'"+val+"'";
            }
            query=query+" WHERE "+pk+"="+val;
            PreparedStatement statement=connection.prepareStatement(query);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                Row row=new Row();
                row.setName(tableName);
                ResultSetMetaData rsm=rs.getMetaData();
                for(int i=1;i<=rsm.getColumnCount();i++){
                    row.addField(rsm.getColumnName(i), rs.getString(i));
                }
                rows.add(row);
            }
        } catch (Exception e){
            ExceptionHandler.createDialog(e);
            return null;
        } finally {
            this.closeConnection();
        }
        return rows;
    }
}
