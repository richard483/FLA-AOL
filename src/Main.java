import repository.DBConnection;

public class Main {
    // what to do:
    // 1. tentuin proxy buat DB
    // 2. buat facade
    public DBConnection dbConnection = new DBConnection();

    public Main() {
        try {
            dbConnection.resultset = dbConnection.getData("SELECT * FROM users");

            while (dbConnection.resultset.next()) {
                System.out.println(dbConnection.resultset.getString("username"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        new Main();

    }
}
