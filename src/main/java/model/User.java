package model;

public class User {

   private String firstName;
   private String email;
   private String password;
   private String lastName;
   private int isBeingReseted;
   private int isDisabled;
   private int isAdmin;
   public static int ELEMENT_BY_PAGE = 10;

   public User() {} // Empty constructor for JPA entity

   public User(String firstName,String lastName, String email, String password, int isBeingReseted, int isDisabled, int isAdmin) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.password = password;
      this.isBeingReseted = isBeingReseted;
      this.isDisabled = isDisabled;
      this.isAdmin = isAdmin;
   }

   public String getFirstName() {
      return firstName;
   }

   public String getEmail() {
      return email;
   }

   public String getPassword() {
      return password;
   }

   public String getLastName() {
      return lastName;
   }

   public int getIsBeingReseted() {
      return isBeingReseted;
   }

   public int getIsDisabled() {
      return isDisabled;
   }

   public int getIsAdmin() {
      return isAdmin;
   }
}
