import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

class data {
    ArrayList<String> dataId = new ArrayList<String>();
    ArrayList<String> dataName = new ArrayList<String>();
    ArrayList<String> dataPass = new ArrayList<String>();
    ArrayList<String> bookName = new ArrayList<String>();
    ArrayList<String> bookPage = new ArrayList<String>();
    ArrayList<String> bookAuthor = new ArrayList<String>();
    ArrayList<String> availability = new ArrayList<String>();
    ArrayList<String> dataTemp = new ArrayList<String>();

    data() {
        try {
            File dataFile = new File("security.txt");
            Scanner datasc = new Scanner(dataFile);
            File bookDataFile = new File("booksInfo.txt");
            Scanner bookDatasc = new Scanner(bookDataFile);
            while (datasc.hasNext()) {
                dataId.add(datasc.next());
                dataName.add(datasc.next());
                dataPass.add(datasc.next());
            }
            while (bookDatasc.hasNextLine()) {
                bookName.add(bookDatasc.nextLine());
                bookAuthor.add(bookDatasc.nextLine());
                bookPage.add(bookDatasc.nextLine());
                availability.add(bookDatasc.nextLine());
            }
            datasc.close();
            bookDatasc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}

class home extends data {
    String choice, temp, id, pass;
    int counter = 0;
    Scanner input = new Scanner(System.in);

    home() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***\n");
        frontSide();
    }

    void frontSide() {
        System.out.println("\tView Current Status( status )...");
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
            System.out.println("Total Users : " + dataId.size());
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
        id = input.nextLine();
        counter = 0;
        for (int i = 0; i < dataId.size(); i++) {
            if (dataId.get(i).compareTo(id) == 0) {
                System.out.println("Enter Your Password : ");
                pass = input.nextLine();
                if (dataPass.get(i).compareTo(pass) == 0) {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                    user();
                    break;
                } else {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***");
                    System.out.println("\t\t\t\t\t\t  Invalid Password! Try Again...");
                    frontSide();
                }
            }
            counter++;
        }
        if (counter >= dataId.size()) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***");
            System.out.println("\t\t\t\t\t\t Invalid User Name! Try Again...");
            frontSide();
        }
    }

