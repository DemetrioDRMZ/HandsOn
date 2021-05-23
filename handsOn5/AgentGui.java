package examples.handsOn.handsOn5;

import jade.core.AID;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.regex.*;

class AgentGui extends JFrame {
    private AgentClips myAgent;

    private String rutaHechos;
    private String rutaReglas;

    JFileChooser vB = new JFileChooser();
    FileNameExtensionFilter fi = new FileNameExtensionFilter("*.clp", "clp");

    private JTextField factsField, rulesField;

    AgentGui(AgentClips a){
        super(a.getLocalName());

        myAgent = a;

        vB.setFileFilter(fi);

        JPanel p = new JPanel();
        p.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        p.add(new JLabel("Archivo de Hechos: "));
        factsField = new JTextField(30);
        factsField.setEnabled(false);
        p.add(factsField);

        JButton addFactButton = new JButton("Add");
        addFactButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                int se = vB.showOpenDialog(vB);
                if(se == 0){
                    if(checkFileName(vB.getSelectedFile().getName(), 2)){
                        factsField.setText("" + vB.getSelectedFile().getName());
                        rutaHechos = vB.getSelectedFile().getParent();
                    }
                    else{
                        JOptionPane.showMessageDialog(AgentGui.this, "Favor de no escoger archivo de reglas", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } );
        p.add(addFactButton);

        p.add(new JLabel("Archivo de Reglas: "));
        rulesField = new JTextField(30);
        rulesField.setEnabled(false);
        p.add(rulesField);

        JButton addRulesButton = new JButton("Add");
        addRulesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                int se = vB.showOpenDialog(vB);
                if(se == 0){
                    
                    if(checkFileName(vB.getSelectedFile().getName(), 1)){
                        rulesField.setText("" + vB.getSelectedFile().getName());
                        rutaReglas = vB.getSelectedFile().getParent();
                    }
                    else{
                        JOptionPane.showMessageDialog(AgentGui.this, "Favor de escoger archivo de reglas", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } );
        p.add(addRulesButton);

        getContentPane().add(p, BorderLayout.CENTER);

        JButton addButton = new JButton("Add");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
                
                String nombreHechos = factsField.getText().trim();
                String nombreReglas = rulesField.getText().trim();
                
                if(rutaHechos.equals(rutaReglas)){
                    try {
                        myAgent.cargarArchivos(rutaHechos + "\\" + nombreHechos, rutaReglas + "\\" + nombreReglas);
                        factsField.setText("");
                        rulesField.setText("");
                    }
                    catch (Exception e) {
                        JOptionPane.showMessageDialog(AgentGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
                    }
                }
                else{
                    JOptionPane.showMessageDialog(AgentGui.this, "Favor de escoger archivos de la misma carpeta", "Error", JOptionPane.ERROR_MESSAGE);
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

    private boolean checkFileName(String fileName, int i){
        if(i == 1){
            Pattern p = Pattern.compile("rules.clp");
            Matcher m = p.matcher(fileName);
        
            return m.find();
        }
        else{
            Pattern p = Pattern.compile("rules.clp");
            Matcher m = p.matcher(fileName);
        
            return !m.find();
        }
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
