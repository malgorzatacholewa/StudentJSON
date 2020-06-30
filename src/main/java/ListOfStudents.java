import java.util.ArrayList;
import java.util.List;

public class ListOfStudents {
    private List<Student> list;

    public ListOfStudents() {
        this.list = new ArrayList<>();
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    public List<Student> getList() {
        return list;
    }

    public void add (Student student){
        this.list.add(student);
    }

    public void cancel (Student student){
        for (Student student1 : list){
            if (student1.getName().equals(student.getName()) && student1.getSurname().equals(student.getSurname())){
                list.remove(student1);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Lista studentów: \n");
        int i = 1;
        for (Student student : list) {
            stringBuilder.append(i + ". " + student.getName() + " " + student.getSurname() + "\n");
            i++;
        }
        return stringBuilder.toString();
    }
}
