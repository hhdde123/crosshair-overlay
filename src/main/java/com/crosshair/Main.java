package com.crosshair;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        CrosshairOverlay overlay = new CrosshairOverlay();
        overlay.setVisible(true);

        WindowUtils.makeWindowTransparentToMouse(overlay);

        // 启动右键监控
        Thread checker = new Thread(new MouseChecker(overlay), "MouseChecker");
        checker.setDaemon(true);  // 后台线程，主程序关了它也自动结束
        checker.start();

        // 启动 UI 设置窗口 (按 F8 打开)
        SwingUtilities.invokeLater(() -> {
            JButton openUI = new JButton("打开准星设置 (F8)");
            JFrame frame = new JFrame("菜单");
            frame.setSize(200, 100);
            frame.add(openUI);
            frame.setVisible(true);

            openUI.addActionListener(e -> new SettingsWindow(overlay));
        });

        System.out.println("Crosshair Overlay Running...");
    }
}