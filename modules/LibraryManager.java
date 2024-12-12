import java.time.LocalDate;
import java.util.Iterator;
import java.util.Vector;

public class LibraryManager {

  // Keeps History of the previous records
  Vector<Transaction> HistoricalDate = new Vector<>();
  Vector<Transaction> Current = new Vector<>();

  /**
   * Issues a book to the student
   * 
   * @param bookList
   * @param studentList
   * @param studentID
   * @param bookName
   */
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

  /**
   * return the books by the student
   * 
   * @param bookList
   * @param studentList
   * @param studentID
   * @param bookName
   */

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

        LocalDate returnDate = transaction.getReturnDate();

        if (returnDate.isBefore(today) || returnDate.equals(today)) {
          System.out.println(returnDate.isBefore(today));
          long overdueDays = today.toEpochDay() - returnDate.toEpochDay();
          if (overdueDays > 0) {
            System.out.println("Returned before due date. No fine required.");
            System.out.println("Days returned early: " + overdueDays);
          } else {
            System.out.println("Returned on time. No fine required.");
          }
        }

        // If returned after due date
        else if (today.isAfter(returnDate)) {
          long overdueDays = today.toEpochDay() - returnDate.toEpochDay();
          long finePerDay = 20;
          long totalFine = finePerDay * overdueDays;
          System.out.println("Returned after due date. Fine: " + totalFine + " rs");
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

  /**
   * Finds the student
   * 
   * @param studentID
   * @param studentList
   * @return student if matches the id
   */

  private Student findStudentByID(Integer studentID, Vector<Student> studentList) {
    for (Student student : studentList) {
      if (student.getStudentID().equals(studentID)) {
        return student;
      }
    }
    return null;
  }

  /**
   * Finds the book
   * 
   * @param bookName
   * @param bookList
   * @return books
   */

  private Book findBookByName(String bookName, Vector<Book> bookList) {
    for (Book book : bookList) {
      if (book.getTitle().equals(bookName)) {
        return book;
      }
    }
    return null;
  }

  /**
   * Transaction details of Student
   * 
   * @param student
   * @param book
   * @return transaction details
   */

  private Transaction getTransactionBook(Student student, Book book) {
    for (Transaction transaction : student.transactions) {
      if (transaction.getBook().equals(book)) {
        return transaction;
      }
    }
    return null;
  }

  /**
   * Transaction details of Faculty
   * 
   * @param faculty
   * @param book
   * @return transaction of faculty
   */

  private Transaction getTransactionFaculty(Faculty faculty, Book book) {
    for (Transaction transaction : faculty.transactions) {
      if (transaction.getBook().equals(book)) {
        return transaction;
      }
    }
    return null;
  }

  /**
   * Issues the journal
   * 
   * @param journalList
   * @param facultyList
   * @param facultyID
   * @param journalName
   */

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

  /**
   * Returns Journal
   * 
   * @param journalList
   * @param facultyList
   * @param facultyID
   * @param journalName
   */

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
        if (transaction.getReturnDate().equals(today) || transaction.getReturnDate().isBefore(today)) {
          System.out.println("Returned in before " + (transaction.getReturnDate().toEpochDay() - today.toEpochDay()));
        } else if (transaction.getReturnDate().isAfter(today)) {
          long overDue = today.toEpochDay() - transaction.getReturnDate().toEpochDay();
          System.out.println("You are " + overDue + " days late");
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

  /**
   * Finds faculty member
   * 
   * @param facultyID
   * @param facultyList
   * @return returns faculty member object
   */

  private Faculty findFacultyByID(Integer facultyID, Vector<Faculty> facultyList) {
    for (Faculty faculty : facultyList) {
      if (faculty.getFacultyID().equals(facultyID)) {
        return faculty;
      }
    }
    return null;
  }

  /**
   * Finds journal
   * 
   * @param journalName
   * @param journalList
   * @return journal object
   */

  private Journal findJournalByName(String journalName, Vector<Journal> journalList) {
    for (Journal journal : journalList) {
      if (journal.getTitle().equals(journalName)) {
        return journal;
      }
    }
    return null;
  }

  /**
   * Searches Transaction details
   * 
   * @param faculty
   * @param journal
   * @return transaction object
   */
  private Transaction getTransaction(Faculty faculty, Journal journal) {
    for (Transaction transaction : faculty.transactions) {
      if (transaction.getJournal().equals(journal)) {
        return transaction;
      }
    }
    return null;
  }

  /**
   * Issues book to faculty
   * 
   * @param bookList
   * @param facultyList
   * @param facultyID
   * @param bookName
   */

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

  /**
   * Returns book from the faculty
   * 
   * @param bookList
   * @param facultyList
   * @param facultyID
   * @param bookName
   */

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
