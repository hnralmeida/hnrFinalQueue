/*
*   =========================================================================================
*   AUTORES: 	
*   			Henrique Almeida de Oliveira
                        Luiz Eduardo Marchiori

*   Disciplina: Programacao Orientada a Objetos
*   Trabalho de POO1 - The Final Queue
*   =========================================================================================
*/
package thefinalqueue;

import graphic.Container;
import java.io.*;

public class TheFinalQueue {

    /**
     * Age of Mythology – The Final Queue
     * @param args the command line arguments
     * cria uma variável teste com o tipo Match (LinkedList), cria uma luta
     * com os arquivos de texto - lado1 e lado2
     */
    public static void main(String[] args) {
        // tenta
        try{
            Match teste = new Match("lado1.txt", "lado2.txt");
            Container tela = new Container(teste);
            teste.fight(tela);
        }catch (FileNotFoundException erro) {
            System.out.println("Arquivos não encontrados");
        }catch (InterruptedException erro){
            System.out.println("Programa Interrompido: "+ erro);
        }catch(IOException erro){
            System.out.println(erro);
        }
    }
}
