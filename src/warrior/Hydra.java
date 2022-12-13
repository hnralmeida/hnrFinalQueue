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

//Hydra herda de Guerreiro
public class Hydra extends Warrior {
    private int heads;
    
    //Construtor de Hydra, setando sua cabeça padrão como 1
    public Hydra(int weight, int age, String name) {
        super(weight, age, name);
        this.heads = 1;
        this.setIcon("data\\" + this.getClass().getSimpleName() + ".png");
    }
    
    /*Sobreescrita do método atacar de Guerreiro (abstrato)
    Cria uma váriavel guerreiro do tipo guerreiro
    A cada vez que ele mata um inimigo, ele ganha uma nova cabeça e recupera 10 de hp
    o dano que o inimigo irá sofrer será de 50+(cabeça - 1) * 10
    se o guerreiro do timeB morrer, adiciona na variável cabeça +1
    
    */
    @Override
    public void atacar(LinkedList timeA, LinkedList timeB) {
         Warrior warrior = (Warrior) timeB.get(0);
         int dano=50+((this.heads-1)*10);
         warrior.loseEnergy(dano);
         if (warrior.getCurrentEnergy()< 1){
             this.heads++;
             this.setCurrentEnergy(currentEnergy+10);
         }
    }
    
}
