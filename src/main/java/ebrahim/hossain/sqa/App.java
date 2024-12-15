package ebrahim.hossain.sqa;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int N = scanner.nextInt();
        scanner.close();

        if((N%2) == 0){
            System.out.println("Not Weird");
        }else{
            System.out.println("Weird");
        }

    }
}
