import java.util.Scanner;

public class Knapsack {
    public static int knapsack(int[] weights, int [] values, int n, int V){
        if (n == 0 || V == 0)
            return 0;
        if (weights[n - 1] > V)
            return knapsack(weights, values, n - 1, V);
        else
            return Math.max(values[n - 1] + knapsack(weights, values, n - 1, V - weights[n - 1]), knapsack(weights, values, n - 1, V));
    }
    public static int knapsackm(int[] weights, int [] values, int n, int V, int[][] memo){
        if (n == 0 || V == 0)
            return 0;
        if (memo[n][V] != -1){
            return memo[n][V];
        }
        if (weights[n - 1] > V){
            memo[n][V] = knapsackm(weights, values, n - 1, V, memo);
        }
        else{
            memo[n][V] = Math.max(values[n - 1] + knapsackm(weights, values, n - 1, V - weights[n - 1], memo), knapsackm(weights, values, n - 1, V, memo));
        }
        return memo[n][V];
    }
    public static int knapsackbu(int[] weights, int [] values, int n, int V){
        int[][] sheet = new int[n + 1][V + 1];

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= V; j++){
                if(weights[i - 1] <= j){
                    sheet[i][j] = Math.max(values[i - 1] + sheet[i - 1][j - weights[i - 1]], sheet[i - 1][j]);
                }
                else{
                    sheet[i][j] = sheet[i - 1][j];
                }
            }
        }
        return sheet[n][V];
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("1 - Without optimization");
        System.out.println("2 - Memoization");
        System.out.println("3 - Bottom-Up");
        System.out.print("Choose the solving (1, 2, 3): ");
        int choice = scanner.nextInt();

        System.out.print("Enter the volume of the knapsack: ");
        int V = scanner.nextInt();
        int [] weights = {}; //Сюда подставляются тестовые данные
        int [] values = {}; //Сюда подставляются тестовые данные

        switch(choice) {
            case 1:
                Runtime runtime1 = Runtime.getRuntime();
                runtime1.gc();

                long ms1 = runtime1.totalMemory() - runtime1.freeMemory();
                long start1 = System.nanoTime();

                int result1 =  knapsack(weights, values, weights.length, V);

                long end1 = System.nanoTime();
                long time1 = (end1 - start1);

                long me1 = runtime1.totalMemory() - runtime1.freeMemory();
                long m1 = me1 - ms1;

                System.out.println("The result is: " + result1);
                System.out.println("The time taken in nanoseconds is: " + time1);
                System.out.println("Memory used in bytes: " + m1);
                break;

            case 2:
                int [][] memo = new int[weights.length + 1][V + 1];
                for(int i = 0; i <= weights.length; i++){
                    for (int j = 0; j <= V; j++)
                        memo[i][j] = -1;
                }

                Runtime runtime2 = Runtime.getRuntime();
                runtime2.gc();

                long ms2 = runtime2.totalMemory() - runtime2.freeMemory();
                long start2 = System.nanoTime();

                int result2 =  knapsackm(weights, values, weights.length, V, memo);

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

                int result3 =  knapsackbu(weights, values, weights.length, V);

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

