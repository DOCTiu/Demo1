import javax.accessibility.AccessibleContext;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.*;

public class Login_in  extends JFrame implements ActionListener {
    public Test test;
    private Md5 md5;
    public JLabel account;
    private  JPanel jPanel;
    public TableText tableText;
    public JLabel password;
    public JButton button_login_in;
    public JTextField jt_account;
    public JPasswordField jt_password;
    private JButton jb_register; private  Register register;
    public JLabel jl1;
    public ImageIcon img;
    protected Windows windows = null;
    public Login_in() {
        super("选课管理模拟系统");
        this.test = new Test();
        this.md5 = new Md5();
        this.account = new JLabel("输入用户名"); this.account.setOpaque(false);
        this.password = new JLabel("输入密码"); this.password.setOpaque(false);
        this.button_login_in = new JButton("登录"); this.button_login_in.setOpaque(false);
        this.jb_register=new JButton("注册"); this.jb_register.setOpaque(false);
        //设置背景图片
        this.img= new ImageIcon(getClass().getResource("/photo/6.png"));//导入图片
        this.jl1= new JLabel(this.img);
        this.jl1.setBounds(0,0,this.img.getIconWidth(),this.img.getIconHeight());
         this.jPanel= new JPanel();
         this.jPanel.setOpaque(false);
        this.button_login_in.addActionListener(this);
        this.jt_account = new JTextField(10); this.jt_account.setOpaque(false);
        this.jt_password = new JPasswordField(10); this.jt_password.setOpaque(false);
        //设置页面布局
        this.setSize(this.img.getIconWidth(), this.img.getIconHeight());
        this.getLayeredPane().add(this.jl1, new Integer(Integer.MIN_VALUE));
        ((JPanel)this.getContentPane()).setOpaque(false);
        this.getContentPane().setLayout(null);
        //添加组件
        this.account.setBounds(this.img.getIconHeight()/4,this.img.getIconWidth()/3-80,100,30);
        this.jt_account.setBounds(this.img.getIconHeight()/4+110,this.img.getIconWidth()/3-80,150,30);
        this.password.setBounds(this.img.getIconHeight()/4,this.img.getIconWidth()/3-30,100,30);
        this.jt_password.setBounds(this.img.getIconHeight()/4+110,this.img.getIconWidth()/3-30,150,30);
        this.button_login_in.setBounds(this.img.getIconHeight()/4+20,this.img.getIconWidth()/3+30,80,35);
        this.jb_register.setBounds(this.img.getIconHeight()/4+160,this.img.getIconWidth()/3+30,80,35);
        this.jb_register.setContentAreaFilled(false); this.button_login_in.setContentAreaFilled(false);
        this.jb_register.addActionListener(this);
        this.setLocationRelativeTo(null);
        this.getContentPane().add(this.account);this.getContentPane().add(this.jt_account);this.getContentPane().add(this.password);
        this.getContentPane().add(this.jt_password); this.getContentPane().add(this.button_login_in);this.getContentPane().add(this.jb_register);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);//不能改变大小
        this.setVisible(true);
    }
    public void Login_time(){
        Date date = new Date();
        SimpleDateFormat dateformat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.test.get_sql_connection();
        try{
            String sql ="insert into login_account values(?,?)";
            this.test.ps=this.test.ct.prepareStatement(sql);
            this.test.ps.setString(1,this.jt_account.getText());
            this.test.ps.setString(2,dateformat.format(date));
            this.test.ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.test.close();
        }
    }
    public static void main(String[] args) {
        new Login_in();
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //如果是登录
            String a;
            boolean flag = false;
            String b;
            String result = null;
            String str = null;
            if (actionEvent.getSource().equals(this.button_login_in)) {
                a = this.jt_account.getText();
                b = new String(this.jt_password.getPassword());
                Pattern p_account = Pattern.compile("^[A-Za-z0-9]{6}$");
                //
                Pattern p_account_student = Pattern.compile("^[A-Za-z0-9]{4}$");
                Matcher m_account = p_account.matcher(a);
                Matcher m_accout_student = p_account_student.matcher(a);
                if (m_account.find()) { //教师匹配的话
                    try {
                        this.test.get_sql_connection();
                        this.test.ps = this.test.ct.prepareStatement("select  password  from account_password  where account=? and password=?");
                        this.test.ps.setString(1, a);
                        this.test.ps.setString(2, this.md5.getMd5_String(b));
                        this.test.rs = this.test.ps.executeQuery();
                        flag = this.test.rs.next();
                    } catch (Exception e) {
                    } finally {
                        this.test.close();
                    }
                    if (flag == true) {
                        this.Login_time();
                        if (this.windows == null) {
                            this.windows = new Windows(this, this.test);
                            //传引用
                        }
                        this.windows.setVisible(true);
                        this.setVisible(false);
                        System.out.println("ok is login ");
                    } else {
                        JOptionPane.showMessageDialog(this, "密码或者用户名不正确");
                    }
                }  //教师匹配的话
                //学生匹配的话
                else if (m_accout_student.find()) {
                    try {
                        this.test.get_sql_connection();
                        this.test.ps = this.test.ct.prepareStatement("select  password  from account_password  where account=? and password=?");
                        this.test.ps.setString(1, a);
                        this.test.ps.setString(2, this.md5.getMd5_String(b));
                        this.test.rs = this.test.ps.executeQuery();
                        flag = this.test.rs.next();
                    } catch (Exception e) {
                    } finally {
                        this.test.close();
                    }
                    if (flag == true) {
                        this.Login_time();
                        if (this.tableText == null) {
                            this.tableText = new TableText(this,this.jt_account.getText());

                        }    //传学号
                        this.tableText.setVisible(true);
                        this.setVisible(false);
                        System.out.println("ok is login ");
                    } else {
                        JOptionPane.showMessageDialog(this, "密码或者用户名不正确或者账号不存在");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(this, "account 格式错误！！");
                }
                this.jt_password.setText("");
                this.jt_account.setText("");
            }
        if(actionEvent.getSource().equals(this.jb_register)){
        // 如果是登录的话 ，先看验证是否正确
  if(this.register==null){
      this.register= new Register(this,this.test);
  }
 this.setVisible(false); this.register.setVisible(true);
        }
    }
}
