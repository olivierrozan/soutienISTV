package com.istv.etu.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.istv.etu.dao.ISujetsDAO;
import com.istv.etu.model.Sujet;
import com.istv.etu.model.User;

@Repository
public class SujetsDAO implements ISujetsDAO {
	
	public List<Sujet> getSujets() {
		List<Sujet> sujets = new ArrayList<Sujet>();
		
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
		    //resultat = statement.executeQuery("select * from sujet;");
		    resultat = statement.executeQuery("select s.idSujet, s.etatSujet, s.titreSujet, MAX(p.datePost) as dateDernPost, u.login from sujet s, post p, user u where s.idSujet=p.fk_idSujet and p.fk_idUser=u.idUser group by s.titreSujet");
		    
		    
		    // Récupération des données du résultat de la requête de lecture 
	        while ( resultat.next() ) {
	            Sujet s = new Sujet();
	        	
	            s.setId(resultat.getInt("idSujet"));
	            s.setTitre(resultat.getString("titreSujet"));
	            s.setEtat(resultat.getString("etatSujet"));
	            
	            User u = new User();
	            u.setLogin(resultat.getString("login"));
	            u.setDateDerniereModif(resultat.getDate("dateDernPost"));
	            s.setUser(u);
	            sujets.add(s);
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
	    
	    return sujets;
	}
}
