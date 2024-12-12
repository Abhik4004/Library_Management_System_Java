import Parent.Item;

public class Journal extends Item {
  private Integer id;
  public boolean isIssued = false;
  private static Integer nextId = 1;

  public Journal(String name, String author, String date) {
    super(name, author, date);
    this.id = nextId++;
    // System.out.println("Journal item is created: ");
  }

  public Integer getJournalId() {
    return this.id;
  }

  public String toString() {
    return "Journal ID: " + id + ", Title: " + getTitle() + ", Author: " + getAuthor() + ", Publication Date: "
        + getPublicationDate() + ", Is Issued: " + isIssued;
  }
}
