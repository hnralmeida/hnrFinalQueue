/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thefinalqueue;

import graphic.Container;
import java.io.FileInputStream;
import java.io.IOException;
import warrior.Warrior;
import java.util.*;
import java.util.concurrent.TimeUnit;
import warrior.Anubite;
import warrior.Argus;
import warrior.Cyclope;
import warrior.Feris_Wolf;
import warrior.Hydra;
import warrior.Nemean_Lion;
import warrior.Mummy;
import warrior.Prometean;
import warrior.Satyr;
import warrior.Valkyrie;

/**
 * 
 * @author 2021122760046
 */
public class Match {
    private final LinkedList<Warrior> teamA;
    private final LinkedList<Warrior> teamB;
    private final LinkedList<Warrior> deadA;
    private final LinkedList<Warrior> deadB;
    
    private final long TIME_PAUSE = (long) 1.5;

    /**
    * inicializa os 2 times de uma partida a partir de arquivos
    * @param timeA O nome do primeiro arquivo a ser lido
    * @param timeB O nome do segundo arquivo a ser lido
    * @throws java.io.IOException tratamento da exceção
    */
    public Match(String timeA, String timeB) throws IOException{
        teamA = new LinkedList();
        teamB = new LinkedList();
        deadA = new LinkedList();
        deadB = new LinkedList();
        this.init(timeA, timeB);
    }
    
    /**
     * retorna um guerreiro na posição i no paremetro no timeA
     * @return a lista de guerreiro, se a posição for válida para o timeA
     */
    public LinkedList getTeamA() {
        return (LinkedList) this.teamA;
    }
   
    /**
     * retorna um guerreiro na posição i no paremetro no timeB
     * @return O guerreiro procurado, se a posição for válida para o timeB
     */
    public LinkedList getTeamB() {
        return (LinkedList) this.teamB;
    }

    /**
     * @brief realiza leitura dos arquivos com nome passado por parametro
     * @param timeA nome do primeiro arquivo a ser lido
     * @param timeB nome do segundo arquivo a ser lido
     * @throws IOException
     * tokens = "recorta" a linha
     */
    private void init(String timeA, String timeB) throws IOException{
        FileInputStream file1 = new FileInputStream(timeA);
        Scanner scan1 = new Scanner(file1);
        while(scan1.hasNextLine()){
            String line = scan1.nextLine();
            String[] tokens = line.split(" ");
            int type = Integer.parseInt(tokens[0]);
            String name = tokens[1];
            int age = Integer.parseInt(tokens[2]);
            int weight = Integer.parseInt(tokens[3]);
            switch(type){
                case 1: this.teamA.add(new Cyclope(weight, age, name)); break;
                case 2: this.teamA.add(new Nemean_Lion(weight, age, name)); break;
                case 3: this.teamA.add(new Hydra(weight, age, name)); break;
                case 4: this.teamA.add(new Valkyrie(weight, age, name)); break;
                case 5: this.teamA.add(new Feris_Wolf(weight, age, name)); break;
                default: System.out.println("Tipo não identificado para (" + type + ") ("+ name + ", " + 
                            age + ", " + weight + ")");break;
            }
        }

        FileInputStream file2 = new FileInputStream(timeB);
        Scanner scan2 = new Scanner(file2);
        while(scan2.hasNextLine()){
            String line = scan2.nextLine();
            String[] tokens = line.split(" ");
            int type = Integer.parseInt(tokens[0]);
            String name = tokens[1];
            int age = Integer.parseInt(tokens[2]);
            int weight = Integer.parseInt(tokens[3]);
            switch(type){
                case 1: this.teamB.add(new Prometean(weight, age, name)); break;
                case 2: this.teamB.add(new Satyr(weight, age, name)); break;
                case 3: this.teamB.add(new Argus(weight, age, name)); break;
                case 4: this.teamB.add(new Anubite(weight, age, name)); break;
                case 5: this.teamB.add(new Mummy(weight, age, name)); break;
                default: System.out.println("Tipo não identificado para (" + type + ") ("+ name + ", " + 
                            age + ", " + weight + ")"); break;
            }
        }
        
        int weighttotal=0;
        for (int i = 0; i < teamA.size(); i++) {
            weighttotal += teamA.get(i).getWeight();
        }
        System.out.println("Gregos e Nordicos pesam  (" + weighttotal + ") unidades ");
        
        weighttotal=0;
        for (int i = 0; i < teamB.size(); i++) {
            weighttotal += teamB.get(i).getWeight();
        }
        System.out.println("Atlantes e Egipcios pesam  (" + weighttotal + ") unidades ");
        
        String nome= "";
        int idadeMax=0;
        int aux;
        for (int i = 0; i < teamA.size(); i++) {
            aux= teamA.get(i).getAge();
            if (idadeMax < aux){
                nome = teamA.get(i).getName();
                idadeMax= aux;
            }    
        }
        String nome2= "";
        int idadeMax2=0;
        int aux2;
        for (int i = 0; i < teamB.size(); i++) {
            aux2= teamB.get(i).getAge();
            if (idadeMax2 < aux2){
                nome2 = teamB.get(i).getName();
                idadeMax2= aux2;
           }    
        }
        
        if (idadeMax > idadeMax2){
            System.out.println("" + nome + " e o mais velho (" + idadeMax + ")");
        } else {
            System.out.println("" + nome2 + " e o mais velho (" + idadeMax2 + ")");
        }
    }
    
