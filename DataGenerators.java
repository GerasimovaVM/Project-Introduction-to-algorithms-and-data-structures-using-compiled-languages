import java.util.Scanner;
import java.util.Random;

public class DataGenerators {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("1 - 0/1 Knapsack");
        System.out.println("2 - Longest Common Subsequence");
        System.out.println("3 - Matric Chain Multiplication");
        System.out.print("Choose the generator (1, 2, 3): ");

        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                System.out.print("Enter the amount of items: ");
                int Kn = scanner.nextInt();
                System.out.print("Enter the max value of weights: ");
                int wein = scanner.nextInt();
                System.out.print("Enter the max value of values: ");
                int valn = scanner.nextInt();

                if (Kn > 1 && wein > 1 && valn > 1) {
                    int[] weights = new int[Kn];
                    int[] values = new int[Kn];

                    for (int i = 0; i < Kn; i++) {
                        weights[i] = 1 + random.nextInt(wein);
                        values[i] = 1 + random.nextInt(valn);
                    }

                    System.out.println("Weights are: ");
                    for (int i = 0; i < Kn; i++) {
                        System.out.print(weights[i]);
                        if (i < Kn - 1) System.out.print(", ");
                    }
                    System.out.println();

                    System.out.println("Values are: ");
                    for (int i = 0; i < Kn; i++) {
                        System.out.print(values[i]);
                        if (i < Kn - 1) System.out.print(", ");
                    }
                } else System.out.println("Error! Invalid input!");
                break;

            case 2:
                String A = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                StringBuilder s1 = new StringBuilder();
                StringBuilder s2 = new StringBuilder();

                System.out.print("Enter the first line's length: ");
                int n1 = scanner.nextInt();
                System.out.print("Enter the second line's length: ");
                int n2 = scanner.nextInt();

                if(n1 > 0 && n2 > 0){
                    for(int i = 0; i < n1; i++) s1.append(A.charAt(random.nextInt(A.length())));
                    for(int i = 0; i < n2; i++) s2.append(A.charAt(random.nextInt(A.length())));

                    System.out.println("First string: " + s1);
                    System.out.println("Second string: " + s2);
                }
                else System.out.println("Error! Invalid input!");
                break;

            case 3:
                System.out.print("Enter the amount of matrices: ");
                int Mn = scanner.nextInt();
                System.out.print("Enter the max size of matric: ");
                int s = scanner.nextInt();

                if(Mn > 1 && s > 0){
                    int [] ms = new int[Mn + 1];

                    for (int i = 0; i <= Mn; i++){
                        ms[i] = 1 + random.nextInt(s);
                    }

                    System.out.println("Generated matric dimensions: ");
                    for(int i = 0; i <= Mn; i++){
                        System.out.print(ms[i]);
                        if(i < Mn) System.out.print(", ");
                    }
                }
                else System.out.println("Error! Invalid input!");
                break;

            default:
                System.out.println("Error! Invalid input!");
        }
    }
}
