package com.turing_careers.logic.auth;

import com.turing_careers.data.dao.EmployerDAO;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.Employer;
import com.turing_careers.data.model.User;
import com.turing_careers.logic.validator.UserValidator;
import com.turing_careers.logic.validator.ValidationException;

import java.security.InvalidParameterException;

/**
 * @author Antonino Lorenzo
 * */
public class EmployerAuthenticator extends Authenticator {

    public EmployerAuthenticator() { super(); }

    @Override
    public void loginUser(String email, String password) throws InvalidCredentialsException {
        super.setEncryptionStrategy(new Argon2Encryption());
        EmployerDAO employerDAO = EmployerDAO.getInstance();

        try {
            Employer emp = employerDAO.getEmployerByMail(email);
            System.out.println(emp);
            String psw = emp.getPassword();

            encryptionStrategy.verify(password, psw);
        } catch (InvalidCredentialsException invalidCredentials) {
            throw new InvalidCredentialsException(invalidCredentials.getMessage());
        }
    }

    @Override
    public void signupUser(User user) throws ValidationException, PersistenceException {
        if (!(user instanceof Employer))
            throw new ValidationException("EmployerAuthService: Not an Employer");
        Employer emp = (Employer) user;
        UserValidator.checkValidity(emp);

        super.setEncryptionStrategy(new Argon2Encryption());
        String encryptedPassword = encryptionStrategy.encrypt(emp.getPassword());
        emp.setPassword(encryptedPassword);

        EmployerDAO employerDAO = EmployerDAO.getInstance();
        employerDAO.addEmployer((Employer) user);
    }
}
