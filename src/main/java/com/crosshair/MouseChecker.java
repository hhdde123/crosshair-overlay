package com.crosshair;

import com.sun.jna.platform.win32.User32;

public class MouseChecker implements Runnable {

    private final CrosshairOverlay overlay;
    private boolean lastRightState = false;
    private boolean lastExitState = false; // 记录上一次 F10 状态

    public MouseChecker(CrosshairOverlay overlay) {
        this.overlay = overlay;
    }

    @Override
    public void run() {
        while (true) {
            // 1️⃣ 检查右键（0x02）
            short state = User32.INSTANCE.GetAsyncKeyState(0x02);
            boolean pressed = (state & 0x8000) != 0;

            if (pressed != lastRightState) {
                lastRightState = pressed;
                overlay.setCrosshairVisible(!pressed);
            }

            // 2️⃣ 检查 F10 退出（0x79）
            short exitState = User32.INSTANCE.GetAsyncKeyState(0x79);  // VK_F10 = 0x79
            boolean exitPressed = (exitState & 0x8000) != 0;

            // 只在“从没按 → 按下”的瞬间触发一次
            if (exitPressed && !lastExitState) {
                // 可以先做一些清理，有需要的话再加
                System.out.println("F10 pressed, exit CrosshairOverlay.");
                System.exit(0);
            }
            lastExitState = exitPressed;

            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {}
        }
    }
}
