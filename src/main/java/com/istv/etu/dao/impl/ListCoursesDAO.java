package com.istv.etu.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.istv.etu.model.Cours;
import com.istv.etu.dao.IListCoursesDAO;

@Repository
public class ListCoursesDAO implements IListCoursesDAO {
	
	@PersistenceContext
    private EntityManager em;
	
	public List<Cours> getCourses() {
		List<Cours> cours = new ArrayList<Cours>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3307/istv";
		String utilisateur = "root";
		String motDePasse = "efficient";
		
		Connection connexion = null;
		Statement statement = null;
	    ResultSet resultat = null;
	    
	    try {
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
			
		    statement = connexion.createStatement();		    
		    resultat = statement.executeQuery("select * from cours;");
		    
		    // Récupération des données du résultat de la requête de lecture 
	        while ( resultat.next() ) {
	            Cours c = new Cours();
	        	
	            c.setIdCours(resultat.getInt("idCours"));
	            c.setLibelleCours(resultat.getString( "libelleCours" ));
	            c.setDateDerniereModif(resultat.getDate("dateDerniereModif"));
	            cours.add(c);
	        }

		} catch ( SQLException e ) {
		    // Gérer les éventuelles erreurs ici 
			System.out.println(e.getMessage());
		} finally {
		    if ( connexion != null )
		        try {
		            // Fermeture de la connexion 
		            connexion.close();
		        } catch ( SQLException ignore ) {
		            // Si une erreur survient lors de la fermeture, il suffit de l'ignorer. 
		        }
		}
	    
	    return cours;
	}
	
	public Cours getOneCourse(String id) {
		Cours c = new Cours();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3307/istv";
		String utilisateur = "root";
		String motDePasse = "efficient";
		
		Connection connexion = null;
		Statement statement = null;
	    ResultSet resultat = null;
	    
	    try {
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		    statement = connexion.createStatement();		    
		    resultat = statement.executeQuery("select * from user u, cours c where u.idUser=c.fk_idUser and u.idUser=" + id + ";");
		    
		    c.setIdCours(resultat.getInt("idCours"));
		    c.setLibelleCours(resultat.getString("libelleCours"));
		    c.setDateDerniereModif(resultat.getDate("dateDerniereModif"));

		} catch ( SQLException e ) {
		    // Gérer les éventuelles erreurs ici 
			System.out.println(e.getMessage());
		} finally {
		    if ( connexion != null )
		        try {
		            // Fermeture de la connexion 
		            connexion.close();
		        } catch ( SQLException ignore ) {
		            // Si une erreur survient lors de la fermeture, il suffit de l'ignorer. 
		        }
		}
	    
	    return c;
	}
	
	public int getOneCourseByName(String name) {
		int c = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3307/istv";
		String utilisateur = "root";
		String motDePasse = "efficient";
		
		Connection connexion = null;
		Statement statement = null;
	    ResultSet resultat = null;
	    
	    try {
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		    statement = connexion.createStatement();		    
		    //resultat = statement.executeQuery("select idCours from cours where libelleCours='" + name + "';");
		    resultat = statement.executeQuery("select MAX(idCours) as idCours from cours;");
		    
		    while ( resultat.next() ) {
		    	c = resultat.getInt("idCours");
	        }
		    
		    
		} catch ( SQLException e ) {
		    // Gérer les éventuelles erreurs ici 
			System.out.println(e.getMessage());
		} finally {
		    if ( connexion != null )
		        try {
		            // Fermeture de la connexion 
		            connexion.close();
		        } catch ( SQLException ignore ) {
		            // Si une erreur survient lors de la fermeture, il suffit de l'ignorer. 
		        }
		}
	    
	    return c;
	}
	
	public void createCourse(Cours c, final String id) {
		//em.persist(c);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3307/istv";
		String utilisateur = "root";
		String motDePasse = "efficient";
		
		Connection connexion = null;
		Statement statement = null;
	    
	    try {
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		    statement = connexion.createStatement();
		    
		    String sql = "insert into cours(libelleCours,imageTitre,dateDerniereModif,nbVues,fk_idUser) values('" + c.getLibelleCours() + "','" + c.getImageTitre() + "','2017-01-04','" + c.getNbVues() + "','" + id + "')";
		    statement.executeUpdate(sql);
		            
		} catch ( SQLException e ) {
		    // Gérer les éventuelles erreurs ici 
			System.out.println(e.getMessage());
		} finally {
		    if ( connexion != null )
		        try {
		            // Fermeture de la connexion 
		            connexion.close();
		        } catch ( SQLException ignore ) {
		            // Si une erreur survient lors de la fermeture, il suffit de l'ignorer. 
		        }
		}
	}
}
