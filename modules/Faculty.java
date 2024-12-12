import java.util.Vector;

import Parent.Member;

public class Faculty extends Member {
  private Integer facultyID;
  public int MAX_BOOK_ALLOWED = 10;
  Vector<Book> booksIssued = new Vector<>();
  Vector<Transaction> transactions = new Vector<>();
  Vector<Journal> journalsIssued = new Vector<>();

  public Faculty(
      String name, String department,
      Integer facultyID) {
    super(name, department);
    this.facultyID = facultyID;
    System.out.println("Faculty Item is created: ");
  }

  public Integer getFacultyID() {
    return facultyID;
  }

  public String toString() {
    return "Faculty ID: " + facultyID + ", Name: " + getName() + ", Department: " + getDepartment() +
        ", Max Books Allowed: " + MAX_BOOK_ALLOWED + ", Books Issued: " + booksIssued.size() +
        ", Transactions: " + transactions.size();
  }
}
