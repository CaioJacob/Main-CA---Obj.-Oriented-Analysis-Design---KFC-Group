/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package adminsoftware;

/**
 * Main entrypoint for the AdminSoftware console application.
 * Kept minimal: initialize UserManager and ConsoleMenu, then run menu.
 */
public class AdminSoftware {

    public static void main(String[] args) {

        // Initialize application components
        UserManager userManager = new UserManager();
        ConsoleMenu consoleMenu = new ConsoleMenu(userManager);

        // Start interactive console menu
        consoleMenu.displayMenu();
    }

}
