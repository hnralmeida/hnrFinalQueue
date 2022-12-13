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

//Prometean herda de guerreiro
public class Prometean extends Warrior {

    //Construtor de Prometean
    public Prometean(int weight, int age, String name) {
        super(weight, age, name);
        this.setIcon("data\\" + this.getClass().getSimpleName() + ".png");
    }

    /*Sobreescrita do método dies de guerreiro
    se o prometean morre , adiciona no ultimo da fila um novo prometeano e seta a energia dele como 
    a sua energia / 2 (50% da energia do prometeano original) e quando chega a 1 de energia, ele morre por completo
    */
    @Override
    public void dies (LinkedList<Warrior> time, LinkedList<Warrior> dead){
        if(this.getMaxEnergy() > 1){
            time.addLast(new Prometean(this.getWeight(), this.getAge(), this.getName()));
            time.getLast().setMaxEnergy(this.getMaxEnergy()/2);
            time.getLast().setCurrentEnergy(this.getMaxEnergy()/2);

            time.addLast(new Prometean(this.getWeight(), this.getAge(), this.getName()));
            time.getLast().setMaxEnergy(this.getMaxEnergy()/2);
            time.getLast().setCurrentEnergy(this.getMaxEnergy()/2);
        }
        super.dies(time, dead);
    }
    
    //Sobreescrita do metodo atacar de guerreiro
    @Override
    public void atacar(LinkedList timeA, LinkedList timeB) {
        Warrior warrior = (Warrior) timeB.get(0);
        warrior.loseEnergy(10);
    }
    
}
