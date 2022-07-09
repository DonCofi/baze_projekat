package database;

import java.util.HashMap;
import java.util.Map;

public class Row {
    private String name;
    private Map<String, Object> fields;

    public Row(){
        this.fields=new HashMap<>();
    }

    public void addField(String name, Object value){
        this.fields.put(name, value);
    }

    public void removeField(String name){
        this.fields.remove(name);
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public Map<String, Object> getFields(){
        return fields;
    }

    public void setFields(Map<String, Object> fields){
        this.fields=fields;
    }

    @Override
    public String toString(){
        return name+"  "+fields.toString();
    }
}
