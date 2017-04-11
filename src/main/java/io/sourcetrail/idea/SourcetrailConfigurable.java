package io.sourcetrail.idea;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.text.ParseException;

public class SourcetrailConfigurable implements Configurable {
/*    private JTextField myIp = new JTextField();
    private JSpinner mySourcetrailPort = new JSpinner();
    private JSpinner myEditorPort = new JSpinner();*/
    private JTextField myIp;
    private JSpinner mySourcetrailPort;
    private JSpinner myEditorPort;
    private JPanel myPanel;

    public void disposeUIResources() {

    }

    public void apply() throws ConfigurationException {
        SourcetrailOptions sourcetrailOptions = SourcetrailOptions.getInstance();
        sourcetrailOptions.setIp(myIp.getText());
        try {
            mySourcetrailPort.commitEdit();
        }
        catch (ParseException pe) {
            pe.printStackTrace();
        }
        try {
            myEditorPort.commitEdit();
        }
        catch (ParseException pe) {
            pe.printStackTrace();
        }
        sourcetrailOptions.setSourcetrailPort((Integer)mySourcetrailPort.getValue());
        sourcetrailOptions.setEditorPort((Integer)myEditorPort.getValue());
    }

    @Nullable
    public JComponent createComponent() {
/*        myPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent("IP: ", myIp)
                .addLabeledComponent("Sourcetrail Port: ", mySourcetrailPort)
                .addLabeledComponentFillVertically("Editor Port: ", myEditorPort)
                .getPanel();*/

        return myPanel;

    }

    public boolean isModified() {
        SourcetrailOptions sourcetrailOptions = SourcetrailOptions.getInstance();
        return true;
    }

    public void reset() {
        SourcetrailOptions sourcetrailOptions = SourcetrailOptions.getInstance();
        myIp.setText(sourcetrailOptions.getIp());
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
