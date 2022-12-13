/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warrior;

import java.awt.Image;
import java.util.LinkedList;
import javax.swing.ImageIcon;

/**
 * Guerreiro é a classe principal de personagens no jogo, da qual são criados
 * os tipos de guerreiro especialiados
 * @author 2021122760224
 */
public abstract class Warrior {
    final private String name;
    private int weight;
    private int maxEnergy;
    protected int currentEnergy;
    private int age;
    private int ready;
    
    // Parte Grafica
    private int x,y;
    private Image icon;
    private int width, height;

    /**
     * Um guerreiro pronto para batalha é criado com os parametros 
     * passados e com energia base 100
     * @param weight peso
     * @param age idade
     * @param name nome
     */
    public Warrior(int weight, int age, String name) {
        this.name = name;
        this.weight = weight;
        this.age = age;
        this.maxEnergy = 100;
        this.currentEnergy = 100;
        this.ready = 1;
        this.y = 525;
        this.x = 640;
    }
        
    /**
     * 
     * @return width
     */
    public int getWidth() {
        return width;
    }

    
    /**
     * 
     * @return height 
     */
    public int getHeight() {
        return height;
    }

    
    /**
     * 
     * @return x position 
     */
    public int getX() {
        return x;
    }
 
    /**
     * 
     * @param x new x position 
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 
     * @return y position 
     */
    public int getY() {
        return y;
    }

    /**
     * 
     * @param y new y position 
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * 
     * @return Graphic warrior img
     */
    public Image getIcon() {
        return icon;
    }

    /**
     * 
     * @param path dir to the image of warrior img
     */
    public void setIcon(String path) {
        ImageIcon reff = new ImageIcon(path);
        this.icon = reff.getImage();
        width = this.icon.getWidth(null);
        this.height = this.icon.getHeight(null);
    }

    @Override
    public String toString() {
        return name + ", " + weight + ", " + age;
    }

    /*
    * @brief retorna nome do guerreiro
    */
    public String getName() {
        return name;
    }
    
    /**
     * Retorna se um guerreiro está apto a atacar
     * @return true or false
     */
    public int getReady() {
        return this.ready;
    }

    /**
     * Define se um guerreiro está preparado ou não
     * @param ready o novo status
     */
    public void setReady(int ready) {
        this.ready = ready;
    }

    /**
     * Retorna o valor do peso do guerreiro
     * @return o peso do guerreiro
     */
    public int getWeight() {
        return this.weight;
    }

    /**
     *
     * @param weight define peso para o guerreiro
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * 
     * @return energia máxima do guerreiro
     */
    public int getMaxEnergy() {
        return maxEnergy;
    }

    /**
     * 
     * @param maxEnergy energia máxima do guerreiro
     */
    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    /**
     * Retorna o valor da energia atual do guerreiro
     * @return a energia atual
     */
    public int getCurrentEnergy() {
        return this.currentEnergy;
    }

    /**
     * Define o valor da energia atual respeitando o máximo
     * @param currentEnergy valor da energia atual
     */
    public void setCurrentEnergy(int currentEnergy) {
        if(currentEnergy>this.getMaxEnergy()) currentEnergy = this.getMaxEnergy();
        this.currentEnergy = currentEnergy;
    }

    /**
     * Retorna o valor da idade do guerreiro
     * @return idade do guerreiro
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Define um valor de idade para o guerreiro passado como parametro
     * @param age idade do guerreiro
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Todo Guerreiro deve saber atacar, um ataque ocorre a partir da 
     * primeira lista, timeA, e tem alvo na segunda lista, timeB, o tipo de 
     * ataque (guerreiro atacante que chama a função) define a interação com
     * ambas as filas
     * @param timeA o lado atacante
     * @param timeB o lado alvo
     */
    public abstract void atacar (LinkedList timeA, LinkedList timeB); 
    
    /**
     * Um guerreiro morto é colocado na lista de guerreiros mortos
     * @param time o lado ao qual pertence o guerreiro
     * @param dead o lado de mortos ao qual vai ser adicionado
     */
    protected void dies (LinkedList<Warrior> time, LinkedList<Warrior> dead){
        this.setCurrentEnergy(0);
        this.setMaxEnergy(0);
        this.setReady(0);
        dead.addLast(this);
    }
    
    /**
     * O guerreiro da fila alvo é removido da fila
     * @param timeA Linked list time A
     * @param timeB Linked list time B
     * @param deadB Linked list mortos do time B
     */
    public void killsWarrior (LinkedList<Warrior> timeA, LinkedList<Warrior> timeB, LinkedList<Warrior> deadB){
        timeB.removeFirst().dies(timeB, deadB);
    }
    
    /**
     * Um guerreiro perde energia igual ao parametro
     * @param i Quantidade de energia perdida
     * função setCurrentEnergy para setar a energia atual do guerreiro que sofreu o ataque
     */
    public void loseEnergy (int i){
        int hp = this.getCurrentEnergy()- i;
        this.setCurrentEnergy(hp);
    }
}
