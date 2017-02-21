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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import com.istv.etu.dao.IUsersDAO;
import com.istv.etu.model.Cours;
import com.istv.etu.model.User;
import com.istv.etu.model.form.UpdateUserForm;

@Repository
public class UsersDAO implements IUsersDAO {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public List<User> checkLogin() {
		List<User> users = new ArrayList<User>();
		
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
		    resultat = statement.executeQuery("select login,password from user where statut not in ('banni');");
		    
		    // R�cup�ration des donn�es du r�sultat de la requ�te de lecture 
	        while ( resultat.next() ) {
	            User u = new User();
	        	
	            u.setLogin(resultat.getString( "login" ));
	            u.setPassword(resultat.getString( "password" ));
	            
	            users.add(u);
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
	    
	    return users;
	}
	
	public String checkStatut(String id) {
		
		User u = new User();
		
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
		    resultat = statement.executeQuery("select statut from user where idUser=" + id + ";");
		    
		    // R�cup�ration des donn�es du r�sultat de la requ�te de lecture 
	        while ( resultat.next() ) {	            
	            u.setStatut(resultat.getString( "statut" ));
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
		
		return u.getStatut();
	}
	
    public List<User> getUsers() {
        
    	// Connexion � la base de donn�es 
    	
    	List<User> users = new ArrayList<User>();
    	
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
		    resultat = statement.executeQuery("select * from user;");
		    
		    // R�cup�ration des donn�es du r�sultat de la requ�te de lecture 
	        while ( resultat.next() ) {
	            User u = new User();
	        	
	            u.setId(resultat.getInt( "idUser" ));
	            u.setLogin(resultat.getString( "login" ));
	            u.setPassword(resultat.getString( "password" ));
	            u.setAvatar(resultat.getString( "avatar" ));
	            u.setFormation(resultat.getString( "formation" ));
	            u.setNom(resultat.getString( "nom" ));
	            u.setPrenom(resultat.getString( "prenom" ));
	            u.setStatut(resultat.getString( "statut" ));
	            
	            users.add(u);
	            
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
		
		
		return users;
    }
    
    public User getOneUser(String id) {
    	
    	User user = new User();
		
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
		    resultat = statement.executeQuery("select u.*,c.idCours as id,c.libelleCours as l from user u join cours c on u.idUser=c.fk_idUser and u.idUser=" + id + ";");
		    
		    // R�cup�ration des donn�es du r�sultat de la requ�te de lecture 
		    
		    List<ResultSet> resultList = new ArrayList<ResultSet>();
		    List<Cours> cours = new ArrayList<Cours>();
		    
            while (resultat.next()) {
            	
            	user.setId(resultat.getInt( "idUser" ));                
                user.setLogin(resultat.getString( "login" ));
                user.setPassword(resultat.getString( "password" ));
                user.setEmail(resultat.getString( "email" ));
                user.setAvatar(resultat.getString( "avatar" ));
                user.setFormation(resultat.getString( "formation" ));
                user.setNom(resultat.getString( "nom" ));
                user.setPrenom(resultat.getString( "prenom" ));
                user.setStatut(resultat.getString( "statut" ));
                
                resultList.add(resultat);
                
                Cours c = new Cours();
            	c.setIdCours(resultList.get(0).getInt("id"));
                c.setLibelleCours(resultList.get(0).getString("l"));
	            
                cours.add(c);
                
                user.setCours(cours);
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
	    
	    return user;
    }
    
    public User getOneUserWithoutCourses(String id) {
    	
    	User user = new User();
		
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
		    resultat = statement.executeQuery("select * from user where idUser=" + id + ";");
		    
		    // R�cup�ration des donn�es du r�sultat de la requ�te de lecture 
		    		    
            while (resultat.next()) {
            	user.setId(resultat.getInt( "idUser" ));                
                user.setLogin(resultat.getString( "login" ));
                user.setPassword(resultat.getString( "password" ));
                user.setEmail(resultat.getString( "email" ));
                user.setAvatar(resultat.getString( "avatar" ));
                user.setFormation(resultat.getString( "formation" ));
                user.setNom(resultat.getString( "nom" ));
                user.setPrenom(resultat.getString( "prenom" ));
                user.setStatut(resultat.getString( "statut" ));                
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
	    
	    return user;
    }
    	
    public void createUser(final User pUser) {
		
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
		    
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String date = formatter.format(new Date());
		    
		    String sql = "insert into user(login,password,email,nom,prenom,create_time,statut,avatar,formation) values(?,?,?,?,?,?,?,?,?)";
		    //statement.executeUpdate(sql);
		    PreparedStatement pstmt = connexion.prepareStatement(sql);
		    pstmt.setString(1, pUser.getLogin());
		    pstmt.setString(2, pUser.getPassword());
		    pstmt.setString(3, pUser.getEmail());
		    pstmt.setString(4, pUser.getNom());
		    pstmt.setString(5, pUser.getPrenom());
		    pstmt.setString(6, date);
		    pstmt.setString(7, pUser.getStatut());
		    pstmt.setString(8, pUser.getAvatar());
		    pstmt.setString(9, pUser.getFormation());
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
    
    public void banUser(final int id, final String etat) {

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
		    
		    String sql = "update user set statut=? where idUser=?;";
		    PreparedStatement pstmt = connexion.prepareStatement(sql);
		    
		    String e = etat.equals("banni") ? "user" : "banni";
		    pstmt.setString(1, e);
		    pstmt.setInt(2, id);
		    
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
    
    public void updateUser(final UpdateUserForm pUser, final String idUser) {
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
		    
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String date = formatter.format(new Date());
		    
		    String sql = "update user set login=?,email=?,nom=?,prenom=?,create_time=?,avatar=?,formation=? where idUser=?";
		    //statement.executeUpdate(sql);
		    PreparedStatement pstmt = connexion.prepareStatement(sql);
		    pstmt.setString(1, pUser.getLogin());
		    pstmt.setString(2, pUser.getEmail());
		    pstmt.setString(3, pUser.getNom());
		    pstmt.setString(4, pUser.getPrenom());
		    pstmt.setString(5, date);
		    pstmt.setString(6, pUser.getAvatar());
		    pstmt.setString(7, pUser.getFormation());
		    pstmt.setString(8, idUser);
		    pstmt.executeUpdate();
            
		} catch ( SQLException e ) {
		    // G�rer les �ventuelles erreurs ici 
			System.out.println("Update error : " + e.getMessage());
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
    
    public void updatePassword(final String pwd, final String idUser) {
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
		    
		    String sql = "update user set password=? where idUser=?;";
		    PreparedStatement pstmt = connexion.prepareStatement(sql);
		    pstmt.setString(1, pwd);
		    pstmt.setString(2, idUser);
		    
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