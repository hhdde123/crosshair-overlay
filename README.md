# 🎯 Crosshair Overlay

一个为 FPS 游戏设计的 **可自定义准星覆盖层**（Overlay），支持 Apex Legends 等所有 Windows 游戏。  
无需修改游戏文件、无需外挂注入、不卡反作弊（Overlay 不与游戏交互）。

本项目使用 **Java 17 + JNA + Swing** 实现透明穿透窗口，并通过全局监听右键实现开镜隐藏准星。

---

## ✨ 功能特性

- ✔ **可自定义准星样式**
  - 颜色
  - 粗细
  - 长度
  - 中心间距

- ✔ **支持右键开镜隐藏准星**
  - 按住右键（ADS） → 准星自动隐藏  
  - 松开右键 → 准星自动恢复

- ✔ **透明点击穿透窗口**
  - Overlay 不会阻挡游戏鼠标操作

- ✔ **实时保存设置**
  - 所有设置自动写入 `config.properties`
  - 下次启动自动应用

- ✔ **全局退出快捷键：F10**
  - 随时按 F10 退出程序（无需任务管理器）

- ✔ **可独立运行的 Windows EXE**
  - 通过 `jpackage` 打包
  - 无需安装 Java

---

## 📦 下载方式

在 Release 页面下载最新的 EXE：

👉 https://github.com/hhdde123/crosshair-overlay/releases

---

## 📸 软件截图（如需我可以帮你制作示意图）

（可放运行截图 / 设置界面）

---

## 🛠 本地构建

### 1. 克隆项目

```bash
git clone https://github.com/hhdde123/crosshair-overlay.git
cd crosshair-overlay