    void signup() {
        String id, userName, pass;
        System.out.println("Enter Your ID : ");
        id = input.nextLine();
        for (int i = 0; i < dataId.size(); i++) {
            if (dataId.get(i).compareTo(id) == 0) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***");
                System.out.println("\t\t\t\t\t   This Id Is Alrady Present! Try Again...");
                frontSide();
            }
        }
        dataId.add(id);
        System.out.println("Enter Your Short name : ");
        userName = input.nextLine();
        dataName.add(userName);
        System.out.println("Enter Your Password : ");
        pass = input.nextLine();
        dataPass.add(pass);
        try {
            FileWriter fileWriter = new FileWriter("security.txt", true);
            fileWriter.write(id + " " + userName + " " + pass + "\n");
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("\t\t\t\t\t\t    *** Welcome To UU Library ***");
            System.out.println("\t\t\t\t\t\t      ---Sign Up Successful---");
            frontSide();
            fileWriter.close();
            System.exit(1);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void user() {
        System.out.println("Change Name ==> (change_name)");
        System.out.println("Change Password ==> (change)");
        System.out.println("My Books ==> (my)");
        System.out.println("Availbale Book List ==> (books)");
        System.out.println("Search Book ==> (search)");
        System.out.println("Exit ==>(exit)");
        choice = input.nextLine();
        switch (choice) {
            case "change_name":
                try {
                    String chid = "", chpass = "";
                    FileWriter fileWriter = new FileWriter("security.txt", true);
                    File file = new File("security.txt");
                    Scanner sc = new Scanner(file);
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library ***");
                    System.out.println("Enter Password : ");
                    temp = input.nextLine();
                    System.out.println(pass);
                    if (temp.compareTo(pass) == 0) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\t\t\t\t\t\t\t*** UU Library ***");
                        System.out.println("Enter New Name : ");
                        String tempname = input.nextLine();
                        System.out.println("Enter Confirm Name : ");
                        temp = input.nextLine();
                        if (tempname.compareTo(temp) == 0) {
                            for (int i = 0; i < dataId.size(); i++) {
                                if (pass.compareTo(dataPass.get(i)) == 0) {
                                    dataTemp.add(tempname);
                                } else {
                                    dataTemp.add(dataName.get(i));
                                }
                            }
                            dataName.clear();
                            for (int i = 0; i < dataTemp.size(); i++) {
                                dataName.add(dataTemp.get(i));
                            }
                            dataTemp.clear();
                            FileWriter fileWriter1 = new FileWriter("temp.txt");
                            File old = new File("security.txt");
                            Scanner scold = new Scanner(old);
                            while (scold.hasNext()) {
                                String tempid = scold.next();
                                temp = scold.next();
                                pass = scold.next();
                                if (id.compareTo(tempid) != 0) {
                                    fileWriter1.write(tempid + " " + temp + " " + pass + "\n");
                                } else {
                                    chid = tempid;
                                    chpass = pass;
                                }
                            }
                            fileWriter1.close();
                            scold.close();
                            try {
                                FileWriter fileWriter2 = new FileWriter("security.txt");
                                File nw = new File("temp.txt");
                                Scanner nwsc = new Scanner(nw);
                                while (nwsc.hasNext()) {
                                    String tempid = nwsc.next();
                                    temp = nwsc.next();
                                    pass = nwsc.next();
                                    fileWriter2.write(tempid + " " + temp + " " + pass + "\n");
                                }
                                fileWriter2.write(chid + " " + tempname + " " + chpass + "\n");
                                if (nw.delete()) {
                                    System.out.println("File is deleted");
                                }
                                fileWriter2.close();
                                nwsc.close();
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("\t\t\t\t\t\t\t*** UU Library ***");
                                user();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                    fileWriter.close();
                    sc.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            case "change":
                try {
                    String chid = "", chname = "";
                    FileWriter fileWriter = new FileWriter("security.txt", true);
                    File file = new File("security.txt");
                    Scanner sc = new Scanner(file);
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library ***");
                    System.out.println("Enter Password : ");
                    temp = input.nextLine();
                    System.out.println(pass);
                    if (temp.compareTo(pass) == 0) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\t\t\t\t\t\t\t*** UU Library ***");
                        System.out.println("Enter New Password : ");
                        String tempPass = input.nextLine();
                        System.out.println("Enter Confirm Password : ");
                        temp = input.nextLine();
                        if (tempPass.compareTo(temp) == 0) {
                            for (int i = 0; i < dataId.size(); i++) {
                                if (pass.compareTo(dataPass.get(i)) == 0) {
                                    dataTemp.add(tempPass);
                                } else {
                                    dataTemp.add(dataPass.get(i));
                                }
                            }
                            dataPass.clear();
                            for (int i = 0; i < dataTemp.size(); i++) {
                                dataPass.add(dataTemp.get(i));
                            }
                            dataTemp.clear();
                            FileWriter fileWriter1 = new FileWriter("temp.txt");
                            File old = new File("security.txt");
                            Scanner scold = new Scanner(old);
                            while (scold.hasNext()) {
                                String tempid = scold.next();
                                temp = scold.next();
                                pass = scold.next();
                                if (id.compareTo(tempid) != 0) {
                                    fileWriter1.write(tempid + " " + temp + " " + pass + "\n");
                                } else {
                                    chid = tempid;
                                    chname = temp;
                                }
                            }
                            fileWriter1.close();
                            scold.close();
                            try {
                                FileWriter fileWriter2 = new FileWriter("security.txt");
                                File nw = new File("temp.txt");
                                Scanner nwsc = new Scanner(nw);
                                while (nwsc.hasNext()) {
                                    String tempid = nwsc.next();
                                    temp = nwsc.next();
                                    pass = nwsc.next();
                                    fileWriter2.write(tempid + " " + temp + " " + pass + "\n");
                                }
                                fileWriter2.write(chid + " " + chname + " " + tempPass + "\n");
                                if (nw.delete()) {
                                    System.out.println("File is deleted");
                                }
                                fileWriter2.close();
                                nwsc.close();
                                System.out.print("\033[H\033[2J");
                                System.out.flush();
                                System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***");
                                frontSide();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                    fileWriter.close();
                    sc.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
            case "my":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library ***");
                System.out.println("My Books : ");
                counter = 0;
                for (int i = 0; i < bookAuthor.size(); i++) {
                    if (availability.get(i).compareTo("my") == 0) {
                        System.out.println(
                                "\t" + i + " " + bookName.get(i) + " (" + bookPage.get(i) + ")\n\t\""
                                        + bookAuthor.get(i) + "\"");
                        counter++;
                    }
                }
                if (counter <= 0) {
                    System.out.println("\tEmpty.");
                    System.out.println("Press \"#\" To Go Back.");
                    System.out.print("Enter Command : ");
                    choice = input.nextLine();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                    user();
                } else {
                    System.out.println("Press \"#\" To Go Back.");
                    System.out.println("Enter Book ID To Return It.");
                    System.out.print("Enter Command : ");
                    choice = input.nextLine();
                    if (choice.compareTo("#") == 0) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                        user();
                    } else {
                        for (int i = 0; i < bookName.size(); i++) {
                            temp = Integer.toString(i);
                            if (choice.compareTo(temp) == 0) {
                                availability.set(i, "available");
                            }
                        }
                    }
                    try {
                        FileWriter myFile = new FileWriter("booksInfo.txt");
                        for (int i = 0; i < bookName.size(); i++) {
                            myFile.write(bookName.get(i) + "\n");
                            myFile.write(bookAuthor.get(i) + "\n");
                            myFile.write(bookPage.get(i) + "\n");
                            myFile.write(availability.get(i) + "\n");
                        }
                        myFile.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                    System.out.println("\t\t\t\t\t\t\t--- Return Successfully ---");
                    user();
                }

                break;
            case "books":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library ***");
                System.out.println("Available Books : ");
                counter = 0;
                for (int i = 0; i < bookAuthor.size(); i++) {
                    if (availability.get(i).compareTo("available") == 0) {
                        System.out.println(
                                "\t" + i + " " + bookName.get(i) + " (" + bookPage.get(i) + ")\n\t\""
                                        + bookAuthor.get(i) + "\"");
                        counter++;
                    }
                }
                if (counter <= 0) {
                    System.out.println("\tEmpty.");
                    System.out.println("Press \"#\" To Go Back.");
                    System.out.print("Enter Command : ");
                    choice = input.nextLine();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                    user();
                } else {
                    System.out.println("Press \"#\" To Go Back.");
                    System.out.println("Enter Book ID To Take It.");
                    System.out.print("Enter Command : ");
                    choice = input.nextLine();
                    if (choice.compareTo("#") == 0) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                        user();
                    } else {
                        for (int i = 0; i < bookName.size(); i++) {
                            temp = Integer.toString(i);
                            if (choice.compareTo(temp) == 0) {
                                availability.set(i, "my");
                            }
                        }
                    }
                    try {
                        FileWriter myFile = new FileWriter("booksInfo.txt");
                        for (int i = 0; i < bookName.size(); i++) {
                            myFile.write(bookName.get(i) + "\n");
                            myFile.write(bookAuthor.get(i) + "\n");
                            myFile.write(bookPage.get(i) + "\n");
                            myFile.write(availability.get(i) + "\n");
                        }
                        myFile.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                    System.out.println("\t\t\t\t\t\t\t    --- Book Added ---");
                    user();
                }
                break;
            case "search":
                String word;
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library ***");
                System.out.println("Enter Book Name : ");
                choice = input.nextLine();
                int indexi = choice.indexOf(" ");
                if (indexi < 0) {
                    word = choice;
                } else {
                    word = choice.substring(0, indexi);
                }
                System.out.println("Books : ");
                counter = 0;
                for (int i = 0; i < bookName.size(); i++) {
                    int booki = bookName.get(i).indexOf(" ");
                    if (availability.get(i).compareTo("my") != 0) {
                        if (booki < 0) {
                            String com = bookName.get(i);
                            if (word.compareTo(com) == 0) {
                                counter++;
                                System.out.println("\t" + i + " " + bookName.get(i) + " (" + bookPage.get(i) + ")\n\t\""
                                        + bookAuthor.get(i) + "\" " + "<" + availability.get(i) + ">");
                            }
                        } else {
                            String com = bookName.get(i).substring(0, booki);
                            if (word.compareTo(com) == 0) {
                                counter++;
                                System.out.println("\t" + i + " " + bookName.get(i) + " (" + bookPage.get(i) + ")\n\t\""
                                        + bookAuthor.get(i) + "\" " + "<" + availability.get(i) + ">");
                            }
                        }
                    }
                }
                if (counter == 0) {
                    System.out.println("\tBook Not Found.");
                    System.out.println("Press \"#\" To Go Back.");
                    System.out.print("Enter Command : ");
                    choice = input.nextLine();
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                    System.out.println("\t\t\t\t\t\t\t    --- Book Added ---");
                    user();
                } else {
                    System.out.println("Press \"#\" To Go Back.");
                    System.out.println("Enter Book ID To Take It.");
                    System.out.print("Enter Command : ");
                    choice = input.nextLine();
                    if (choice.compareTo("#") == 0) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
                        System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                        user();
                    } else {
                        for (int i = 0; i < bookName.size(); i++) {
                            temp = Integer.toString(i);
                            if (choice.compareTo(temp) == 0) {
                                availability.set(i, "my");
                            }
                        }
                    }
                    try {
                        FileWriter myFile = new FileWriter("booksInfo.txt");
                        for (int i = 0; i < bookName.size(); i++) {
                            myFile.write(bookName.get(i) + "\n");
                            myFile.write(bookAuthor.get(i) + "\n");
                            myFile.write(bookPage.get(i) + "\n");
                            myFile.write(availability.get(i) + "\n");
                        }
                        myFile.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    System.out.println("\t\t\t\t\t\t\t*** UU Library-Inventory ***");
                    System.out.println("\t\t\t\t\t\t\t    --- Book Added ---");
                    user();
                }
                break;
            case "exit":
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t\t*** UU Library ***");
                System.out.println("\t\t\t\t\t\t\" Today a reader tomorrow a leader \"");
                System.exit(1);
                break;
            default:
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("\t\t\t\t\t\t*** Welcome To UU Library ***");
                System.out.println("\t\t\t\t\t\t  Invalid Command ! Try Again...");
                user();
        }

    }

    void admin() {

    }
}

public class Main {
    public static void main(String[] args) {
        new data();
        new home();
    }
}
