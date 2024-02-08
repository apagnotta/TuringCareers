package com.turing_careers.presentation;

import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Employer;
import com.turing_careers.logic.user.UserNotValidException;
import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet(name = "AuthenticationServlet", value = "/AuthenticationServlet")
public class AuthenticationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authType = request.getParameter("authType");
        String userType = request.getParameter("userType");

        boolean authOutcome = false;
        
        if (authType.equals("login")) {
            authOutcome = this.loginUser(request, userType);
        } else if (authType.equals("register")) {
            authOutcome = this.registerUser(request, userType);
        }

        if (authOutcome) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("authOutcome", "negative");
            if (authType.equals("login")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
                dispatcher.forward(request, response);
            } else if (authType.equals("register")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("subscription.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    /**
     * TODO:
     * - Implementare Authenticator: classe nel package domain che gestisce logica di login e logout
     * - Implementare DeveloperDAO/Repository e EmployerDAO/Repository: classi che effettuano query al database
     * */
    private boolean loginUser(HttpServletRequest request, String userType) {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        if (userType.equals("developer")) {
            /**
             * La logica per interagire col database deve essere spostata a livello data
             * */
            EntityManagerFactory dev_emf = Persistence.createEntityManagerFactory("turing_careersPU");
            EntityManager dev_em = dev_emf.createEntityManager();
            List<Developer> d = null;
            try {
                /**
                 * La password dovrebbe essere cifrata
                 * */
                d = dev_em.createNamedQuery("findDevsByMailAndPassword", Developer.class).setParameter("mail", mail).setParameter("password", password).getResultList();
            } catch (NoResultException exception) {
                System.out.println("No dev found!!!");
                exception.printStackTrace();

                /**
                 * gli errori vengono segnalati tramite exception
                 * */
                return false;
            }
        } else if (userType.equals("employer")) {
            /**
             * Stessa cosa di sopra, inoltre andrebbe creato un meccanismo per astrarre il processo essendo identico
             * */
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("turing_careersPU");
            EntityManager em = emf.createEntityManager();
            List<Employer> e = null;
            try {
                e = em.createNamedQuery("findEmplsByMailAndPassword", Employer.class).setParameter("mail", mail).setParameter("password", password).getResultList();
            } catch (NoResultException exception) {
                System.out.println("No dev founded!!!");
                exception.printStackTrace();
                return false;
            }
            if (e == null || e.size() != 1)
                return false;
            Employer emp = e.get(0);
            HttpSession session = request.getSession();
            session.setAttribute("loggedIn", "true");
            session.setAttribute("user", emp);
            return true;
        }
        return false;
    }

    private boolean registerUser(HttpServletRequest request, String userType) {
        final String firstname = request.getParameter("firstname");
        final String lastname = request.getParameter("lastname");
        final String mail = request.getParameter("email");
        final String password = request.getParameter("password");

        //controlliamo mail e password passati dall'utente per verificare
        //che rispettino il giusto formato
        if (!validate(request)) {
            return false;
        }

        if (userType.equals("developer")) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("turing_careersPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            Developer dev = new Developer();
            tx.begin();
            //strano che la persist non generi eccezioni???? come faccio a sapere che ha
            //avuto effettivamente successo???? devo controllare tramite query???
            em.persist(dev);
            tx.commit();
            //si può effettuare un controllo aggiuntivo cercando il dev per mail e password
            //se la query non genera un eccezzione allora ha trovato una corrispondenza e
            //il commit ha avuto successo
            HttpSession session = request.getSession();
            session.setAttribute("isLoggedIn", "true");
            session.setAttribute("utente", dev);
            return true;
        } else if (userType.equals("employer")) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("turing_careersPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            Employer emp = new Employer();
            tx.begin();
            //strano che la persist non generi eccezzioni???? come faccio a sapere che ha
            //avuto effettivamente successo???? devo controllare tramite query???
            em.persist(emp);
            tx.commit();
            //si può effettuare un controllo aggiuntivo cercando il dev per mail e password
            //se la query non genera un eccezzione allora ha trovato una corrispondenza e
            //il commit ha avuto successo
            HttpSession session = request.getSession();
            session.setAttribute("isLoggedIn", "true");
            session.setAttribute("utente", emp);
            return true;
        }
        return false;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //vuoto
    }

    private boolean validate(HttpServletRequest request) {

        final String firstname = request.getParameter("firstname");
        final String lastname = request.getParameter("lastname");
        final String mail = request.getParameter("email");
        final String password = request.getParameter("bio");
        final String bio = request.getParameter("password");
        Pattern mailPattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");

        if(firstname.length() == 0
                || firstname.length() > 32
                || lastname.length() == 0
                || lastname.length() > 64
                || bio.length() > 2048
                || mail.length() == 0
                || !mailPattern.matcher(mail).matches()
        ) return false;
        return true;
    }
}