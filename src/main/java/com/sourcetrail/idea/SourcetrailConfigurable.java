package io.sourcetrail.idea;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.text.ParseException;

public class SourcetrailConfigurable implements Configurable {

    private JSpinner mySourcetrailPort;
    private JSpinner myEditorPort;
    private JPanel myPanel;

    public void disposeUIResources() {
    }

    public void apply() throws ConfigurationException {
        SourcetrailOptions sourcetrailOptions = SourcetrailOptions.getInstance();
        try {
            mySourcetrailPort.commitEdit();
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        try {
            myEditorPort.commitEdit();
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
        sourcetrailOptions.setSourcetrailPort((Integer)mySourcetrailPort.getValue());
        sourcetrailOptions.setEditorPort((Integer)myEditorPort.getValue());
    }

    @Nullable
    public JComponent createComponent() {
        return myPanel;
    }

    public boolean isModified() {
        SourcetrailOptions sourcetrailOptions = SourcetrailOptions.getInstance();
        return true;
    }

    public void reset() {
        SourcetrailOptions sourcetrailOptions = SourcetrailOptions.getInstance();
        mySourcetrailPort.setValue(sourcetrailOptions.getSourcetrailPort());
        myEditorPort.setValue(sourcetrailOptions.getEditorPort());
    }

    @Nls
    public String getDisplayName() {
        return "Sourcetrail SourcetrailConfigurable";
    }

    @Nullable
    public String getHelpTopic() {
        return "Configuration for the Sourcetrail plugin";
    }
}
