package challenges.arrays;

public class MyMatrixRotation {

    private int [][] matrix;
    private int size;

    public MyMatrixRotation(int size) {
        this.size = size;
        this.matrix = new int[size][size];
        int value = 1;
        for(int i = 0; i < size; i++)
            for(int j=0; j < size; j++)
                matrix[i][j] = value++;
    }

    public void printMatrix(){
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
                System.out.print(String.format("\t%d", matrix[i][j]));
            System.out.println();
        }
    }

    public void rotate90Degrees(){
        for (int layer=0; layer<size/2; layer++) {
            int first = layer;
            int last = size-1-layer;
            for (int idx = layer; idx < last; idx++){
                int offset = idx - first;

                // top temp var
                int top = matrix[first][idx]; //layer+idx

                // left to top
                matrix[first][idx] = matrix[last-offset][first];

                // bottom to left
                matrix[last-offset][first] = matrix[last][last-offset];

                // right to bottom
                matrix[last][last-offset] = matrix[idx][last];

                // top to right
                matrix[idx][last] = top;
            }
        }
    }

    public static void main(String[] args) {
        MyMatrixRotation mr = new MyMatrixRotation(5);
        mr.printMatrix();
        mr.rotate90Degrees();
        System.out.println();
        mr.printMatrix();
        mr.rotate90Degrees();
        System.out.println();
        mr.printMatrix();
    }
}
