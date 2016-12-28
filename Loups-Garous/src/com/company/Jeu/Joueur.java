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
    private String nom;
    private int carte;

    public Joueur(String nom, int carte) {
        this.nom = nom;
        this.carte=carte;
    }


    public String toString(){
        return " "+ this.getNom()+" ";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCarte() {
        return carte;
    }

    public void setCarte(int carte) {
        this.carte = carte;
    }
}
