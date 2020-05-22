package string;

public class ReverseString {
    public static void main(String[] args) {
        String apple = "Greetings!";
        StringBuilder sb = new StringBuilder();
        for (int i = apple.length()-1; i >= 0; i--)
            sb.append(apple.charAt(i));
        System.out.println(sb.toString());
        System.out.println("Done");
    }
}
