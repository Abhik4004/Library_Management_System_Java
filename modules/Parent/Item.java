package Parent;

abstract public class Item {
  protected String title;
  protected String author;
  protected String publicationDate;

  public Item(String title, String author, String publicationDate) {
    this.title = title;
    this.author = author;
    this.publicationDate = publicationDate;
  }

  public String getTitle() {
    return this.title;
  };

  public String getAuthor() {
    return this.author;
  };

  public String getPublicationDate() {
    return this.publicationDate;
  };
}
