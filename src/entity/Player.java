package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/B_witch_run_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/B_witch_run_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/B_witch_run_3.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/B_witch_run_4.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/B_witch_run_5.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/B_witch_run_6.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/B_witch_run_7.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/B_witch_run_8.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyH.upPressed) {
            direction = "up";
            y -= speed;
        } else if (keyH.downPressed) {
            direction = "down";
            y += speed;
        } else if (keyH.leftPressed) {
            direction = "left";
            x -= speed;
        } else if (keyH.rightPressed) {
            direction = "right";
            x += speed;
        }

        spriteCounter ++;
        if (spriteCounter > 10) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) spriteNumber = 1;
            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2) {

//        g2.setColor(Color.white);
////        g2.fillRect(x, y, width, height);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                } else {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                } else {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                } else {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                } else {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
