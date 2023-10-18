package tests;

import com.aqa.dto.Student;
import com.aqa.util.HibernateClient;
import com.aqa.util.JDBCClient;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DataBaseTest {

    @Test
    public void dbTest() {

        List<Student> studentsList = JDBCClient.executeQuery("SELECT * FROM student",
                (resultSet) -> {
            List<Student> studentArrayList = new ArrayList<>();
            try {
                while (resultSet.next()) {
                   studentArrayList.add(new Student().setId(resultSet.getInt("id"))
                                 .setName(resultSet.getString("name"))
                                 .setGroupNumber(resultSet.getInt("groupNumber")));
                }
                return studentArrayList;
            } catch (SQLException sqlException) {
                log.error(sqlException.getMessage());
                return new ArrayList<>();
            }
        });
        System.out.println(studentsList);
    }

    @Test
    public void hibernateDbTest() {
        Student st = null;
        try {
            st = HibernateClient.findById(2);
            if (st == null)
                System.out.println("Student's ID is equal to NULL");
            } catch (Exception e) {
            System.out.println("OOps ;(");
            log.error(e.getMessage());
        }
        Assert.assertEquals(st.getName(), "IVAN", "Student with id=1 is not Ivan");
    }
}