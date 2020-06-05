import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Base64;
public class TableText extends JFrame implements ActionListener {
     private MyDefaultTable table;
     protected  Login_in login_in;
     private ImageIcon img;
     protected  select_table se;
     private  JTable jTable;
     private  JScrollPane jScrollPane;
     private String Sno;
     //按钮
     private JButton jb1_insert;
     private JButton jb3_delete;
     private  JButton jb4_select;
      private  JButton jb5_return ;
    // 输入框
    private  JLabel jl_select;
     private  JTextField insert1,delete1,select1;
     private JPanel jPanel_all;
     private JPanel jPanel;  public JPanel jPanel1,jPanel2,jPanel3;
     private  JLabel jLabel1,jl;//提示信息
    //连接数据的 类
    public Test test;
    //提示输入信息
    public JLabel jl1,jl3,jl5;
     public TableText(Login_in login_in,String s){
         this.login_in= login_in;
         this.img= new ImageIcon(getClass().getResource("/photo/10.jpg"));//导入图片
         this.jl= new JLabel(this.img);
         this.jl.setBounds(0,0,800,550);
         this.test= new Test();
         this.Sno=s;
         this.jPanel3= new JPanel();
         this.test.select("Course");
        String [] str1={ "Cno","Cname","grade"};
        this.table= new MyDefaultTable(str1,22);
        //表格中添加信息
        for(int i=1;i<=this.test.con.size();i++){
            this.table.setValueAt(this.test.con.get(i-1),i-1,0);
            this.table.setValueAt(this.test.sno.get(i-1),i-1,1);
            this.table.setValueAt(this.test.Cname.get(i-1),i-1,2);
        }
        this.test.vector_free();
        this.jTable= new JTable(this.table);this.jScrollPane= new JScrollPane(this.jTable);
        //输入框
        this.insert1=new JTextField(20);this.delete1= new JTextField(20);
       this.select1=new JTextField(20);
        //提示输入信息
        this.jl1= new JLabel("选择的课程");
        this.jl3= new JLabel("选择的课程");
        this.jl5= new JLabel("选择的课程");
        this.jl_select= new JLabel("查看你所选的课程");
        //按钮
        this.jb1_insert= new JButton("插入");  this.jb3_delete= new JButton("删除");
        this.jb4_select= new JButton("查看"); this.jb5_return= new JButton("返回");
        this.jb1_insert.addActionListener(this);  this.jb3_delete.addActionListener(this);
        this.jb4_select.addActionListener(this); this.jb5_return.addActionListener(this);
        this.jPanel= new JPanel(); this.jPanel1= new JPanel();this.jPanel2=new JPanel();//四个不同的
        this.jLabel1= new JLabel("以上是可以选择的选课信息，你可以进行查询，修改，添加，删除操作,要选择的课程!!");
        this.jPanel_all= new JPanel();
        this.jPanel_all.setLayout(new GridLayout(5,1));
         this.jb5_return.setContentAreaFilled(false);
        this.jb4_select.setContentAreaFilled(false);
        this.jb3_delete.setContentAreaFilled(false);
         this.jb1_insert.setContentAreaFilled(false);
        //分为5行1列添加组件
        this.jPanel.add(this.jb1_insert);this.jPanel.add(this.jl1);this.jPanel.add(this.insert1);
        this.jPanel1.add(this.jb3_delete);this.jPanel1.add(this.jl3);this.jPanel1.add(this.delete1);
        this.jPanel2.add(this.jl_select);
        this.jPanel2.add(this.jb4_select);//this.jPanel2.add(this.jl5);this.jPanel2.add(this.select1);
        this.jPanel3.add(this.jb5_return);
        this.setLayout(new GridLayout(2,1));
        this.setSize(800,550);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().add(this.jScrollPane);
        this.jPanel_all.add(this.jLabel1);this.jPanel_all.add(this.jPanel); this.jPanel_all.add(this.jPanel1);this.jPanel_all.add(this.jPanel2);
        this.jPanel_all.add(this.jPanel3);
         this.getLayeredPane().add(this.jl, new Integer(Integer.MIN_VALUE));
         ((JPanel)this.getContentPane()).setOpaque(false);
         this.jPanel1.setOpaque(false);
         this.jPanel_all.setOpaque(false); this.jPanel.setOpaque(false); this.jPanel2.setOpaque(false); this.jPanel3.setOpaque(false);
         this.jScrollPane.setOpaque(false); this.insert1.setOpaque(false); this.delete1.setOpaque(false); this.select1.setOpaque(false);
         this.jTable.setOpaque(false);
         this.jl_select.setOpaque(false);
         this.getContentPane().add(this.jPanel_all);
        this.setVisible(true);
        this.setDefaultCloseOperation(Windows.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
         // insert按钮
        if(actionEvent.getSource().equals(this.jb1_insert)) {

            String b = this.insert1.getText();
                try {
                    this.test.insert_SC_by_Stu(this.Sno, b);
                    JOptionPane.showMessageDialog(this, "insert 成功！！");
                } catch (Exception e) {
                   JOptionPane.showMessageDialog(this,"输入信息错误或者不能为空！！");
                }
        }
        //select 按钮
       if(actionEvent.getSource().equals(this.jb4_select)){
         if(this.se==null){
             this.se= new select_table(this);
         }
         this.se.into(this.Sno);
         this.setVisible(false);
        this.se.setVisible(true);
       }
        //delete 按钮
      if(actionEvent.getSource().equals(this.jb3_delete)) {
          int i;
          String b = this.delete1.getText();
              try {
                 i= this.test.delete_SC(this.Sno, b);
                 if(i==1) {
                     JOptionPane.showMessageDialog(this, "delete 成功！！！");
                 }
                 else {
                     JOptionPane.showMessageDialog(this,"输入错误或者不能为空！");
                 }
                 } catch (Exception e) {
                  JOptionPane.showMessageDialog(this, "输入信息错误");
              }
          }
        //return 按钮
       if(actionEvent.getSource().equals(this.jb5_return)){
           this.setVisible(false);
            this.login_in.setVisible(true);
       }
    }
}
