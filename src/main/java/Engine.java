import java.util.Scanner;

public class Engine {
    CustomerService customerService = new CustomerService();
    ListOfStudents listOfStudents = new ListOfStudents();
    String filePath = "C:\\Users\\malgo\\Desktop\\Kurs Java\\Programowanie II\\Zdalnie\\zajecia1\\student\\listOfStudents.json";


    public void start() {
        listOfStudents = customerService.JSONReader(filePath);
        boolean flag = false;
        while (!flag) {
            Menu.mainMenu();
            int choice = customerService.choice();
            switch (choice) {
                case 0:
                    customerService.JSONWriter(listOfStudents,filePath);
                    System.exit(0);
                    flag = true;
                    break;
                case 1:
                    listOfStudents.add(customerService.addStudents());
                    break;
                case 2:
                    System.out.println(listOfStudents.toString());
                    listOfStudents.cancel(customerService.cancelStudent());
                    break;
                case 3:
                    System.out.println(listOfStudents.toString());
                    break;
                case 4:
                    customerService.JSONWriter(listOfStudents,filePath);
                    break;

            }
        }
    }
}