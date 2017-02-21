package com.istv.etu.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		    
		    // R�cup�ration des donn�es du r�sultat de la requ�te de lecture 
	        while ( resultat.next() ) {
	        	Paragraphe p = new Paragraphe();
	        	p.setIdParagraphe(resultat.getInt("idParagraphe"));
	        	
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
		    // G�rer les �ventuelles erreurs ici 
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
	
	public int getOrderMax(final int idCours) {
		
		int orderMax = 0;
		
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
		    resultat = statement.executeQuery("select count(*) as max from paragraphe where fk_idCours=" + idCours + ";");
		    
		    // R�cup�ration des donn�es du r�sultat de la requ�te de lecture 
	        while ( resultat.next() ) {	        		            
	        	orderMax = resultat.getInt("max");
	        }

		} catch ( SQLException e ) {
		    // G�rer les �ventuelles erreurs ici 
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
	    
	    return orderMax;
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
		    pstmt.setInt(4, p.getFk_idCours());
		    pstmt.executeUpdate();
		            
		} catch ( SQLException e ) {
		    // G�rer les �ventuelles erreurs ici 
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
	
	public void updateParagraphe(final Paragraphe par) {
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
	    
	    try {
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		    
		    String sql = "update paragraphe set texte=?,imageLocation=? where idParagraphe=?";
		    PreparedStatement pstmt = connexion.prepareStatement(sql);
		    pstmt.setString(1, par.getTexte());
		    pstmt.setString(2, par.getImageLocation());
		    pstmt.setInt(3, par.getIdParagraphe());
		    pstmt.executeUpdate();
		            
		} catch ( SQLException e ) {
		    // G�rer les �ventuelles erreurs ici 
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
