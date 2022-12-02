import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

class home {
    String choice, temp;
    Scanner input = new Scanner(System.in);

    home() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***\n");
        frontSide();
    }

    void frontSide() {
        System.out.println("\tViwe Current Status( status )...");
        System.out.println("\tLogIn To The UU Library( signin )...");
        System.out.println("\tJoin UU Library( signup )...");
        System.out.println("\tExit From UU Library( exit )...");
        System.out.print("Type Command (status/signin/signup/exit): ");
        choice = input.nextLine();
        switch (choice) {
            case "status":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library - Status ***");
                status();
                frontSide();
                break;
            case "signin":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library - Sign In ***");
                signin();
                break;
            case "signup":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library - Sign Up ***");
                signup();
                break;
            case "exit":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library ***");
                System.out.println("\t\t\t\t\t\t\" Today a reader tomorrow a leader \"");
                break;
            default:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***");
                System.out.println("\t\t\t\t\t\t  Invalid Command ! Try Again...");
                frontSide();
        }
    }

    void status() {
        int lines = 0;
        try {
            File file1 = new File("security.txt");
            Scanner sc1 = new Scanner(file1);
            File file2 = new File("booksInfo.txt");
            Scanner sc2 = new Scanner(file2);
            File file3 = new File("magazineInfo.txt");
            Scanner sc3 = new Scanner(file3);
            while (sc1.hasNext()) {
                lines++;
                temp = sc1.nextLine();
            }
            System.out.println("Total Users : " + lines);
            lines = 0;
            while (sc2.hasNext()) {
                lines++;
                temp = sc2.nextLine();
            }
            System.out.println("Total Books : " + lines);
            lines = 0;
            while (sc3.hasNext()) {
                lines++;
                temp = sc3.nextLine();
            }
            System.out.println("Total Magazins : " + lines + "\n");
            sc1.close();
            sc2.close();
            sc3.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void signin() {
        System.out.println("Enter Your ID : ");
        String ID = input.nextLine();
        System.out.println("Enter Your Password : ");
        String passWord = input.nextLine();
        try {
            File file = new File("security.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String id = sc.next();
                temp = sc.next();
                String pass = sc.next();
                if (id.compareTo(ID) == 0) {
                    if (pass.compareTo(passWord) == 0) {
                        System.out.println("Access granted!...");
                        break;
                    } else {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***");
                        System.out.println("\t\t\t\t\t\t  Invalid Password! Try Again...");
                        frontSide();
                    }
                }
            }
            // {
            // System.out.print("\033[H\033[2J");
            // System.out.flush();
            // System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***");
            // System.out.println("\t\t\t\t\t\t Invalid User Name! Try Again...");
            // frontSide();
            // }
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void signup() {
        String id, userName, pass;
        System.out.println("Enter Your ID : ");
        id = input.nextLine();
        System.out.println("Enter Your Short name : ");
        userName = input.nextLine();
        System.out.println("Enter Your Password : ");
        pass = input.nextLine();
        try {
            FileWriter fileWriter = new FileWriter("security.txt", true);
            fileWriter.write("\n" + id+" ");
            fileWriter.write(userName+" ");
            fileWriter.write(pass);
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***");
            System.out.println("\t\t\t\t\t\t\t---Sign Up Successful---");
            System.exit(1);
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        home h = new home();
    }
}