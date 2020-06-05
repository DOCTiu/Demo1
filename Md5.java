import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
public class Md5  {
    public String getMd5_String(String a){
        String str = null;
        try{
            MessageDigest md=MessageDigest.getInstance("md5");
            byte[] bytes = md.digest(a.getBytes("UTF-8"));
            //得到了一个字节 计算摘要
            // a-z A-Z 0-9 / * 表示生成的string
             str= Base64.getEncoder().encodeToString(bytes);

        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e){
            e.printStackTrace();
            System.out.println("编码不支持");
        }
        return str;
    }
    public static void main(String[] args) {
        Md5 md5= new Md5();
        System.out.println(md5.getMd5_String("123"));
    }
}

