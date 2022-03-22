package view;

import javax.swing.*;
import java.awt.*;

public class ChambreView extends JPanel {
    public ChambreView() {
        FlowLayout layout = new FlowLayout();

        setLayout(layout);

        for (int i = 0; i < 10; ++i)
            add(new JButton("-> " + i));
    }
}
