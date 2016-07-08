package io.coati.idea;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public class CoatiIcons {
    private static Icon load(String path) {
        return IconLoader.getIcon(path, CoatiIcons.class);
    }

    public static final Icon Coati = load("/coati.png"); // 16x16
    Icon COATI = IconLoader.getIcon("/coati.png");
//    public static final Icon GradleImport = load("/icons/gradleImport.png"); // 16x16
//    public static final Icon GradleNavigate = load("/icons/gradleNavigate.png"); // 16x16
//    public static final Icon GradlePlugin = load("/icons/gradlePlugin.png"); // 16x16
//    public static final Icon GradleSync = load("/icons/gradleSync.png"); // 16x16
//    public static final Icon OfflineMode = load("/icons/offlineMode.png"); // 16x16
}