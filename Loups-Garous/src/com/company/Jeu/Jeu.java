/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Jeu;

import com.sun.org.apache.xpath.internal.SourceTree;

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
    private ArrayList<Joueur> listVictime;
    private int nbJoueurs;
    private boolean nuit=false;



    public void debuter() {
        this.listVilla= new ArrayList<Villageois>();
        this.tabJoueur = new ArrayList<Joueur>();
        this.listLp = new ArrayList<LoupGarous>();
        Scanner sc = new Scanner(System.in);
        int numCarte = 1;
        int joueurJeu=1;
        System.out.println("Bienvenue dans le jeu du Loup Garou, pour commencer à jouer, veuillez indiquer le nombre de joueurs participant au jeu :");

        this.nbJoueurs = sc.nextInt(); // On récupère le nombre de joueurs
        //On créé les joueurs et on les ajoutent au tableau des joueurs tabJoueur  <=== Erreur de collection == liste et pas tab
        for (int i = 0; i < nbJoueurs; i++) {
            String nom;


            System.out.println("Veuillez renseigner le nom du joueur numéro " + joueurJeu + "");
            Scanner sc2 = new Scanner(System.in);
            nom = sc2.nextLine();

            while(verifDoublonNom(this,nom)){
                System.out.println("Ce nom existe déjà ! Veuillez entrer un autre nom");
                sc2 = new Scanner(System.in);
                nom = sc2.nextLine();
            }
            Joueur j = new Joueur(nom,numCarte);
            tabJoueur.add(j);
            becomeVillageois(j);
            role(j);
            numCarte+=1;
            joueurJeu++;

        }
        while (!(verifFinPartie(this))){
            goNuit();
            choixVictime();
            goJour();
            choixVillageois();
        }
        finPartie(this);


    }

    public void choixVillageois() {
        String nom = "";
        boolean b = false;
        int[] vote=new int[tabJoueur.size()];
        Arrays.fill(vote, 0);
        for (int i = 0; i < tabJoueur.size(); i++) {
            while (!(b)) {
                Scanner sc = new Scanner(System.in);
                System.out.println(tabJoueur.get(i).getNom() + " , veuillez saisir un villageois qui selon vous est un loup garou :");
                nom = sc.nextLine();
                for (int j = 0; j < tabJoueur.size(); j++) {

                    if (tabJoueur.get(j).getNom().contains(nom)) {
                        vote[j] +=1;
                        b= true;
                        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    } else if (tabJoueur.size() == j) {
                        System.out.println("Ce joueur n'existe pas, veuillez réésayer :");
                    }
                }
            }
            b = false;
        }
        System.out.println("tableau des votes :"+Arrays.toString(vote));
    }

    public int getNumVillageois(Jeu j,String nom){
        for(int i=0;i<j.tabJoueur.size();i++){
            if(j.tabJoueur.get(i).getNom()== nom){
                return i;
            }
        }
        return -1;
    }

    public void choixVictime() {

        this.listVictime = new ArrayList<>();
        boolean b = false;
        int victime1 = 1;
        int victime2 = 3;
        Random ra = new Random();
        String numVictime;
        int nbLP = 1;

        for (int i = 0; i < listLp.size(); i++) {

            while (!(b)) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Loup garou " + nbLP + " , veuillez saisir une victime :");
                numVictime = sc.next();
                //System.out.println(tabJoueur.get(0).getNom());

                for (int j = 0; j < tabJoueur.size(); j++) {

                        if (tabJoueur.get(j).getNom().contains(numVictime)) {

                            listVictime.add(tabJoueur.get(j));
                            b = true;
                            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

                        } else if (tabJoueur.size()==j) {
                            System.out.println("Ce joueur n'existe pas, veuillez réésayer :");
                        }
                    }
            }
            b = false;
            nbLP++;
        }

        int fonction = victime1 + ra.nextInt(victime2-victime1);
        for (int i=0; i<listVictime.size();i++){
            if (fonction==1){
                if (tabJoueur.contains(listVictime.get(0))){
                    tabJoueur.remove(listVictime.get(0));
                    supprimerVillageois(this,listVictime.get(0).getNom());
                }else{
                    //System.out.println("Surprise");
                }
            }else if (fonction==2){
                if (tabJoueur.contains(listVictime.get(1))){
                    tabJoueur.remove(listVictime.get(1));
                    supprimerVillageois(this,listVictime.get(1).getNom());

                }else{
                    System.out.println("Surprise 4");
                }
            }else {
                System.out.println("ghghhg");
            }
        }

        System.out.println(tabJoueur+" Nouveau tab joueurs");
        System.out.println(listVictime+" liste des victimes");
    }


    public void becomeVillageois(Joueur j){

        Villageois v = new Villageois(j.getNom(),j.getCarte());
        listVilla.add(v);
        //System.out.println(listVilla+" liste villa "+v.getNumVillageois());

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
        //System.out.println(listLp+" Liste LP");
        //System.out.println(tabJoueur+" Liste Joueur");
    }


    public void goNuit() {
        nuit=true;
        System.out.println("///////// La nuit est là ! ///////////");
        System.out.println(" Voici la liste actuelle des joueurs "+tabJoueur);

    }

    public void goJour() {
        nuit=false;
        System.out.println("///////// Le jour se lève ! ///////////");
        System.out.println(" Voici la liste actuelle des joueurs "+tabJoueur+"\n");

    }

    public boolean verifFinPartie(Jeu j){
        if(listLp.isEmpty() || listVilla.isEmpty()){
            return true;
        }
        else{
            return false;
        }

    }

    public void finPartie(Jeu j){
        if(listLp.isEmpty()){
            System.out.println("Fin de la partie. Les villageois remportent la victoire !");
        }
        else{
            System.out.println("Fin de la partie. Les loup garous remportent la victoire !");

        }
    }

    public void supprimerVillageois(Jeu j,String n){
        for (int i=0;i<listVilla.size();i++){
            if(listVilla.get(i).getNom()==n){
                listVilla.remove(listVilla.get(i));
            }
        }
    }

    public void supprimerLoup(Jeu j,String n){
        for (int i=0;i<listLp.size();i++){
            if(listLp.get(i).getNom()==n){
                listLp.remove(listLp.get(i));
            }
        }
    }

    public boolean verifDoublonNom(Jeu j,String nom){
        boolean trouve = false;
        for(int i=0;i<j.tabJoueur.size();i++){
            if(j.tabJoueur.get(i).getNom().equals(nom)){
                trouve=true;
                return trouve;
            }
        }


        return trouve;
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
