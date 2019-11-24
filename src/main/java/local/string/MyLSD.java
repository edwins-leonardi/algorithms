package local.string;

public class MyLSD {

    public static int R = 256;

    public static void sort(String[] array, int w) {

        int size = array.length;
        String[] aux = new String[size];

        for(int d = w-1; d >= 0; d--){
            int[] counter = new int[R + 1];

            for(int i = 0; i < size; i++)
                counter[array[i].charAt(d)+1]++;

            for(int i=0; i<R; i++)
                counter[i+1] += counter[i];


            for(int i = 0; i < array.length; i++) {
                aux[counter[array[i].charAt(d)]++] = array[i];
            }

            for(int i = 0; i < array.length; i++) {
                array[i] = aux[i];
            }

        }



    }

    public static void main(String[] args) {
        String[] names = new String[]{
            "DAB",
            "ADD",
            "CAB",
            "FAD",
            "FEE",
            "BAD",
            "DAD",
            "BEE",
            "FED",
            "BED",
            "EBB",
            "ACE"
        };

        MyLSD.sort(names, 3);

        for (String s : names)
            System.out.println(s);

    }
}