/*
Реализовать простой интерактивный телефонный справочник c набором команд:
add phoneNumber name
find phoneNumber
del phoneNumber
list
exit (выход из программы)
где phoneNumber - строка вида dd-dd-dd, name - любая строка состоящая из латинских букв и цифр длинной не более 15 символов и начинающаяся с буквы.
Подсказка: java.util.Scanner, java.util.Hashtable, java regex.
 */

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.showPhoneBook();

    }
}
