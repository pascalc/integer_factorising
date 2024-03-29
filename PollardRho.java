import java.math.BigInteger;
    
public class PollardRho {
    private final static BigInteger ZERO = new BigInteger("0");
    private final static BigInteger ONE  = new BigInteger("1");
    private final static BigInteger TWO  = new BigInteger("2");

    public static BigInteger f(BigInteger x) {
        return x.multiply(x).add(ONE);
    }

    public static BigInteger divisor(BigInteger N) {
        BigInteger x = TWO;
        BigInteger y = TWO;
        BigInteger d = ONE;

        while((d.compareTo(ONE)) == 0) {
            x = f(x).mod(N);
            y = f(f(y)).mod(N);
            d = x.subtract(y).gcd(N);
        }

        return d;
    }

    public static void factor(BigInteger N) {
        if (N.compareTo(ONE) == 0) return;
        
        if (N.isProbablePrime(10)) { System.out.println(N); return; }
        
        if (N.bitLength() > 65) { System.out.println("fail"); return; }
        
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