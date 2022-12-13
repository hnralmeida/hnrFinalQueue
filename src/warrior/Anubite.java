/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package warrior;

import java.util.LinkedList;

/**
 *
 * @author 2021122760224
 */

//Anubite é um herdeiro de Guerreiro
public class Anubite extends Warrior {

    // Construtor de Anubite

    /**
     *
     * @param weight peso do Anubite
     * @param age idade do Anubite
     * @param name nome do Anubite
     */
    public Anubite(int weight, int age, String name) {
        super(weight, age, name);
        this.setIcon("data\\" + this.getClass().getSimpleName() + ".png");
    }
    
    /*Método atacar de Anubite, utilizando como sobreescrita o método abstrato atacar de guerreiro
    Cria a variável guerreiro pegando a primeira posição do timeB (que irá sofrer o ataque)
    Chama o metodo loseEnergy de Guerreiro
    
    */
    @Override
    public void atacar(LinkedList timeA, LinkedList timeB) {
        Warrior warrior = (Warrior) timeB.get(0);
        warrior.loseEnergy(15);
        if(timeB.size()>1){
            warrior = (Warrior) timeB.getLast();
            warrior.loseEnergy(15);
        }
    }
    
}
