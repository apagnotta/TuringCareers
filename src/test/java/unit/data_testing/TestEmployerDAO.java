package unit.data_testing;

import com.turing_careers.data.dao.EmployerDAO;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.Employer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestEmployerDAO {
    @Test
    public void addEmployerTest()
    {
        EmployerDAO employerDAO = EmployerDAO.getInstance();
        Employer employer = new Employer(
                "Antonio",
                "Pagnotta",
                "diocan@gmail.com",
                "123456",
                "Tony Loaf Inc."
        );

        try {
            employerDAO.addEmployer(employer);
        } catch (PersistenceException ex) {
            Assertions.fail();
        }

        List<Employer> employers = employerDAO.getEmployers();
        Assertions.assertFalse(employers.isEmpty());
        System.out.println(employers);
    }
}
