import java.math.BigInteger;
import java.security.SecureRandom;
    
public class PollardRho {
    private final static BigInteger ZERO = new BigInteger("0");
    private final static BigInteger ONE  = new BigInteger("1");
    private final static BigInteger TWO  = new BigInteger("2");
    private final static SecureRandom random = new SecureRandom();

    public static BigInteger f(BigInteger x, BigInteger c, BigInteger N) {
        // Implement multiplyMod?
        return x.multiply(x).mod(N).add(c);
    }

    public static BigInteger rho(BigInteger N) {
        BigInteger c = new BigInteger(N.bitLength(), random);

        BigInteger x = new BigInteger(N.bitLength(), random);
        BigInteger y = x;
        BigInteger divisor = new BigInteger("1");

        // check if even
        if (N.mod(TWO).compareTo(ZERO) == 0) return TWO;

        while((divisor.compareTo(ONE)) == 0) {
            x = f(x,c,N);
            y = f(f(y,c,N),c,N);
            divisor = x.subtract(y).gcd(N); // <-- this can blow up
        }

        return divisor;
    }

    public static void factor(BigInteger N) {
        if (N.compareTo(ONE) == 0) return;
        
        // memoize isProbablePrime
        if (N.isProbablePrime(10)) { System.out.println(N); return; }
        
        // Test random small factors here
        if (N.bitLength() > 67) { System.out.println("fail"); return; }
        BigInteger divisor = rho(N);
        
        // In parallel
        factor(divisor);
        factor(N.divide(divisor)); 
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