package org.example;

import java.sql.SQLOutput;
import java.util.Deque;
import java.util.LinkedList;

public class Game {
    Board board;
    Dice dice;
    Deque<Player> playesList=new LinkedList<>();
    Player winner;

    public Game(){
        intializegame();
    }
    private void intializegame(){
        board=new Board(10,5,4);
        dice=new Dice(1);
        winner=null;
        addPlayes();
    }
    private void addPlayes(){
        Player player1=new Player("p1",0);
        Player player2=new Player("p2",0);
        playesList.add(player1);
        playesList.add(player2);
    }
    public  void startGame(){
        while(winner==null){
            Player playerTurn=findPlayerTurn();
            System.out.println("Player turn is "+playerTurn.id+"  current position is :"+playerTurn.currentPosition);

            int diceNumbers=dice.rollDice();

            int playerNewPosition=playerTurn.currentPosition+diceNumbers;
            playerNewPosition=jumpCheck(playerNewPosition);
            playerTurn.currentPosition=playerNewPosition;

            System.out.println("Player turn is : "+playerTurn.id + " new positionis :"+playerNewPosition);

            if(playerNewPosition>=board.cells.length*board.cells.length-1){
                winner=playerTurn;
            }
        }
        System.out.println("Winner is:"+winner.id);
    }
    private Player findPlayerTurn(){
        Player playerTurns=playesList.removeFirst();
        playesList.addLast(playerTurns);
        return  playerTurns;
    }
    private  int jumpCheck(int playerNewPosition){
        if(playerNewPosition> board.cells.length*board.cells.length-1)
                return  playerNewPosition;

        Cell cell=board.getCell(playerNewPosition);
        if(cell.jump!=null && cell.jump.start==playerNewPosition){
            String jumpBY=(cell.jump.start<cell.jump.end) ? "ladder":"Snake";
            System.out.println("jump done by "+jumpBY);
            return cell.jump.end;
        }
        return playerNewPosition;
    }


}
