import java.time.LocalDate;
import java.util.Iterator;
import java.util.Vector;

public class LibraryManager {

  // Keeps History of the previous records
  Vector<Transaction> HistoricalDate = new Vector<>();
  Vector<Transaction> Current = new Vector<>();

  public void bookIssue(Vector<Book> bookList, Vector<Student> studentList, Integer studentID, String bookName) {
    LocalDate today = LocalDate.now();
    Student student = findStudentByID(studentID, studentList);
    Book book = findBookByName(bookName, bookList);

    if (student == null || book == null) {
      System.out.println("Not Found");
      return;
    }
    if (student.MAX_BOOK_ALLOWED == 0) {
      System.out.println("All books are issued. Return a book to issue a new one.");
      return;
    }

    if (book.isIssued == true) {
      System.out.println("Book already issued");
      return;
    }

    student.booksIssued.add(book);
    student.MAX_BOOK_ALLOWED = student.MAX_BOOK_ALLOWED - 1;
    book.isIssued = true;
    Transaction made = new Transaction("issued", today, today.plusDays(7), book);
    student.transactions.add(made);
    Current.add(made);
    System.out.println("Book is issued");
    System.out.println(made);
  }

  public void returnBook(Vector<Book> bookList, Vector<Student> studentList, Integer studentID, String bookName) {
    LocalDate today = LocalDate.now();
    Student student = findStudentByID(studentID, studentList);
    Book book = findBookByName(bookName, bookList);
    Transaction transaction = getTransactionBook(student, book);

    if (transaction == null) {
      System.out.println("No matching transaction found for return.");
      return;
    }

    if (student == null || book == null) {
      System.out.println("Student or Book not found.");
      return;
    }

    Iterator<Transaction> iterator = Current.iterator();
    while (iterator.hasNext()) {
      Transaction currentTransaction = iterator.next();
      if (currentTransaction.getTransactionsID().equals(transaction.getTransactionsID())) {
        iterator.remove();
        if (transaction.getReturnDate().equals(today)) {
          System.out.println("Returned in time");
        } else {
          System.out.println("Pay the fine");
        }
        student.booksIssued.remove(book);
        student.MAX_BOOK_ALLOWED = student.MAX_BOOK_ALLOWED + 1;
        book.isIssued = false;
        HistoricalDate.add(currentTransaction);
        System.out.println("Book is returned");
        return;
      }
    }
    System.out.println("No matching transaction found for return.");
  }

  private Student findStudentByID(Integer studentID, Vector<Student> studentList) {
    for (Student student : studentList) {
      if (student.getStudentID().equals(studentID)) {
        return student;
      }
    }
    return null;
  }

  private Book findBookByName(String bookName, Vector<Book> bookList) {
    for (Book book : bookList) {
      if (book.getTitle().equals(bookName)) {
        return book;
      }
    }
    return null;
  }

  private Transaction getTransactionBook(Student student, Book book) {
    for (Transaction transaction : student.transactions) {
      if (transaction.getBook().equals(book)) {
        return transaction;
      }
    }
    return null;
  }

  private Transaction getTransactionFaculty(Faculty faculty, Book book) {
    for (Transaction transaction : faculty.transactions) {
      if (transaction.getBook().equals(book)) {
        return transaction;
      }
    }
    return null;
  }

  public void journalIssue(Vector<Journal> journalList, Vector<Faculty> facultyList, Integer facultyID,
      String journalName) {
    LocalDate today = LocalDate.now();
    Faculty faculty = findFacultyByID(facultyID, facultyList);
    Journal journal = findJournalByName(journalName, journalList);

    if (faculty == null || journal == null) {
      System.out.println("Not Found");
      return;
    }
    if (faculty.MAX_BOOK_ALLOWED == 0) {
      System.out.println("All journals are issued. Return a journal to issue a new one.");
      return;
    }

    if (journal.isIssued == true) {
      System.out.println("Journal already issued");
      return;
    }

    faculty.journalsIssued.add(journal);
    faculty.MAX_BOOK_ALLOWED = faculty.MAX_BOOK_ALLOWED - 1;
    journal.isIssued = true;
    Transaction made = new Transaction("issued", today, today.plusDays(7), journal);
    faculty.transactions.add(made);
    Current.add(made);
    System.out.println("Journal is issued");
    System.out.println(made);
  }

