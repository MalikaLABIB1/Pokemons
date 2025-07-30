import java.awt.Point;
import java.util.*;

public class Board {
    private Pokemon[][] grid;

    public Board(int size) {
        grid = new Pokemon[size][size];
    }

    public void placePokemon(int row, int col, Pokemon pokemon) {
        if (isValidPosition(row, col)) {
            grid[row][col] = pokemon;
        }
    }

    public Pokemon getPokemon(int row, int col) {
        if (isValidPosition(row, col)) {
            return grid[row][col];
        }
        return null;
    }

    public boolean movePokemon(int fromRow, int fromCol, int toRow, int toCol) {
        if (!isValidMove(fromRow, fromCol, toRow, toCol)) {
            return false;
        }

        grid[toRow][toCol] = grid[fromRow][fromCol];
        grid[fromRow][fromCol] = null;
        return true;
    }

    public boolean isValidMove(int fromRow, int fromCol, int toRow, int toCol) {
        if (!isValidPosition(fromRow, fromCol) || !isValidPosition(toRow, toCol)) {
            return false;
        }

        Pokemon pokemon = grid[fromRow][fromCol];
        if (pokemon == null || grid[toRow][toCol] != null) {
            return false;
        }

        int rowDiff = Math.abs(toRow - fromRow);
        int colDiff = Math.abs(toCol - fromCol);
        int maxMove = pokemon.getMovement();

        if (pokemon.getMovement() == 5) {
            return (rowDiff == 0 || colDiff == 0) && !isPathBlocked(fromRow, fromCol, toRow, toCol);
        } else if (pokemon.getMovement() == 3 && rowDiff == colDiff) {
            return !isPathBlocked(fromRow, fromCol, toRow, toCol);
        } else if (pokemon.getMovement() == 3) {
            return (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);
        } else if (pokemon.getMovement() == 7) {
            return (rowDiff == 0 || colDiff == 0 || rowDiff == colDiff) && !isPathBlocked(fromRow, fromCol, toRow, toCol);
        } else {
            return rowDiff <= maxMove && colDiff <= maxMove;
        }
    }

    public boolean isValidAttack(int fromRow, int fromCol, int toRow, int toCol) {
        if (!isValidPosition(fromRow, fromCol) || !isValidPosition(toRow, toCol)) {
            return false;
        }

        Pokemon attacker = grid[fromRow][fromCol];
        Pokemon target = grid[toRow][toCol];

        if (attacker == null || target == null || attacker.isPlayer2() == target.isPlayer2()) {
            return false;
        }

        return Math.abs(fromRow - toRow) <= 1 && Math.abs(fromCol - toCol) <= 1;
    }

    public void attack(int fromRow, int fromCol, int toRow, int toCol) {
        Pokemon attacker = getPokemon(fromRow, fromCol);
        Pokemon defender = getPokemon(toRow, toCol);
        if (attacker == null || defender == null) return;

        attacker.attaque(defender);

        if (attacker.getHp() <= 0) {
            setPokemon(fromRow, fromCol, null);
        }
        if (defender.getHp() <= 0) {
            setPokemon(toRow, toCol, null);
        }
    }

    private boolean isPathBlocked(int fromRow, int fromCol, int toRow, int toCol) {
        int rowStep = Integer.compare(toRow, fromRow);
        int colStep = Integer.compare(toCol, fromCol);

        int currentRow = fromRow + rowStep;
        int currentCol = fromCol + colStep;

        while (currentRow != toRow || currentCol != toCol) {
            if (grid[currentRow][currentCol] != null) {
                return true;
            }
            currentRow += rowStep;
            currentCol += colStep;
        }

        return false;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    public List<Point> getValidMoves(int fromRow, int fromCol) {
        List<Point> validMoves = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (isValidMove(fromRow, fromCol, row, col)) {
                    validMoves.add(new Point(col, row));
                }
            }
        }
        return validMoves;
    }

    public void setPokemon(int row, int col, Pokemon pokemon) {
        if (isValidPosition(row, col)) {
            grid[row][col] = pokemon;
        }
    }
}
