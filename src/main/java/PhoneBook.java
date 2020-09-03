import java.io.*;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.exit;
import static java.util.regex.Pattern.*;

public class PhoneBook {

    private static final Scanner in = new Scanner(System.in);
    private Hashtable<String, String> contacts = new Hashtable();

    public void showPhoneBook() {
        showMenu();
        System.out.println("Please select menu item:");
        while (true) {
            try {
                int item = in.nextInt();
                switch (item) {
                    case 1:
                        System.out.println("You selected: " + item);
                        addContact();
                        showMenu();
                        break;
                    case 2:
                        System.out.println("You selected: " + item);
                        findContact();
                        showMenu();
                        break;
                    case 3:
                        System.out.println("You selected: " + item);
                        deleteContact();
                        showMenu();
                        break;
                    case 4:
                        System.out.println("You selected: " + item);
                        listContacts();
                        showMenu();
                        break;
                    case 5:
                        System.out.println("You selected: " + item);
                        saveContactsToFile();
                        exit(0);
                        break;
                    default:
                        System.out.println("You selected wrong item, try again");
                        showMenu();
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Not correct item? try again");
                showMenu();
            }
        }
    }

    private void showMenu() {
        System.out.println("\tMenu\n1 - add\n2 - find\n3 - del\n4 - list\n5 - exit");
    }

    private void addContact() {
        System.out.println("Please enter phone number(dd-dd-dd for example)");
        String phoneNumber = in.next();
        if (checkPhoneNumberWithRegExp(phoneNumber)) {
            System.out.println("Please enter name of contact:");
            String name = in.next();
            if (checkNameWithRegExp(name)) {
                contacts.put(phoneNumber, name);
            } else {
                System.out.println("Wrong name, try again");
            }
        } else {
            System.out.println("Wrong phone number? try again");
        }


    }

    private void findContact() {

        if (contacts.isEmpty()) {
            loadContactsFromFile();
        }
        if (contacts.size() == 0) {
            System.out.println("Phone book is empty");
        } else {

            System.out.println("Please enter phone number to search(dd-dd-dd for example)");
            String phoneNumber = in.next();

            if (checkPhoneNumberWithRegExp(phoneNumber)) {
                System.out.println(contacts.getOrDefault(phoneNumber, "Sorry, contact not found"));
            } else {
                System.out.println("Wrong phone number, try again");
            }
        }

    }

    private void deleteContact() {
        if (contacts.isEmpty()) {
            loadContactsFromFile();
        }
        if (contacts.size() == 0) {
            System.out.println("Phone book is empty");
        } else {
            System.out.println("Which contact do you want to remove from Phone Book?(dd-dd-dd for example)");
            String phoneNumber = in.next();

            if (checkPhoneNumberWithRegExp(phoneNumber) && contacts.containsKey(phoneNumber)) {
                contacts.remove(phoneNumber);
            } else {
                System.out.println("Wrong phone number, try again");
            }
        }
    }

    private void listContacts() {
        if (contacts.isEmpty()) {
            loadContactsFromFile();
        }
        if (contacts.size() == 0) {
            System.out.println("Phone book is empty");
        } else {
            for (Map.Entry entry : contacts.entrySet()) {
                System.out.println("Phone number: " + entry.getKey() + "; Name: " + entry.getValue());
            }
        }
    }

    private void saveContactsToFile() {
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("PhoneBook.dat"))) {
            writer.writeObject(contacts);
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }

    private void loadContactsFromFile() {
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream("PhoneBook.dat"))) {
            contacts = ((Hashtable<String, String>) reader.readObject());
        } catch (IOException e) {
            System.out.println("file not found");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean checkPhoneNumberWithRegExp(String phoneNumber) {
        Pattern pattern = compile("\\d{2}-\\d{2}-\\d{2}");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    private boolean checkNameWithRegExp(String name) {
        Pattern pattern = compile("\\D\\w{0,14}");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

}
