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
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.istv.etu.dao.IListUsersDAO;
import com.istv.etu.model.Cours;
import com.istv.etu.model.User;

@Repository
public class ListUsersDAO implements IListUsersDAO {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public List<User> CheckAuth() {
		List<User> users = new ArrayList<User>();
		
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
		    resultat = statement.executeQuery("select login,password from user;");
		    
		    // Récupération des données du résultat de la requête de lecture 
	        while ( resultat.next() ) {
	            User u = new User();
	        	
	            u.setLogin(resultat.getString( "login" ));
	            u.setPassword(resultat.getString( "password" ));
	            
	            users.add(u);
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
	    
	    return users;
	}
	
    public List<User> getUsers() {
        
    	// Connexion à la base de données 
    	
    	List<User> users = new ArrayList<User>();
    	
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
		    resultat = statement.executeQuery("select * from user;");
		    
		    // Récupération des données du résultat de la requête de lecture 
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
		
		String url = "jdbc:mysql://localhost:3307/istv";
		String utilisateur = "root";
		String motDePasse = "efficient";
		
		Connection connexion = null;
		Statement statement = null;
	    ResultSet resultat = null;
	    
	    try {
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		    statement = connexion.createStatement();		    
		    resultat = statement.executeQuery("select u.*,c.idCours as id,c.libelleCours as l from user u inner join cours c on u.idUser=c.fk_idUser and u.idUser=" + id + ";");
		    
		    // Récupération des données du résultat de la requête de lecture 
		    
		    List<ResultSet> resultList = new ArrayList<ResultSet>();
		    List<Cours> cours = new ArrayList<Cours>();
		    
            while (resultat.next()) {
            	user.setId(resultat.getInt( "idUser" ));
                
                user.setLogin(resultat.getString( "login" ));
                user.setPassword(resultat.getString( "password" ));
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
	    
	    return user;
    }
	
    public void createUser(final User pUser) {
		
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
		    
		    String sql = "insert into user(login,password,email,nom,prenom,create_time,statut,avatar,formation) values('" + pUser.getLogin() + "','" + pUser.getPassword() + "','mail@mail.fr','" + pUser.getNom() + "','" + pUser.getPrenom() + "','2017-01-04','" + pUser.getStatut() + "','" + pUser.getAvatar() + "','" + pUser.getFormation() + "')";
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
    
    public void deleteUser(final User pUser) {
    	final User lUser = entityManager.getReference(User.class, pUser.getId());
        entityManager.remove(lUser);
    }
    
    public void updateUser(final User pUser) {
    	final CriteriaBuilder lCriteriaBuilder = entityManager.getCriteriaBuilder();

        final CriteriaUpdate<User> lCriteriaUpdate = lCriteriaBuilder.createCriteriaUpdate(User.class);
        final Root<User> lRoot = lCriteriaUpdate.from(User.class);
        final Path<User> lPath = lRoot.get("id");
        final Expression<Boolean> lExpression = lCriteriaBuilder.equal(lPath, pUser.getId());
        lCriteriaUpdate.where(lExpression);
        lCriteriaUpdate.set("nom", pUser.getNom());
        final Query lQuery = entityManager.createQuery(lCriteriaUpdate);
        final int lRowCount = lQuery.executeUpdate();

        if (lRowCount != 1) {
            final org.hibernate.Query lHQuery = lQuery.unwrap(org.hibernate.Query.class);
            final String lSql = lHQuery.getQueryString();
            throw new RuntimeException("Nombre d'occurences (" + lRowCount + 
                    ") modifiés différent de 1 pour " + lSql);
        }
    }
}
