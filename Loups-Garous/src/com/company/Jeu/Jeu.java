/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Jeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Seb
 */
public class Jeu {

    private ArrayList<Joueur> tabJoueur;
    private ArrayList<LoupGarous> listLp;
    private int nbJoueurs;
    private boolean nuit=false;



    public void debuter() {
        this.tabJoueur = new ArrayList<Joueur>();
        this.listLp = new ArrayList<LoupGarous>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenue dans le jeu du Loup Garou, pour commencer à jouer, veuillez indiquer le nombre de joueurs participant au jeu :");

        this.nbJoueurs = sc.nextInt(); // On récupère le nombre de joueurs
        int max = 4;
        int min = 1;
        int loupsMax =0;
        Random ra = new Random();

        //On créé les joueurs et on les ajoutent au tableau des joueurs tabJoueur  <=== Erreur de collection == liste et pas tab
        for (int i = 0; i < nbJoueurs; i++) {
            String nom;
            Scanner sc2 = new Scanner(System.in);

            System.out.println("Veuillez renseigner le nom du joueur numéro " + i + "");
            nom = sc2.nextLine();
            Joueur j = new Joueur(nom);
            tabJoueur.add(j);

            
            role(j);
        }

        //
    }

    public void role(Joueur j) {
        int max = 3;
        int min = 1;
        int loupsMax =0;
        Random ra = new Random();
        int fonction = min + ra.nextInt(max-min);
           for (int i=0; i<tabJoueur.size(); i++){
               if ((fonction==2) && (loupsMax!=2)){
                   LoupGarous lp = new LoupGarous(j.getNom());
                   listLp.add(lp);
               }
               loupsMax +=1;
           }
           System.out.println(listLp+" Liste LP");
        System.out.println(tabJoueur+" Liste Joueur");
    }


    public void nuit() {

    }

    public ArrayList<Joueur> getTabJoueur() {
        return tabJoueur;
    }

    public void setTabJoueur(ArrayList<Joueur> tabJoueur) {
        this.tabJoueur = tabJoueur;
    }

    public ArrayList<LoupGarous> getListLp() {
        return listLp;
    }

    public void setListLp(ArrayList<LoupGarous> listLp) {
        this.listLp = listLp;
    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }

    public void setNbJoueurs(int nbJoueurs) {
        this.nbJoueurs = nbJoueurs;
    }

    public boolean isNuit() {
        return nuit;
    }

    public void setNuit(boolean nuit) {
        this.nuit = nuit;
    }
}
