package com.istv.etu.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.istv.etu.dao.IPostDAO;
import com.istv.etu.model.Post;
import com.istv.etu.model.User;

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
		
		String url = "jdbc:mysql://localhost:3307/istv?autoReconnect=true&useSSL=false";
		String utilisateur = "root";
		String motDePasse = "efficient";
		
		Connection connexion = null;
		Statement statement = null;
	    ResultSet resultat = null;
	    
	    try {
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
			
		    statement = connexion.createStatement();		    
		    String query = "select p.datePost, p.contenu, u.login, u.avatar from post p, user u where p.fk_idUser=u.idUser and p.fk_idSujet=" + idSujet + " order by p.datePost;";
		    resultat = statement.executeQuery(query);		    
		    
		    // Récupération des données du résultat de la requête de lecture 
	        while ( resultat.next() ) {
	            Post p = new Post();
	        	
	            p.setContenu(resultat.getString("contenu"));
	            
	            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEEE d MMMM yyyy HH:mm:ss");
	            String date = DATE_FORMAT.format(resultat.getDate("datePost"));
	            
	            Date t;
	            try {
	               t = DATE_FORMAT.parse(date); 
	               //System.out.println(t); 
	            }catch (ParseException e) { 
	               System.out.println("Unparseable using " + DATE_FORMAT); 
	            }
	            
	            p.setDatePost(resultat.getDate("datePost"));
	            
	            User u = new User();
	            u.setLogin(resultat.getString("u.login"));
	            u.setAvatar(resultat.getString("u.avatar"));
	            p.setUser(u);
	            
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
	
	public void addPost(String contenu, int idSujet, String idUser) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			/* TODO Changer format date : 2016-12-01 -> 1 Décembre 2016
			 * 		
			 */
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
		    
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String date = formatter.format(new Date());
		    
		    String sql = "insert into post(datePost,contenu,fk_idSujet,fk_idUser) values(?,?,?,?);";
		    //statement.executeUpdate(query);
		    PreparedStatement pstmt = connexion.prepareStatement(sql);
		    pstmt.setString(1, date);
		    pstmt.setString(2, contenu);
		    pstmt.setInt(3, idSujet);
		    pstmt.setString(4, idUser);
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
