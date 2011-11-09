import java.math.BigInteger;
import java.security.SecureRandom;

public class MakeBigNumbers {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        for(int i = 0; i < Integer.parseInt(args[0]); i++)
            System.out.println(new BigInteger(Integer.parseInt(args[1]),random));
    }
}