package model;

import java.util.UUID;

public class Application {

   public static int ELEMENT_BY_PAGE = 10;
   private String appOwner;
   private String name;
   private String description;

   private int keyAPI;
   private int keySecret;
   
   public Application(String appOwner ,String name, String description) {
      this.appOwner = appOwner;
      this.name = name;
      this.description = description;
      
      // create unique number or something else with keyAPI et keySecret
      keyAPI = UUID.randomUUID().hashCode();
      keySecret =  UUID.randomUUID().hashCode();
   }
   
   public String getName() { return name; }
   public String getDescription() { return description; }

   public String getAppOwner() {
      return appOwner;
   }

   public int getKeyAPI() {
      return keyAPI;
   }

   public int getKeySecret() {
      return keySecret;
   }

   // In order to change the keys when we get  the data from
   // DB and create a new app with the data
   public void setKeyAPI(int keyAPI) {
      this.keyAPI = keyAPI;
   }

   public void setKeySecret(int keySecret) {
      this.keySecret = keySecret;
   }
}