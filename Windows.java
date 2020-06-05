
/*
*   这个页面是作为教师登录的应用
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Base64;
import javax.swing.event.*;
public class Windows  extends JFrame implements ActionListener {
public Test test;
    public Login_in login_in =null;
    protected Cour_tea cour_tea;
   private  Stu_tea stu_tea;
   private  SC_tea sc_tea;
   private ImageIcon img;

    public JButton jb_back;
    public JButton jb_Student,jb_SC,jb_Course;
    // 容器

    //图片
    public ImageIcon Icon ; public JLabel jl1;
    // 提示信息
    private JLabel jl_Student,jl_SC,jl_Course,jl;
    public Windows(Login_in login_in ,Test test){
        super("ADMIN");
        this.login_in=login_in;
       this.test= test;
        this.img= new ImageIcon(getClass().getResource("/photo/14.jpg"));//导入图片
        this.jl= new JLabel(this.img);
        this.jl.setBounds(0,0,800,600);
        //定义图片
        //this.Icon= new ImageIcon(getClass().getResource("/photo/5.jpg"));//使用的是getResource
       // this.Icon.setImage(this.Icon.getImage().getScaledInstance(500,300,Image.SCALE_DEFAULT));
        this.jl1= new JLabel();
       // this.jl1.setIcon(this.Icon);//设置图片位置
        //按钮
        this.jb_back= new JButton("return");
        this.jb_back.addActionListener(this);

        this.jb_SC= new JButton("SC "); this.jb_Student= new JButton("Student");this.jb_Course= new JButton("Course");
        this.jb_SC.addActionListener(this); this.jb_Course.addActionListener(this); this.jb_Student.addActionListener(this);
        this.jb_back.addActionListener(this);

         //提示信息
        this.jl_Course= new JLabel("Course 表"); this.jl_SC= new JLabel("SC 表");this.jl_Student= new JLabel("Student 表");
       //设置尺寸大小 布局方式

        this.setSize(800,600);
        this.getLayeredPane().add(this.jl, new Integer(Integer.MIN_VALUE));
        ((JPanel)this.getContentPane()).setOpaque(false);
        this.setLayout(null);
        this.jl_SC.setBounds(480,150,80,30); this.jl_SC.setOpaque(false);
        this.jb_SC.setBounds(600,150,80,30);
        this.jl_Student.setBounds(480,200,80,30);this.jl_Student.setOpaque(false);
        this.jb_Student.setBounds(600,200,80,30);
        this.jl_Course.setBounds(480,250,80,30); this.jl_Course.setOpaque(false);
        this.jb_Course.setBounds(600,250,80,30);
        this.jb_back.setBounds(550,300,80,30);

        // 容器添加组件
        this.jb_back.setContentAreaFilled(false); this.jb_Student.setContentAreaFilled(false);
        this.jb_Course.setContentAreaFilled(false); this.jb_SC.setContentAreaFilled(false);
         //this.getContentPane().add(this.jl1);
        this.getContentPane().add(this.jb_SC);this.getContentPane().add(this.jb_Student);this.getContentPane().add(this.jb_Course);this.getContentPane().add(this.jb_back);
         this.getContentPane().add(this.jl_Course);this.getContentPane().add(this.jl_Student);
         this.getContentPane().add(this.jl_SC);
        this.setLocationRelativeTo(null);
         this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
 if(actionEvent.getSource().equals(this.jb_back)){
  // 返回主页面
     this.setVisible(false);
     this.login_in.setVisible(true);
 }
 if(actionEvent.getSource().equals(this.jb_SC)){
     if(this.sc_tea==null){
         this.sc_tea=new SC_tea(this,this.test);
     }
     else
         this.sc_tea.setVisible(true);
     this.setVisible(false);
 }
if(actionEvent.getSource().equals(this.jb_Student)){
    if(this.stu_tea==null){
      this.stu_tea=new Stu_tea(this,this.test);
    }
    else this.stu_tea.setVisible(true);
    this.setVisible(false);
}
if(actionEvent.getSource().equals(this.jb_Course)){
    if(this.cour_tea==null){
        this.cour_tea= new Cour_tea(this,this.test);
    }
    else this.cour_tea.setVisible(true);
    this.setVisible(false);
}

    }
}
