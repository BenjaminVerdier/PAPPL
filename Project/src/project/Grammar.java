/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 * Classe contenant la grammaire : le mot initial et ceux qui peuvent le remplacer.
 * @author akagami
 */
public class Grammar {
    /**
     * Le mot initial et ses remplaçants.
     */
    private Production prod;
    /**
     * Le constructeur par défaut, correspondant à une grammaire vide.
     */
    public Grammar(){
        prod = new Production();
    }
    /**
     * Le constructeur définissant la grammaire à partir d'une production existante.
     * @param prod de type Production : Mot initial de la grammaire.
     */
    public Grammar(Production prod){
        this.prod = prod;
    }
    /**
     * Permet de set l'attribut prod.
     * @param prod de type Production : Nouvelle valeur de l'attribut prod.
     */
    public void setProd(Production prod){
        this.prod = prod;
    }
    /**
     * Renvoie le pointeur vers l'attribut prod.
     * @return Le pointeur de prod.
     */
    public Production getProd(){
        return prod;
    }
    /**
     * Génère un mot à partir de la grammaire.
     * @return La phrase générée par la grammaire.
     */
    public String generate(){
        return ProcessProd(prod);
    }
    /**
     * Permet de traiter une production en renvoyant un String si le mot est terminal
     * ou en appliquant cette méthode à une expression suivant le mot si il est non terminal.
     * @param p : La production à traiter.
     * @return Le mot terminal.
     */
    public String ProcessProd(Production p){
        String result = new String();
        
        if (p.isTerm()){
          result =  p.getMot();
        }
        else{
            for (Production p1 : p.getRandomExpr().getMots()){
                result += ProcessProd(p1);
            }
        }
        
        return result;
    }
}
