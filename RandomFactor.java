import java.math.BigInteger;
import java.security.SecureRandom;
    
public class RandomFactor {
    private final static BigInteger ZERO = new BigInteger("0");
    private final static BigInteger ONE  = new BigInteger("1");
    private final static BigInteger TWO  = new BigInteger("2");

    private final static SecureRandom random = new SecureRandom();

    public static BigInteger divisor(BigInteger N) {
        BigInteger d = new BigInteger("1");
        BigInteger x,y;

        while((d.compareTo(ONE)) == 0) {
            x = new BigInteger(N.bitLength()-1, random);
            y = new BigInteger(N.bitLength()-1, random);
            d = x.subtract(y).gcd(N);
        }

        return d;
    }

    public static void factor(BigInteger N) {
        if (N.compareTo(ONE) == 0) return;
        
        if (N.isProbablePrime(10)) { System.out.println(N); return; }
        
        if (N.bitLength() > 55) { System.out.println("fail"); return; }

        BigInteger d = divisor(N);
        factor(d);
        factor(N.divide(d)); 
    }

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        while (io.hasMoreTokens()) {
            BigInteger N = new BigInteger(io.getWord());
            factor(N);
            System.out.println();
        }
    }
}