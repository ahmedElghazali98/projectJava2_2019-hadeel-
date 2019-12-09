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
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class storeForm extends JFrame {

	private JPanel contentPane;
	static JList list;
    static DefaultListModel listModel;
    private JButton addBtn;
    private JButton subBtn;
    private JButton button;
    private JButton divBtn;
    private JButton btnStore;
    private JButton editLabelBtn;
    private JButton clearAllBtn;
    private JButton closeBtn;
    public static boolean isCreated=false;

	
	/**
	 * Create the frame.
	 */
	public storeForm() {
		 isCreated=true;
		setTitle("RPN Store");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 436, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    
	    list = new JList(addElementList());
	  		list.setBounds(77, 20, 168, 192);
	  		getContentPane().add(list);
	  		
	  		JScrollPane scrollPane = new JScrollPane(list);
	  		scrollPane.setBounds(85, 22, 254, 219);
	  		getContentPane().add(scrollPane);
	  		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	  		
	  		addBtn = new JButton("+");
	  		//***********************************action btn +***************************************************

	  		addBtn.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent e) {
	  				 operation('+');

	  			}
	  		});
	  		addBtn.setBounds(114, 252, 41, 23);
	  		contentPane.add(addBtn);
	  		
	  		subBtn = new JButton("--");
	  		//***********************************action btn --***************************************************

	  		subBtn.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent e) {
	  				 operation('-');

	  			}
	  		});
	  		subBtn.setBounds(165, 252, 47, 23);
	  		contentPane.add(subBtn);
	  		
	  		button = new JButton("*");
	  		//***********************************action btn (*) ***************************************************

	  		button.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent e) {
	  				 operation('*');
	  			}

				
	  		});
	  		button.setBounds(219, 252, 41, 23);
	  		contentPane.add(button);
	  		
	  		divBtn = new JButton("/");
	  		//***********************************action btn / ***************************************************

	  		divBtn.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent e) {
	  				
	  				 operation('/');

	  				
	  				
	  				
	  				
	  			}
	  		});
	  		divBtn.setBounds(273, 252, 37, 23);
	  		contentPane.add(divBtn);
	  		
	  		btnStore = new JButton("Store");
	  		//***********************************action btn store***************************************************

	  		btnStore.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent e) {
	  				
	  				try {
	  			    if(RPNForm.rpnCalc.theStack.size()==0) {
	  			       		 JOptionPane.showMessageDialog(null, "sorry, the stack is empty");

	  			    }else {
	  				int index= list.getSelectedIndex();
	  				
	  				
			    	 double top= RPNForm.rpnCalc.theStack.getFirst();
			    	 
			    	 RPNForm.rpnCalc.register[index].setValue(top);
			    	 list.setModel(addElementList());
			    	 if(recallForm.isCreated) {
			    		 recallForm.list.setModel(addElementList());
			    	 }
		    		 JOptionPane.showMessageDialog(null, "done successfully");

	  			    		}
	  				}catch(Exception e1) {
						 JOptionPane.showMessageDialog(null,"pless select value", "Error" ,JOptionPane.WARNING_MESSAGE);


					        
					}
			      
			    	 

	  				
	  			}
	  		});
	  		btnStore.setBounds(12, 299, 89, 23);
	  		contentPane.add(btnStore);
	  		
	  		editLabelBtn = new JButton("Edit Label");
	  		//***********************************action btn edit label***************************************************

	  		editLabelBtn.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent e) {
	  				
	  				
	  				try {
		  				int index= list.getSelectedIndex();
		  				
				    	 
				    	 String label=JOptionPane.showInputDialog("The new label to register { " +  RPNForm.rpnCalc.register[index].getName() +" }" );
				    	 RPNForm.rpnCalc.register[index].setLable(label);
				    	 list.setModel(addElementList());
				    	 if(recallForm.isCreated) {
				    		 recallForm.list.setModel(addElementList());
				    	 }
				    	 
		  				}catch(Exception e1) {
							 JOptionPane.showMessageDialog(null,"pless select value", "Error" ,JOptionPane.WARNING_MESSAGE);

						        
						}
	  				
	  				
	  			}
	  		});
	  		editLabelBtn.setBounds(114, 299, 89, 23);
	  		contentPane.add(editLabelBtn);
	  		
	  		clearAllBtn = new JButton("clear All");
	  		//***********************************action btn clear all***************************************************

	  		clearAllBtn.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent e) {
	  				
	  				
  			     for(int i=0;i<RPNForm.rpnCalc.register.length;++i){
  			    	RPNForm.rpnCalc.register[i]= new Register(0.0,"no lable",""+(char)('A'+i));
  		           }
  			     
	  	    	 list.setModel(addElementList());
	  	    	 if(recallForm.isCreated) {
	  	    		 recallForm.list.setModel(addElementList());
	  	    	 }
	    		 JOptionPane.showMessageDialog(null, "done successfully");

	  				
	  			}
	  		});
	  		clearAllBtn.setBounds(213, 299, 89, 23);
	  		contentPane.add(clearAllBtn);
	  		
	  		closeBtn = new JButton("close");
	  		//***********************************action btn close***************************************************

	  		closeBtn.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent e) {
					setVisible(false);
	  			}
	  		});
	  		closeBtn.setBounds(321, 299, 89, 23);
	  		contentPane.add(closeBtn);
	  		
	  		
	  		

	    
	}
	
	
	private void operation(char c) {

		try {
			    if(RPNForm.rpnCalc.theStack.size()==0) {
			       		 JOptionPane.showMessageDialog(null, "sorry, the stack is empty");

			    }else {
				int index= list.getSelectedIndex();
				
				
	    	 double top= RPNForm.rpnCalc.theStack.getFirst();
	    	double oldValue= RPNForm.rpnCalc.register[index].getValue();
	    	double newValue=0;
	    	switch(c) {
	    	case '+':newValue =top+oldValue;break;
	    	case '-':newValue =top-oldValue;break;
	    	case '*':newValue =top*oldValue;break;
	    	case '/':newValue =top/oldValue;break;

	    	}
	    	  
	    	 RPNForm.rpnCalc.register[index].setValue(newValue);
	    	 list.setModel(addElementList());
	    	 if(recallForm.isCreated) {
	    		 recallForm.list.setModel(addElementList());
	    	 }
    		 JOptionPane.showMessageDialog(null, "done successfully");

			    		}
				}catch(Exception e1) {
				 JOptionPane.showMessageDialog(null,"pless select value", "Error" ,JOptionPane.WARNING_MESSAGE);


			        
			}
		
		
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static  DefaultListModel addElementList() {
        Iterator iterator = RPNForm.rpnCalc.theStack.iterator(); 
	    listModel = new DefaultListModel();
		  
		  for(int i=0 ;i<RPNForm.rpnCalc.register.length;++i) {
		  	    listModel.addElement(RPNForm.rpnCalc.register[i]);

		  }
		
     
     return listModel;	}

}
