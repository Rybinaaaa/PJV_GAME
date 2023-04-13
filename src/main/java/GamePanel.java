import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //    SCREEEN SETTINGS
    final int originalTileSize = 16;
    //    16 x 16 tile (клетка)
    final int scale = 3;

    final int tileSize = originalTileSize * scale;

    final int maxScreenColumn = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColumn; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

//    set player's default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
//        ??
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

//        double drawInterval = Math.pow(10, 9) / FPS;
//        double nextDrawTime = System.nanoTime() + drawInterval;
////        game loop
//        while (gameThread != null) {
////            System.out.println("Game running");
//
//            long currentTime = System.nanoTime();
//
////            1 update (about position of character)
//            update();
////            2 drow
//            repaint();
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime /= Math.pow(10, 6);
////                для тредов надо переводить в МИЛЛИсекунды
//
//                if (remainingTime < 0) {
//                    remainingTime = 0;
//                }
//
//                Thread.sleep((long)remainingTime);
//
//                nextDrawTime += drawInterval;
//
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }

        double drawInterval = Math.pow(10, 9) / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
//                repaint вызывает автоматически paintComponent
                delta--;
                drawCount++;
            }

            if (timer >= Math.pow(10,9)) {
                System.out.println(drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if(keyH.upPressed) {
            playerY -= playerSpeed;
        } else if (keyH.downPressed) {
            playerY += playerSpeed;
        } else if (keyH.leftPressed) {
            playerX -= playerSpeed;
        } else if (keyH.rightPressed) {
            playerX += playerSpeed;
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
//        g2.fillRect(x, y, width, height);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
