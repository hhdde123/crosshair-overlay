package com.crosshair;

import javax.swing.*;
import java.awt.*;

public class CrosshairOverlay extends JWindow {

    public Color crosshairColor = Color.RED;
    public int crosshairThickness = 2;
    public int crosshairLength = 20;
    public String crosshairType = "cross"; // cross / dot / circle

    private volatile boolean visibleCrosshair = true;


    public CrosshairOverlay() {

        // 加载配置
        applyConfig(
                Color.decode(ConfigManager.get("color", "#FF0000")),
                Integer.parseInt(ConfigManager.get("thickness", "2")),
                Integer.parseInt(ConfigManager.get("length", "20")),
                ConfigManager.get("type", "cross")
        );

        setAlwaysOnTop(true);
        setBackground(new Color(0, 0, 0, 0));
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
    }


    public void setCrosshairVisible(boolean visible) {
        this.visibleCrosshair = visible;
        SwingUtilities.invokeLater(this::repaint);
    }

    public void applyConfig(Color color, int thickness, int length, String type) {
        this.crosshairColor = color;
        this.crosshairThickness = thickness;
        this.crosshairLength = length;
        this.crosshairType = type;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        // 清屏
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setComposite(AlphaComposite.SrcOver);

        if (!visibleCrosshair) {
            g2.dispose();
            return;
        }

        g2.setColor(crosshairColor);
        g2.setStroke(new BasicStroke(crosshairThickness));

        int cx = getWidth() / 2;
        int cy = getHeight() / 2;

        switch (crosshairType) {
            case "dot":
                g2.fillOval(cx - 3, cy - 3, 6, 6);
                break;

            case "circle":
                g2.drawOval(cx - crosshairLength, cy - crosshairLength,
                        crosshairLength * 2, crosshairLength * 2);
                break;

            default: // cross
                g2.drawLine(cx - crosshairLength, cy, cx + crosshairLength, cy);
                g2.drawLine(cx, cy - crosshairLength, cx, cy + crosshairLength);
                break;
        }

        g2.dispose();
    }
}
