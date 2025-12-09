import java.util.Scanner;

public class MatricChainMultiplication {
    public static int matric(int[] p, int n1, int n2){
        if (n1 == n2)
            return 0;

        int m = Integer.MAX_VALUE;

        for(int i = n1; i < n2; i++){
            int c = matric(p, n1, i) + matric(p, i + 1, n2) + p[n1]*p[i + 1]*p[n2];

            if (c < m) {
                m = c;
            }
        }
        return m;
    }
    public static int matricm(int[] p, int n1, int n2, int[][] memo){
        if (n1 == n2)
            return 0;
        if (memo[n1][n2] != -1){
            return memo[n1][n2];
        }

        int m = Integer.MAX_VALUE;

        for(int i = n1; i < n2; i++){
            int c = matricm(p, n1, i, memo) + matricm(p, i + 1, n2, memo) + p[n1]*p[i + 1]*p[n2];

            if (c < m) {
                m = c;
            }
        }

        memo[n1][n2] = m;
        return m;
    }
    public static int matricbu(int[] p){
        int n = p.length - 1;

        int[][] sheet = new int[n + 1][n + 1];

        for(int i = 2; i <= n; i++){
            for(int j = 0; j <= n - i; j++){
                int k = j + i;
                sheet[j][k] = Integer.MAX_VALUE;

                for(int m = j; m < k; m++){
                    int c = sheet[j][m] + sheet[m + 1][k] + p[j]*p[m + 1]*p[k];

                    if(c < sheet[j][k]) sheet[j][k] = c;
                }
            }
        }
        return sheet[0][n];
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("1 - Without optimization");
        System.out.println("2 - Memoization");
        System.out.println("3 - Bottom-Up");
        System.out.print("Choose the solving (1, 2, 3): ");
        int choice = scanner.nextInt();

        int [] matdim = {}; //Сюда подставляются тестовые данные

        switch(choice){
            case 1:
                int n1 = matdim.length - 1;

                Runtime runtime1 = Runtime.getRuntime();
                runtime1.gc();

                long ms1 = runtime1.totalMemory() - runtime1.freeMemory();
                long start1 = System.nanoTime();

                int result1 =  matric(matdim, 0, n1);

                long end1 = System.nanoTime();
                long time1 = (end1 - start1);

                long me1 = runtime1.totalMemory() - runtime1.freeMemory();
                long m1 = me1 - ms1;

                System.out.println("The result is: " + result1);
                System.out.println("The time taken in nanoseconds is: " + time1);
                System.out.println("Memory used in bytes: " + m1);
                break;

            case 2:
                int n2 = matdim.length - 1;

                int [][] memo = new int[matdim.length][matdim.length];
                for(int i = 0; i < matdim.length; i++){
                    for (int j = 0; j < matdim.length; j++)
                        memo[i][j] = -1;
                }

                Runtime runtime2 = Runtime.getRuntime();
                runtime2.gc();

                long ms2 = runtime2.totalMemory() - runtime2.freeMemory();
                long start2 = System.nanoTime();

                int result2 =  matricm(matdim, 0, n2, memo);

                long end2 = System.nanoTime();
                long time2 = (end2 - start2);

                long me2 = runtime2.totalMemory() - runtime2.freeMemory();
                long m2 = me2 - ms2;

                System.out.println("The result is: " + result2);
                System.out.println("The time taken in nanoseconds is: " + time2);
                System.out.println("Memory used in bytes: " + m2);
                break;

            case 3:
                Runtime runtime3 = Runtime.getRuntime();
                runtime3.gc();

                long ms3 = runtime3.totalMemory() - runtime3.freeMemory();
                long start3 = System.nanoTime();

                int result3 =  matricbu(matdim);

                long end3 = System.nanoTime();
                long time3 = (end3 - start3);

                long me3 = runtime3.totalMemory() - runtime3.freeMemory();
                long m3 = me3 - ms3;

                System.out.println("The result is: " + result3);
                System.out.println("The time taken in nanoseconds is: " + time3);
                System.out.println("Memory used in bytes: " + m3);
                break;

            default:
                System.out.println("Error! Invalid input!");
        }
    }
}


