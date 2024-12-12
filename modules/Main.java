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
    studentList.add(new Student("John Doe", "Information Technology", 35));
    studentList.add(new Student("Alice Smith", "Computer Science", 101));
    studentList.add(new Student("Bob Johnson", "Electrical Engineering", 102));
    studentList.add(new Student("Charlie Brown", "Mechanical Engineering", 103));
    studentList.add(new Student("Diana Prince", "Civil Engineering", 104));
    studentList.add(new Student("Eve Adams", "Biotechnology", 105));

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    Vector<Faculty> facultyList = new Vector<>();
    facultyList.add(new Faculty("Jamson", "Information Technology", 110));
    facultyList.add(new Faculty("Alice", "Computer Science", 111));
    facultyList.add(new Faculty("Robert", "Mechanical Engineering", 112));
    facultyList.add(new Faculty("Emily", "Civil Engineering", 113));
    facultyList.add(new Faculty("Daniel", "Electrical Engineering", 114));

  }

  /**
   * Add students to the student list vector
   * 
   * @param studentList
   */

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

  /**
   * add book to the book list vector
   * 
   * @param bookList
   */

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

  /**
   * Prints out the list
   * 
   * @param bookList
   * @param studentList
   * @param facultyList
   * @param journalList
   */

  public static void printList(Vector<Book> bookList, Vector<Student> studentList, Vector<Journal> journalList,
      Vector<Faculty> facultyList) {
    System.out.println("Books:");
    for (Book book : bookList) {
      System.out.println(book);
    }

    System.out.println("\nStudents:");
    for (Student student : studentList) {
      System.out.println(student);
    }

    System.out.println("\nTeachers:");
    for (Faculty faculty : facultyList) {
      System.out.println(faculty);
    }

    System.out.println("\nJournals:");
    for (Journal journal : journalList) {
      System.out.println(journal);
    }
  }
}
