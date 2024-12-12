import java.util.Scanner;
import java.util.Vector;

public class Main {
  public static void main(String[] args) {
    Design.printArtSideBySide();
    LibraryManager admin = new LibraryManager();
    boolean running = true;
    Scanner sc = new Scanner(System.in);

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

    // admin.bookIssue(bookList, studentList, 35, "test");

    while (running) {
      Design.getMenu();
      System.out.println("Menu:");
      System.out.println("1. Add Student");
      System.out.println("2. Add Book");
      System.out.println("3. Add Journal");
      System.out.println("4. Add Faculty");
      System.out.println("5. Issue Book (Student || Teacher)");
      System.out.println("6. Return Book (Student || Teacher)");
      System.out.println("7. Issue Journal");
      System.out.println("8. Return Journal");
      System.out.println("9. Exit");
      System.out.println("10. Health Check");
      System.out.print("Enter your choice: ");

      int choice = sc.nextInt();

      switch (choice) {
        case 1:
          addStudent(studentList);
          break;
        case 2:
          addBook(bookList);
          break;
        case 3:
          addJounral(journalList);
          break;
        case 4:
          addFaculty(facultyList);
          break;
        case 5:
          System.out.println("Enter S for Student T for Teacher: ");
          String memeberchoice = sc.next().toLowerCase();
          if (memeberchoice.equals("t")) {
            System.out.println("Enter Faculty ID: ");
            Integer id = sc.nextInt();
            sc.nextLine(); // Specifically, when you use sc.nextInt(), it reads the integer input, but when
                           // you call sc.nextLine() immediately afterward, it consumes the remaining
                           // newline character from the previous input, causing sc.nextLine() to read an
                           // empty line instead of the book name.
            System.out.println("Enter Book Name: ");
            String bookName = sc.nextLine();
            admin.bookFacultyIssue(bookList, facultyList, id, bookName);
          } else if (memeberchoice.equals("s")) {
            System.out.println("Enter Student ID: ");
            Integer id = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Book Name: ");
            String bookName = sc.nextLine();
            admin.bookIssue(bookList, studentList, id, bookName);
          }
          break;
        case 6:
          System.out.println("Enter S for Student T for Teacher: ");
          String heirarchychoice = sc.next().toLowerCase();
          if (heirarchychoice.equals("t")) {
            System.out.println("Enter Faculty ID: ");
            Integer id = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Book Name: ");
            String bookName = sc.nextLine();
            admin.returnFacultyBook(bookList, facultyList, id, bookName);
          } else if (heirarchychoice.equals("s")) {
            System.out.println("Enter Student ID: ");
            Integer id = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter Book Name: ");
            String bookName = sc.nextLine();
            admin.returnBook(bookList, studentList, id, bookName);
          }
          break;
        case 7:
          System.out.println("Enter Faculty ID: ");
          Integer id = sc.nextInt();
          sc.nextLine();
          System.out.println("Enter journal Name: ");
          String journalName = sc.nextLine();
          admin.journalIssue(journalList, facultyList, id, journalName);
          break;
        case 8:
          System.out.println("Enter Faculty ID: ");
          Integer identificationNumber = sc.nextInt();
          sc.nextLine();
          System.out.println("Enter journal Name: ");
          String journal = sc.nextLine();
          admin.returnJournal(journalList, facultyList, identificationNumber, journal);
          break;
        case 9:
          System.out.println("Exiting....");
          running = false;
          break;
        case 10:
          System.out.println("Health Check");
          printList(bookList, studentList, journalList, facultyList);
          break;
        default:
          break;
      }
    }
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
    String name = sc.next();
    System.out.println("Dept: ");
    String dept = sc.next();
    System.out.println("Roll No: ");
    Integer id = sc.nextInt();
    Student student = new Student(name, dept, id);
    studentList.add(student);
    System.out.println(student);
  }

  /**
   * adding memeber tot the faculty list
   * 
   * @param facultyList
   */

  public static void addFaculty(Vector<Faculty> facultyList) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter Faculty detials: ");
    System.out.println("Name: ");
    String name = sc.next();
    System.out.println("Dept: ");
    String dept = sc.next();
    System.out.println("Faculty ID: ");
    Integer id = sc.nextInt();
    Faculty faculty = new Faculty(name, dept, id);
    facultyList.add(faculty);
    System.out.println(faculty);
  }

  /**
   * add book to the book list vector
   * 
   * @param bookList
   */

  public static void addBook(Vector<Book> bookList) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter Book detials: ");
    System.out.println("Author: ");
    String author = sc.next();
    System.out.println("Title: ");
    String title = sc.next();
    System.out.println("Publication date: ");
    String date = sc.next();
    Book book = new Book(title, author, date);
    bookList.add(book);
    System.out.println(book);
  }

  /**
   * Adds journal to the list
   * 
   * @param journalList
   */

  public static void addJounral(Vector<Journal> journalList) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter Journal detials: ");
    System.out.println("Author: ");
    String author = sc.next();
    System.out.println("Title: ");
    String title = sc.next();
    System.out.println("Publication date: ");
    String date = sc.next();
    Journal journal = new Journal(title, author, date);
    journalList.add(journal);
    System.out.println(journal);
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
