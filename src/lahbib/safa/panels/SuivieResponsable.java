package lahbib.safa.panels;

import javax.swing.*;

public class SuivieResponsable extends JPanel {
    JTabbedPane tabbedPane = new JTabbedPane();

    public SuivieResponsable(){
        SuivieEmployeePanel suivieEmployee = new SuivieEmployeePanel();
        SuivieEnfantPanel suivieEnfantPanel = new SuivieEnfantPanel();
        tabbedPane.addTab("Suivie Employee", suivieEmployee);
        tabbedPane.addTab("Suivie Enfant", suivieEnfantPanel);
        this.add(tabbedPane);
    }

}
