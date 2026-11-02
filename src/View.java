import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel {
    int width = 360;
    int height = 640;

    private Logic logic;
    private JLabel scoreLabel; //buat skornya le

    public View(Logic logic){ //implementasi kelas logic ke tampilan
        this.logic = logic;
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.orange);
        setLayout(null);

        scoreLabel = new JLabel("Skor: 0"); //skor awal nol
        //styling teks
        scoreLabel.setBounds(20, 20, 150, 30);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 22));
        scoreLabel.setForeground(Color.white);
        add(scoreLabel);

        setFocusable(true);
        addKeyListener(logic);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        scoreLabel.setText("Skor: " + logic.getScore());

        //generate burung manuk
        Player player = logic.getPlayer();
        if(player != null){
            g.drawImage(player.getImg(), player.getPostX(), player.getPosY(), player.getWidth(), player.getHeight(), null);
        }

        //generate pipa
        ArrayList<Pipe> pipes = logic.getPipes();
        if(pipes != null){
            for(int i = 0; i < pipes.size(); i++){
                Pipe pipe = pipes.get(i); //tampilkan sebanyak pipa generate (selagi game berjalan)
                g.drawImage(pipe.getImg(), pipe.getPostX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
            }
        }

        //Tampilan petunjuk awal
        if (!logic.isGameStarted() && !logic.isGameOver()) {
            //set kalimat dan styling
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            FontMetrics fm = g.getFontMetrics();
            String startMsg = "Tekan SPACE untuk Mulai";
            g.drawString(startMsg, (width - fm.stringWidth(startMsg)) / 2, height / 2);
        }

        // Tampilan Game Over
        if(logic.isGameOver()){
            g.setColor(Color.black);
            g.setFont(new Font("Arial", Font.BOLD, 36));
            FontMetrics fm = g.getFontMetrics();
            String msg = "GAME OVER";
            g.drawString(msg, (width - fm.stringWidth(msg)) / 2, height / 2 - 50);

            g.setFont(new Font("Arial", Font.BOLD, 16));
            FontMetrics fm2 = g.getFontMetrics();
            String restartMsg = "Tekan 'R' untuk Restart";
            g.drawString(restartMsg, (width - fm2.stringWidth(restartMsg)) / 2, height / 2 + 10);

            String menuMsg = "Tekan 'M' untuk Main Menu";
            FontMetrics fm3 = g.getFontMetrics();
            g.drawString(menuMsg, (width - fm3.stringWidth(menuMsg)) / 2, height / 2 + 35);
        }
    }
}