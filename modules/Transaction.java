import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
  private UUID transactionsID;
  private String type;
  private LocalDate today;
  private LocalDate returnDate;
  public Book book;
  public Journal journal;

  public Transaction(String type, LocalDate today, LocalDate returnDate, Book book) {
    this.transactionsID = UUID.randomUUID(); // Generate a random, unique UUID
    this.type = type;
    this.today = today;
    this.returnDate = returnDate;
    this.book = book;
  }

  public Transaction(String type, LocalDate today, LocalDate returnDate, Journal journal) {
    this.transactionsID = UUID.randomUUID(); // Generate a random, unique UUID
    this.type = type;
    this.today = today;
    this.returnDate = returnDate;
    this.journal = journal;
  }

  public String getTransactionsID() {
    return transactionsID.toString();
  }

  public String getType() {
    return type;
  }

  public LocalDate getToday() {
    return today;
  }

  public Book getBook() {
    return book;
  }

  public Journal getJournal() {
    return journal;
  }

  public LocalDate getReturnDate() {
    return returnDate;
  }

  public String toString() {
    return "Transaction ID: " + transactionsID + ", Type: " + type + ", Date: " + today +
        ", Return Date: " + returnDate;
  }
}
