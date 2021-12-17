package sample;

public class Persons {
  private String firstName;
  private String lastName;
  private String email;
  private String middleInitial;
  private String password;
  private String gender;
  private String race;
  public Persons(String firstName, String lastName, String email, String middleInitial, String password,String race){
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.middleInitial = middleInitial;
    this.password = password;
    this.race = race;
  }
    public String getFirstName(){
      return firstName;
  }
  public void setFirstName(String firstName){
      this.firstName = firstName;
  }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getMiddleInitial(){
        return middleInitial;
    }
    public void setMiddleInitial(String middleInitial){
        this.middleInitial = middleInitial;
    }
    public String getPassword(){
      return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getGender(){
      return gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public String getRace(){
        return race;
    }
    public void setRace(String race){
        this.race = race;
    }


}

