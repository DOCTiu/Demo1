/*
重写这个类
 */
import javax.swing.table.DefaultTableModel;

public class MyDefaultTable extends DefaultTableModel {
    public MyDefaultTable(String [] str,int rows){
        super(str,rows);
    }
    public boolean  isCellEditable(int row,int col){
       return false;
    }
}
