import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args){
        //ini ngeset frame/window nya
        JFrame frame = new JFrame("FlappyBird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        //Panel Main Menu
        JPanel menuPanel = new JPanel();

        //background keren sumpah
        menuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image bg = new ImageIcon(App.class.getResource("assets/icon.jpg")).getImage();
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        };

        menuPanel.setBackground(Color.orange);
        menuPanel.setLayout(new GridBagLayout());

        //gambar keren dah janji
        ImageIcon Iconkren = new ImageIcon(App.class.getResource("assets/icon.jpg"));
        ImageIcon icon = Iconkren;
        Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel iconLabel = new JLabel(new ImageIcon(scaledImage));

        //buat tombol start sama exit
        JButton startButton = new JButton("Mulai Permainan");
        JButton exitButton = new JButton("Keluar");

        //tampilan tombolnya
        Dimension btnSize = new Dimension(180, 45);
        startButton.setPreferredSize(btnSize);
        exitButton.setPreferredSize(btnSize);
        startButton.setFont(new Font("Arial", Font.BOLD, 14));
        exitButton.setFont(new Font("Arial", Font.BOLD, 14));

        //kotak buttonnya
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        menuPanel.add(startButton, gbc);

        gbc.gridy = 1;
        menuPanel.add(exitButton, gbc);

        // Inisialisasi Game
        Logic logik = new Logic();
        View tampilan = new View(logik);
        logik.setView(tampilan);

        //Callback untuk kembali ke Main Menu
        logik.setOnReturnToMenu(() -> cardLayout.show(mainPanel, "MENU"));

        mainPanel.add(menuPanel, "MENU");
        mainPanel.add(tampilan, "GAME");

        //Aksi tombol Mulai
        startButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "GAME");
            tampilan.requestFocusInWindow();
            logik.startGame();
        });

        //Aksi tombol Keluar
        exitButton.addActionListener(e -> System.exit(0));

        //buat ngerun file
        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
}