import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
public class Stu_tea extends JFrame implements ActionListener {
     public Windows windows;
    private MyDefaultTable table;
    private ImageIcon img;
    private DefaultTableCellRenderer render;
    private  JTable jTable;
    private  JScrollPane jScrollPane;
    private Test test;
    public JButton jb_delete,jb_select,jb_update,jb_insert,jb_back;
    //布局容器
    protected  JPanel jp_all,jp_insert ,jp_update,jp_delete,jp_select;
    //输入信息
    protected  JTextField jt_insert,jt_insert2,jt_insert3;
    protected  JTextField jt_update,jt_update2,jt_update3;
    protected  JTextField jt_delete,jt_delete2,jt_delete3;

    //提示信息
    protected  JLabel jl_insert,jl_insert2,jl_insert3,jl;
    protected  JLabel jl_update,jl_update2,jl_update3;

    protected  JLabel jl_delete,jl_delete2,jl_delete3;
    public Stu_tea(Windows windows,Test test){
        super("Student");
        this.render= new DefaultTableCellRenderer();
        this.render.setOpaque(false);
        this.img= new ImageIcon(getClass().getResource("/photo/13.jpg"));//导入图片
        this.jl= new JLabel(this.img);
        this.jl.setBounds(0,0,1000,700);
          this.windows=windows;
        this.test=  test;
        String []str={"学号","姓名","年龄"};
        this.table=new MyDefaultTable(str,22);
        this.jTable= new JTable(this.table);
        this.jScrollPane= new JScrollPane(this.jTable);
        //按钮
        this.jb_delete= new JButton("删除");this.jb_insert= new JButton("插入");
        this.jb_select= new JButton("查看");this.jb_update= new JButton("更新");
        this.jb_update.addActionListener(this); this.jb_select.addActionListener(this);
        this.jb_insert.addActionListener(this);this.jb_delete.addActionListener(this);
        this.jb_back= new JButton("return"); this.jb_back.addActionListener(this);
        // insert
        this.jt_insert= new JTextField(10);this.jt_insert2= new JTextField(10);this.jt_insert3= new JTextField(10);
        this.jl_insert = new JLabel("学号"); this.jl_insert2= new JLabel("姓名"); this.jl_insert3= new JLabel("年龄");
        this.jt_insert.setOpaque(false); this.jt_insert2.setOpaque(false); this.jt_insert3.setOpaque(false);
        this.jl_insert.setOpaque(false); this.jl_insert2.setOpaque(false); this.jl_insert3.setOpaque(false);
        // update
        this.jt_update= new JTextField(10);this.jt_update2= new JTextField(10);this.jt_update3= new JTextField(10);
        this.jl_update = new JLabel("学号"); this.jl_update2= new JLabel("姓名");this.jl_update3= new JLabel("年龄");
        this.jt_update.setOpaque(false); this.jt_update2.setOpaque(false); this.jt_update3.setOpaque(false);
        this.jl_update.setOpaque(false); this.jl_update2.setOpaque(false); this.jl_update3.setOpaque(false);        // select
        // delete
        this.jt_delete= new JTextField(10);this.jt_delete2= new JTextField(10);this.jt_delete3= new JTextField(10);
        this.jl_delete= new JLabel("学号");this.jl_delete2= new JLabel("姓名");this.jl_delete3= new JLabel("年龄");
        this.jt_delete.setOpaque(false); this.jt_delete2.setOpaque(false); this.jt_delete3.setOpaque(false);
        this.jl_delete.setOpaque(false); this.jl_delete2.setOpaque(false); this.jl_delete3.setOpaque(false);
        //容器
        this.jp_all= new JPanel();this.jp_insert= new JPanel();this.jp_select= new JPanel();this.jp_update= new JPanel();
        this.jp_delete= new JPanel();
        //布局
        this.setSize(1000,700);
        this.setLayout(new GridLayout(2,1));
        this.getContentPane().add(this.jScrollPane);
        this.jp_all.setLayout(new GridLayout(4,1));
        // jp_insert
        this.jp_insert.add(this.jb_insert);this.jp_insert.add(this.jl_insert);this.jp_insert.add(this.jt_insert);
        this.jp_insert.add(this.jl_insert2);this.jp_insert.add(this.jt_insert2);this.jp_insert.add(this.jl_insert3);this.jp_insert.add(this.jt_insert3);
        // jp_update
        this.jp_update.add(this.jb_update);this.jp_update.add(this.jl_update);this.jp_update.add(this.jt_update);this.jp_update.add(this.jl_update2);
        this.jp_update.add(this.jt_update2);this.jp_update.add(this.jl_update3);this.jp_update.add(this.jt_update3);
        // jp_delete
        this.jp_delete.add(this.jb_delete);this.jp_delete.add(this.jl_delete);this.jp_delete.add(this.jt_delete);
        this.jp_delete.add(this.jl_delete2);this.jp_delete.add(this.jt_delete2);
        this.jp_delete.add(this.jl_delete3);this.jp_delete.add(this.jt_delete3);
        //jp_select
        this.jp_select.add(this.jb_select);
      this.jp_select.add(this.jb_back);
        //布局
        this.jp_all.setOpaque(false);
        this.jp_insert.setOpaque(false); this.jp_update.setOpaque(false); this.jp_delete.setOpaque(false);
        this.jp_select.setOpaque(false);
        this.jp_all.add(this.jp_insert);this.jp_all.add(this.jp_update);this.jp_all.add(this.jp_delete);this.jp_all.add(this.jp_select);
        //jb_delete,jb_select,jb_update,jb_insert,jb_back; 组件为空

        this.jb_delete.setContentAreaFilled(false); this.jb_back.setContentAreaFilled(false); this.jb_insert.setContentAreaFilled(false);
        this.jb_select.setContentAreaFilled(false); this.jb_update.setContentAreaFilled(false);
        //设置
        this.getLayeredPane().add(this.jl, new Integer(Integer.MIN_VALUE));
        ((JPanel)this.getContentPane()).setOpaque(false);
        for(int i=0;i<=2;i++){
            this.jTable.getColumn(str[i]).setCellRenderer(this.render);
        }
        this.jScrollPane.setOpaque(false);
        this.jTable.setOpaque(false);
        this.jScrollPane.getViewport().setOpaque(false);
        this.getContentPane().add(this.jp_all);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(Windows.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
  //insert 功能
   if(actionEvent.getSource().equals(this.jb_insert)) {
       try {
           String a = this.jt_insert.getText();
           String b = this.jt_insert2.getText();
           String c = this.jt_insert3.getText();
           this.test.insert_by_tea(a, b, c, "Student");
       JOptionPane.showMessageDialog(this,"ok！");
       }
       catch (Exception e){
           e.printStackTrace();
           JOptionPane.showMessageDialog(this,"请检查输入信息!!");
       }
   }
   // delete 功能
        if(actionEvent.getSource().equals(this.jb_delete)) {
            String a = this.jt_delete.getText();
            if (a.equals("")) {
               JOptionPane.showMessageDialog(this,"ERROR 检查输入!!");
            } else {
                try {
                    this.test.delete(a);
                    JOptionPane.showMessageDialog(this, "delete ok!!!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "ERROR!!!");
                }
            }
        }
    // update
       if(actionEvent.getSource().equals(this.jb_update)){
      String a =this.jt_update.getText();
      String b=this.jt_update2.getText();
      String c=this.jt_update3.getText();
           try {
               if(this.test.update_Student_tea(a,b,c)==1){
                   JOptionPane.showMessageDialog(this,"update ok!!");
               }
               else {
                   JOptionPane.showMessageDialog(this,"检查输入!");
               }
           } catch (Exception e) {
              JOptionPane.showMessageDialog(this,"ERROR!!! 检查输入信息");
           }
       }
       // select
       if(actionEvent.getSource().equals(this.jb_select)){
           try{
               int i=0;
               this.test.get_sql_connection();
               this.test.ps=this.test.ct.prepareStatement("select * from Student");
               this.test.rs= this.test.ps.executeQuery();
               while (this.test.rs.next()){
                   this.table.setValueAt(this.test.rs.getString(1),i,0);
                   this.table.setValueAt(this.test.rs.getString(2),i,1);
                   this.table.setValueAt(this.test.rs.getString(3),i,2);
                   i++;
               }
           }catch (Exception e){
              JOptionPane.showMessageDialog(this,"检查输入信息");
           }
           finally {
               this.test.close();
           }
       }
        if(actionEvent.getSource().equals(this.jb_back)){
            //返回
              this.setVisible(false);
              this.windows.setVisible(true);
        }
    }
}
