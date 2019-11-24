package local.string;

public class MyKeyIndexingCounting {

    private char[] array;
    private static int A = 26;

    public MyKeyIndexingCounting(int length){
        array = new char[length];
    }

    public MyKeyIndexingCounting(char[] array){
        this.array = array;
    }

    public void sort(){
        int[] counter = new int[A+1];

        for(int i = 0; i < array.length; i++) {
            counter[array[i]-65+1]++;
        }

        for(int i=1; i<counter.length; i++) {
            counter[i] = counter[i-1] + counter[i];
        }

        for(int i = 0; i < counter.length-1; i++)
            System.out.printf("%c = %d\n", (char)(i+65), counter[i]);

        char[] aux = new char[array.length];

        for(int i = 0; i < array.length; i++) {
            aux[counter[array[i]-65]++] = array[i];
        }

        array = aux;
    }

    public char[] getArray(){
        return array;
    }

    public static void main(String[] args) {
        String message = "PARALELEPIPIDOHELICOPTEROXAMAZY";

        MyKeyIndexingCounting indexingCounting = new MyKeyIndexingCounting(message.toCharArray());

        indexingCounting.sort();

        System.out.println(indexingCounting.getArray());
    }
}
