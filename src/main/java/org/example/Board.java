package org.example;

import java.util.concurrent.ThreadLocalRandom;

public class Board {
    Cell[][] cells;
    Board(int boardSize,int numberofSnakes,int numberofLadder){
        initializeCells(boardSize);
        addSnakeAndLadder(cells,numberofSnakes,numberofLadder);
    }
    private  void initializeCells(int boardSize){
        cells=new Cell[boardSize][boardSize];

        for(int i=0;i<boardSize;i++){
            for(int j=0;j<boardSize;j++){
                Cell cellObj=new Cell();
                cells[i][j]=cellObj;
            }
        }
    }

    private void addSnakeAndLadder(Cell[][] cells,int numberOfSnakes,int numberOfLadder){
            while(numberOfSnakes>0){
                int snakeHead= ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);
                int snakeTail=ThreadLocalRandom.current().nextInt(1,cells.length* cells.length-1);

                if(snakeTail>=snakeHead)
                        continue;
                Jump snakeObj=new Jump();
                snakeObj.start=snakeHead;
                snakeObj.end=snakeTail;
                Cell cell=getCell(snakeHead);
                cell.jump=snakeObj;
                numberOfSnakes--;
            }

            while (numberOfLadder>0){
                int ladderStart=ThreadLocalRandom.current().nextInt(1, cells.length* cells.length-1);
                int ladderEnd=ThreadLocalRandom.current().nextInt(1,cells.length*cells.length-1);

                if(ladderEnd<=ladderStart)
                        continue;

                Jump ladderObj=new Jump();
                ladderObj.start=ladderStart;
                ladderObj.end=ladderEnd;

                Cell cellObj=getCell(ladderStart);
                cellObj.jump=ladderObj;
                numberOfLadder--;
            }

        }
    Cell getCell(int PlayerPosition){
        int boardRow=PlayerPosition/cells.length;
        int boardCol=PlayerPosition%cells.length;

        return cells[boardRow][boardCol];
    }
}

