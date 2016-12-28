/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Jeu;


/**
 *
 * @author Seb
 */
public class Joueur {

    public Joueur(String nom) {
        this.nom = nom;
    }
    private String nom,prenom;

    public String toString(){
        return " "+ this.getNom()+" ";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
