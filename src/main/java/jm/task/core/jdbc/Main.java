package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;



public class Main {


    public static void main(String[] args)  {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Max", "Pasevich", (byte) 21);
        userService.saveUser("Svetlana", "Grizheliyk", (byte) 18);
        userService.saveUser("Ivan", "Ivanov", (byte) 99);
        userService.saveUser("Oleg", "OLegov", (byte) 99);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }

}
