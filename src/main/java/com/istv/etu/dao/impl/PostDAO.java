package com.istv.etu.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.istv.etu.dao.IPostDAO;
import com.istv.etu.model.Post;

@Repository
public class PostDAO implements IPostDAO {
	
	public List<Post> getPosts(int idSujet) {
		List<Post> posts = new ArrayList<Post>();
		
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
		    resultat = statement.executeQuery("select * from post where fk_idSujet=" + idSujet + ";");
		    
		    
		    // Récupération des données du résultat de la requête de lecture 
	        while ( resultat.next() ) {
	            Post p = new Post();
	        	
	            p.setContenu(resultat.getString("contenu"));
	            p.setDatePost(resultat.getDate("datePost"));
	            posts.add(p);
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
	    
	    return posts;
	}
}
