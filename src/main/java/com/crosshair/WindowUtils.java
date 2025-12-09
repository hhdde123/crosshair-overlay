package com.crosshair;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

import javax.swing.*;
import java.awt.*;

public class WindowUtils {

    public static void makeWindowTransparentToMouse(Window window) {
        WinDef.HWND hwnd = new WinDef.HWND(Native.getComponentPointer(window));

        int style = User32.INSTANCE.GetWindowLong(hwnd, WinUser.GWL_EXSTYLE);
        style |= WinUser.WS_EX_LAYERED | WinUser.WS_EX_TRANSPARENT;

        User32.INSTANCE.SetWindowLong(hwnd, WinUser.GWL_EXSTYLE, style);
    }
}
