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

import com.istv.etu.dao.ISujetsDAO;
import com.istv.etu.model.Sujet;
import com.istv.etu.model.User;

@Repository
public class SujetsDAO implements ISujetsDAO {
	
	public List<Sujet> getSujets(int id) {
		List<Sujet> sujets = new ArrayList<Sujet>();
		
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
		    String query = "select s.idSujet, s.etatSujet, s.titreSujet, MAX(p.datePost) as dateDernPost, u.login from sujet s, post p, user u where p.fk_idUser=u.idUser and s.fk_idTheme=" + String.valueOf(id) + " group by s.titreSujet";
		    resultat = statement.executeQuery(query);
		    
		    
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
	
	public List<Sujet> getSujetsByName(String libelle) {
		List<Sujet> sujets = new ArrayList<Sujet>();
		
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
		    String query = "select s.*, u.avatar, u.login from sujet s, user u where s.fk_idUser=u.idUser and s.titreSujet like '%" + libelle + "%';";
		    resultat = statement.executeQuery(query);		    
		    
		    // Récupération des données du résultat de la requête de lecture 
	        while ( resultat.next() ) {
	            Sujet s = new Sujet();
	        	s.setTitre(resultat.getString("titreSujet"));
	        	s.setEtat(resultat.getString("etatSujet"));
	            
	            User u = new User();
	            u.setLogin(resultat.getString("u.login"));
	            u.setAvatar(resultat.getString("u.avatar"));
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
	
	public void createSujet(Sujet s, String id) {
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

		    //SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    //String date = formatter.format(new Date());
		    
		    String sql = "insert into sujet(titreSujet,etatSujet,fk_idTheme,fk_idUser) values(?,?,?,?)";
		    
		    PreparedStatement pstmt = connexion.prepareStatement(sql);
		    pstmt.setString(1, s.getTitre());
		    pstmt.setString(2, "Non résolu");
		    pstmt.setInt(3, s.getFk_idTheme());
		    pstmt.setString(4, id);
		    
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
	
	public int getSujetId(String libelle) {
		int id = 0;
		
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
		    String query = "select idSujet from sujet where titreSujet='" + libelle + "';";
		    resultat = statement.executeQuery(query);		    
		    
		    // Récupération des données du résultat de la requête de lecture 
	        while ( resultat.next() ) {
	        	id = resultat.getInt("idSujet");
	        	
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
	    
	    return id;
	}
	
	public List<Sujet> getLastSujets(){
		List<Sujet> sujets = new ArrayList<Sujet>();
		
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
		    resultat = statement.executeQuery("select s.*, u.login as login from sujet s, user u where s.fk_idUser=u.idUser order by s.idSujet desc limit 3");
		    
		    // Récupération des données du résultat de la requête de lecture 
	        while ( resultat.next() ) {
	            Sujet s = new Sujet();
	        		            
	            s.setId(resultat.getInt("idSujet"));
	            s.setTitre(resultat.getString( "titreSujet" ));
	            s.setDateCreation(resultat.getDate("dateCreation"));
	            
	            User u = new User();
	            u.setId(resultat.getInt("fk_idUser"));
	            u.setLogin(resultat.getString("login"));
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
