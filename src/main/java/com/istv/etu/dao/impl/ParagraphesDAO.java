package com.istv.etu.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.istv.etu.dao.IParagraphesDAO;
import com.istv.etu.model.Paragraphe;

@Repository
public class ParagraphesDAO implements IParagraphesDAO {
	
	public List<Paragraphe> getParagraphes(int idCours) {
		List<Paragraphe> par = new ArrayList<Paragraphe>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3307/istv?autoReconnect=true&useSSL=false";
		String utilisateur = "root";
		String motDePasse = "efficient";
		
		Connection connexion = null;
		Statement statement = null;
	    ResultSet resultat = null;
	    
	    try {
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		    statement = connexion.createStatement();		    
		    resultat = statement.executeQuery("select * from paragraphe where fk_idCours=" + idCours + ";");
		    
		    // Récupération des données du résultat de la requête de lecture 
	        while ( resultat.next() ) {
	        	Paragraphe p = new Paragraphe();
	        	if (resultat.getString("texte") != null) {
	        		p.setTexte(resultat.getString("texte").replaceAll("\n", "<br />"));
	        	} else {
	        		p.setTexte(null);
	        	}
	        	
	        	if (resultat.getString("imageLocation") != null) {
	        		p.setImageLocation(resultat.getString("imageLocation"));
	        	} else {
	        		p.setImageLocation(null);
	        	}
	        	
	            p.setOrdre(resultat.getInt("ordreParagraphe"));
	            par.add(p);
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
	    
	    return par;
	}
	
	public void createParagraphe(final Paragraphe p) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String url = "jdbc:mysql://localhost:3307/istv?autoReconnect=true&useSSL=false";
		String utilisateur = "root";
		String motDePasse = "efficient";
		
		Connection connexion = null;
		//Statement statement = null;
	    
	    try {
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		    //statement = connexion.createStatement();
		    
		    String sql = "insert into paragraphe(texte,imageLocation,ordreParagraphe,fk_idCours) values(?,?,?,?)";
		    //statement.executeUpdate(sql);
		    PreparedStatement pstmt = connexion.prepareStatement(sql);
		    pstmt.setString(1, p.getTexte());
		    pstmt.setString(2, p.getImageLocation());
		    pstmt.setInt(3, p.getOrdre());
		    pstmt.setInt(4, p.getIdCours());
		    pstmt.executeUpdate();
		            
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
