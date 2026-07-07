import java.sql.*;
import java.util.*;

public class MARIA {
    public static void main(String[] args) {
        Connection conn = null;
        Scanner input = new Scanner(System.in);
        String url = "jdbc:mariadb://localhost:3306/project";
        String user = "root";
        String pwd = "sqlpassword";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            Statement stmt = conn.createStatement();
            System.out.println("Connected to the database successfully.");

            while (true) {
                System.out.println("\n==== Station Table Menu ====");
                System.out.println("1. Insert new record.");
                System.out.println("2. Display all records.");
                System.out.println("3. Exit.");
                System.out.print("Choose an operation (^_^): ");
                String choice = input.nextLine();

                switch (choice) {
                    case "1":
                        while (true) {
                            System.out.println("\nStation Insertion:");
                            String postalCode, type, workingHours;

                            System.out.print("Input Name: ");
                            String name = input.nextLine();
                            if (name.trim().equals("")) {
                                System.out.println("Error: Station name cannot be empty (Primary Key).");
                                continue;
                            }

                            System.out.print("Input Street Name: ");
                            String streetName = input.nextLine();

                            while (true) {
                                System.out.print("Input Postal Code: ");
                                postalCode = input.nextLine();
                                try {
                                    Integer.parseInt(postalCode);
                                } catch (NumberFormatException e) {
                                    System.out.println("Error: Postal code must contain only digits.");
                                    continue;
                                }
                                if (postalCode.length() != 5) {
                                    System.out.println("Error: Postal code must be exactly 5 digits.");
                                    continue;
                                }
                                break;
                            }

                            while (true) {
                                System.out.print("Input Type: ");
                                type = input.nextLine();
                                if (!type.equalsIgnoreCase("Metro") && !type.equalsIgnoreCase("Bus")) {
                                    System.out.println("Error: Station type must be either 'Metro' or 'Bus'.");
                                    continue;
                                } else break;
                            }

                            while (true) {
                                System.out.print("Input Working Hours (e.g., 6 AM - 11 PM): ");
                                workingHours = input.nextLine();
                                if (!workingHours.contains("-")) {
                                    System.out.println("Error: Working hours must contain '-' between start and end time.");
                                    continue;
                                }
                                try {
                                    int dashIndex = workingHours.indexOf("-");
                                    String partBeforeDash = workingHours.substring(0, dashIndex);
                                    String partAfterDash = workingHours.substring(dashIndex + 2);

                                    int startHour = Integer.parseInt("" + partBeforeDash.charAt(0));
                                    if (Character.isDigit(partBeforeDash.charAt(1))) {
                                        startHour = Integer.parseInt("" + partBeforeDash.charAt(0) + partBeforeDash.charAt(1));
                                    }

                                    int endHour = Integer.parseInt("" + partAfterDash.charAt(0));
                                    if (Character.isDigit(partAfterDash.charAt(1))) {
                                        endHour = Integer.parseInt("" + partAfterDash.charAt(0) + partAfterDash.charAt(1));
                                    }

                                    if (startHour < 1 || startHour > 12 || endHour < 1 || endHour > 12) {
                                        System.out.println("Error: Working hours must be between 1 and 12.");
                                        continue;
                                    }
                                } catch (Exception e) {
                                    System.out.println("Error: Invalid working hours format.");
                                    continue;
                                }
                                break;
                            }

                            String sql = "INSERT INTO Station (Name, Street_Name, Postal_Code, Type, Working_Hours) " +
                                    "VALUES ('" + name + "','" + streetName + "','" + postalCode + "','" + type + "','" + workingHours + "')";
                            try {
                                stmt.executeUpdate(sql);
                                System.out.println("Record inserted successfully.");
                            } catch (SQLException e) {
                                System.out.println("Insertion error: " + e.getMessage());
                            }

                            System.out.print("Insert another record? (Y/N): ");
                            String again = input.nextLine();
                            if (!again.equalsIgnoreCase("Y"))
                                break;
                        }
                        break;

                    case "2":
                        try {
                            ResultSet rs = stmt.executeQuery("SELECT * FROM Station");
                            System.out.println("\n--- Station Records ---");
                            while (rs.next()) {
                                System.out.println(
                                        rs.getString("Name") + "\t" +
                                        rs.getString("Street_Name") + "\t" +
                                        rs.getString("Postal_Code") + "\t" +
                                        rs.getString("Type") + "\t" +
                                        rs.getString("Working_Hours"));
                            }
                        } catch (SQLException e) {
                            System.out.println("Error displaying records: " + e.getMessage());
                        }
                        break;

                    case "3":
                        System.out.println("Exiting...");
                        input.close();
                        stmt.close();
                        conn.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }
}
