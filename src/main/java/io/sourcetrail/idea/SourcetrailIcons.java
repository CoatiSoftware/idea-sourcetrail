package io.sourcetrail.idea;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public class SourcetrailIcons {
    private static Icon load(String path) {
        return IconLoader.getIcon(path, SourcetrailIcons.class);
    }

    public static final Icon Sourcetrail = load("/sourcetrail.png"); // 16x16
    Icon SOURCETRAIL = IconLoader.getIcon("/sourcetrail.png");
//    public static final Icon GradleImport = load("/icons/gradleImport.png"); // 16x16
//    public static final Icon GradleNavigate = load("/icons/gradleNavigate.png"); // 16x16
//    public static final Icon GradlePlugin = load("/icons/gradlePlugin.png"); // 16x16
//    public static final Icon GradleSync = load("/icons/gradleSync.png"); // 16x16
//    public static final Icon OfflineMode = load("/icons/offlineMode.png"); // 16x16
}