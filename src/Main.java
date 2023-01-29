import facade.MenuFacade;

public class Main {

    MenuFacade menuFacade = new MenuFacade();

    public Main() {
        menuFacade.showWelcomeMenu();
    }

    public static void main(String[] args) throws Exception {
        new Main();

    }
}