    /**
     * @brief percorre ambas filas de guerreiros verificando se há guerreiros 
     * mortos e faz com que o primeiro da fila inimiga elimine 
     * (warrior.killWarrior) um guerreiro com energia<0
     */
    private void check(){
        // Separa os atacantes de cada fila
        Warrior attackerA = this.teamA.get(0);
        Warrior attackerB = this.teamB.get(0);

        // Verifica se há na fila A mortos, remove eles e os coloca na primeira 
        // posição para ser devidamente eliminados 
        if(!this.teamA.isEmpty()){
            for(int i = 0; i< this.teamA.size(); i++){
                if (this.teamA.get(i).getCurrentEnergy() < 1){
                    this.teamA.addFirst(this.teamA.remove(i));
                    attackerB.killsWarrior(this.teamB, this.teamA, this.deadA);
                    i=-1;
                }
            }
        }
        
        // Verifica se há na fila B mortos, remove eles e os coloca na primeira 
        // posição para ser devidamente eliminados 
        if(!this.teamB.isEmpty()){
            for(int i = 0; i< this.teamB.size(); i++){
                if (this.teamB.get(i).getCurrentEnergy()<1){
                    this.teamB.addFirst(this.teamB.remove(i));
                    attackerA.killsWarrior(this.teamA, this.teamB, this.deadB);
                    i=-1;
                }
            }
        }
    }
    
    private void printScene(Container tela) throws InterruptedException{
        tela.repaint();
        TimeUnit.SECONDS.sleep(TIME_PAUSE);
    }

    /**
     * funcao principal da classe, realiza uma luta entre as filas que  
     * possui como atributo.
     * @throws java.lang.InterruptedException tratamento da exceção
     * @param tela parte grafica
     */
    public void fight(Container tela) throws InterruptedException{
        this.printScene(tela);
        this.printScene(tela);
        int war1, war2;
        do{
            // Define o primeiro Warrior de cada fila como apto a batalhar
            this.printScene(tela);
            //<editor-fold defaultstate="collapsed" desc="realização dos ataques">    
            Warrior warrior1 = (Warrior) this.teamA.get(0);
            Warrior warrior2 = (Warrior) this.teamB.get(0);
            warrior1.setReady(1);
            warrior2.setReady(1);
                    
            // Escole quem vai atacar primeiro e realiza os ataques da rodada
            int random = (int) ( 10*Math.random() );
            if(random%2==1){
                if(warrior1.getReady()==1) warrior1.atacar(teamA, teamB);
                this.printScene(tela);
                if(warrior2.getReady()==1 && warrior2.getCurrentEnergy()>0) warrior2.atacar(teamB, teamA);
            }
            else {
                if(warrior2.getReady()==1) warrior2.atacar(teamB, teamA);
                this.printScene(tela);
                if(warrior1.getReady()==1 && warrior1.getCurrentEnergy()>0) warrior1.atacar(teamA, teamB);
            }
            //</editor-fold>
            
            // Verifica se existem mortos nas filas
            this.printScene(tela);
            this.check();
            this.printScene(tela);
            
            // Verifica a quantidade de Guerreiro e passa o primeiro para o ultimo
            // Acontece só se tiver ao menos 1 guerreiro
            war1 = this.teamA.size();
            war2 = this.teamB.size();
            if(war1!=0) this.teamA.addLast(this.teamA.removeFirst());
            if(war2!=0) this.teamB.addLast(this.teamB.removeFirst());
            // Se alguma fila estiver com 0 Warrior, termina o programa
        } while(war1!=0 && war2!=0);
                
        // Quando a batalha termina, verifica qual fila está vazia (Perdeu)
        // e indica ultimo golpe
        if(this.teamB.isEmpty()){
            Warrior aux1 = (Warrior) this.teamA.getLast();
            Warrior aux2 = (Warrior) this.deadB.getLast();
            System.out.println("Gregos e Nordicos venceram");
            System.out.println(aux2.toString() );
            System.out.println(aux1.toString() );
        }else{
            Warrior aux1 = (Warrior) this.deadA.getLast();
            Warrior aux2 = (Warrior) this.teamB.getLast();
            System.out.println("Atlantes e Egipicios venceram");
            System.out.println(aux1.toString() );
            System.out.println(aux2.toString() );
        }
    }
}
