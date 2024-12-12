import java.util.Scanner;
import java.util.Vector;

public class Main {
  public static void main(String[] args) {
    Design.printArtSideBySide();
    LibraryManager admin = new LibraryManager();

    Vector<Book> bookList = new Vector<>();
    bookList.add(new Book("test", "test", "20-24-12"));
    bookList.add(new Book("Java Programming", "John Doe", "01-01-2020"));
    bookList.add(new Book("Data Structures", "Jane Smith", "15-05-2018"));
    bookList.add(new Book("Algorithms", "Robert Sedgewick", "10-10-2015"));
    bookList.add(new Book("Clean Code", "Robert C. Martin", "01-08-2008"));
    bookList.add(new Book("Design Patterns", "Erich Gamma", "21-10-1994"));
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Vector<Journal> journalList = new Vector<>();
    journalList.add(new Journal("Journal of Computer Science", "Alice Johnson", "01-01-2021"));
    journalList.add(new Journal("Journal of AI Research", "Bob Brown", "15-03-2020"));
    journalList.add(new Journal("Journal of Software Engineering", "Carol White", "10-07-2019"));
    journalList.add(new Journal("Journal of Data Science", "David Green", "05-05-2018"));
    journalList.add(new Journal("Journal of Cybersecurity", "Eve Black", "20-12-2017"));
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    Vector<Student> studentList = new Vector<>();
    studentList.add(new Student("test", "IT", 34));
    studentList.add(new Student("Alice", "Computer Science", 101));
    studentList.add(new Student("Bob", "Electrical Engineering", 102));
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    admin.bookIssue(bookList, studentList, 34, "test");
    System.out.println("///////////");
    printList(bookList, studentList);
    System.out.println("///////////");
    admin.returnBook(bookList, studentList, 34, "test");
    System.out.println("///////////");
    printList(bookList, studentList);
    System.out.println("///////////");

  }

  public static void addStudent(Vector<Student> studentList) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter student detials: ");
    System.out.println("Name: ");
    String name = sc.nextLine();
    System.out.println("Dept: ");
    String dept = sc.nextLine();
    System.out.println("Roll No: ");
    Integer id = sc.nextInt();
    Student student = new Student(name, dept, id);
    System.out.println(student);
    studentList.add(student);
    System.out.println(student);
  }

  public static void addBook(Vector<Book> bookList) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter student detials: ");
    System.out.println("Author: ");
    String author = sc.nextLine();
    System.out.println("Title: ");
    String title = sc.nextLine();
    System.out.println("Publication date: ");
    String date = sc.nextLine();
    Book book = new Book(title, author, date);
    bookList.add(book);
    System.out.println(book);
  }

  public static void printList(Vector<Book> bookList, Vector<Student> studentList) {
    System.out.println("Books:");
    for (Book book : bookList) {
      System.out.println(book);
    }

    System.out.println("\nStudents:");
    for (Student student : studentList) {
      System.out.println(student);
    }

  }
}
