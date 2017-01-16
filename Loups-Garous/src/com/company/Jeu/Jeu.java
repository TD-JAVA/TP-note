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
        System.out.println("Bienvenue dans le jeu du Loup Garou,\n Le jeu se déroule de la façon suivante :  \n On entre le nombre de joueurs participant au jeu(de 8 à 12), chaque joueur entre son nom et 2 d'entre eux auront le rôle de loup garou, les autres seront des villageois. \n Ensuite le jeu se déroulera en deux cycle, le jour où les villageois voteront afin de tuer le joueur qu'ils soupçonnent d'être un loup garou, et ensuite la nuit, où les loups garous choisissent une victime. \n Le jeu s'arrête dès qu'il n'y a plus de villageois ou que tous les loups garous sont mort. Bon jeu à tous ! \n\n\n Pour commencer à jouer,  veuillez indiquer le nombre de joueurs participant au jeu :");
        this.nbJoueurs = sc.nextInt(); // On récupère le nombre de joueurs

        while(this.nbJoueurs<8 || (this.nbJoueurs>12)){
            System.out.println("Veuillez saisir un nombre de joueur compris entre 8 et 12 :");
            sc = new Scanner(System.in);
            this.nbJoueurs = sc.nextInt(); // On récupère le nombre de joueurs
        }

        //On créé les joueurs et on les ajoutent au tableau des joueurs tabJoueur  <=== Erreur de collection == liste et pas tab
        for (int i = 0; i < nbJoueurs; i++) {
            String nom;


            System.out.println("Veuillez renseigner le nom du joueur numéro " + joueurJeu + "");
            Scanner sc2 = new Scanner(System.in);
            nom = sc2.nextLine();

            while(verifDoublonNom(this,nom) ){
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
        while (!(verifFinPartie(this))){ // Si les critères de fin de partie sont remplies, on arrête le jeu
            goNuit();
            choixVictime();
            goJour();
            choixVillageois();
        }
        finPartie(this);


    }
    //Méthode qui demande aux villageois de choisir une victime
    public void choixVillageois() {
        String nom = "";
        boolean b = false;
        int[] vote=new int[tabJoueur.size()];
        Arrays.fill(vote, 0);
        for (int i = 0; i < tabJoueur.size(); i++) {
            while (!(b)) {
                Scanner sc = new Scanner(System.in);
                System.out.println(tabJoueur.get(i).getNom() + " , veuillez saisir un villageois qui selon vous est un loup garou :");
                System.out.println("Veuillez taper le nom d'un des joueurs disponibles : "+tabJoueur);
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
        System.out.println("tableau des votes :  "+tabJoueur);
        System.out.println("tableau des joueurs :"+Arrays.toString(vote));
        if(choixVote(this,vote)){
            for (int k = 0; k < tabJoueur.size(); k++) {
                if(vote[k]  == Arrays.stream(vote).max().getAsInt()){
                    System.out.println("Les villageois ont choisi la victime ! "+tabJoueur.get(k).getNom()+" est éxécuté !");

                    for(int l=0;l<listLp.size();l++){
                        //System.out.println("liste lg:"+listLp.get(l).getNom());
                        if((tabJoueur.get(k).getNom().equals(listLp.get(l).getNom()))){
                            System.out.println(tabJoueur.get(k).getNom()+" était un loup !");
                            listLp.remove(listLp.get(l));
                        }
                        else{
                            //System.out.println("premier test avant méthode :"+listVilla);
                            //System.out.println("deuxieme test avant méthode :"+listLp);

                            int numVillageois = getNumVillageois(this,tabJoueur.get(k).getNom());
                            listVilla.remove(listVilla.get(numVillageois));
                            l= tabJoueur.size();
                        }
                    }
                    tabJoueur.remove(tabJoueur.get(k));
                }
            }

        }
        else{
            System.out.println("Egalité entre plusieurs joueurs, veuillez recommencer le vote :");
            choixVillageois();
        }
    }




    ////Méthode qui demande aux loups garous de choisir une victime
    public void choixVictime() {
        this.listVictime = new ArrayList<>();
        boolean b = false;
        int victime1 = 1;
        int victime2 = 3;
        Random ra = new Random();
        String numVictime;
        int nbLP = 1;

        for (int i = 0; i < listLp.size(); i++) { //Pour chaque loup garou
            while (!(b)) { // Tant que la victime entrée n'est pas valide
                Scanner sc = new Scanner(System.in);
                System.out.println("Loup garou " + nbLP + " , veuillez saisir une victime :");
                System.out.println("Voici la liste des victimes disponibles, veuillez entrer le nom de la personne que vous souhaitez tuer :"+listVilla);
                numVictime = sc.next();


                for (int j = 0; j < tabJoueur.size(); j++) {
                        if (tabJoueur.get(j).getNom().contains(numVictime)) {// on vérifie que le joueur existe
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
        if (listLp.size() != 1) { // S'il il y a plus d'un loup garou
            int fonction = victime1 + ra.nextInt(victime2-victime1);
            for (int i=0; i<listVictime.size();i++){
                if (fonction==1){
                    if (tabJoueur.contains(listVictime.get(0))){
                        System.out.println("Les loups garous ont sévit, la victime est "+listVictime.get(0).getNom());
                        tabJoueur.remove(listVictime.get(0));
                        supprimerVillageois(this,listVictime.get(0).getNom());
                    }else{
                        //System.out.println("Surprise");
                    }
                }else if (fonction==2){
                    if (tabJoueur.contains(listVictime.get(1))){
                        System.out.println("Les loups garous ont sévit, la victime est "+listVictime.get(1).getNom());
                        tabJoueur.remove(listVictime.get(1));
                        supprimerVillageois(this,listVictime.get(1).getNom());

                    }else{
                        //System.out.println("Surprise 4");
                    }
                }else {
                    //System.out.println("ghghhg");
                }
            }
        }
        else{ //Si il reste un seul loup garou
            supprimerVillageois(this,listVictime.get(0).getNom()); // on supprime directement le joueur sans passer par le choix aléatoire d'une victime
        }

        //System.out.println(tabJoueur+" Nouveau tab joueurs");
        //System.out.println(listVictime+" liste des victimes");
        if(verifFinPartie(this)){
            finPartie(this);
        }
    }


    public void becomeVillageois(Joueur j){  //ajoute un joueur à la liste des villageois

        Villageois v = new Villageois(j.getNom(),j.getCarte());
        listVilla.add(v);
        //System.out.println(listVilla+" liste villa "+v.getNumVillageois());
    }



    public void role(Joueur j) { //Ajoute ou non(aléatoire) un joueur à la liste des loups garous s'il ils ne sont pas 2
        int max = 3;
        int min = 1;
        Random ra = new Random();
        int fonction = min + ra.nextInt(max-min);
               if ((fonction==2) && (listLp.size()!=2)){

                   LoupGarous lp = new LoupGarous(j.getNom());
                   listLp.add(lp);
                       for(int k =0;k<listLp.size();k++){
                           for(int i =0;i<listVilla.size();i++){
                           if(listVilla.get(i).getNom().equals(listLp.get(k).getNom())){

                               listVilla.remove(listVilla.get(i));
                           }
                       }

                   }
               }
        //System.out.println(listLp+" Liste LP");
        //System.out.println(tabJoueur+" Liste Joueur");
    }


    public void goNuit() {  //Passage au cycle nuit
        nuit=true;
        System.out.println("///////// La nuit est là ! ///////////");
        //System.out.println(" Voici la liste actuelle des joueurs "+tabJoueur);

    }

    public void goJour() { //Passage au cycle jour
        nuit=false;
        System.out.println("///////// Le jour se lève ! ///////////");
        //System.out.println(" Voici la liste actuelle des joueurs "+tabJoueur+"\n");

    }

    public boolean verifFinPartie(Jeu j){ //On vérifie qu'il existe toujours au moins un villageois et un loup
        if(listLp.isEmpty() || listVilla.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }

    public void finPartie(Jeu j){ //On arrête le jeu
        if(listLp.isEmpty()){
            System.out.println("Fin de la partie. Les villageois remportent la victoire !");
            System.exit(0);
        }
        else{
            System.out.println("Fin de la partie. Les loup garous remportent la victoire !");
            System.exit(0);
        }
    }

    public void supprimerVillageois(Jeu j,String n){  //On supprime un villageois en le cherchant dans la liste avec son nom
        for (int i=0;i<listVilla.size();i++){ // on parcourt la liste des villageois
            if(listVilla.get(i).getNom()==n) { //Si le nom est égal au paramètre n
                listVilla.remove(listVilla.get(i)); // on le supprime de la liste des villageois
                tabJoueur.remove(listVictime.get(0)); //on le supprime de la liste des joueurs
            }

        }
    }

    /*public void supprimerLoup(Jeu j,String n){
        for (int i=0;i<listLp.size();i++){
            if(listLp.get(i).getNom()==n){
                listLp.remove(listLp.get(i));
            }
        }
    }*/

    public boolean verifDoublonNom(Jeu j,String nom){ // On vérifie que le nom n'existe pas déjà
        boolean trouve = false;
        for(int i=0;i<j.tabJoueur.size();i++){
            if(j.tabJoueur.get(i).getNom().equals(nom)){ //Si le nom existe déjà
                trouve=true;
                return trouve;
            }
        }
        return trouve;
    }


    public boolean choixVote(Jeu j,int[] vote){  // On vérifie qu'il n'y a pas une égalité dans les votes
        int max = Arrays.stream(vote).max().getAsInt();  // nb maximal de votes reçus
        int nb = nbOccurences(vote,max); // On compte le nombres d'occurences de max de votes

        if(nb==1){
            return true;
        }
        else{
            return false;
        }
    }

    public int nbOccurences(int[] vote,int max){ // Méthode qui compte le nombres d'occurences de max de votes
        int compteur = 0;

        for(int i=0;i<this.tabJoueur.size();i++){
            if(vote[i] == max){  // si max est égal à au nb de votes de la peronne courante
                compteur++; // on incrémente
            }
        }

        return compteur;
    }


    public int getNumVillageois(Jeu j,String nom){  //On récupère l'index d'un villageois grace à son nom
        for(int i=0;i<j.listVilla.size();i++){
            if(j.listVilla.get(i).getNom().equals(nom)){ // Si les noms sont égaux
                return i;  // on retourne l'index
            }
        }
        return -1;
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
