package com.turing_careers;

import com.turing_careers.data.dao.DeveloperDAO;
import com.turing_careers.data.dao.EmployerDAO;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Employer;
import com.turing_careers.logic.auth.Argon2Encryption;

import java.util.List;

public class PasswordEncryptor {
    public static void main(String[] args) {
        DeveloperDAO devdao = DeveloperDAO.getInstance();
        EmployerDAO empdao = EmployerDAO.getInstance();

        List<Developer> devs = devdao.getDevelopers();
        List<Employer> emps = empdao.getEmployers();

        Argon2Encryption encryptor = new Argon2Encryption();

        for (Developer dev : devs) {
            dev.setPassword(encryptor.encrypt(dev.getPassword()));
            try {
                devdao.updateDeveloper(dev);
            } catch (PersistenceException e) {
                throw new RuntimeException(e);
            }
        }

        for (Employer emp : emps) {
            emp.setPassword(encryptor.encrypt(emp.getPassword()));
            try {
                empdao.updateEmployer(emp);
            } catch (PersistenceException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
