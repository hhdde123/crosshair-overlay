package com.crosshair;

import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JFrame {

    public SettingsWindow(CrosshairOverlay overlay) {
        setTitle("准星设置");
        setSize(300, 260);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1));

        // --- 颜色选择 ---
        JButton colorBtn = new JButton("选择颜色");
        colorBtn.addActionListener(e -> {
            Color c = JColorChooser.showDialog(null, "选择准星颜色", overlay.crosshairColor);
            if (c != null) {
                overlay.crosshairColor = c;
                overlay.repaint();

                // 保存颜色到配置文件
                String hex = "#" + Integer.toHexString(c.getRGB()).substring(2).toUpperCase();
                ConfigManager.set("color", hex);
            }
        });
        add(colorBtn);

        // --- 粗细滑块 ---
        JSlider thicknessSlider = new JSlider(1, 10, overlay.crosshairThickness);
        thicknessSlider.setBorder(BorderFactory.createTitledBorder("粗细"));
        thicknessSlider.addChangeListener(e -> {
            int value = thicknessSlider.getValue();
            overlay.crosshairThickness = value;
            overlay.repaint();

            // 保存粗细
            ConfigManager.set("thickness", String.valueOf(value));
        });
        add(thicknessSlider);

        // --- 长度滑块 ---
        JSlider lengthSlider = new JSlider(5, 50, overlay.crosshairLength);
        lengthSlider.setBorder(BorderFactory.createTitledBorder("长度"));
        lengthSlider.addChangeListener(e -> {
            int value = lengthSlider.getValue();
            overlay.crosshairLength = value;
            overlay.repaint();

            // 保存长度
            ConfigManager.set("length", String.valueOf(value));
        });
        add(lengthSlider);

        // --- 类型选择 ---
        String[] types = {"十字", "中点", "圆"};
        JComboBox<String> typeBox = new JComboBox<>(types);

        // 初始化选项（根据 ConfigManager 中的值）
        switch (overlay.crosshairType) {
            case "dot" -> typeBox.setSelectedItem("中点");
            case "circle" -> typeBox.setSelectedItem("圆");
            default -> typeBox.setSelectedItem("十字");
        }

        typeBox.addActionListener(e -> {
            String type = switch (typeBox.getSelectedItem().toString()) {
                case "中点" -> "dot";
                case "圆" -> "circle";
                default -> "cross";
            };

            overlay.crosshairType = type;
            overlay.repaint();

            // 保存类型
            ConfigManager.set("type", type);
        });

        add(typeBox);

        setVisible(true);
    }
}
