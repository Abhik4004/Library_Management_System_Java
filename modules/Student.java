import java.util.Vector;

import Parent.Member;

public class Student extends Member {
  private Integer studentID;
  public int MAX_BOOK_ALLOWED = 5;
  Vector<Book> booksIssued = new Vector<>();
  Vector<Transaction> transactions = new Vector<>();

  public Student(String name, String department, Integer studentId) {
    super(name, department);
    this.studentID = studentId;
    System.out.println("Student Item is created: ");
  }

  public Integer getStudentID() {
    return studentID;
  }

  public String toString() {
    return "Student ID: " + studentID + ", Name: " + getName() + ", Department: " + getDepartment() +
        ", Max Books Allowed: " + MAX_BOOK_ALLOWED + ", Books Issued: " + booksIssued.size() +
        ", Transactions: " + transactions.size();
  }
}
