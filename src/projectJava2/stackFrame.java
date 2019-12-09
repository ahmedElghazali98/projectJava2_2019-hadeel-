package projectJava2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class stackFrame extends JFrame {

	private JPanel contentPane;
	static JList list;
     static DefaultListModel listModel;
     static boolean isCreated =false;


	/**
	 * Create the frame.
	 */
	public stackFrame() {
		isCreated =true;
		setTitle("RPN Stack");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Top>>");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setBounds(32, 36, 46, 14);
		contentPane.add(lblNewLabel);
		

	    
	    list = new JList(addElementStack());
		list.setBounds(77, 20, 168, 192);
		getContentPane().add(list);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(88, 34, 177, 192);
		getContentPane().add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JLabel lblTheStack = new JLabel("The Stack");
		lblTheStack.setForeground(Color.BLUE);
		lblTheStack.setBounds(88, 11, 81, 14);
		contentPane.add(lblTheStack);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		closeBtn.setBounds(176, 240, 89, 23);
		contentPane.add(closeBtn);
		
		JButton upBtn = new JButton("UP");
  		//***********************************action btn up***************************************************

		upBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(RPNForm.rpnCalc.theStack.size()!=0) {
				double num=RPNForm.rpnCalc.theStack.pop();
				RPNForm.rpnCalc.theStack.add(num);
			       list.setModel(addElementStack());
				}else {
					 JOptionPane.showMessageDialog(null, "The stack is empty","Error" ,JOptionPane.WARNING_MESSAGE);

				}

			}
		});
		upBtn.setBounds(306, 32, 89, 23);
		contentPane.add(upBtn);
		
		JButton downBtn = new JButton("Down");
  		//***********************************action btn down***************************************************

		downBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(RPNForm.rpnCalc.theStack.size()!=0) {
					double num=RPNForm.rpnCalc.theStack.removeLast();
					RPNForm.rpnCalc.theStack.push(num);
				       list.setModel(addElementStack());
					}else {
						 JOptionPane.showMessageDialog(null, "The stack is empty","Error" ,JOptionPane.WARNING_MESSAGE);

					}
				
			}
		});
		downBtn.setBounds(306, 66, 89, 23);
		contentPane.add(downBtn);
		
		JButton copyBtn = new JButton("Copy");
  		//***********************************action btn copy***************************************************

		copyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
		        double num =(double) list.getSelectedValue() ;
		        
		        StringSelection stringSelection = new StringSelection(num+"");
		        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		        clipboard.setContents(stringSelection, null);
				}
		        catch(Exception e1) {
					 JOptionPane.showMessageDialog(null,"pless select value", "Error" ,JOptionPane.WARNING_MESSAGE);

		        }
		       

			}
		});
		copyBtn.setBounds(306, 102, 89, 23);
		contentPane.add(copyBtn);
		
		JButton editBtn = new JButton("Edit");
  		//***********************************action btn edit***************************************************

		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					double num_old = (double) list.getSelectedValue() ;
					
					String nun_new_Str=JOptionPane.showInputDialog("the value is selected" +num_old +
							"\n plaess enter new value");
					
					double num_new =Double.parseDouble(nun_new_Str);
					
				       Object[] a= RPNForm.rpnCalc.theStack.toArray();
				    	 a[ list.getSelectedIndex()]=num_new;
				    	  RPNForm.rpnCalc.theStack.clear();

				      for(int i=0;i<a.length;++i) {
				    	  RPNForm.rpnCalc.theStack.add((Double) a[i]);
				    	  
				      }
				       list.setModel(addElementStack());
				}
				catch(Exception e1) {
					 JOptionPane.showMessageDialog(null,"pless select value", "Error" ,JOptionPane.WARNING_MESSAGE);


			        
				}
		      
				
				
				
			}
		});
		editBtn.setBounds(306, 136, 89, 23);
		contentPane.add(editBtn);
		
		JButton deleteBtn = new JButton("Delete");
  		//***********************************action btn delete***************************************************

		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int index =list.getSelectedIndex();
					
				
					
				       Object[] a= RPNForm.rpnCalc.theStack.toArray();
				    	  RPNForm.rpnCalc.theStack.clear();

				      for(int i=0;i<a.length;++i) {
				    	  if(i!=index)
				    	  RPNForm.rpnCalc.theStack.add((Double) a[i]);
				    	  
				      }
				       list.setModel(addElementStack());
				}
				catch(Exception e1) {
					 JOptionPane.showMessageDialog(null,"pless select value", "Error" ,JOptionPane.WARNING_MESSAGE);


			        
				}
		      
		        
	
				
			}
		});
		deleteBtn.setBounds(306, 170, 89, 23);
		contentPane.add(deleteBtn);
		
		JButton btnNewButton_5 = new JButton("Clear");
  		//***********************************action btn clear***************************************************

		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				        RPNForm.rpnCalc.theStack.clear();
				       list.setModel(addElementStack());
				}
				catch(Exception e1) {
					 JOptionPane.showMessageDialog(null,"Error", "Error" ,JOptionPane.WARNING_MESSAGE);


			        
				}
				
			}
		});
		btnNewButton_5.setBounds(306, 203, 89, 23);
		contentPane.add(btnNewButton_5);
		
		
	    
	}
	
	public static  DefaultListModel addElementStack() {
        Iterator iterator = RPNForm.rpnCalc.theStack.iterator(); 
        
	    listModel = new DefaultListModel();
		  while(iterator.hasNext()) {
		  	    listModel.addElement(iterator.next());
			  
		  }
     
     return listModel;	}
}
