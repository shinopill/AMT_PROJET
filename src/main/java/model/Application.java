package model;

import java.util.UUID;

public class Application {
   private String name;
   private String description;
   final private int keyAPI;
   final private int keySecret;
   
   public Application(String name, String description) {
      
      this.name = name;
      this.description = description;
      
      // create unique number or something else with keyAPI et keySecret
      keyAPI = UUID.randomUUID().hashCode();  // change this
      keySecret =  UUID.randomUUID().hashCode(); // change this
   }
   
   public String getName() { return name; }
   public String getDescription() { return description; }

   public void setName(String newName){
      name = newName;
   }
   
   public void setDescription(String newDescription) {
      description = newDescription;
   }
   
}
