import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Base64.Decoder;

public class Encryption{

  public static String decrypt(String encrypted, String initVector, String key) {
       try {
           IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
           SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

           Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5PADDING");
           cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

           byte[] byteArray = cipher.doFinal(Base64.getDecoder().decode(encrypted));
           //System.out.println(new String(byteArray));

           return new String(byteArray);
       } catch (Exception ex) {
           ex.printStackTrace();
       }
       return null;
   }

  public static String encrypt(String value, String initVector, String key){
    try {
           IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
           SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

           Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5PADDING");
           cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

           byte[] byteArray = cipher.doFinal(value.getBytes());
           String encrypted = Base64.getEncoder().encodeToString(byteArray);

           //System.out.println("encrypted string: " + encrypted);
           //decrypt(encrypted, initVector, key);
           return encrypted;
       } catch (Exception ex) {
           ex.printStackTrace();
       }

       return null;
  }

  public static void main (String[] args){
    String key = "passwordpassword";
    String initVector = "0123456789101112";
    String message ="Did it work?!?!?!";
    String encrypted = encrypt(message, initVector, key);
    String original = decrypt(encrypted, initVector, key);

    //tests
    System.out.println("This: " + message);
    System.out.println("Should = This: " + original);
    System.out.println("This is the encypted text: " + encrypted);


    //decrypt(key, initVector, );
    }

}
