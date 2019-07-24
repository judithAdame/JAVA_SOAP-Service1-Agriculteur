package com.adame.ws;

import com.adame.DAO.Produit;
import com.adame.DAO.ProduitDAO;
import static com.adame.DAO.ProduitDAO.insertProduitObjet;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

@WebService(serviceName = "RegistrerProduits")
public class RegistrerProduits {

    /**
     * This is a sample web service operation
     * @param produit
     * @return 
     */   
    @WebMethod(operationName = "insertProduitObjet")
    public String insertProduitO(@WebParam(name = "produit") Produit produit) {
        return insertProduitObjet(produit);
    }
    
    @WebMethod(operationName = "augmenterQte")
    public String augmenterQte(@WebParam(name = "nom") String nom, @WebParam(name = "qte") int qte) {
        return ProduitDAO.addQte(nom, qte);
    }
    
    @WebMethod(operationName = "modifierQte")
    public String modifierQte(@WebParam(name = "nom") String nom, @WebParam(name = "qte") int qte) {
        return ProduitDAO.updateQte(nom, qte);
    }
    
    @WebMethod(operationName = "getAllProduits")
    public List<Produit> getAllProduits() {                    
        return ProduitDAO.getAll();
    }
    
    @WebMethod(operationName = "getListProduits")
    public List<String> getListProduits() {                    
        return ProduitDAO.getAllNomProduit();
    }
}
