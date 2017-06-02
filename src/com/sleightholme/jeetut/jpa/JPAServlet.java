package com.sleightholme.jeetut.jpa;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sleightholme.jeetut.util.ExtendedServlet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceUnit;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Servlet implementation class Manager
 */
@WebServlet("/Jpa/Manager")
public class JPAServlet extends ExtendedServlet {
	private static final long serialVersionUID = 1L;
       
        @Resource
        UserTransaction utx;
        
	@EJB
        Manager man;
        
        EntityManager em;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JPAServlet() {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		title = "JPA Manager";
		super.init(config);
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                header();
                em = man.getEntityManager();
                utx.begin();
                out.println("<p>Creating a new user and basket</p>");
                User user = new User("jonathan", "jonathan.coustick@payara.fish");
                Basket basket = new Basket();
                user.setBasket(basket);
                out.println("<p>Persisting classess</p>");
                em.persist(user);
                //em.persist(basket); Not needed as Basket in user is cascaded
                out.println("<p>Committing</p>");
                utx.commit();
                
                out.println("Retrieving data: User 1");
                User retrievedUser = em.find(User.class, 1);
                out.println("<p>" + retrievedUser.getUsername() + "</br>");
                out.println(retrievedUser.getEmail() + "</br>");
                out.println(retrievedUser.getRegistrationDate().toString() + "</br>");
                out.println("BasketID: " + retrievedUser.getBasket().getID() + "</p>");
            } catch (NotSupportedException | SystemException ex) {
                out.println(ex.toString());
                Logger.getLogger(JPAServlet.class.getName()).log(Level.SEVERE, null, ex);
                out.println(ex.toString());
            } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex){
                out.println(ex.toString());
                Logger.getLogger(JPAServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex){
                out.println(ex.toString());
            }
            out.println("<p>Finished</p>");
            super.footer();
	}
	
	

}
