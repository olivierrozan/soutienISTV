package com.istv.etu.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.istv.etu.dao.IThemesDAO;
import com.istv.etu.model.Theme;

@Repository
public class ThemesDAO implements IThemesDAO {
	
	@PersistenceContext
    private EntityManager entityManager;
	
    public List<Theme> getThemes() {
        
    	List<Theme> themes = new ArrayList<Theme>();
		
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
		    resultat = statement.executeQuery("select * from theme;");		    
		    
		    // Récupération des données du résultat de la requête de lecture 
	        while ( resultat.next() ) {
	        	Theme t = new Theme();
	        	
	        	t.setIdTheme(resultat.getInt("idTheme"));
	            t.setLibelleTheme(resultat.getString("libelleTheme"));
	            themes.add(t);
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
	    
	    return themes;
    }
    
    public void addTheme(final Theme t) {
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
		    
		    String sql = "insert into theme(libelleTheme,fk_idUser) values(?,?)";
		    //statement.executeUpdate(sql);
		    PreparedStatement pstmt = connexion.prepareStatement(sql);
		    pstmt.setString(1, t.getLibelleTheme());
		    pstmt.setInt(2, t.getFk_idUser());		    
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
