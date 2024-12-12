import Parent.Item;

public class Book extends Item {
  private Integer id;
  public boolean isIssued = false;
  private static Integer nextId = 1;

  public Book(String name, String author, String date) {
    super(name, author, date);
    this.id = nextId++;
    // System.out.println("Book item is created: ");
  }

  public Integer getBookId() {
    return this.id;
  }

  @Override
  public String toString() {
    return "Book ID: " + id + ", Title: " + getTitle() + ", Author: " + getAuthor() + ", Publication Date: "
        + getPublicationDate() + ", Is Issued: " + isIssued;
  }
}
