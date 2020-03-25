
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Project extends JFrame implements ActionListener  {

	
	// Global variables
	
    private JButton select,result,exit,noOfAppl ;
    private JTextField socialtext,algorithmtext,gpatext,agetext,ktext ; 
    private JLabel sociallabel,algorithmlabel,gpalabel,agelabel,filelabel,klabel,image ;
    private JFileChooser chooser;
    private double social,algo,gpa,age ;// Features of applicants.
    private Applicant[] applicants ;    //  hold the applicants who applied to job in previous years in an array.
    private File file ; 
    private MergeSort mergesort ;
    private int k;
    private int x,xdir,totalAppl;
    private Timer timer;
    
    
    public Project() throws IOException {
    	
    timer = new Timer(200,this);  
    timer.start();
    
    x = 50 ;
    xdir = 10 ;
          
    setLayout(null);
    setSize(410,420);
    
    sociallabel = new JLabel("Social"); // Creation of label of social
    sociallabel.setSize(50,30);
    sociallabel.setLocation(10,50);
    add(sociallabel);
    
    
    algorithmlabel = new JLabel("Algorithm"); // Creation of label of algorithm
    algorithmlabel.setSize(60,30);
    algorithmlabel.setLocation(10,90);
    add(algorithmlabel); 
    
    
    gpalabel = new JLabel("GPA"); // Creation of label of GPA
    gpalabel.setSize(30,30);
    gpalabel.setLocation(10,130);
    add(gpalabel);   
    
    agelabel = new JLabel("Age"); // Creation of label of age
    agelabel.setSize(30,30);
    agelabel.setLocation(10,170);
    add(agelabel); 
    
    filelabel = new JLabel("Please open a file");
    filelabel.setSize(160,30);
    filelabel.setLocation(10,250);
    add(filelabel);   
    
    klabel = new JLabel("K Closest Applicant(s)");
    klabel.setSize(175,30);
    klabel.setLocation(10,210);
    add(klabel); 
    
    
    
    socialtext = new JTextField(); // Creation of text field of social
    socialtext.setSize(50,20);
    socialtext.setLocation(100,55);
    add(socialtext);
    
    algorithmtext = new JTextField();  // Creation of text field of algorithm
    algorithmtext.setSize(50,20);
    algorithmtext.setLocation(100,95);
    add(algorithmtext);
    
    gpatext = new JTextField();  // Creation of text field of GPA
    gpatext.setSize(50,20);
    gpatext.setLocation(100,135);
    add(gpatext);
    
    agetext = new JTextField();  // Creation of text field of age
    agetext.setSize(50,20);
    agetext.setLocation(100,175);
    add(agetext);
    
    ktext = new JTextField(); // Creation of text field of k
    ktext.setSize(50,20);
    ktext.setLocation(165,215);
    add(ktext);
    
    select = new JButton();  // Creation of button of file chooser
    select.setSize(40,20);
    select.setLocation(164,255);
    add(select);
    select.addActionListener(this);
    
    result = new JButton("Result"); // Creation of button of result
    result.setSize(100,40);
    result.setLocation(70,300);
    add(result);
    result.addActionListener(this);
    
    exit = new JButton("Exit");  // Creation of button of exit
    exit.setSize(100,40);
    exit.setLocation(240,300);
    add(exit);
    exit.addActionListener(this);
   
    mergesort = new MergeSort();   
    
    setTitle("KNN");    
  
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setVisible(true);  
    	
    }
    
 // Creation of text that is seen at bottom.
    public void paint(Graphics g) {
     
        super.paint(g);
        g.setFont(new Font("TimesRoman",Font.BOLD,10));
    	g.setColor(Color.BLACK);
    	
    	
    } 
	
    // Main Method
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		
	// It decides that how file chooser will be appeared from one operating systems to another .
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		try {
			new Project();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	public void actionPerformed(ActionEvent event) {
	
		
		if(event.getSource() == select) {
			
		// File chooser to reach location of sample text
		chooser = new JFileChooser();
	    chooser.setFileSelectionMode(JFileChooser.APPROVE_OPTION);
	    chooser.showOpenDialog(this);
		this.file = chooser.getSelectedFile();
		findNumofAppl(file);
		applicants(file); 
				
				} 
		 	
		else if(event.getSource() == exit) {
			
			System.exit(0); // When user click the exit button,program shutdowns itself.
			
		}
		
		else if(event.getSource() == result) {
			
		try {	
	   // When user click the result button knnAlgo method is invoked with the values that are received from text fields.
			this.social = Double.valueOf(socialtext.getText().trim());
		    this.algo = Double.valueOf(algorithmtext.getText().trim());       // features of new applicant 
		    this.gpa = Double.valueOf(gpatext.getText().trim());
		    this.age = Double.valueOf(agetext.getText().trim()); 
		    this.k =  Integer.valueOf(ktext.getText().trim()); 	
		  
		    knnAlgorithm(social,algo,gpa,age);
		    result();			
	  // After knnAlgo method is executed then result() is invoked to display possible result.		    
		}				
	   catch(Exception e) {   
	// in case of any error we warn the user to check his/her entries.	   
		   JOptionPane.showMessageDialog(null,"Please make sure you did everything correct","Something went wrong!!!",JOptionPane.ERROR_MESSAGE, null);
	   }					
		}
		else if(event.getSource() == timer) {
			
			x = x - xdir ;
			
			if(x<-130) {
				
				x = 400 ;    // Second part of code of text that is seen at bottom.
				             // it allows text to go to the right.
			}
			
			repaint();
				
		}
		
		
	}
	
	// KNN algorithm is applied in this method.
	
	public void knnAlgorithm(double social,double algo,double gpa,double age) {
		

		 int i;
		
		for(i=0 ; i<applicants.length ; i++){
              
	         double res = Math.sqrt(Math.pow(social-applicants[i].social,2)+Math.pow(algo-applicants[i].algorithm,2)
	         +Math.pow(gpa-applicants[i].gpa,2)+Math.pow(age-applicants[i].age,2)) ;
	         applicants[i].result = res ;
	      
	        
	         
	       }
		
		mergesort.sort(applicants, 0, applicants.length,applicants.length) ; // sort the array according to results that are received from 
		                                             // KNN algorithm		
	
	}
	
	
	public void applicants(File file)  {		
		
				try {

					Scanner sc = new Scanner(file);
					applicants = new Applicant[totalAppl-1]; //  create an array at first to hold applicants.
					sc.nextLine();
					
					int i = 0 ;
				      
					while(sc.hasNextLine()) {
						Applicant applicant = new Applicant(); // create object of applicant as long as there is         
						String infos = sc.nextLine(); 	
						String info[] = infos.split(",");      //  used this method to split line into 5 parts
			            applicant.social = Double.valueOf(info[0]);
			        
			            applicant.algorithm = Double.valueOf(info[1]);
			           
			            applicant.gpa= Double.valueOf(info[2]);
			          
			            applicant.age= Double.valueOf(info[3]);
			          
			            applicant.y= Integer.valueOf(info[4]);
			      
			            applicants[i] = applicant ;
			            i++ ;} 
					
					  
					sc.close();
					
				}
					
					
					catch (Exception e) {
						
						JOptionPane.showMessageDialog(null, "Please choose a file"); }	
						
		
	}
	
	
	// look at the y values of closest persons to our new applicant in this method.	 
	
	
	public void result() {
		
		int zeros = 0;
		int ones  = 0;
		
		for(int j=0 ; j<k ; j++) {   
	           if(applicants[j].y==1)    
	                 ones++ ;     
	           else               
	               zeros++ ;
	       
	       }
		
		
 // Finally  compare the ones and zeros and display a message to user according to result.
		if(ones>zeros)      
	         JOptionPane.showMessageDialog(null,"The Applicant Accepted." );
	                                                             
	       else if(ones==zeros)
	    	   JOptionPane.showMessageDialog(null,"Please change the value of k ");
	       
	       else
	    	   JOptionPane.showMessageDialog(null,"The Applicant Reject.");    
		
		
	}
	
	public void findNumofAppl(File file) {
		try {
			
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			this.totalAppl = 0 ;
			while(br.readLine() != null) {
				
				this.totalAppl++ ;
				
			}
		   
			
			br.close();
			fr.close();
		} 
		
		catch (Exception e) {
			
		e.printStackTrace();}
		
	}
	}
	
	
	
	
   

