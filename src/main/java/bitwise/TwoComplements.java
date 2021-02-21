package bitwise;

public class TwoComplements {
    public static void main(String[] args) {
//        String b = "111111111110110";
//        short decimal = Short.parseShort(b, 2);
//        System.out.println(decimal);
        int i = 23;
        System.out.println(i);
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(i & 1));
        System.out.println(Integer.toBinaryString(i ^ 1));
        System.out.println(Integer.toBinaryString(1 << 2));
        System.out.println();
        int plusOne = addOne(i);
        System.out.println(plusOne);
        System.out.println(Integer.toBinaryString(plusOne));
    }

    static int addOne(int x){
        int m = 1;
        while((int)(x & m) >= 1) {
            x = x ^ m;
            m <<= 1;
        }
        x = x ^ m;
        return x;
    }
}
