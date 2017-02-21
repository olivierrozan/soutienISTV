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

import com.istv.etu.model.Cours;
import com.istv.etu.model.Paragraphe;
import com.istv.etu.model.Theme;
import com.istv.etu.model.User;
import com.istv.etu.dao.ICoursesDAO;

@Repository
public class CoursesDAO implements ICoursesDAO {
	
	@PersistenceContext
    private EntityManager em;
	
	public List<Cours> getCourses() {
		List<Cours> cours = new ArrayList<Cours>();
		
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
		    resultat = statement.executeQuery("select * from cours;");
		    
		    // Récupération des données du résultat de la requête de lecture 
	        while ( resultat.next() ) {
	            Cours c = new Cours();
	        	
	            c.setIdCours(resultat.getInt("idCours"));
	            c.setLibelleCours(resultat.getString( "libelleCours" ));
	            c.setDateDerniereModif(resultat.getDate("dateDerniereModif"));
	            c.setEtat(resultat.getString( "etat" ));
	            c.setNbVues(resultat.getInt("nbVues"));
	            
	            User u = new User();
	            u.setId(resultat.getInt("fk_idUser"));
	            c.setUser(u);
	            
	            cours.add(c);
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
	    
	    return cours;
	}
	
	public List<Cours> getCoursesOfOneUser(String id) {
		List<Cours> cours = new ArrayList<Cours>();
		
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
		    resultat = statement.executeQuery("select * from cours where fk_idUser=" + id + ";");
		    
		    // Récupération des données du résultat de la requête de lecture 
	        while ( resultat.next() ) {
	            Cours c = new Cours();
	        	
	            c.setIdCours(resultat.getInt("idCours"));
	            c.setLibelleCours(resultat.getString( "libelleCours" ));
	            c.setImageTitre(resultat.getString( "imageTitre" ));
	            c.setDateDerniereModif(resultat.getDate("dateDerniereModif"));
	            c.setEtat(resultat.getString( "etat" ));
	            c.setNbVues(resultat.getInt("nbVues"));
	            
	            cours.add(c);
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
	    
	    return cours;
	}
	
	public Cours getOneCourse(int id) {
		Cours c = new Cours();
		
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
		    resultat = statement.executeQuery("select c.*,p.*, u.idUser,u.login from cours c,paragraphe p,user u where p.fk_idCours=c.idCours and c.fk_idUser=u.idUser and c.idCours=" + id + ";");
		    
		    List<ResultSet> resultList = new ArrayList<ResultSet>();
		    List<Paragraphe> paragraphes = new ArrayList<Paragraphe>();
		    
		    while (resultat.next()) {
		    	c.setIdCours(resultat.getInt("idCours"));
			    c.setLibelleCours(resultat.getString("libelleCours"));
			    c.setImageTitre(resultat.getString("imageTitre"));
			    c.setDateDerniereModif(resultat.getDate("dateDerniereModif"));
			    c.setNbVues(resultat.getInt("nbVues"));
			    
			    resultList.add(resultat);
			    
			    Paragraphe p = new Paragraphe();
			    p.setIdParagraphe(resultat.getInt("idParagraphe"));
			    /*p.setTexte(resultat.getString("texte"));
			    p.setImageLocation(resultat.getString("imageLocation"));*/
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
			    
			    paragraphes.add(p);
			    
			    User u = new User();
			    u.setId(resultat.getInt("idUser"));
			    u.setLogin(resultat.getString("login"));
			    c.setUser(u);
			    
			    c.setParagraphes(paragraphes);
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
	    
	    return c;
	}
	
	public int getOneCourseByName(String name) {
		int c = 0;
		
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
		    //resultat = statement.executeQuery("select idCours from cours where libelleCours='" + name + "';");
		    resultat = statement.executeQuery("select MAX(idCours) as idCours from cours;");
		    
		    while ( resultat.next() ) {
		    	c = resultat.getInt("idCours");
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
	    
	    return c;
	}
	
	public void createCourse(Cours c, final String id) {
		//em.persist(c);
		
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

		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String date = formatter.format(new Date());
		    
		    String sql = "insert into cours(libelleCours,imageTitre,dateDerniereModif,etat,nbVues,fk_idUser) values(?,?,?,?,?,?)";
		    
		    PreparedStatement pstmt = connexion.prepareStatement(sql);
		    pstmt.setString(1, c.getLibelleCours());
		    pstmt.setString(2, c.getImageTitre());
		    pstmt.setString(3, date);
		    pstmt.setString(4, "En attente de validation");
		    pstmt.setInt(5, c.getNbVues());
		    pstmt.setString(6, id);
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
	
	public void updateCourse(final Cours cours) {
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
		    
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    String date = formatter.format(new Date());
			
		    /*String sql = "update cours c, paragraphe p set c.libelleCours=?, c.dateDerniereModif=?, p.texte=?, p.imageLocation=? where p.fk_idCours=c.idCours and c.idCours=? and p.idParagraphe=?;";
		    PreparedStatement pstmt = connexion.prepareStatement(sql);
		    pstmt.setString(1, cours.getLibelleCours());
		    pstmt.setString(2, date);
		    pstmt.setString(3, cours.getParagraphes().get(0).getTexte());
		    pstmt.setString(4, cours.getParagraphes().get(0).getImageLocation());
		    pstmt.setInt(5, cours.getIdCours());
		    pstmt.setInt(6, cours.getParagraphes().get(0).getIdParagraphe());*/
		    
		    String sql = "update cours set libelleCours=?,imageTitre=?,dateDerniereModif=? where idCours=?;";
		    PreparedStatement pstmt = connexion.prepareStatement(sql);
		    pstmt.setString(1, cours.getLibelleCours());		    
		    pstmt.setString(2, cours.getImageTitre());
		    pstmt.setString(3, date);
		    pstmt.setInt(4, cours.getIdCours());
		    
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
	
	public void deleteCourse(final int id) {
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
	    
	    try {
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
		    statement = connexion.createStatement();
		    
		    String sql = "delete from cours where idCours=" + id + ";";
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
	
	public void validateCourse(final int id, final int idTheme) {
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
		    
		    String sql = "update cours set etat=?,fk_idTheme=? where idCours=?;";
		    PreparedStatement pstmt = connexion.prepareStatement(sql);
		    
		    String _etat = "Validé";
		    
		    pstmt.setString(1, _etat);
		    pstmt.setInt(2, idTheme);
		    pstmt.setInt(3, id);
		    
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
	
	public void deactivateCourse(final int id) {
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
		    
		    String sql = "update cours set etat=?,fk_idTheme=null where idCours=?;";
		    PreparedStatement pstmt = connexion.prepareStatement(sql);
		    
		    String _etat = "En attente de validation";
		    		    
		    pstmt.setString(1, _etat);
		    pstmt.setInt(2, id);
		    
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
	
	public List<Cours> getCoursesByName(String libelle) {
		
		List<Cours> cours = new ArrayList<Cours>();
		
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
		    resultat = statement.executeQuery("select c.*, u.idUser, u.login from cours c, user u where c.fk_idUser=u.idUser and c.libelleCours like '%" + libelle + "%' and etat='Validé';");
		    
		    // Récupération des données du résultat de la requête de lecture 
	        while ( resultat.next() ) {
	            Cours c = new Cours();
	        		            
	            c.setIdCours(resultat.getInt("idCours"));
	            c.setLibelleCours(resultat.getString( "libelleCours" ));
	            c.setDateDerniereModif(resultat.getDate("dateDerniereModif"));
	            c.setEtat(resultat.getString( "etat" ));
	            c.setNbVues(resultat.getInt("nbVues"));
	            
	            User u = new User();
	            u.setId(resultat.getInt("fk_idUser"));
	            u.setLogin(resultat.getString("login"));
	            c.setUser(u);
	            
	            cours.add(c);	           	            
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
	    
	    return cours;
	}
	
	public List<Cours> getLastCours() {
		List<Cours> cours = new ArrayList<Cours>();
		
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
		    resultat = statement.executeQuery("select c.*, u.login as login from cours c, user u where c.fk_idUser=u.idUser order by c.idCours desc limit 3");
		    
		    // Récupération des données du résultat de la requête de lecture 
	        while ( resultat.next() ) {
	            Cours c = new Cours();
	        		            
	            c.setIdCours(resultat.getInt("idCours"));
	            c.setLibelleCours(resultat.getString( "libelleCours" ));
	            c.setImageTitre(resultat.getString( "imageTitre" ));
	            c.setDateDerniereModif(resultat.getDate("dateDerniereModif"));
	            c.setEtat(resultat.getString( "etat" ));
	            c.setNbVues(resultat.getInt("nbVues"));
	            
	            User u = new User();
	            u.setId(resultat.getInt("fk_idUser"));
	            u.setLogin(resultat.getString("login"));
	            c.setUser(u);
	            
	            cours.add(c);	           	            
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
	    
	    return cours;
	}
	
	public List<Cours> getCoursesByTheme(int idTheme) {
		List<Cours> cours = new ArrayList<Cours>();
		
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
		    resultat = statement.executeQuery("select c.*, t.*, u.login as login from cours c, theme t, user u where c.fk_idUser=u.idUser and c.fk_idTheme=t.idTheme and t.idTheme=" + idTheme + ";");
		    
		    // Récupération des données du résultat de la requête de lecture 
	        while ( resultat.next() ) {
	            Cours c = new Cours();
	        		            
	            c.setIdCours(resultat.getInt("idCours"));
	            c.setLibelleCours(resultat.getString( "libelleCours" ));
	            c.setImageTitre(resultat.getString( "imageTitre" ));
	            c.setDateDerniereModif(resultat.getDate("dateDerniereModif"));
	            c.setEtat(resultat.getString( "etat" ));
	            c.setNbVues(resultat.getInt("nbVues"));
	            
	            Theme t = new Theme();
	            t.setIdTheme(resultat.getInt("t.idTheme"));
	            t.setLibelleTheme(resultat.getString("t.libelleTheme"));
	            c.setTheme(t);
	            
	            User u = new User();
	            u.setId(resultat.getInt("fk_idUser"));
	            u.setLogin(resultat.getString("login"));
	            c.setUser(u);
	            
	            cours.add(c);	           	            
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
	    
	    return cours;
	}
	
	public void updateNbVues(int idCours) {
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
		    			
		    String sql = "update cours set nbVues=nbVues+1 where idCours=?;";
		    PreparedStatement pstmt = connexion.prepareStatement(sql);
		    
		    pstmt.setInt(1, idCours);
		    
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
