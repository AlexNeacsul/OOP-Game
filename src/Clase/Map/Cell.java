package Clase.Map;

import Clase.Entities.Enemy;
import Enumerations.CellEntityType;

public class Cell {
    private int Ox;
    private int Oy;
    private CellEntityType type;
    private CellEntityType originalType;
    private boolean visited;
    private Enemy enemy;

    public Cell(int Ox, int Oy, CellEntityType type) {
        this.Ox = Ox;
        this.Oy = Oy;
        this.type = type;
        this.originalType = type;
        this.visited = false;
        if(type == CellEntityType.ENEMY) {
            this.enemy = new Enemy();
        }
    }

    public int getOx() {
        return Ox;
    }

    public void setOx(int ox) {
        Ox = ox;
    }

    public int getOy() {
        return Oy;
    }

    public void setOy(int oy) {
        Oy = oy;
    }

    public CellEntityType getType() {
        return type;
    }

    public void setType(CellEntityType type) {
        this.type = type;
    }

    public CellEntityType getOriginalType() {
        return originalType;
    }

    public void setOriginalType(CellEntityType originalType) {
        this.originalType = originalType;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }
}
