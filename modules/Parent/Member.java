package Parent;

abstract public class Member {
  protected String name;
  protected String department;

  public Member(String name, String department) {
    this.name = name;
    this.department = department;
  }

  public String getName() {
    return this.name;
  }

  public String getDepartment() {
    return this.department;
  }

  public void setName(String Name) {
    this.name = Name;
    System.out.println("Name is changed to " + name);
  }
}
