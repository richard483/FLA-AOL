import facade.MenuFacade;
import repository.DBConnection;

public class Main {
    // what to do:
    // 1. tentuin proxy buat DB
    // 2. buat facade
    // public DBConnection dbConnection = new DBConnection();
    MenuFacade menuFacade = new MenuFacade();

    public Main() {
        // try {
        // dbConnection.resultset = dbConnection.getData("SELECT * FROM `short-link`");

        // while (dbConnection.resultset.next()) {
        // System.out.println(dbConnection.resultset.getString("link_name"));
        // }
        // } catch (Exception e) {
        // System.out.println(e);
        // }

        menuFacade.showWelcomeMenu();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        new Main();

    }
}
