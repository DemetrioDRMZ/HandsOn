package examples.handsOn.handsOn4;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class AgentGui extends JFrame {
    private AgentClips myAgent;

    private JTextField factsField, rulesField;

    AgentGui(AgentClips a){
        super(a.getLocalName());

        myAgent = a;

        JPanel p = new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        p.add(new JLabel("(assert "));
        factsField = new JTextField(50);
        p.add(factsField);
        p.add(new JLabel(")"));
        p.add(new JLabel("(defrule "));
        rulesField = new JTextField(50);
        p.add(rulesField);
        p.add(new JLabel(")"));

        getContentPane().add(p, BorderLayout.CENTER);

        JButton addButton = new JButton("Add");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
                try {
                    String facts = factsField.getText().trim();
                    String rules = rulesField.getText().trim();
                    myAgent.CargarReglas(facts, rules);
                    factsField.setText("");
                    rulesField.setText("");
                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(AgentGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
                }
			}
		} );
		p = new JPanel();
		p.add(addButton);

        JButton runButton = new JButton("Run");
		runButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
                    myAgent.askTime = true;
			}
		} );

		p.add(runButton);
		getContentPane().add(p, BorderLayout.SOUTH);

        //Acabar con el agente cuando se cierra el GUI
        addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.detener = true;
			}
		} );
		
		setResizable(false);
    }

    public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}
    
}
