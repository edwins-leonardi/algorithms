package bitwise;

public class BitWise {
    public static void main(String[] args) {
        int checker = 0;
        char c = 'A';
        System.out.println(Integer.valueOf(c));
        System.out.println(Integer.toBinaryString(c));
        System.out.println();
        byte one = 1;
        System.out.println(Integer.valueOf(one));
        System.out.println(Integer.toString(one, 2));
        System.out.println();
        int d = one << 0;
        System.out.println(Integer.valueOf(d));
        System.out.println(Integer.toString(d, 2));
        System.out.println();

        checker |= 1 << 0;
        System.out.println(Integer.valueOf(checker));
        System.out.println(Integer.toString(checker, 2));
        System.out.println();


        checker |= 1 << 6;
        System.out.println(Integer.valueOf(checker));
        System.out.println(Integer.toString(checker, 2));
        System.out.println();

        String s = "priscila";
        System.out.printf("Has %s only unique chars?%n", s);
        int ch = 0;
        boolean result = true;
        for (int i = 0; i < s.length(); i++) {
            int value = s.charAt(i) - 'a';
            if((ch & (1 << value)) > 0) {
                System.out.println(s.charAt(i) + " was repeated");
                result = false;
                break;
            }
            ch |= (1 << value);
        }
        System.out.println(result);



    }



}
