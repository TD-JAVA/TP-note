/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.Jeu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Seb
 */
public class Jeu {

    private ArrayList<Joueur> tabJoueur;
    private int nbJoueurs;

    public void debuter() {
        ArrayList<Joueur> tabJoueur = new ArrayList<Joueur>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenue dans le jeu du Loup Garou, pour commencer à jouer, veuillez indiquer le nombre de joueurs participant au jeu :");

        this.nbJoueurs = sc.nextInt(); // On récupère le nombre de joueurs


        //On créé les joueurs et on les ajoutent au tableau des joueurs tabJoueur
        for (int i = 0; i < nbJoueurs; i++) {
            String nom, prenom;
            Scanner sc2 = new Scanner(System.in);

            System.out.println("Veuillez renseigner le nom du joueur numéro " + i + 1);
            nom = sc2.nextLine();

            System.out.println("Veuillez renseigner le prénom du joueur numéro " + i + 1);
            prenom = sc2.nextLine();

            Joueur j = new Joueur(nom, prenom);
            tabJoueur.add(j);
        }
        //System.out.println(this.tabJoueur.get(0).getNom());
    }
}
