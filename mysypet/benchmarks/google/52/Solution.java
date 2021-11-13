import java.math.BigInteger;


public static BigInteger leastCommonMultiple(BigInteger arg0, BigInteger arg1){
    BigInteger arg2 = arg0.gcd(arg1);
    BigInteger arg3 = arg0.multiply(arg1);
    arg3 = arg3.divide(arg2);
    return arg3;
}