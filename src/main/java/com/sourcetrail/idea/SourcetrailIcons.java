package io.sourcetrail.idea;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public class SourcetrailIcons {

    private static Icon load(String path) {
        return IconLoader.getIcon(path, SourcetrailIcons.class);
    }

    public static final Icon Sourcetrail = load("/sourcetrail.png"); // 16x16
    Icon SOURCETRAIL = IconLoader.getIcon("/sourcetrail.png");
}
