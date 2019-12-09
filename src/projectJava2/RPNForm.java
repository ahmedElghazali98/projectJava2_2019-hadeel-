package projectJava2;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class RPNForm extends JFrame implements ActionListener {
  
  private Container contentPane;
  private JPanel displayPanel;
  private JLabel RPNLabel;
  public static JTextField displayTextField;
  private JPanel buttonPanel;
  private String inString = "";
  
  private JButton[][] buttonGrid;
  public static RPNCalculator calc;
  public static RPNCalculator rpnCalc;
  public boolean isHelp=false;
 public double multiplicative_inverse=0;
 
  
  public static void main(String[] args) {
    RPNForm gui = new RPNForm();
    gui.setVisible(true);
  }

  
  
  
  public RPNForm() {
	  rpnCalc=new RPNCalculator();
    this.calc = new RPNCalculator();
  
    setSize(660, 330);
    setDefaultCloseOperation(3);
    setTitle("  RPN Calculator");
    this.contentPane = getContentPane();
    this.contentPane.setLayout(new BorderLayout());
    
    this.displayPanel = new JPanel();
    this.displayPanel.setLayout(new BoxLayout(this.displayPanel, 0));
    this.displayPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(204, 153, 255)));
    
    this.RPNLabel = new JLabel(" RPN ");
    this.RPNLabel.setBackground(new Color(51, 0, 102));
    this.RPNLabel.setFont(new Font("Courier New", 1, 36));
    this.RPNLabel.setForeground(new Color(102, 51, 102));
    this.displayPanel.add(this.RPNLabel);
    
    this.displayTextField = new JTextField("");
    this.displayTextField.setFont(new Font("Courier New", 1, 24));
    this.displayTextField.setHorizontalAlignment(4);
    this.displayTextField.setActionCommand("Enter");
    this.displayTextField.addActionListener(this);
    this.displayPanel.add(this.displayTextField);
    this.contentPane.add(this.displayPanel, "North");
    
    this.buttonPanel = new JPanel();
    this.buttonPanel.setBackground(new Color(255, 239, 223));
    this.buttonPanel.setLayout(new GridLayout(4, 8));
    this.buttonPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, new Color(153, 255, 255)));
    
    this.buttonGrid = new JButton[4][8];
    
    for (int i = 0; i < 4; i++) {
      
      for (int j = 0; j < 8; j++) {
        
        this.buttonGrid[i][j] = new JButton();
        this.buttonGrid[i][j].setFont(new Font("Courier New", 1, 16));
        this.buttonGrid[i][j].addActionListener(this);
        this.buttonGrid[i][j].setBorder(BorderFactory.createBevelBorder(1));
        this.buttonPanel.add(this.buttonGrid[i][j]);
      } 
    } 
    this.buttonGrid[0][0].setText("eXit");
    this.buttonGrid[0][1].setText("C");
    this.buttonGrid[0][2].setText("CE");
    this.buttonGrid[0][3].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[0][3].setBackground(new Color(240, 246, 255));
    this.buttonGrid[0][3].setForeground(new Color(153, 0, 102));
    this.buttonGrid[0][3].setText("7");
    this.buttonGrid[0][4].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[0][4].setBackground(new Color(240, 246, 255));
    this.buttonGrid[0][4].setForeground(new Color(153, 0, 102));
    this.buttonGrid[0][4].setText("8");
    this.buttonGrid[0][5].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[0][5].setBackground(new Color(240, 246, 255));
    this.buttonGrid[0][5].setForeground(new Color(153, 0, 102));
    this.buttonGrid[0][5].setText("9");
    this.buttonGrid[0][6].setText("+");
    this.buttonGrid[0][7].setText("x");
    this.buttonGrid[1][0].setText("STO");
    this.buttonGrid[1][1].setText("RCL");
    this.buttonGrid[1][2].setText("Up");
    this.buttonGrid[1][3].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[1][3].setBackground(new Color(240, 246, 255));
    this.buttonGrid[1][3].setForeground(new Color(153, 0, 102));
    this.buttonGrid[1][3].setText("4");
    this.buttonGrid[1][4].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[1][4].setBackground(new Color(240, 246, 255));
    this.buttonGrid[1][4].setForeground(new Color(153, 0, 102));
    this.buttonGrid[1][4].setText("5");
    this.buttonGrid[1][5].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[1][5].setBackground(new Color(240, 246, 255));
    this.buttonGrid[1][5].setForeground(new Color(153, 0, 102));
    this.buttonGrid[1][5].setText("6");
    this.buttonGrid[1][6].setText("-");
    this.buttonGrid[1][7].setText("/");
    this.buttonGrid[2][0].setText("Load");
    this.buttonGrid[2][1].setText("Save");
    this.buttonGrid[2][2].setText("Down");
    this.buttonGrid[2][3].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[2][3].setBackground(new Color(240, 246, 255));
    this.buttonGrid[2][3].setForeground(new Color(153, 0, 102));
    this.buttonGrid[2][3].setText("1");
    this.buttonGrid[2][4].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[2][4].setBackground(new Color(240, 246, 255));
    this.buttonGrid[2][4].setForeground(new Color(153, 0, 102));
    this.buttonGrid[2][4].setText("2");
    this.buttonGrid[2][5].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[2][5].setBackground(new Color(240, 246, 255));
    this.buttonGrid[2][5].setForeground(new Color(153, 0, 102));
    this.buttonGrid[2][5].setText("3");
    this.buttonGrid[2][6].setText("^");
    this.buttonGrid[2][7].setText("%");
    this.buttonGrid[3][0].setText("BSP");
    this.buttonGrid[3][1].setText("Stack");
    this.buttonGrid[3][2].setText("?");
    this.buttonGrid[3][3].setText("+/-");
    this.buttonGrid[3][4].setFont(new Font("Courier New", 1, 20));
    this.buttonGrid[3][4].setBackground(new Color(240, 246, 255));
    this.buttonGrid[3][4].setForeground(new Color(153, 0, 102));
    this.buttonGrid[3][4].setText("0");
    this.buttonGrid[3][5].setText(".");
    this.buttonGrid[3][6].setText("1/n");
    this.buttonGrid[3][7].setFont(new Font("Courier New", 1, 15));
    this.buttonGrid[3][7].setBackground(new Color(246, 240, 255));
    this.buttonGrid[3][7].setForeground(new Color(153, 0, 102));
    this.buttonGrid[3][7].setText("Enter");
    
    this.contentPane.add(this.buttonPanel, "Center");
  }

  
  

  
  public void actionPerformed(ActionEvent event) { try {
	dealWith(event.getActionCommand());
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} }
  


 public void dealWith(String actionCommand) throws IOException {
    if (actionCommand.equals("eXit")) {
    	if(isHelp) {
    		help("Exit the program");
    	}else {
            System.exit(0);
    		
    	}
     }
    
    if (actionCommand.equals("?")) {
        if(isHelp) {
        	isHelp=false;
        	this.buttonGrid[3][2].setBackground(new JButton().getBackground());
            this.buttonGrid[3][2].setForeground(new JButton().getForeground());
        }else {
        	isHelp=true;
        	this.buttonGrid[3][2].setBackground(new Color(240, 246, 255));
            this.buttonGrid[3][2].setForeground(new Color(153, 0, 102));
        }
     }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("Stack")) {
    	if(isHelp) {
    		help("show the stack");
    	}else {
    		  stackFrame s = new stackFrame();
    	        s.setVisible(true);    		
    	}
      
     }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("STO")) {
    	if(isHelp) {
    		help("Store number to one of 26 internal registers. These can be\r\n" + 
    				"saved when the save button is clicked and can be restored when load button is\r\n" + 
    				"clicked");
    	}else {
    		storeForm s = new storeForm();
    	        s.setVisible(true); 
    		
    	}
    
     }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("RCL")) {
    	if(isHelp) {
    		help("Recall a number from one of the 26 internal storage registers");
    	}else {
    		recallForm s = new recallForm();
    	        s.setVisible(true);    		
    	}
    	
     }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("C")) {
    	if(isHelp) {
    		help("removes all elements from the stack");
    	}else {
    		 rpnCalc.theStack.clear();
    		 if(stackFrame.isCreated) {
    			 stackFrame.list.setModel(stackFrame.addElementStack());
    			 }
    		 JOptionPane.showMessageDialog(null, "done successfully");

    	}
  	 
     }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("CE")) {
    	if(isHelp) {
    		help("removes the top element from the stack");
    	}else {
    		 rpnCalc.theStack.pop();
    		 
    		 if(stackFrame.isCreated) {
    			 stackFrame.list.setModel(stackFrame.addElementStack());
    			 }
    		 JOptionPane.showMessageDialog(null, "done successfully");

    	}
  	 
     }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("Save")) {
    	if(isHelp) {
    		help("Save the stack and the registers to Files");
    	}else {
    		//save stack
    	
    		BufferedWriter outputWriter = null;
    		  outputWriter = new BufferedWriter(new FileWriter("stack.txt"));
    		   
		        Iterator iterator = rpnCalc.theStack.iterator(); 

		        while(iterator.hasNext()) {
		    outputWriter.write(iterator.next() +"");
		    outputWriter.newLine();
		        }
    		  
    		  outputWriter.flush();  
    		  outputWriter.close(); 
    		  
    		  //svae register
    		  
    		  FileOutputStream fos = new FileOutputStream("register.txt");
    		    ObjectOutputStream oos = new ObjectOutputStream(fos);
    		    oos.writeObject(rpnCalc.register);
    		    oos.close();
    		    
       		 JOptionPane.showMessageDialog(null, "done successfully");

    		
    		
    	}
  	 
     }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("Load")) {
    	if(isHelp) {
    		
    		help("Load from files -- loads the stack list element-by-element from the file\r\n" + 
    				"to the stack – and loads the registers form the file to the registers.");
    	}else {
    		
    		//load stack 
    		 FileReader fileReader = new FileReader("stack.txt");
             
    	        BufferedReader bufferedReader = new BufferedReader(fileReader);
    	        String line = null;
    	         
    	        while ((line = bufferedReader.readLine()) != null) 
    	        {
    	        	rpnCalc.theStack.add(Double.parseDouble(line));
    	        }
    	         
    	        bufferedReader.close();
    	        
    	        if(stackFrame.isCreated) {
    	   		 stackFrame.list.setModel(stackFrame.addElementStack());
    	   		 }
    	        
    	        //load register
    	        
    	        FileInputStream fis = new FileInputStream("register.txt");
    	        ObjectInputStream ois = new ObjectInputStream(fis);
    	        try {
					rpnCalc.register=(Register[]) ois.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	        ois.close();
    	        if(storeForm.isCreated) {
    	        	storeForm.list.setModel(storeForm.addElementList());
    	        }
    	        if(recallForm.isCreated) {
    	        	recallForm.list.setModel(storeForm.addElementList());

    	        }
    	        
       		 JOptionPane.showMessageDialog(null, "done successfully");


    	}
  	 
     }
    
    
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("Enter")) {
    	 if(isHelp) {
     		
     		help("push value to the stack.");
    	 }else {
    		 if(displayTextField.getText().contains("/")) {
    			 rpnCalc.theStack.push(multiplicative_inverse);
    			 displayTextField.setText(null);
    			 if(stackFrame.isCreated) {
    			 stackFrame.list.setModel(stackFrame.addElementStack());
    			 }

    		 }else {
    	double number =Double.parseDouble(displayTextField.getText());
		 rpnCalc.theStack.push(number);
		 displayTextField.setText(null);
		 if(stackFrame.isCreated) {
		 stackFrame.list.setModel(stackFrame.addElementStack());
		 }
		 }
    	 }
       }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("BSP")) {
         if(isHelp) {
    		
    		help("(Backspace) used to correct mistakes in the display field by erases the last\r\n" + 
    				"typed character on the display");
    	}else {

    		char[] text=displayTextField.getText().toCharArray();
    		displayTextField.setText(null);
    		for(int i=0 ;i<text.length-1;++i) {
    			displayTextField.setText(displayTextField.getText()+ text[i]);
    		}
    	}


       }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("Up")) {
        if(isHelp) {
   		
   		help("remove the top element and place it at the bottom");
   	}else {
   		
   		if(rpnCalc.theStack.size()!=0) {
   			double num =rpnCalc.theStack.pop();
   			rpnCalc.theStack.add(num);
   			if(stackFrame.isCreated) {
   			 stackFrame.list.setModel(stackFrame.addElementStack());
   			 }  
   			
   			
   			
   		}else {
			 JOptionPane.showMessageDialog(null, "The stack is empty","Error" ,JOptionPane.WARNING_MESSAGE);

   		}
   	}// end else help

      }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("Down")) {
        if(isHelp) { 
   		
   		help("remove the bottom element and place it at the\r\n" + 
   				"top.");
   	}else {
   		
   		if(rpnCalc.theStack.size()!=0) {
   			double num =rpnCalc.theStack.removeLast();
   			rpnCalc.theStack.push(num);
   			if(stackFrame.isCreated) {
   			 stackFrame.list.setModel(stackFrame.addElementStack());
   			 } 
   			
   			
   		}else {
			 JOptionPane.showMessageDialog(null, "The stack is empty","Error" ,JOptionPane.WARNING_MESSAGE);

   		}
   	}// end else help

      }
    
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
    
    if (actionCommand.equals("1/n")) {
    	double num =Double.parseDouble(displayTextField.getText());

    	displayTextField.setText( "1/" +displayTextField.getText());
    	multiplicative_inverse=1/ num;

       }
    
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		    
		    
		if (actionCommand.equals("+/-")) {
		double num =Double.parseDouble(displayTextField.getText());
		if(num<0) {
			num=num*-1;
		}else {
			num=num*-1;
		}
		displayTextField.setText( num+"");
		
		}
		    
    
   
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     
    
    if (actionCommand.equals("1")) {
    	displayTextField.setText(displayTextField.getText() +"1");

       }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("2")) {
    	displayTextField.setText(displayTextField.getText() +"2");

       }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("3")) {
    	displayTextField.setText(displayTextField.getText() +"3");

       }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("4")) {
    	displayTextField.setText(displayTextField.getText() +"4");

       }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("5")) {
    	displayTextField.setText(displayTextField.getText() +"5");

       }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("6")) {
    	displayTextField.setText(displayTextField.getText() +"6");

       }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("7")) {
    	displayTextField.setText(displayTextField.getText() +"7");

       }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("8")) {
    	displayTextField.setText(displayTextField.getText() +"8");

       }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("9")) {
    	displayTextField.setText(displayTextField.getText() +"9");

       }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals("0")) {
    	displayTextField.setText(displayTextField.getText() +"0");

       }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    if (actionCommand.equals(".")) {
    	displayTextField.setText(displayTextField.getText() +".");

       }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    if (actionCommand.equals("+")) {
    	operation('+');
       }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    if (actionCommand.equals("-")) {
    	operation('-');
       }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    if (actionCommand.equals("x")) {
    	operation('x');
       }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    if (actionCommand.equals("/")) {
    	operation('/');
       }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    if (actionCommand.equals("%")) {
    	operation('%');
       }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    if (actionCommand.equals("^")) {
    	operation('^');
       }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    

}


private void operation(char c) {

	if(rpnCalc.theStack.size()>=2) {
		double fitstNum =rpnCalc.theStack.pop();
		double secNum =rpnCalc.theStack.pop();
		double newNum =0;
		switch(c) {
		case '+':newNum=secNum +fitstNum;break;
		case '-':newNum=secNum -fitstNum;break;
		case 'x':newNum=secNum *fitstNum;break;
		case '/':newNum=secNum /fitstNum;break;
		case '%':newNum=secNum %fitstNum;break;
		case '^':newNum=Math.pow(secNum, fitstNum);break;
						
		}
		rpnCalc.theStack.push(newNum);
		if(stackFrame.isCreated) {
  			 stackFrame.list.setModel(stackFrame.addElementStack());
  			 } 
				
				
		

		
	}else {
		 JOptionPane.showMessageDialog(null, "The stack must contain  least two elements","Error" ,JOptionPane.WARNING_MESSAGE);

	}
}




private void help(String string) {
	  JOptionPane.showMessageDialog(null, string);

}



}