/**
 * PayCheckCalc.java: Program that recieves money information, and calculates the current amount of money I have
 *
 * @author : Ayden T. LaBaff
 * @email : labaffat208@potdam.edu
 */
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.*;

public class PayCheckCalc {
    static Scanner console = new Scanner(System.in);
    static double total;
    public static double payPerHour = 15.25;
    public static String file = "balance.txt";

    public PayCheckCalc(){
        JFrame jframe = new JFrame("PayCheckCalculator");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(300,200);

        JLabel currentBalance = new JLabel("Your Current Balance is: "+total);
        JTextField hoursInput = new JTextField("Write How Many Hours You Worked Here!!!");

        jframe.add(currentBalance, BorderLayout.NORTH);
        jframe.add(hoursInput, BorderLayout.CENTER);
        jframe.setVisible(true);   
    }

    public static void main(String[] args) {
        readFromFile(file);
        /*
        System.out.print("How many hours did you work?-: ");
        double hoursWorked = console.nextDouble();
        makeNewTotal(hoursWorked, payPerHour);
        System.out.println("Your new balance is: " + total);
    */
        new PayCheckCalc();
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
            System.out.println("Error saving balance to file. closing program to be safe... oops");
            System.exit(1);
        }
        return total;
    }
}
