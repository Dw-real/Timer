package Graphic;

import java.awt.*;
import java.io.*;

public class FontManager {
    private static Font customFont;

    public static Font getCustomFont(float size) {
        if (customFont == null) {
            try {
                File fontFile = new File("C:\\Timer\\Timer\\timer\\src\\resources\\NanumBarunpenR.ttf");
                customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(size);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(customFont);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return customFont;
    }
}
