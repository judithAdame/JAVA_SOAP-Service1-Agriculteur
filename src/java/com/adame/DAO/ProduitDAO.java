package com.adame.DAO;

import com.adame.utilitaire.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author 1895648
 */
public class ProduitDAO {
    public static ArrayList<Produit> getAll() {
        ArrayList<Produit> listeProd = new ArrayList();
        try {
            Connection conn = Connexion.createConnexion();
            String sql = "SELECT * FROM PRODUIT";
            System.out.println("public static ArrayList<Produit> getAll()");
            PreparedStatement stmt = conn.prepareStatement(sql);
            try (ResultSet rs = stmt.executeQuery()) {
                Produit p;
                while (rs.next()) {
                    p = new Produit();
                    p.setNom(rs.getString("NOM"));
                    p.setQte(rs.getInt("QTE"));
                    p.setPrix(rs.getDouble("PRIX"));
                    listeProd.add(p);
                }
            }
            Connexion.cleanConnexion(stmt, conn);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Probleme de connection " + e.getMessage());
        }
        return listeProd;
    }

    public static ArrayList<String> getAllNomProduit() {
        ArrayList<String> listeNomProd = new ArrayList();
        try {
            Connection conn = Connexion.createConnexion();
            String sql = "SELECT DISTINCT(nom) NOM FROM PRODUIT";
            System.out.println("public static ArrayList<String> getNomProduit()");
            PreparedStatement stmt = conn.prepareStatement(sql);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    listeNomProd.add(rs.getString("NOM"));
                }
            }
            Connexion.cleanConnexion(stmt, conn);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Probleme de connection " + e.getMessage());
        }
        return listeNomProd;
    }
    
    public static String updateQte(String nom, int qte) {
        try {
            Connection conn = Connexion.createConnexion();
            String sql;
            sql = "UPDATE PRODUIT SET QTE = ? WHERE NOM = ?";
            System.out.println("public static boolean update(String nom, int qte)");
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, qte);
            stmt.setString(2, nom);
            int nombre = stmt.executeUpdate();
            Connexion.cleanConnexion(stmt, conn);
            return (nombre == 1)? "La Qte a ete modifiee!" : "Le produit " +nom+ " n'existe pas";
        } catch (SQLException | ClassNotFoundException e) {
            return"Probleme de connection " + e.getMessage();
        }
    }
    
    public static String addQte(String nom, int qte) {
        try {
            Connection conn = Connexion.createConnexion();
            String sql = "UPDATE PRODUIT SET QTE = ((SELECT QTE FROM PRODUIT WHERE NOM = ?) + ?) WHERE NOM = ?";
            System.out.println("public static boolean addQte(String nom, int qte)");
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setInt(2, qte);
            stmt.setString(3, nom);
            int nombre = stmt.executeUpdate();
            Connexion.cleanConnexion(stmt, conn);
            return (nombre == 1)? "La qte a ete modifiee!" : "Le produit " +nom+ " n'existe pas";
        } catch (SQLException | ClassNotFoundException e) {
            return"Probleme de connection " + e.getMessage();
        }
    }
    
    public static Produit getProduit(String nom) {
        Produit produit = null;
        try {
            Connection conn = Connexion.createConnexion();
            String sql = "SELECT * FROM PRODUIT WHERE NOM = ?";
            System.out.println("public static Produit getProduit(String nom)");
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nom);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    produit = new Produit();
                    produit.setNom(rs.getString("NOM"));
                    produit.setQte(rs.getInt("QTE"));     
                    produit.setPrix(rs.getInt("PRIX"));                    
                }                
                Connexion.cleanConnexion(stmt, conn);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Probleme de connection " + e.getMessage());
            return null;
        }
       return produit;
    }
    
    public static String insertProduitPrimitif(String nom, int qte, double prix) {
        try {
            Connection conn = Connexion.createConnexion();
            String sql;
            sql = "INSERT INTO PRODUIT VALUES (?,?, ?)";
            System.out.println("public static boolean insertProduitPrimitif(String nom, int qte)");
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nom);
            stmt.setInt(2, qte);
            stmt.setDouble(3, prix);
            int nombre = stmt.executeUpdate();
            Connexion.cleanConnexion(stmt, conn);
            return (nombre == 1)? "Le produit ete ajoute!" : "Le produit " +nom+ " existe deja";
        } catch (SQLException | ClassNotFoundException e) {
            return"Probleme de connection " + e.getMessage();
        }
    }
    
        public static String insertProduitObjet(Produit produit) {
        try {
            Connection conn = Connexion.createConnexion();
            String sql;
            sql = "INSERT INTO PRODUIT VALUES (?,?,?)";
            System.out.println("public static boolean insertProduitObjet(Produit produit)");
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, produit.getNom());
            stmt.setInt(2, produit.getQte());
            stmt.setDouble(3, produit.getPrix());
            int nombre = stmt.executeUpdate();
            Connexion.cleanConnexion(stmt, conn);
            return (nombre == 1)? "Le produit ete ajoute!" : "Le produit " +produit.getNom()+ " existe deja";
        } catch (SQLException | ClassNotFoundException e) {
            return"Probleme de connection " + e.getMessage();
        }
    }
}
