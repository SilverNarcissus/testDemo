package algorithm;

/**
 * Created by SilverNarcissus on 2019/3/31.
 */

/**
 * A number n and a negative base negBase is given to us, we need to represent n in that negative base. Negative base works similar to positive base. For example in base 2 we multiply bits to 1, 2, 4, 8 and so on to get actual number in decimal. In case of base -2 we need to multiply bits with 1, -2, 4, -8 and so on to get number in decimal.

 Examples:

 Input  : n = 13, negBase = -2
 Output : 11101
 1*(16) + 1*(-8) + 1*(4) + 0*(-2) + 1*(1)  = 13

 */
public class ConvertToNegBase {
    public static void main(String[] args) {
        ConvertToNegBase convertToNegBase = new ConvertToNegBase();
        System.out.println(convertToNegBase.convert(-7, -2));
    }

    public String convert(int n, int base){
        if(n == 0){
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        while(n != 0){
            int remainder = n % base;
            n /= base;
            if(remainder < 0){
                remainder -= base;
                n++;
            }

            sb.append(remainder);
        }

        return sb.reverse().toString();
    }
}
