import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class select_table extends JFrame implements ActionListener {
   protected  MyDefaultTable table; protected  TableText tableText;
    private  JTable jTable;
    public Test test;
    private JTextField jTextField;
    private DefaultTableCellRenderer render;
    private  JScrollPane jScrollPane;
    public ImageIcon img;
    private int count;
    public JLabel jl; public JButton jb;

   public select_table(TableText tableText){
       this.tableText=tableText;

       this.render= new DefaultTableCellRenderer();
       this.render.setOpaque(false);
       this.img= new ImageIcon(getClass().getResource("/photo/9.png"));//导入图片
       this.jTextField = new JTextField();
       this.jl= new JLabel(this.img);
       this.jl.setBounds(0,0,this.img.getIconWidth(),this.img.getIconHeight());
       this.test= new Test();
       this.jb= new JButton("return ");
    //   this.jPanel= new JPanel();
     // this.jPanel2= new JPanel();
       String []sc={"Sno","Cno","Cname"};
       this.table= new MyDefaultTable(sc,22);
       this.jTable= new JTable(this.table);this.jScrollPane= new JScrollPane(this.jTable);
       this.setSize(this.img.getIconWidth(),this.img.getIconHeight());
       //流布局
       this.setLayout(null);
       this.jb.addActionListener(this);
       this.jScrollPane.setBounds(100,this.img.getIconHeight()/2-40,350,260);
       this.jb.setBounds(450,this.img.getIconHeight()/2+190,80,30);
       this.jTextField.setBounds(225,this.img.getIconHeight()/2+230,80,30);
       //this.jPanel.setLayout(new GridLayout(2,1));
       //this.jPanel2.add(this.jb);
      //this.jPanel.add(this.jScrollPane);this.jPanel.add(this.jPanel2);
       this.getLayeredPane().add(this.jl, new Integer(Integer.MIN_VALUE));
       ((JPanel)this.getContentPane()).setOpaque(false);
       for(int i=0;i<=2;i++){
           this.jTable.getColumn(sc[i]).setCellRenderer(this.render);
       }
       this.jScrollPane.setOpaque(false);
       this.jTable.setOpaque(false);
       this.jScrollPane.getViewport().setOpaque(false);
       this.jTextField.setOpaque(false);
      // this.getContentPane().add(this.jPanel);
       this.jb.setContentAreaFilled(false);
       //this.jPanel.setOpaque(false); this.jPanel2.setOpaque(false);

       this.getContentPane().add(this.jTextField);
       this.getContentPane().add(this.jScrollPane);this.getContentPane().add(this.jb);
       this.setDefaultCloseOperation(Windows.EXIT_ON_CLOSE);
       this.setLocationRelativeTo(null);
       this.setVisible(true);
   }
public void into(String s){
       try{
           for( int i=0;i<22;i++){
               this.table.setValueAt("",i,0);
               this.table.setValueAt("",i,1);
               this.table.setValueAt("",i,2);
           }
          int i=0;
           this.test.get_sql_connection();
           this.test.ps=this.test.ct.prepareStatement("{call pro_tran(?)}");
           this.test.ps.setString(1,s);
           this.test.rs= this.test.ps.executeQuery();
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
       this.jTextField.setText("共有"+this.count+"记录");
}
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
       if(actionEvent.getSource().equals(this.jb)){
           //返回的按钮
        this.tableText.setVisible(true);
         this.setVisible(false);
       }
    }
}