  public void returnJournal(Vector<Journal> journalList, Vector<Faculty> facultyList, Integer facultyID,
      String journalName) {
    LocalDate today = LocalDate.now();
    Faculty faculty = findFacultyByID(facultyID, facultyList);
    Journal journal = findJournalByName(journalName, journalList);
    Transaction transaction = getTransaction(faculty, journal);

    if (transaction == null) {
      System.out.println("No matching transaction found for return.");
      return;
    }

    if (faculty == null || journal == null) {
      System.out.println("Faculty or Journal not found.");
      return;
    }

    Iterator<Transaction> iterator = Current.iterator();
    while (iterator.hasNext()) {
      Transaction currentTransaction = iterator.next();
      if (currentTransaction.getTransactionsID().equals(transaction.getTransactionsID())) {
        iterator.remove();
        if (transaction.getReturnDate().equals(today)) {
          System.out.println("Returned in time");
        } else {
          System.out.println("Pay the fine");
        }
        faculty.journalsIssued.remove(journal);
        faculty.MAX_BOOK_ALLOWED = faculty.MAX_BOOK_ALLOWED + 1;
        journal.isIssued = false;
        HistoricalDate.add(currentTransaction);
        System.out.println("Journal is returned");
        return;
      }
    }
    System.out.println("No matching transaction found for return.");
  }

  private Faculty findFacultyByID(Integer facultyID, Vector<Faculty> facultyList) {
    for (Faculty faculty : facultyList) {
      if (faculty.getFacultyID().equals(facultyID)) {
        return faculty;
      }
    }
    return null;
  }

  private Journal findJournalByName(String journalName, Vector<Journal> journalList) {
    for (Journal journal : journalList) {
      if (journal.getTitle().equals(journalName)) {
        return journal;
      }
    }
    return null;
  }

  private Transaction getTransaction(Faculty faculty, Journal journal) {
    for (Transaction transaction : faculty.transactions) {
      if (transaction.getJournal().equals(journal)) {
        return transaction;
      }
    }
    return null;
  }

  public void bookFacultyIssue(Vector<Book> bookList, Vector<Faculty> facultyList, Integer facultyID, String bookName) {
    LocalDate today = LocalDate.now();
    Faculty faculty = findFacultyByID(facultyID, facultyList);
    Book book = findBookByName(bookName, bookList);

    if (faculty == null || book == null) {
      System.out.println("Not Found");
      return;
    }
    if (faculty.MAX_BOOK_ALLOWED == 0) {
      System.out.println("All books are issued. Return a book to issue a new one.");
      return;
    }

    if (book.isIssued == true) {
      System.out.println("Book already issued");
      return;
    }

    faculty.booksIssued.add(book);
    faculty.MAX_BOOK_ALLOWED = faculty.MAX_BOOK_ALLOWED - 1;
    book.isIssued = true;
    Transaction made = new Transaction("issued", today, today.plusDays(7), book);
    faculty.transactions.add(made);
    Current.add(made);
    System.out.println("Book is issued");
    System.out.println(made);
  }

  public void returnFacultyBook(Vector<Book> bookList, Vector<Faculty> facultyList, Integer facultyID,
      String bookName) {
    LocalDate today = LocalDate.now();
    Faculty faculty = findFacultyByID(facultyID, facultyList);
    Book book = findBookByName(bookName, bookList);
    Transaction transaction = getTransactionFaculty(faculty, book);

    if (transaction == null) {
      System.out.println("No matching transaction found for return.");
      return;
    }

    if (faculty == null || book == null) {
      System.out.println("Faculty or Book not found.");
      return;
    }

    Iterator<Transaction> iterator = Current.iterator();
    while (iterator.hasNext()) {
      Transaction currentTransaction = iterator.next();
      if (currentTransaction.getTransactionsID().equals(transaction.getTransactionsID())) {
        iterator.remove();
        if (transaction.getReturnDate().equals(today)) {
          System.out.println("Returned in time");
        } else {
          System.out.println("Pay the fine");
        }
        faculty.booksIssued.remove(book);
        faculty.MAX_BOOK_ALLOWED = faculty.MAX_BOOK_ALLOWED + 1;
        book.isIssued = false;
        HistoricalDate.add(currentTransaction);
        System.out.println("Book is returned");
        return;
      }
    }
    System.out.println("No matching transaction found for return.");
  }

}
