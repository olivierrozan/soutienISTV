package com.istv.etu.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.istv.etu.dao.IImagesDAO;
import com.istv.etu.model.Image;

@Repository
public class ImagesDAO implements IImagesDAO {
	
	public List<Image> getImages(int idCours) {
		List<Image> images = new ArrayList<Image>();
		
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
		    resultat = statement.executeQuery("select * from image where fk_idCours=" + idCours + ";");
		    
		    // Récupération des données du résultat de la requête de lecture 
	        while ( resultat.next() ) {
	            Image i = new Image();
	        	
	            i.setLocation(resultat.getString("location"));
	            i.setOrdre(resultat.getInt("ordreImage"));
	            images.add(i);
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
	    
	    return images;
	}
	
	public void createImage(Image i) {
		
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
		    
		    String sql = "insert into image(location,ordreImage) values('" + i.getLocation() + "','" + i.getOrdre() + "')";
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
