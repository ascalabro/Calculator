package gui;

import javax.swing.*;
import javax.swing.text.Caret;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class Prog_GUI{
	
	private final int defaultnumberwidth = 165;
	private final int defaultnumberheight = 24;
	private final int defaultfunctionbuttonsize = 55;
	
	JFrame window;
	JPanel contentPane;
	
	static JTextField secondnumber_field;
	static JTextField firstnumber_field;
	static JButton display;
	static JButton add;
	static JButton subtract;
	static JButton multiply;
	static JButton divide;
	static JButton save;
	
	static JTextArea savedAnswersDisplay;
	static ArrayList<String> answersList = new ArrayList<String>();
	static JTextArea answer;
	
	static File data;
	
	Prog_GUI(){
		//create main window, add all components to main frame
		  window = new JFrame("Calculator Program");
		  contentPane = new JPanel(); 
		  contentPane.setLayout(null);
		  contentPane.setPreferredSize(new Dimension(700, 425));
		  contentPane.setBackground(Color.lightGray);
		 
		  
		  //create start button
		  display = new JButton("Add");
		  display.setBounds(616, 55, 65, 58 );  
		  
		  //create addition button
		  add = new JButton("+");
		  add.setBounds(55, 355, defaultfunctionbuttonsize , defaultfunctionbuttonsize );
		  
		  //create subtraction button
		  subtract = new JButton("-");
		  subtract.setBounds(115, 355, defaultfunctionbuttonsize , defaultfunctionbuttonsize );
		  
		  //create multiply button
		  multiply = new JButton("*");
		  multiply.setBounds(175, 355, defaultfunctionbuttonsize , defaultfunctionbuttonsize );
		  
		  //create divide button
		  divide = new JButton("/");
		  divide.setBounds(235, 355, defaultfunctionbuttonsize , defaultfunctionbuttonsize );
		  
		  //create first number's field
		  firstnumber_field = new JTextField();
		  firstnumber_field.setBounds(55, 55, defaultnumberwidth, defaultnumberheight);
		  
		  //create second number's field
		  secondnumber_field = new JTextField();
		  secondnumber_field.setBounds(55, 95, defaultnumberwidth, defaultnumberheight);
		  
		  save = new JButton("Save Results");
		  save.setBounds(350, 350 ,155 ,60 );
		  
		  //create prompt for numbers
		  JLabel fi = new JLabel("First number");
		  fi.setBounds(55, 35, defaultnumberwidth, defaultnumberheight);
		  
		  JLabel se = new JLabel("Second number");
		  se.setBounds(55, 75, defaultnumberwidth, defaultnumberheight);
		  
		  answer = new JTextArea("Answer is displayed here");
		  answer.setBounds(350, 55, 255, 60);
		  answer.setEditable(false);
		  
		  savedAnswersDisplay = new JTextArea("Saved Answers:");
		  savedAnswersDisplay.setBounds(350,120,255, 220);
		  savedAnswersDisplay.setEditable(false);
		  
		  
		  
		  contentPane.add(display);
		  contentPane.add(firstnumber_field);
		  contentPane.add(secondnumber_field);
		  contentPane.add(add);
		  contentPane.add(subtract);
		  contentPane.add(divide);
		  contentPane.add(multiply);
		  contentPane.add(answer);
		  contentPane.add(se);
		  contentPane.add(fi);
		  contentPane.add(savedAnswersDisplay);
		  contentPane.add(save);
		  		  
		  window.setVisible(true);
		  window.setLocationRelativeTo(null);
		  window.getContentPane().add(contentPane);
		  window.pack(); 
		  window.setResizable(false);
		  window.setLocationRelativeTo(null);
		  window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		  addCalcListener(add);
		  addCalcListener(subtract);
		  addCalcListener(divide);
		  addCalcListener(multiply);
		  addCalcListener(display);
		  addCalcListener(save);
		  	  
	
	}
	//use this to add listener calc to buttons only
	static void addCalcListener( final JButton a){
		 a.addActionListener(new ActionListener() {

		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	float x = Float.parseFloat(firstnumber_field.getText());
	            	float y = Float.parseFloat(secondnumber_field.getText());
	            	String ans;
		            if(a == add){
		            	ans = String.valueOf(x+y);
		            	answer.setText(ans);
		            }
		            else if(a == subtract){
		            	ans = String.valueOf(x-y);
		            	answer.setText(ans);
		            }
		            else if(a == multiply){
		            	ans = String.valueOf(x*y);
		            	answer.setText(ans);
		            }
		            else if(a == divide){
		            	ans = String.valueOf(x/y);
		            	answer.setText(ans);		            	      
		            }
		            else if(a == display){
		            	savedAnswersDisplay.append("\n" + answer.getText());
		            	answersList.add(answer.getText());
		            }
		            else if(a == save){
		            	//save the results to dat(text) file
		            	try {
							Write();
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							savedAnswersDisplay.append(e1.toString());
						}
		            }

		        }
		    });
	}

	static void Write() throws FileNotFoundException{
		data = new File("sa.dat");
    	PrintWriter output = new PrintWriter(data);
    	//Iterator<String> iterator = answersList.iterator();
    	for (String temp : answersList) {
    		output.append(temp);
    	}
    	output.close();
	}
	
}
