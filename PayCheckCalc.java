/**
 * PayCheckCalc.java: Base class that recieves money information, and calculates the current amount of money I have
 *
 * @author : Ayden T. LaBaff
 * @email : labaffat208@potdam.edu
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PayCheckCalc {

    static Scanner console = new Scanner(System.in);
    static double total;
    public static double payPerHour = 15.25;
    public static String file = "balance.txt";

    public static void main(String[] args) {
        readFromFile(file);
        System.out.println("Your current balance is: " + total + "\n");
        System.out.print("How many hours did you work?-: ");
        double hoursWorked = console.nextDouble();
        makeNewTotal(hoursWorked, payPerHour);
        System.out.println("Your new balance is: " + total);

    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void readFromFile(String file) {
        try (Scanner scan = new Scanner(new File(file))) {
            if (scan.hasNextDouble()) {
                total = scan.nextDouble();
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public static void clearTotal() {
        total = 0.0;
    }

    public static double makeNewTotal(double hoursWorked, double hourlyPay) {
        total += hoursWorked * hourlyPay;
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.printf("%.2f", total);
        } catch (IOException e) {
            System.out.println("Error saving balance to file.");
        }
        return total;
    }
}
