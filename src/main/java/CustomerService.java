import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerService {
    Scanner scanner = new Scanner(System.in);

    public int choice() {
        boolean flag = false;
        int number = 0;
        while (!flag) {
            String choice = scanner.nextLine();
            if (!choice.matches("[0-4]")) {
                System.out.println("Nie ma takiego punktu.");
            } else {
                flag = true;
                number = Integer.parseInt(choice);
            }
        }
        return number;
    }

    public Student addStudents(){
        String name = checkDataString("Podaj imię studenta");
        String surname = checkDataString("Podaj nazwisko studenta");
        Student student = new Student(name,surname);
        return student;
    }

    public Student cancelStudent(){
        String name = checkDataString("Podaj imię studenta, którego chcesz usunąć z listy.");
        String surname = checkDataString("Podaj nazwisko studenta, którego chcesz usunąć z listy");
        Student student = new Student(name,surname);
        return student;
    }

    public String checkDataString(String statement) {
        boolean flag = false;
        String name = "";
        while (!flag) {
            System.out.println(statement);
            String choice = scanner.nextLine().trim().toLowerCase();
            if (!choice.matches("[a-złąęćśżźó]+")) {
                System.out.println("Wprowadzono błędne dane.");
            } else {
                flag = true;
                name = choice.substring(0, 1).toUpperCase() + choice.substring(1).toLowerCase();
            }
        }
        return name;
    }

    public void JSONWriter(ListOfStudents listOfStudents, String filePath){
        ObjectMapper mapper = new ObjectMapper();
        try {
            String JSON = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(listOfStudents);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(filePath));
            bufferedWriter.write(JSON);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ListOfStudents JSONReader (String filePath){
//        byte[] bytes;
//        ListOfStudents listOfStudents = new ListOfStudents();
//        try {
//            bytes = Files.readAllBytes(Paths.get(filePath));
//            String fileContents = new String(bytes, "UTF-8");
//            ObjectMapper objectMapper = new ObjectMapper();
//             listOfStudents = objectMapper.readValue(fileContents,ListOfStudents.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//            return listOfStudents.getList();
//        }

        ListOfStudents result = new ListOfStudents();
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(filePath));
            String fileContents = new String(bytes, "UTF-8");

            JSONObject students = new JSONObject(fileContents);

            JSONArray studentsJSONArray = students.getJSONArray("list");

            for (int i = 0; i < studentsJSONArray.length(); i++) {
                Object record = studentsJSONArray.get(i);
                JSONObject studentJSON = new JSONObject(record.toString());
                Student student = new Student(studentJSON.getString("name"),studentJSON.getString("surname"));
                result.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
