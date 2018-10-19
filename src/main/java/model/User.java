package model;

public class User {

   private String name;
   private String email;
   private String password;
   
   public User() {} // Empty constructor for JPA entity
   public User(String name, String email, String password) {
      
      this.name  = name;
      this.email = email;
      this.password = password;
   }
   
   public String getName() { return name; }
   public String getId() { return email; }
   public String getPassword() { return password; }
   
   public void setName(String newName) {
      name = newName;
   }
   
   public void setMail(String newMail) {
      email = newMail;
   }
   
   public void setPassword(String newPassword) {
      password = newPassword;
   }
   
   
}
