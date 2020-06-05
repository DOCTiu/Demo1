/*
 注册类
 需要传图片
 */
import javafx.scene.control.PasswordField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
public class Register extends JFrame implements ActionListener {
   //属性
    private JLabel jl_account,jl_password,jl_img,jl_img_put,jl_code;//放置图片的组件
    private  JButton jb_submit,jb_return; //传递按钮 ,返回按钮
    private  ImageIcon img; //图片
     private  JTextField jt_account,jt_img,jt_code;//输入框,输入验证码
     private  Test test;
     public Login_in login_in;
     private JPasswordField jt_password;
     private  Md5 md5;//MD5算法
    //构造方法
  private  validCode validcode;
    public Register( Login_in login_in2 ,Test test){
        super("账户注册");
        //数据流
        this.test= test;
        this.login_in=login_in2;
      this.md5= new Md5();
        //组件定义
        //图片
        this.img= new ImageIcon(getClass().getResource("/photo/7.PNG"));//使用的是getResource
        this.img.setImage(this.img.getImage().getScaledInstance(80,100, Image.SCALE_DEFAULT));
        this.jb_submit= new JButton("注册"); this.jb_submit.addActionListener(this);
        this.jt_account=new JTextField(10);
        this.jt_password= new JPasswordField(10);
        this.jt_img= new JTextField(10);
        this.jl_account= new JLabel("输入账号");
        this.jl_password= new JLabel("输入密码"); this.jt_code= new JTextField(10);
        this.jl_code= new JLabel("输入验证码");  this.jb_return= new JButton("返回");
        this.jl_img= new JLabel("输入图片路径"); this.jl_img_put= new JLabel("上传的图片");
        this.validcode= new validCode();
        this.setSize(500,650);
        //添加组件
        this.getContentPane().setLayout(null);
        this.getContentPane().add(this.validcode);
        this.getContentPane().add(this.jb_submit); this.getContentPane().add(this.jt_account);
        this.getContentPane().add(this.jt_password);this.getContentPane().add(this.jt_img);
        this.getContentPane().add(this.jl_account);this.getContentPane().add(this.jl_img);
         this.getContentPane().add(this.jl_password); this.getContentPane().add(this.jl_img_put); this.getContentPane().add(this.jl_code);
         this.getContentPane().add(this.jt_code); this.getContentPane().add(this.jb_return); this.jb_return.addActionListener(this);
        //设置组件位置
        this.jl_account.setBounds(80,100,100,30);
        this.jt_account.setBounds(200,100,150,30);
        this.jl_password.setBounds(80,150,100,30);
        this.jt_password.setBounds(200,150,150,30);
        this.jl_img.setBounds(80,200,100,30);
        this.jt_img.setBounds(200,200,150,30);
        this.jb_submit.setBounds(120,280,80,30);this.jb_submit.setContentAreaFilled(false);
        this.jb_return.setBounds(240,280,80,30); this.jb_return.setContentAreaFilled(false);
        this.jl_code.setBounds(80,240,80,30);
        this.jt_code.setBounds(200,240,150,30);
        this.validcode.setBounds(370,230,80,30);
        this.jl_img_put.setBounds(100,380,100,30);
        //设置页面的布局
        this.setSize(500,600);
        this.setDefaultCloseOperation(Windows.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    //画图
    public void paint(Graphics g){
    super.paint(g);//如果没有的话将会出错
      g.drawImage(this.img.getImage(),200,400,100,100,null);
    }

    public boolean is_right(){
        if(this.jt_code.getText().equals(this.validcode.getCode())){
            return true;
        }
        else
            return  false;
    }
    //检查账户是否相同
    public boolean is_same(String a){
        boolean flag=true;
        String sql="select password  from account_password  where account=?";
        this.test.get_sql_connection();
        try{
         this.test.ps= this.test.ct.prepareStatement(sql);
         this.test.ps.setString(1,a);
            this.test.rs=this.test.ps.executeQuery();
            flag=this.test.rs.next();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            this.test.close();
            //记得关闭
        }
        return flag;
    }
    public boolean is_zai(String a){
      boolean flag=false;
        //是不是存在
       this.test.get_sql_connection();
       try{
           String sql="select * from Student where Sno=?";
           this.test.ps=this.test.ct.prepareStatement(sql);
           this.test.ps.setString(1,a);
           this.test.rs = this.test.ps.executeQuery();
           flag = this.test.rs.next();
       }
       catch (Exception e){
           e.printStackTrace();
       }
       finally {
           this.test.close();
       }
        return flag;
    }
    //向account_password 中插入信息
    public void  is_register(String a, String b) throws IOException {
        FileInputStream fileInputStream = null;
        File f=null;
        this.test.get_sql_connection();
        System.out.println(this.jt_img.getText());
        String sql ="insert into account_password values(?,?,?)";
        try{
             f= new File(this.jt_img.getText());
            this.test.ps=this.test.ct.prepareStatement(sql);
            this.test.ps.setString(1,a);
            this.test.ps.setString(2,this.md5.getMd5_String(b));
            fileInputStream= new FileInputStream(f);
            this.test.ps.setBinaryStream(3,fileInputStream,(int)f.length());
            this.test.ps.executeUpdate();
            this.img = new ImageIcon(this.jt_img.getText());
            repaint();
            JOptionPane.showMessageDialog(this, "注册欧克！！！");
        }catch (Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"图片路径有误");
        }
        finally {
            this.test.close();
        }
        fileInputStream.close();
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
     if(actionEvent.getSource().equals(this.jb_submit)){
         //首先判断验证码
         if(this.jt_code.getText().isEmpty()) JOptionPane.showMessageDialog(this,"验证码不能为空");
     //验证码不正确
     else  if(is_right()==false){
          JOptionPane.showMessageDialog(this,"输入正确的验证码！");
         }
     else if(jt_img.getText().isEmpty()){
         JOptionPane.showMessageDialog(this,"需要上传图片！");
         }
     //都正确的话  进行用户，密码的检验 要求密码只能是由[A-Za-z0-9]组成
     else {
         String exr=new String(this.jt_password.getPassword());
             Pattern p_password = Pattern.compile("^[A-Za-z0-9]{3,6}$");
             Matcher m_password;
             m_password = p_password.matcher(exr);
             //检查密码组合是不是有问题
             if(!m_password.find()){
                 JOptionPane.showMessageDialog(this,"密码是由大小写字母数字组成的3，6位组合的！");
             }
             //检查账户是否合理，需要访问student表判断是否存在如果没有不能创建，访问account_password表账户是否存在，存在的话，用户重复！！
             else {
                 if (this.is_zai(this.jt_account.getText()) == false) {
                     JOptionPane.showMessageDialog(this, "学号不存在");
                 }
                 //学号存在的话：
                 else {
                     String ex2= new String(this.jt_password.getPassword());
                     if (this.is_same(this.jt_account.getText()) == true) {
                         JOptionPane.showMessageDialog(this, "账号已经存在！！");
                     } else {
                         try {
                             this.is_register(this.jt_account.getText(),ex2);
                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                         System.out.println(this.jt_img.getText());
                     }
                     //账户存在
                 }
                 //学号存在
             }
             //密码符合规范
       }
     //验证码正确，图片路径不为空
        // this.jt_account.setText("");this.jt_password.setText("");this.jt_img.setText(""); this.jt_code.setText("");
     }
     //如果是注册的话
     if(actionEvent.getSource().equals(this.jb_return )){
         //返回按钮 返回到login in页面
         this.jt_code.setText("");
         this.jt_password.setText("");
         this.jt_account.setText("");
         this.jt_img.setText("");
         this.img= new ImageIcon(getClass().getResource("/photo/7.PNG"));//使用的是getResource
         this.img.setImage(this.img.getImage().getScaledInstance(80,100, Image.SCALE_DEFAULT));
         repaint();
         this.login_in.setVisible(true);
         this.setVisible(false);
     }
    }
}
