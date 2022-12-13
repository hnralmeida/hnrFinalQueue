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

//Mummy herda de guerreiro
public class Mummy extends Warrior {

    
    //Construtor de Mumia
    public Mummy(int weight, int age, String name) {
        super(weight, age, name);
        this.setIcon("data\\" + this.getClass().getSimpleName() + ".png");
    }
    
    
    /*Sobreescrita do método dies de guerreiro passando a lista de guerreiro como 1º parametro
    e a lista de guerreiros mortos como 2ª parametro
    adiciona 4 anubite no final da fila
    */
   
    @Override
    public void dies (LinkedList<Warrior> time, LinkedList<Warrior> dead){
        time.addLast(new Anubite(60, 0, this.getName()));
        time.addLast(new Anubite(60, 0, this.getName()));
        time.addLast(new Anubite(60, 0, this.getName()));
        time.addLast(new Anubite(60, 0, this.getName()));
        super.dies(time, dead);
    }
    
    /* Sobreescrita do método killsWarrior de guerreiro, passando parametro a lista do timeA
    a lista do timeB e a lista de mortos
    cria um morto vivo no timeA, pegando o peso, idade e nome
    */
    
    @Override
    public void killsWarrior (LinkedList<Warrior> timeA, LinkedList<Warrior> timeB, LinkedList<Warrior> deadB){
        timeA.add(new Undead(timeB.get(0).getWeight(), timeB.get(0).getAge(), timeB.get(0).getName()));
        super.killsWarrior(timeA, timeB, deadB);
    }

    // Sobreescrita do método atacar de guerreiro
    @Override
    public void atacar(LinkedList timeA, LinkedList timeB) {
        Warrior warrior = (Warrior) timeB.get(0);
        warrior.loseEnergy(50);
    }
    
}
