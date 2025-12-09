import java.util.Scanner;

public class Subsequence {
    public static int subsequence(String s1, String s2, int n1, int n2){
        if (n1 == 0 || n2 == 0){
            return 0;
        }
        if (s1.charAt(n1 - 1) == s2.charAt(n2 - 1)){
            return 1 + subsequence(s1, s2, n1 - 1, n2 - 1);
        }
        else{
            return Math.max(subsequence(s1, s2, n1 - 1, n2), subsequence(s1, s2, n1, n2 - 1));
        }
    }
    public static int subsequencem(String s1, String s2, int n1, int n2, int[][] memo){
        if (n1 == 0 || n2 == 0)
            return 0;
        if (memo[n1][n2] != -1){
            return memo[n1][n2];
        }
        if (s1.charAt(n1 - 1) == s2.charAt(n2 - 1)){
            memo[n1][n2] = 1 + subsequencem(s1, s2, n1 - 1, n2 - 1, memo);
        }
        else{
            memo[n1][n2] = Math.max(subsequencem(s1, s2, n1 - 1, n2, memo), subsequencem(s1, s2, n1, n2 - 1, memo));
        }
        return memo[n1][n2];
    }
    public static int subsequencebu(String s1, String s2, int n1, int n2){
        int[][] sheet = new int[n1 + 1][n2 + 1];

        for(int i = 1; i <= n1; i++){
            for(int j = 1; j <= n2; j++){
                if(s1.charAt(i - 1) == s2.charAt(j - 1)){
                    sheet[i][j] = 1 + sheet[i - 1][j - 1];
                }
                else{
                    sheet[i][j] = Math.max(sheet[i - 1][j], sheet[i][j - 1]);
                }
            }
        }
        return sheet[n1][n2];
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        String s1 = ""; //Сюда подставляются тестовые данные
        String s2 = ""; //Сюда подставляются тестовые данные

        System.out.println("1 - Without optimization");
        System.out.println("2 - Memoization");
        System.out.println("3 - Bottom-Up");
        System.out.print("Choose the solving (1, 2, 3): ");
        int choice = scanner.nextInt();

        switch(choice){
            case 1:
                Runtime runtime1 = Runtime.getRuntime();
                runtime1.gc();

                long ms1 = runtime1.totalMemory() - runtime1.freeMemory();
                long start1 = System.nanoTime();

                int result1 =  subsequence(s1, s2, s1.length(), s2.length());

                long end1 = System.nanoTime();
                long time1 = (end1 - start1);

                long me1 = runtime1.totalMemory() - runtime1.freeMemory();
                long m1 = me1 - ms1;

                System.out.println("The result is: " + result1);
                System.out.println("The time taken in nanoseconds is: " + time1);
                System.out.println("Memory used in bytes: " + m1);
                break;
            case 2:
                int[][] memo = new int[s1.length() + 1][s2.length() + 1];
                for(int i = 0; i <= s1.length(); i++){
                    for (int j = 0; j <= s2.length(); j++)
                        memo[i][j] = -1;
                }
                Runtime runtime2 = Runtime.getRuntime();
                runtime2.gc();

                long ms2 = runtime2.totalMemory() - runtime2.freeMemory();
                long start2 = System.nanoTime();

                int result2 =  subsequencem(s1, s2, s1.length(), s2.length(), memo);

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

                int result3 =  subsequencebu(s1, s2, s1.length(), s2.length());

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

