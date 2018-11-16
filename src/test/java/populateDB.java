public class populateDB {
    public static void main(String[] args) {
        System.out.println("USE AMT;");
        for(int i = 0 ; i < 100000; i++){
            System.out.println("INSERT INTO amt.dev_users(firstName, lastName, email, passwd, isBeingReseted, isDisabled, isAdmin) VALUES (\"a\",\"a\",\"" + i + "@admin.com\",\"a\",0,0,1);");
        }
    }
}
