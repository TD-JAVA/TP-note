package com.company.Jeu;

/**
 * Created by Gaby on 29/11/2016.
 */
public class LoupGarous {
    private String nom;

    public LoupGarous(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public String toString(){
        return "Le nom est "+ this.getNom()+"";

    }
    public void setNom(String nom) {
        this.nom = nom;
    }

}
