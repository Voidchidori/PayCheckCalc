import java.awt.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.Border;

public class PayCheckCalc extends JFrame {
    static double total;
    public static double payPerHour = 15.25;
    public static String file = "balance.txt";
    public static Scanner console = new Scanner(System.in);
    JLabel currentBalance = new JLabel();

    public PayCheckCalc() {
        readFromFile(file);

        // Frame setup
        setTitle("PayCheck Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 200);
        setLayout(new BorderLayout());

        // Border for text field
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);

        // Top label
        JLabel currentBalance = new JLabel("Current Balance: $" + total);
        currentBalance.setHorizontalAlignment(SwingConstants.CENTER);
        add(currentBalance, BorderLayout.NORTH);

        // Panel for input field
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout());
        //get info from user
        JLabel timeQuery = new JLabel("Hours Worked: ");
        JTextField hoursInput = new JTextField(4); // 4 columns
        hoursInput.setPreferredSize(new Dimension(40, 30)); // explicitly set small box
        hoursInput.setBorder(border);

        //String hoursWorked = hoursInput.toString();

        //Confirmation button
        JButton confirmButton = new JButton("Confirm?");
        confirmButton.addActionListener( e -> {
            String input = hoursInput.getText();
            double hoursWorked = Double.parseDouble(input);
            makeNewTotal(hoursWorked, payPerHour);
            currentBalance.setText(String.format("Current Balance: $%.2f", total));
            hoursInput.setText("");
        });


        centerPanel.add(timeQuery);
        centerPanel.add(hoursInput);
        centerPanel.add(confirmButton);
        add(centerPanel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        /*readFromFile(file);
        System.out.println("Your current balance is: "+total);
        System.out.print("How many hours did you work?-: ");
        double hoursWorked = console.nextDouble();
        makeNewTotal(hoursWorked, payPerHour);
        System.out.printf("Your new balance is: $%.2f\n", total);*/
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(UnsupportedLookAndFeelException e){
            System.err.println("!");
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
        new PayCheckCalc();   
    }

    public static void readFromFile(String file) {
        try (Scanner scan = new Scanner(new File(file))) {
            if (scan.hasNextDouble()) {
                total = scan.nextDouble();
            }
        } catch (FileNotFoundException e) {
            System.out.println("debug here");
            //total = 0.0; // Set to 0 if file not found
        }
    }

    public static double makeNewTotal(double hoursWorked, double hourlyPay) {
        total += hoursWorked * hourlyPay;
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.printf("%.2f", total);
        } catch (IOException e) {
            System.out.println("Error saving balance to file.");
            System.exit(1);
        }
        return total;
    }
}
