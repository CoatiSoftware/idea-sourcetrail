package io.coati.idea;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.text.ParseException;

public class CoatiConfigurable implements Configurable {
/*    private JTextField myIp = new JTextField();
    private JSpinner myCoatiPort = new JSpinner();
    private JSpinner myEditorPort = new JSpinner();*/
    private JTextField myIp;
    private JSpinner myCoatiPort;
    private JSpinner myEditorPort;
    private JPanel myPanel;

    public void disposeUIResources() {

    }

    public void apply() throws ConfigurationException {
        CoatiOptions coatiOptions = CoatiOptions.getInstance();
        coatiOptions.setIp(myIp.getText());
        try {
            myCoatiPort.commitEdit();
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
        coatiOptions.setCoatiPort((Integer)myCoatiPort.getValue());
        coatiOptions.setEditorPort((Integer)myEditorPort.getValue());
    }

    @Nullable
    public JComponent createComponent() {
/*        myPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent("IP: ", myIp)
                .addLabeledComponent("Coati Port: ", myCoatiPort)
                .addLabeledComponentFillVertically("Editor Port: ", myEditorPort)
                .getPanel();*/

        return myPanel;

    }

    public boolean isModified() {
        CoatiOptions coatiOptions = CoatiOptions.getInstance();
        return true;
    }

    public void reset() {
        CoatiOptions coatiOptions = CoatiOptions.getInstance();
        myIp.setText(coatiOptions.getIp());
        myCoatiPort.setValue(coatiOptions.getCoatiPort());
        myEditorPort.setValue(coatiOptions.getEditorPort());
    }

    @Nls
    public String getDisplayName() {
        return "Coati CoatiConfigurable";
    }

    @Nullable
    public String getHelpTopic() {
        return "Configuration for the Coati plugin";
    }
}
