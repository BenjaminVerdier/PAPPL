/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;
/**
 * Classe représentant une production. Une production représente un mot et les expressions
 * qui peuvent le remplacer si il est non-terminal.
 * @author akagami
 */
public class Production {
    /**
     * Etiquette représentant le mot qui peut être terminal ou non.
     */
    private String mot;
    /**
     * Expressions pouvant remplacer le mot. Vide si le mot est terminal.
     */
    private ArrayList<Expression> expr; 
    /**
     * Poids pour les expressions pouvant remplacer le mot.
     * @see getRandomExpr
     */
    private ArrayList<Double> weights;
    /**
     * Minimum d'occurences lors du traitement.
     */
    private int minOccurence;
    /**
     * Maximum d'occurences lors du traitement.
     */
    private int maxOccurence;
    /**
     * Constructeur par défaut. Crée une production sans étiquette et sans successeur.
     */
    
    public final static int MAX_RANDOM = 100;
            
    public Production(){
        mot = "";
        expr = new ArrayList<>();
        weights = new ArrayList<>();
        maxOccurence = 1;
        minOccurence = 1;
    }
    /**
     * Constructeur créant une production représentant un mot terminal d'étiquette donnée.
     * @param txt de typer String : L'étiquette du mot.
     */
    public Production(String txt){
        mot = txt;
        expr = new ArrayList<>();
        weights = new ArrayList<>();
        maxOccurence = 1;
        minOccurence = 1;
    }
    /**
     * Constructeur créant une production représentant un mot terminal d'étiquette donnée et de nombres d'occurences min et max données.
     * @param txt de typer String : L'étiquette du mot.
     * @param min de type int : Nombre minimum d'occurences de la production.
     * @param max de type int : Nombre maximum d'occurences de la production.
     */
    public Production(String txt, int min, int max){
        mot = txt;
        expr = new ArrayList<>();
        weights = new ArrayList<>();
        maxOccurence = min;
        minOccurence = max;
    }
    /**
     * Getter renvoyant l'étiquette de la production.
     * @return L'étiquette du mot.
     */
    public String getMot(){
        return mot;
    }
    /**
     * Setter permettant de changer l'étiquette d'un mot.
     * @param txt de type String : La nouvelle étiquette du mot.
     */
    public void setMot(String txt){
        mot = txt;
    }
    /**
     * Méthode permettant d'ajouter une expression à la liste des expressions
     * pouvant remplacer le mot si il est non-terminal.
     * @param expression de type Expression : L'expression à rajouter.
     */
    public void addExpr(Expression expression){
        expr.add(expression);
        weights.add(1.00d);
    }
    /**
     * Méthode permettant d'enlever une expresion de la liste des expressions
     * pouvant remplacer le mot si il est non-terminal.
     * @param expression de type Expression : L'expression à enlever.
     */
    public void removeExpr(Expression expression){
        int i = expr.indexOf(expression);
        expr.remove(i);
        weights.remove(i);
    }
    /**
     * Supprime toutes les expressions de la liste, rendant le mot terminal.
     */
    public void clearExpr(){
        expr.clear();
    }
    /**
     * Getter donnant accès à la liste des expressions.
     * @return : La liste des expressions.
     */
    public ArrayList<Expression> getExpr(){
        return expr;
    }
    /**
     * Getter donant accès à la liste des poids des expressions.
     * @return La liste des poids.
     */
    public ArrayList<Double> getWeights(){
        return weights;
    }
    /**
     * Permet de savoir si un mot est terminal ou pas.
     * @return Renvoie true si le mot est terminal, false sinon.
     */
    public boolean isTerm(){
        return expr.isEmpty();
    }
    /**
     * Divise le poids d'une expression par deux. Utilisé quand une expression est
     * utilisée dans la génération de phrase.
     * @param i : L'index de l'expression dont le poids doit être divisé par deux.
     */
    public void halfWeight(int i){
        weights.set(i, weights.get(i)/2);
    }

    public int getMinOccurence(){
        return minOccurence;
    }


    public int getMaxOccurence() {
        return maxOccurence;
    }

    public void setMinOccurence(int minOccurence) {
        this.minOccurence = minOccurence;
    }

    public void setMaxOccurence(int maxOccurence) {
        this.maxOccurence = maxOccurence;
    }
    
    /**
     * Choisit aléatoirement une expression dans la liste des expressions en tenant
     * compte des poids attribués à ces expressions.
     * @return L'expression choisie.
     */
    public Expression getRandomExpr() {
        double totalWeight = 0.00d;
        for (double w : weights){
            totalWeight += w;
        }
        int indexRandom = -1;
        double random = Math.random()*totalWeight;
        for (int i = 0; i < expr.size(); i++){
            random -= weights.get(i);
            if (random <= 0.0d){
                indexRandom = i;
                break;
            }
        }
        halfWeight(indexRandom);
        return expr.get(indexRandom);
    }
    /**
     * Permet de trouver une expression dans la liste.
     * @param ex : L'expression à trouver.
     * @return True si l'expression a été trouvée, false sinon.
     */
    public boolean findExpr(Expression ex){
        for (Expression e : expr){
            if (ex.equals(e)){
                return true;
            }
        }
        return false;
    }
    
    public boolean equals(Production p){
    return this.mot.equals(p.getMot()) && this.minOccurence == p.getMinOccurence() && this.maxOccurence == p.getMaxOccurence();
    }
}
