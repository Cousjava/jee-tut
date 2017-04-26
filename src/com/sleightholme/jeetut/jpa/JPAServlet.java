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
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 * Servlet implementation class Manager
 */
//@PersistenceContext(unitName="jpaexample", name="persistence/em")
@WebServlet("/Jpa/Manager")
public class JPAServlet extends ExtendedServlet {
	private static final long serialVersionUID = 1L;
       
        @Resource
        UserTransaction utx;
        
	@EJB
        Manager man;
        
        //@PersistenceUnit(unitName="jee-tut-SNAPSHOTPU")
        //private EntityManagerFactory emf;
        
        EntityManager em;
	//EntityTransaction tx;
	
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
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");
		//em = emf.createEntityManager();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                super.doGet(request, response);
                em = man.getEntityManager();
                //InitialContext ic = new InitialContext();
                //em = (EntityManager) ic.lookup("java:comp/env/persistence/em");
                //tx = em.getTransaction();
                utx.begin();
                //em = emf.createEntityManager();
                out.println("<p>Creating a new user and basket</p>");
                User user = new User();
                Basket basket = new Basket();
                user.setBasket(basket);
                out.println("<p>Persisting classess</p>");
                em.persist(user);
                em.persist(basket);
                out.println("<p>Committing</p>");
                utx.commit();
                
            } catch (NotSupportedException ex) {
                Logger.getLogger(JPAServlet.class.getName()).log(Level.SEVERE, null, ex);
                out.println(ex.toString());
            } catch (SystemException ex) {
                Logger.getLogger(JPAServlet.class.getName()).log(Level.SEVERE, null, ex);
                out.println(ex.toString());
            } catch (Exception ex){
                out.println(ex.toString());
                Logger.getLogger(JPAServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("<p>finished</p>");
            super.footer();
	}
	
	

}
