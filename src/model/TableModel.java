package model;

import database.Row;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public class TableModel extends DefaultTableModel {

    private List<Row> rows;

    private void updateModel(){
        int colCount=rows.get(0).getFields().keySet().size();
        Vector colVector= DefaultTableModel.convertToVector(rows.get(0).getFields().keySet().toArray());
        Vector dataVector=new Vector(colCount);
        for(int i=0;i<rows.size();i++){
            dataVector.add(DefaultTableModel.convertToVector(rows.get(i).getFields().values().toArray()));
        }
        setDataVector(dataVector, colVector);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public void setRows(List<Row> rows){
        this.rows=rows;
        updateModel();
    }

    public List<Row> getRows(){
        return rows;
    }
}
