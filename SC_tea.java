import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
public class SC_tea extends JFrame implements ActionListener {
    //表格信息
    public Windows windows;
    private MyDefaultTable table;
    private  JTable jTable;
    private  JScrollPane jScrollPane;
    private DefaultTableCellRenderer render;
    private Test test;
    private JTextField jTextField;
    private ImageIcon img;
    private int count;
    //按钮
    protected JButton jb_insert ,jb_select ,jb_update,jb_delete,jb_back,jb_order,jb_select2,jb_select_sc;
    //布局容器
    protected  JPanel jp_all,jp_insert ,jp_update,jp_delete,jp_select,jp_back,jp_1;
    //输入信息
    protected  JTextField jt_insert,jt_insert2,jt_insert3;
    protected  JTextField jt_update,jt_update2,jt_update3;
    protected  JTextField jt_delete,jt_delete2,jt_delete3;
    protected  JTextField jt_select,jt_order,jt_select2;
    //提示信息
    protected  JLabel jl_insert,jl_insert2,jl_insert3,jl;
    protected  JLabel jl_update,jl_update2,jl_update3;
    protected  JLabel jl_select,jl_order,jl_select2;
    protected  JLabel jl_delete,jl_delete2,jl_delete3;
    //构造
    public SC_tea(Windows windows, Test test){
       //表格
        super("SC");

        this.windows=windows;
        this.test=test;
        this.render= new DefaultTableCellRenderer();
        this.render.setOpaque(false);
        this.img= new ImageIcon(getClass().getResource("/photo/12.jpg"));//导入图片
        this.jl= new JLabel(this.img);
        this.jl.setBounds(0,0,1000,700);
        String []str={"Sno","Cno","Grade"};
        this.table=new MyDefaultTable(str,22);
        this.jb_select2= new JButton("查询课程");
        this.jb_select_sc=new JButton("查看所有");
        this.jTable= new JTable(this.table);
        this.jScrollPane= new JScrollPane(this.jTable);
        //按钮
        this.jb_delete= new JButton("删除");this.jb_insert= new JButton("插入");
        this.jb_select= new JButton("查看学生");this.jb_update= new JButton("更新");
        this.jb_update.addActionListener(this); this.jb_select.addActionListener(this);
        this.jb_insert.addActionListener(this);this.jb_delete.addActionListener(this);
        this.jb_back= new JButton("return"); this.jb_back.addActionListener(this);
        this.jb_order= new JButton("排序");this.jb_order.addActionListener(this);
        this.jb_select_sc.addActionListener(this);
        this.jb_select2.addActionListener(this);
        //输入信息 提示信息
         // insert
         this.jt_insert= new JTextField(10);this.jt_insert2= new JTextField(10);this.jt_insert3= new JTextField(10);
         this.jl_insert = new JLabel("学号"); this.jl_insert2= new JLabel("选课编号"); this.jl_insert3= new JLabel("成绩");
          this.jt_insert.setOpaque(false); this.jt_insert2.setOpaque(false); this.jt_insert3.setOpaque(false);
          this.jl_insert.setOpaque(false); this.jl_insert2.setOpaque(false); this.jl_insert3.setOpaque(false);
            this.jTextField= new JTextField(10);
         // update
          this.jt_update= new JTextField(10);this.jt_update2= new JTextField(10);this.jt_update3= new JTextField(10);
          this.jl_update = new JLabel("学号"); this.jl_update2= new JLabel("选课编号");this.jl_update3= new JLabel("成绩");
          this.jt_update.setOpaque(false); this.jt_update2.setOpaque(false); this.jt_update3.setOpaque(false);
          this.jl_update.setOpaque(false); this.jl_update2.setOpaque(false); this.jl_update3.setOpaque(false);
          // select

         this.jt_select = new JTextField(10); this.jl_select2= new JLabel("输入课程编号"); this.jt_select2= new JTextField(10);
         this.jl_select= new JLabel("学号"); this.jt_order= new JTextField(10); this.jl_order=new JLabel("输入课程编号");
         this.jt_select.setOpaque(false); this.jl_select2.setOpaque(false); this.jt_select2.setOpaque(false);
         this.jl_select.setOpaque(false); this.jt_order.setOpaque(false); this.jl_order.setOpaque(false);
         // delete
         this.jt_delete= new JTextField(10);this.jt_delete2= new JTextField(10);this.jt_delete3= new JTextField(10);
         this.jl_delete= new JLabel("学号");this.jl_delete2= new JLabel("选课编号");this.jl_delete3= new JLabel("成绩");
         this.jt_delete.setOpaque(false); this.jt_delete2.setOpaque(false); this.jt_delete3.setOpaque(false);
         this.jl_delete.setOpaque(false); this.jl_delete2.setOpaque(false); this.jl_delete3.setOpaque(false);

        //容器
        this.jp_all= new JPanel();this.jp_insert= new JPanel();this.jp_select= new JPanel();this.jp_update= new JPanel();
        this.jp_delete= new JPanel(); this.jp_back=new JPanel(); this.jp_1= new JPanel();
        //布局
        this.setSize(1000,700);
        this.setLayout(new GridLayout(2,1));
         this.getContentPane().add(this.jScrollPane);
        this.getLayeredPane().add(this.jl, new Integer(Integer.MIN_VALUE));
        ((JPanel)this.getContentPane()).setOpaque(false);
        for(int i=0;i<=2;i++){
            this.jTable.getColumn(str[i]).setCellRenderer(this.render);
        }
        this.jScrollPane.setOpaque(false);
        this.jTable.setOpaque(false);
        this.jScrollPane.getViewport().setOpaque(false);
         this.jp_all.setLayout(new GridLayout(6,1));
         // jp_insert
        this.jp_insert.setOpaque(false); this.jTextField.setOpaque(false);
        this.jp_insert.add(this.jTextField);
         this.jp_insert.add(this.jb_insert);this.jp_insert.add(this.jl_insert);this.jp_insert.add(this.jt_insert);
         this.jp_insert.add(this.jl_insert2);this.jp_insert.add(this.jt_insert2);this.jp_insert.add(this.jl_insert3);this.jp_insert.add(this.jt_insert3);
        // jp_update
        this.jp_update.setOpaque(false);
        this.jp_update.add(this.jb_update);this.jp_update.add(this.jl_update);this.jp_update.add(this.jt_update);this.jp_update.add(this.jl_update2);
        this.jp_update.add(this.jt_update2);this.jp_update.add(this.jl_update3);this.jp_update.add(this.jt_update3);
        // jp_delete
        this.jp_delete.setOpaque(false);
        this.jp_delete.add(this.jb_delete);this.jp_delete.add(this.jl_delete);this.jp_delete.add(this.jt_delete);
        this.jp_delete.add(this.jl_delete2);this.jp_delete.add(this.jt_delete2);
        this.jp_delete.add(this.jl_delete3);this.jp_delete.add(this.jt_delete3);
        //jp_select
        this.jp_select.setOpaque(false);
        this.jp_select.add(this.jb_select);this.jp_select.add(this.jl_select);this.jp_select.add(this.jt_select);
         this.jp_select.add(this.jb_select2);this.jp_select.add(this.jl_select2);this.jp_select.add(this.jt_select2);
       //
        this.jp_back.setOpaque(false);
       this.jp_back.add(this.jb_order); this.jp_back.add(this.jl_order);this.jp_back.add(this.jt_order);
       //
        this.jp_1.setOpaque(false);
        this.jp_1.add(this.jb_select_sc); this.jp_1.add(this.jb_back);
       //布局
        this.jp_all.setOpaque(false);
         this.jp_all.add(this.jp_insert);this.jp_all.add(this.jp_update);this.jp_all.add(this.jp_delete);this.jp_all.add(this.jp_select);this.jp_all.add(this.jp_back);
        this.jp_all.add(this.jp_1);
        //jb_insert ,jb_select ,jb_update,jb_delete,jb_back,jb_order,jb_select2,jb_select_sc;
        this.jb_insert.setContentAreaFilled(false); this.jb_select.setContentAreaFilled(false);
        this.jb_update.setContentAreaFilled(false); this.jb_delete.setContentAreaFilled(false);
        this.jb_back.setContentAreaFilled(false); this.jb_order.setContentAreaFilled(false);
        this.jb_select2.setContentAreaFilled(false); this.jb_select_sc.setContentAreaFilled(false);
          //设置
         this.getContentPane().add(this.jp_all);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(Windows.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public void into (){
        try{
            int i=0;
            this.test.get_sql_connection();
            this.test.ps=this.test.ct.prepareStatement("select * from SC ");
            this.test.rs= this.test.ps.executeQuery();
            for (int j=i;j<22;j++){
                this.table.setValueAt(null,i,0);
                this.table.setValueAt(null,i,1);
                this.table.setValueAt(null,i,2);
            }
            while (this.test.rs.next()){
                this.table.setValueAt(this.test.rs.getString(1),i,0);
                this.table.setValueAt(this.test.rs.getString(2),i,1);
                this.table.setValueAt(this.test.rs.getString(3),i,2);
                i++;
                this.count=i;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            this.test.close();
        }
        this.jTextField.setText("");
        this.jTextField.setText("共有"+this.count+"记录");
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
  // insert
        if(actionEvent.getSource().equals(this.jb_insert)){
        String a=this.jt_insert.getText();
        String b= this.jt_insert2.getText();
        String c= this.jt_insert3.getText();
            try {
                this.test.insert_SC_by_tea(a,b,c);
                JOptionPane.showMessageDialog(this,"加入成功");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,"检查输入！！");
            }
  }
        //delete
        if(actionEvent.getSource().equals(this.jb_delete)){
           int i;
            String a=this.jt_delete.getText();
           String b=this.jt_delete2.getText();
            try {
                i=this.test.delete_SC(a,b);
               if(i==1){
                   JOptionPane.showMessageDialog(this,"删除成功!!");
               }
                else {
                    JOptionPane.showMessageDialog(this,"信息错误！！");
               }
            } catch (SQLException e) {
              JOptionPane.showMessageDialog(this,"检查输入!!");
            }
        }
        // update
        if(actionEvent.getSource().equals(this.jb_update)){
            String a=this.jt_update.getText();
            String b=this.jt_update2.getText();
            String c=this.jt_update3.getText();
            System.out.println(c);
            try {
                if(this.test.update_sc_by_tea(a,b,c)==1){
                    JOptionPane.showMessageDialog(this,"更新成功！");
                }
                else{
                    JOptionPane.showMessageDialog(this,"检查输入!");
                }
            } catch (Exception e) {
             JOptionPane.showMessageDialog(this,"ERROR 检查输入!!");
            }
        }
        //select
        if(actionEvent.getSource().equals(this.jb_select)){
            String a=this.jt_select.getText();
            try{
                for(int j=0;j<22;j++){
                    this.table.setValueAt(null,j,0);
                    this.table.setValueAt(null,j,1);
                    this.table.setValueAt(null,j,2);
                }
                int i=0;
                this.test.get_sql_connection();
                this.test.ps=this.test.ct.prepareStatement("select * from SC where Sno ="+a);
                this.test.rs= this.test.ps.executeQuery();
                while (this.test.rs.next()){
                    this.table.setValueAt(this.test.rs.getString(1),i,0);
                    this.table.setValueAt(this.test.rs.getString(2),i,1);
                    this.table.setValueAt(this.test.rs.getString(3),i,2);
                    i++;
                 this.count=i;

                }

            }catch (Exception e){
              JOptionPane.showMessageDialog(this,"检查输入！！");
            }
            finally {
                this.test.close();
            }
            this.jTextField.setText("");
            this.jTextField.setText("共有"+this.count+"记录");
        }
        if(actionEvent.getSource().equals(this.jb_select2)){
            String a=this.jt_select2.getText();
            try{
                int i=0;
                this.test.get_sql_connection();
                this.test.ps=this.test.ct.prepareStatement("select * from SC where Cno ="+a);
                this.test.rs= this.test.ps.executeQuery();
                for(int j=i;j<22;j++){
                    this.table.setValueAt(null,j,0);
                    this.table.setValueAt(null,j,1);
                    this.table.setValueAt(null,j,2);
                }
                while (this.test.rs.next()){
                    this.table.setValueAt(this.test.rs.getString(1),i,0);
                    this.table.setValueAt(this.test.rs.getString(2),i,1);
                    this.table.setValueAt(this.test.rs.getString(3),i,2);
                    i++;
                }
              this.count=i;
            }catch (Exception e){
               JOptionPane.showMessageDialog(this,"检查输入!!!!");
            }
            finally {
                this.test.close();
            }
            this.jTextField.setText("共有"+this.count+"记录");
        }
        if(actionEvent.getSource().equals(this.jb_order)){
            String a=this.jt_order.getText();
            try{
                int i=0;
                this.test.get_sql_connection();
                this.test.ps=this.test.ct.prepareStatement("select * from SC where Cno ="+a+" order by Grade");
                this.test.rs= this.test.ps.executeQuery();
                while (this.test.rs.next()){
                    this.table.setValueAt(this.test.rs.getString(1),i,0);
                    this.table.setValueAt(this.test.rs.getString(2),i,1);
                    this.table.setValueAt(this.test.rs.getString(3),i,2);
                    i++;
                }
                for(int j=i;j<22;j++){
                    this.table.setValueAt(null,j,0);
                    this.table.setValueAt(null,j,1);
                    this.table.setValueAt(null,j,2);
                }
            }catch (Exception e){
               JOptionPane.showMessageDialog(this,"检查输入！！");
            }
            finally {
                this.test.close();
            }
            this.jTextField.setText("");
        }
  if(actionEvent.getSource().equals(this.jb_select_sc)){
      this.into();
  }
  //返回
  if(actionEvent.getSource().equals(this.jb_back)){
     this.windows.setVisible(true);
      this.setVisible(false);
  }
}
}