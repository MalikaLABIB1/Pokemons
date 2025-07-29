import MG2D.*;
import MG2D.geometrie.*;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.SwingUtilities;

public class PokemonChess extends Jeu {
    private static final int TILE_SIZE = 64;
    private static final int BOARD_SIZE = 9;
    private Texte statusText;
    private HashMap<String, Image> pokemonImages;
    private Fenetre f;
    private Panneau p;
    private List<Dessin> listeDessins = new ArrayList<>();
    private List<Point> possibleMoves = new ArrayList<>();
    private int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
    private boolean[][] isWhitePiece = new boolean[BOARD_SIZE][BOARD_SIZE];
    private int selectedRow = -1, selectedCol = -1;
    private boolean whiteTurn = true;

    public PokemonChess() {
        SwingUtilities.invokeLater(() -> {
            f = new Fenetre("Pokemon Chess", BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE + 50);
            p = f.getP();
            
            initializeBoard();
            chargerImagesPokemon();
            ajouterElementsGraphiques();
            updateDisplay();
            
            p.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    handleClick(e.getX(), p.getHeight() - e.getY());
                }
            });
        });
    }

    private void initializeBoard() {
        
        int[] whiteBackRow =  {4, 2, 3, 5, 6, 5, 3, 2, 4};
        int[] blackBackRow =  {4, 2, 3, 6, 5, 3, 2, 4, 4};
        
        
        for (int col = 0; col < BOARD_SIZE; col++) {
            board[0][col] = whiteBackRow[col];
            isWhitePiece[0][col] = true;
            board[1][col] = 1;
            isWhitePiece[1][col] = true;
            
            board[8][col] = blackBackRow[col];
            isWhitePiece[8][col] = false;
            board[7][col] = 1;
            isWhitePiece[7][col] = false;
        }
    }

    @Override
    protected void dessiner() {
        p.getListeDessins().clear();
        p.ajouterTous(listeDessins);
        p.repaint();
        p.revalidate();
    }

    @Override
    public void ajouter(Dessin d) {
        listeDessins.add(d);
    }

    private void ajouterElementsGraphiques() {
        statusText = new Texte(Couleur.NOIR, "White's Turn", 
                            new Font("Arial", Font.BOLD, 16), 
                            new Point(10, BOARD_SIZE * TILE_SIZE + 20));
        ajouter(statusText);
    }

    private void chargerImagesPokemon() {
        pokemonImages = new HashMap<>();
        try {
            HashMap<Integer, Integer> pieceMap = new HashMap<>();
            pieceMap.put(1, 39);   
            pieceMap.put(2, 78);  
            pieceMap.put(3, 101); 
            pieceMap.put(4, 76);  
            pieceMap.put(5, 150); 
            pieceMap.put(6, 146);  

            for (int pieceType : pieceMap.keySet()) {
                String num = String.valueOf(pieceMap.get(pieceType));
                String path = "../images/" + num + ".png";
                File imgFile = new File(path);
                
                if (imgFile.exists()) {
                    Image img = new Image(Couleur.TRANSPARENT, path, new Point(0, 0));
                    img.redimensionner(TILE_SIZE - 10, TILE_SIZE - 10);
                    pokemonImages.put(String.valueOf(pieceType), img);
                }
            }
        } catch (Exception e) {
            System.err.println("Error loading images: " + e.getMessage());
        }
    }

    private void updateDisplay() {
        listeDessins.clear();
        drawGrid();
        drawPokemons();
        dessiner();
    }

    private void drawGrid() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Couleur color = (row + col) % 2 == 0 
                              ? new Couleur(240, 217, 181) 
                              : new Couleur(181, 136, 99);
                
                Rectangle tile = new Rectangle(
                    color,
                    new Point(col * TILE_SIZE, (BOARD_SIZE - 1 - row) * TILE_SIZE),
                    TILE_SIZE,
                    TILE_SIZE,
                    true
                );
                ajouter(tile);
            }
        }
        
        if (selectedRow != -1) {
            Rectangle highlight = new Rectangle(
                new Couleur(255, 255, 0, 100),
                new Point(selectedCol * TILE_SIZE, (BOARD_SIZE - 1 - selectedRow) * TILE_SIZE),
                TILE_SIZE,
                TILE_SIZE,
                true
            );
            ajouter(highlight);
        }
        
        for (Point move : possibleMoves) {
            Rectangle hint = new Rectangle(
                new Couleur(0, 255, 0, 80),
                new Point(move.getX() * TILE_SIZE, (BOARD_SIZE - 1 - move.getY()) * TILE_SIZE),
                TILE_SIZE,
                TILE_SIZE,
                true
            );
            ajouter(hint);
        }
    }

    private void drawPokemons() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] != 0) {
                    String pieceKey = String.valueOf(board[row][col]);
                    Image pokemon = pokemonImages.get(pieceKey);
                    if (pokemon != null) {
                        Image clone = new Image(
                            Couleur.TRANSPARENT,
                            pokemon.getChemin(),
                            new Point(col * TILE_SIZE + 5, (BOARD_SIZE - 1 - row) * TILE_SIZE + 5)
                        );
                        clone.redimensionner(TILE_SIZE - 10, TILE_SIZE - 10);
                        if (!isWhitePiece[row][col]) clone.setCouleur(new Couleur(100, 100, 100, 255));
                        ajouter(clone);
                    }
                }
            }
        }
    }

    private void handleClick(int x, int y) {
        int col = x / TILE_SIZE;
        int row = y / TILE_SIZE;
        
        if (col >= BOARD_SIZE || row >= BOARD_SIZE) return;

        if (selectedRow == -1) {
            if (board[row][col] != 0 && isWhitePiece[row][col] == whiteTurn) {
                selectedRow = row;
                selectedCol = col;
                calculatePossibleMoves();
                updateDisplay();
            }
        } else {
            if (isValidMove(col, row)) {
                
                board[row][col] = board[selectedRow][selectedCol];
                isWhitePiece[row][col] = isWhitePiece[selectedRow][selectedCol];
                board[selectedRow][selectedCol] = 0;
                whiteTurn = !whiteTurn;
                statusText.setTexte(whiteTurn ? "White's Turn" : "Black's Turn");
            }
            selectedRow = -1;
            selectedCol = -1;
            possibleMoves.clear();
            updateDisplay();
        }
    }

    private void calculatePossibleMoves() {
        possibleMoves.clear();
        int piece = board[selectedRow][selectedCol];
        
        switch(piece) {
            case 1: calculatePawnMoves(); break;
            case 2: calculateKnightMoves(); break;
            case 3: calculateBishopMoves(); break;
            case 4: calculateRookMoves(); break;
            case 5: calculateQueenMoves(); break;
            case 6: calculateKingMoves(); break;
        }
    }

    private void calculatePawnMoves() {
        int direction = isWhitePiece[selectedRow][selectedCol] ? 1 : -1;
        int startRow = isWhitePiece[selectedRow][selectedCol] ? 1 : 7;

        
        int newRow = selectedRow + direction;
        if (isValidPosition(newRow, selectedCol) && board[newRow][selectedCol] == 0) {
            possibleMoves.add(new Point(selectedCol, newRow));
            
            
            if (selectedRow == startRow && isValidPosition(newRow + direction, selectedCol) && 
                board[newRow + direction][selectedCol] == 0) {
                possibleMoves.add(new Point(selectedCol, newRow + direction));
            }
        }

        
        int[] captureCols = {selectedCol - 1, selectedCol + 1};
        for (int col : captureCols) {
            if (isValidPosition(newRow, col)) {
                if (board[newRow][col] != 0 && isWhitePiece[newRow][col] != whiteTurn) {
                    possibleMoves.add(new Point(col, newRow));
                }
            }
        }
    }

    private void calculateKnightMoves() {
        int[][] jumps = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
                        {1, -2}, {1, 2}, {2, -1}, {2, 1}};
        for (int[] jump : jumps) {
            int newRow = selectedRow + jump[0];
            int newCol = selectedCol + jump[1];
            if (isValidPosition(newRow, newCol) && 
                (board[newRow][newCol] == 0 || isWhitePiece[newRow][newCol] != whiteTurn)) {
                possibleMoves.add(new Point(newCol, newRow));
            }
        }
    }

    private void calculateBishopMoves() {
        int[][] directions = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        for (int[] dir : directions) {
            for (int dist = 1; dist < BOARD_SIZE; dist++) {
                int newRow = selectedRow + dir[0] * dist;
                int newCol = selectedCol + dir[1] * dist;
                if (!isValidPosition(newRow, newCol)) break;
                
                if (board[newRow][newCol] == 0) {
                    possibleMoves.add(new Point(newCol, newRow));
                } else {
                    if (isWhitePiece[newRow][newCol] != whiteTurn) {
                        possibleMoves.add(new Point(newCol, newRow));
                    }
                    break;
                }
            }
        }
    }

    private void calculateRookMoves() {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            for (int dist = 1; dist < BOARD_SIZE; dist++) {
                int newRow = selectedRow + dir[0] * dist;
                int newCol = selectedCol + dir[1] * dist;
                if (!isValidPosition(newRow, newCol)) break;
                
                if (board[newRow][newCol] == 0) {
                    possibleMoves.add(new Point(newCol, newRow));
                } else {
                    if (isWhitePiece[newRow][newCol] != whiteTurn) {
                        possibleMoves.add(new Point(newCol, newRow));
                    }
                    break;
                }
            }
        }
    }

    private void calculateQueenMoves() {
        calculateBishopMoves();
        calculateRookMoves();
    }

    private void calculateKingMoves() {
        int[][] directions = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, 
                         {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int[] dir : directions) {
            int newRow = selectedRow + dir[0];
            int newCol = selectedCol + dir[1];
            if (isValidPosition(newRow, newCol) && 
                (board[newRow][newCol] == 0 || isWhitePiece[newRow][newCol] != whiteTurn)) {
                possibleMoves.add(new Point(newCol, newRow));
            }
        }
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
    }

    private boolean isValidMove(int col, int row) {
        for (Point move : possibleMoves) {
            if (move.getX() == col && move.getY() == row) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        new PokemonChess();
    }

    @Override
    protected void gererClic(int x, int y) {
        
        throw new UnsupportedOperationException("Unimplemented method 'gererClic'");
    }
}