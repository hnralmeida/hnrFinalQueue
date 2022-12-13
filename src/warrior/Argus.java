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

//Classe Argus herda de Guerreiro
public class Argus extends Warrior {
    
    // Construtor de Argus
    // Seta energia máxima e atual como 60
    public Argus(int weight, int age, String name) {
        super(weight, age, name);
        this.setMaxEnergy(60);
        this.setCurrentEnergy(60);
        this.setIcon("data\\" + this.getClass().getSimpleName() + ".png");
    }

    // Sobreescrita do método atacar de guerreiro
    // Seta a energia do guerreiro que sofrerá o ataque como 0 "Insta Kill"
    @Override
    public void atacar(LinkedList timeA, LinkedList timeB) {
        Warrior warrior = (Warrior) timeB.get(0);
        warrior.setCurrentEnergy(0);
    }
    
}
