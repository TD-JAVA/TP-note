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
    private ArrayList<Joueur> listVictime;
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
        choixVictime();

    }

    public void choixVictime(){
        this.listVictime = new ArrayList<>();


        for (int i =0; i<listLp.size();i++){
            int victime1=0;
            int victime2=0;
            Random ra = new Random();
            int fonction = victime2 + ra.nextInt(victime1-victime2);

            int numVictime;
            Scanner sc = new Scanner(System.in);
            System.out.println("Loup garou, veuillez saisir une victime :");
            numVictime = sc.nextInt();
            if (numVictime==1){
                listVictime.add(tabJoueur.get(0));
                victime1=1;
                victime2=1;
            }else if (numVictime==2){
                listVictime.add(tabJoueur.get(1));
                victime1=2;
                victime2=2;
            }else if (numVictime==3){
                listVictime.add(tabJoueur.get(2));
                victime1=3;
                victime2=3;;
            }else if (numVictime==4){
                listVictime.add(tabJoueur.get(3));
                victime1=4;
                victime2=4;
            }else if (numVictime==5){
                listVictime.add(tabJoueur.get(4));
            }else if (numVictime==6){
                listVictime.add(tabJoueur.get(5));
            }else if (numVictime==7){
                listVictime.add(tabJoueur.get(6));
            }else if (numVictime==8){
                listVictime.add(tabJoueur.get(7));
            }else if (numVictime==9){
                listVictime.add(tabJoueur.get(8));
            }else if (numVictime==10){
                listVictime.add(tabJoueur.get(9));
            }else if (numVictime==11){
                listVictime.add(tabJoueur.get(10));
            }else if (numVictime==12){
                listVictime.add(tabJoueur.get(12));
            }
        }

        System.out.println(listVictime+" liste des victimes");

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
