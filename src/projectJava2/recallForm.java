package projectJava2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class recallForm extends JFrame {

	private JPanel contentPane;
	static JList list;
    static DefaultListModel listModel;
    private JButton recallBtn;
    private JButton closeBtn;
    static boolean isCreated =false;

	/**
	 * Create the frame.
	 */
	public recallForm() {
	     isCreated =true;

		setTitle("RPN recall");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		  listModel = new DefaultListModel();
		    
		    list = new JList(storeForm.addElementList());
		  		list.setBounds(77, 20, 168, 192);
		  		getContentPane().add(list);
		  		
		  		JScrollPane scrollPane = new JScrollPane(list);
		  		scrollPane.setBounds(85, 22, 254, 219);
		  		getContentPane().add(scrollPane);
		  		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		  		
		  		recallBtn = new JButton("Recall ");
		  		//***********************************action btn recall***************************************************

		  		recallBtn.addActionListener(new ActionListener() {
		  			public void actionPerformed(ActionEvent e) {
		  				
		  				try {
			  				int index= list.getSelectedIndex();
			  				
					    	 
					    	double vlaue= RPNForm.rpnCalc.register[index].getValue();
					    	 
					    	 RPNForm.rpnCalc.theStack.push(vlaue);
					    	 
					    	 if(stackFrame.isCreated) {
				    			 stackFrame.list.setModel(stackFrame.addElementStack());
				    			 }
				    		 JOptionPane.showMessageDialog(null, "done successfully");

					    	 
			  				}catch(Exception e1) {
								 JOptionPane.showMessageDialog(null,"pless select value", "Error" ,JOptionPane.WARNING_MESSAGE);

							        
							}
		  				
		  				
		  				
		  				
		  			}
		  		});
		  		recallBtn.setBounds(85, 267, 89, 23);
		  		contentPane.add(recallBtn);
		  		
		  		
		  		closeBtn = new JButton("close");
		  		//***********************************action btn close***************************************************
		  		closeBtn.addActionListener(new ActionListener() {
		  			public void actionPerformed(ActionEvent e) {
		  				
						setVisible(false);
		  			}
		  		});
		  		closeBtn.setBounds(250, 267, 89, 23);
		  		contentPane.add(closeBtn);
	}

}
