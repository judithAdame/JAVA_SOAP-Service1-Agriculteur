package com.adame.DAO;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Produit {
    String nom;
    int qte;
    double prix;
    
    public Produit() {
    }
    
    public Produit(String nom, int qte, double prix) {
        this.nom = nom;
        this.qte = qte;
        this.prix = prix;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    @Override
    public String toString() {
        return "Produit{" + "nom=" + nom + ", qte=" + qte + ", prix=" + prix + '}';
    }
}
