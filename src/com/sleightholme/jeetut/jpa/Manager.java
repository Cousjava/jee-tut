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

/**
 * Servlet implementation class Manager
 */
@WebServlet("/Jpa/Manager")
public class Manager extends ExtendedServlet {
	private static final long serialVersionUID = 1L;
       
	@PersistenceContext(unitName = "jpaexample")
	EntityManager em;
	EntityTransaction tx;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Manager() {
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
		super.doGet(request, response);
		tx = em.getTransaction();
		tx.begin();
		User user = new User();
		Basket basket = new Basket();
		user.setBasket(basket);
		
		em.persist(user);
		em.persist(basket);
		tx.commit();
		super.footer();
	}
	
	

}
