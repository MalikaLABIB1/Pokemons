import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;

public class PokemonChess {
    private List<Point> validMoves = new ArrayList<>();
    private static final int BOARD_SIZE = 9;
    private static final int TILE_SIZE = 70;
    private Board board;
    private JFrame frame;
    private JPanel chessPanel;
    private int selectedRow = -1, selectedCol = -1;
    private boolean player1Turn = true;
    private Map<String, BufferedImage> pokemonImages = new HashMap<>();
    private List<String> allPokemonNames = new ArrayList<>();
    

    public static void main(String[] args) {
        new PokemonChess();
    }

    public PokemonChess() {
        loadPokemonImages();
        board = new Board(BOARD_SIZE);
        assignRandomTeams();
        createGUI();
    }

    private void loadPokemonImages() {
        File imagesDir = new File("images");
        if (!imagesDir.exists()) {
            boolean created = imagesDir.mkdir();
            if (!created) {
                JOptionPane.showMessageDialog(null,
                    "Could not create images directory!\n" +
                    "Please create an 'images' folder manually in:\n" +
                    new File("").getAbsolutePath(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }
    
        for (int i = 1; i <= 151; i++) {
            String name = String.valueOf(i); 
    
            try {
                File imageFile = new File("images/" + name + ".png");
    
                if (imageFile.exists()) {
                    BufferedImage img = ImageIO.read(imageFile);
                    if (img != null) {
                        pokemonImages.put(name, img);
                    } else {
                        System.out.println("Could not load: " + name + ".png (null image)");
                        pokemonImages.put(name, createPlaceholderImage(i));
                    }
                } else {
                    System.out.println("Missing image: " + name + ".png");
                    pokemonImages.put(name, createPlaceholderImage(i));
                }
    
                allPokemonNames.add(name);
    
            } catch (IOException e) {
                System.err.println("Error loading " + name + ".png: " + e.getMessage());
                pokemonImages.put(name, createPlaceholderImage(i));
                allPokemonNames.add(name);
            }
        }
    }
    private BufferedImage createPlaceholderImage(int pokemonNumber) {
        BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        
        
        g2d.setColor(new Color(
            (pokemonNumber * 37) % 255,
            (pokemonNumber * 57) % 255,
            (pokemonNumber * 77) % 255
        ));
        g2d.fillOval(5, 5, 90, 90);
        
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));
        String num = String.valueOf(pokemonNumber);
        g2d.drawString(num, 50 - g2d.getFontMetrics().stringWidth(num)/2, 55);
        
        g2d.dispose();
        return img;
    }
    
    private void assignRandomTeams() {
        Collections.shuffle(allPokemonNames);
        
        
        for (int i = 0; i < 9; i++) {
            board.placePokemon(0, i, new Pokemon(allPokemonNames.get(i), getPokemonStats(allPokemonNames.get(i)), false));
            
            board.placePokemon(1, i, new Pokemon(allPokemonNames.get(i+9), getPokemonStats(allPokemonNames.get(i+9)), false));
            
            board.placePokemon(8, i, new Pokemon(allPokemonNames.get(i+18), getPokemonStats(allPokemonNames.get(i+18)), true));
            
            board.placePokemon(7, i, new Pokemon(allPokemonNames.get(i+27), getPokemonStats(allPokemonNames.get(i+27)), true));
        }
    }

    private int[] getPokemonStats(String name) {
        int index = Integer.parseInt(name); // 
        
        if (index <= 8) { 
            return new int[]{100, 20, 5}; 
        } else if (index <= 16) { 
            return new int[]{90, 25, 3};
        } else if (index <= 24) { 
            return new int[]{80, 30, 3};
        } else if (index <= 32) { 
            return new int[]{120, 35, 7};
        } else if (index <= 40) { 
            return new int[]{150, 40, 1};
        } else { 
            return new int[]{70, 15, 1};
        }
    }

    private void createGUI() {
        frame = new JFrame("Pokemon Chess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(BOARD_SIZE * TILE_SIZE + 16, BOARD_SIZE * TILE_SIZE + 39);
        frame.setResizable(false);

        chessPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawBoard(g);
            }
        };
        chessPanel.setPreferredSize(new Dimension(BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE));
        chessPanel.addMouseListener(new ChessMouseListener());

        frame.add(chessPanel);
        frame.setVisible(true);
    }

    private void drawBoard(Graphics g) {
        // 1. Collect attackable positions
        Set<Point> attackable = new HashSet<>();
        if (selectedRow != -1 && selectedCol != -1) {
            for (int row = 0; row < BOARD_SIZE; row++) {
                for (int col = 0; col < BOARD_SIZE; col++) {
                    if (board.isValidAttack(selectedRow, selectedCol, row, col)) {
                        attackable.add(new Point(col, row));
                    }
                }
            }
        }

        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Color tileColor = (row + col) % 2 == 0 ? new Color(240, 217, 181) : new Color(181, 136, 99);
                g.setColor(tileColor);
                g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                // 2. Highlight selected tile
                if (row == selectedRow && col == selectedCol) {
                    g.setColor(new Color(255, 255, 0, 100));
                    g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }

                // 3. Highlight attackable opponent in red
                if (attackable.contains(new Point(col, row))) {
                    g.setColor(new Color(255, 0, 0, 120)); // Red transparent
                    g.fillRect(col * TILE_SIZE, row * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }

                Pokemon pokemon = board.getPokemon(row, col);
                if (pokemon != null) {
                    BufferedImage img = pokemonImages.get(pokemon.getName());
                    if (img != null) {
                        Image scaled = img.getScaledInstance(TILE_SIZE-10, TILE_SIZE-10, Image.SCALE_SMOOTH);
                        g.drawImage(scaled, col * TILE_SIZE + 5, row * TILE_SIZE + 5, null);
                    }
                    
                    
                    g.setColor(pokemon.isPlayer2() ? Color.RED : Color.BLUE);
                    g.fillRect(col * TILE_SIZE + 5, row * TILE_SIZE + TILE_SIZE - 10, 
                              (int)((TILE_SIZE - 10) * ((double)pokemon.getHp() / pokemon.getMaxHp())), 5);
                }
            }
        }
        for (Point move : validMoves) {
            g.setColor(new Color(0, 255, 0, 120)); // Green transparent
            g.fillRect(move.x * TILE_SIZE, move.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        }
    }

    private class ChessMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int col = e.getX() / TILE_SIZE;
            int row = e.getY() / TILE_SIZE;
            

            if (selectedRow == -1) {
                Pokemon pokemon = board.getPokemon(row, col);
                if (pokemon != null && pokemon.isPlayer2() != player1Turn) {
                    selectedRow = row;
                    selectedCol = col;
                    validMoves = board.getValidMoves(row, col); // get valid moves
                    chessPanel.repaint();
                }
            } else {
                Pokemon selected = board.getPokemon(selectedRow, selectedCol);
                Pokemon target = board.getPokemon(row, col);
            
                if (target == null) {
                    if (board.isValidMove(selectedRow, selectedCol, row, col)) {
                        board.movePokemon(selectedRow, selectedCol, row, col);
                        player1Turn = !player1Turn;
                    }
                } else if (target.isPlayer2() != selected.isPlayer2()) {
                    if (board.isValidAttack(selectedRow, selectedCol, row, col)) {
                        board.attack(selectedRow, selectedCol, row, col);
                        player1Turn = !player1Turn;
                    }
                }
            
                selectedRow = -1;
                selectedCol = -1;
                validMoves.clear(); 
                chessPanel.repaint();
            }
            
        }
    }
}