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
    private ArrayList<Villageois> listVilla;
    private int nbJoueurs;
    private boolean nuit=false;



    public void debuter() {
        this.listVilla= new ArrayList<Villageois>();
        this.tabJoueur = new ArrayList<Joueur>();
        this.listLp = new ArrayList<LoupGarous>();
        Scanner sc = new Scanner(System.in);
        int numCarte = 1;
        System.out.println("Bienvenue dans le jeu du Loup Garou, pour commencer à jouer, veuillez indiquer le nombre de joueurs participant au jeu :");

        this.nbJoueurs = sc.nextInt(); // On récupère le nombre de joueurs
        //On créé les joueurs et on les ajoutent au tableau des joueurs tabJoueur  <=== Erreur de collection == liste et pas tab
        for (int i = 0; i < nbJoueurs; i++) {
            String nom;

            Scanner sc2 = new Scanner(System.in);

            System.out.println("Veuillez renseigner le nom du joueur numéro " + i + "");
            nom = sc2.nextLine();
            Joueur j = new Joueur(nom,numCarte);
            tabJoueur.add(j);
            becomeVillageois(j);
            role(j);
            numCarte+=1;

        }
        goNuit();

    }

    public void becomeVillageois(Joueur j){

        Villageois v = new Villageois(j.getNom(),j.getCarte());
        listVilla.add(v);
        System.out.println(listVilla+" liste villa "+v.getNumVillageois());

    }



    public void role(Joueur j) {
        int max = 3;
        int min = 1;
        Random ra = new Random();
        int fonction = min + ra.nextInt(max-min);
               if ((fonction==2) && (listLp.size()!=2)){
                   LoupGarous lp = new LoupGarous(j.getNom());
                   listLp.add(lp);
               }
        System.out.println(listLp+" Liste LP");
        System.out.println(tabJoueur+" Liste Joueur");
    }


    public void goNuit() {
        nuit=true;
        System.out.println("La nuit est là !");

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
