package Clase.Map;

import Clase.Entities.Character;
import Clase.Entities.Warrior;
import Clase.Exceptii.ImpossibleMove;
import Enumerations.CellEntityType;
import Enumerations.Colors;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Grid extends ArrayList<ArrayList<Cell>> {
    private int width;
    private int length;
    private Character character;
    private Cell currentCell;
    private boolean firstMove = true;

    public Grid(int width, int length, Character character) {
        this.width = width;
        this.length = length;
        this.character = character;
        this.currentCell = null;

        for (int i = 0; i < width; i++) {
            ArrayList<Cell> row = new ArrayList<>();
            for (int j = 0; j < length; j++) {
                row.add(new Cell(i, j, CellEntityType.VOID));
            }
            this.add(row);
        }
    }

    private static int randomNumber(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static Grid generateMap(int width, int length, Character character) {
        if (width == 5 && length == 5) {
            Grid map = new Grid(width, length, character);
            Random random = new Random();

            map.get(0).set(3, new Cell(0, 3, CellEntityType.SANCTUARY));
            map.get(1).set(3, new Cell(1, 3, CellEntityType.SANCTUARY));
            map.get(4).set(3, new Cell(4, 3, CellEntityType.SANCTUARY));
            map.get(2).set(0, new Cell(2, 0, CellEntityType.SANCTUARY));
            map.get(4).set(4, new Cell(4, 4, CellEntityType.PORTAL));
            map.get(3).set(4, new Cell(3, 4, CellEntityType.ENEMY));

            Cell playerCell = new Cell(0, 0, CellEntityType.PLAYER);
            playerCell.setVisited(true);
            map.get(0).set(0, playerCell);

            map.currentCell = playerCell;
            return map;
        }

        if (width > 10 || length > 10 || width < 0 || length < 0) {
            throw new IllegalArgumentException("Map size is too big");
        }

        width = randomNumber(5, width);
        length = randomNumber(5, length);

        Grid map = new Grid(width, length, character);
        Random random = new Random();

        for (int i = 0; i < randomNumber(0, 2); i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(length);
            map.get(x).set(y, new Cell(x, y, CellEntityType.SANCTUARY));
        }

        for (int i = 0; i < randomNumber(0, 4); i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(length);
            while (map.get(x).get(y).getType() != CellEntityType.VOID) {
                x = random.nextInt(width);
                y = random.nextInt(length);
            }
            map.get(x).set(y, new Cell(x, y, CellEntityType.ENEMY));
        }

        int x = random.nextInt(width);
        int y = random.nextInt(length);
        while (map.get(x).get(y).getType() != CellEntityType.VOID) {
            x = random.nextInt(width);
            y = random.nextInt(length);
        }
        map.get(x).set(y, new Cell(x, y, CellEntityType.PORTAL));

        while(map.get(x).get(y).getType() != CellEntityType.VOID) {
            x = random.nextInt(width);
            y = random.nextInt(length);
        }
        Cell playerCell = new Cell(x, y, CellEntityType.PLAYER);
        playerCell.setVisited(true);
        map.get(x).set(y, playerCell);

        map.currentCell = playerCell;
        return map;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Cell getCurrentcell() {
        return currentCell;
    }

    public void setCurrentcell(Cell currentcell) {
        this.currentCell = currentcell;
    }

    public void displayMap() {
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.get(i).size(); j++) {
                Cell cell = this.get(i).get(j);
                String cellRepresentation;
                String color;
                String reset = Colors.RESET.getColor();
                switch (cell.getType()) {
                    case PLAYER:
                        cellRepresentation = "P";
                        color = Colors.GREEN_BRIGHT.getColor();
                        break;
                    case VOID:
                        if (cell.isVisited()) {
                            cellRepresentation = "V";
                        } else {
                            cellRepresentation =  "N";
                        }
                        color = reset;
                        break;
                    case ENEMY:
                        cellRepresentation = "E";
                        color = Colors.RED_BRIGHT.getColor();
                        break;
                    case SANCTUARY:
                        cellRepresentation = "S";
                        color = Colors.CYAN_BRIGHT.getColor();
                        break;
                    case PORTAL:
                        cellRepresentation = "F";
                        color = Colors.PURPLE_BRIGHT.getColor();
                        break;
                    default:
                        cellRepresentation = "?";
                        color = reset;
                }
                System.out.print(color + cellRepresentation + reset + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void goHelper(Cell newCell) {
        if (firstMove) {
            currentCell.setType(CellEntityType.VOID);
            firstMove = false;
        } else {
            currentCell.setType(currentCell.getOriginalType());
        }
        currentCell.setVisited(true);

        newCell.setOriginalType(newCell.getType());
        newCell.setType(CellEntityType.PLAYER);
        currentCell = newCell;
    }

    public void goNorth() throws ImpossibleMove {
        int x = currentCell.getOx();
        int y = currentCell.getOy();
        if (x - 1 < 0) {
            throw new ImpossibleMove("Marginea de nord a hărții.");
        }
        goHelper(this.get(x - 1).get(y));
        displayMap();
    }

    public void goSouth() throws ImpossibleMove {
        int x = currentCell.getOx();
        int y = currentCell.getOy();
        if (x + 1 >= length) {
            throw new ImpossibleMove("Marginea de sud a hărții.");
        }
        goHelper(this.get(x + 1).get(y));
        displayMap();
    }

    public void goWest() throws ImpossibleMove {
        int x = currentCell.getOx();
        int y = currentCell.getOy();
        if (y - 1 < 0) {
            throw new ImpossibleMove("Marginea de vest a hărții.");
        }
        goHelper(this.get(x).get(y - 1));
        displayMap();
    }

    public void goEast() throws ImpossibleMove {
        int x = currentCell.getOx();
        int y = currentCell.getOy();
        if (y + 1 >= width) {
            throw new ImpossibleMove("Marginea de est a hărții.");
        }
        goHelper(this.get(x).get(y + 1));
        displayMap();
    }
}