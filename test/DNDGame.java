import java.io.File; 
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;
import java.io.PrintWriter;
import java.util.Timer;
import java.awt.Color;
import java.util.TimerTask;

public class DNDGame{
            public static JPanel panell = new JPanel();
   public static JFrame fframe = new JFrame("Play");
   public static JFrame frame = new JFrame("choose ur game");
   public static JPanel panel = new JPanel();
   public static JFrame newfam = new JFrame("login");

   public static void main(String[] args){
   String SheetName = "", GameName = "";
                   // Setting the width and height of frame
        frame.setSize(450, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        
        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
                 // adding panel to frame
        frame.add(panel);
        /* calling user defined method for adding components
         * to the panel.
         */
         newfam.setSize(450, 250);
        newfam.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

         JPanel pnl = new JPanel();
         newfam.add(pnl);
        Login(pnl);
        newfam.setVisible(true);

   }
   public static JTextField GameNam = new JTextField("enter name of chatroom");
   public static String usernam = "user";
   public static void Login(JPanel panelll){
            panelll.removeAll();

         panelll.revalidate();
         panelll.repaint();
         panelll.setLayout(null);
         
         JLabel info = new JLabel("if you do not hava an acount, this will create one");
         
         JLabel userLabel = new JLabel("username");
          userLabel.setBounds(10,20,80,25);
         panelll.add(userLabel);
         
        JTextField Username = new JTextField("gi");
        Username.setBounds(100,20,165,25);
        panelll.add(Username);
        
        JLabel Name = new JLabel("password");
        Name.setBounds(10,20,80,75);
        panelll.add(Name);

        /* Creating text field where user is supposed to
         * enter user name.
         */
        JTextField Pass = new JPasswordField();
        Pass.setBounds(100,50,165,25);
        panelll.add(Pass);
        
        JButton sub = new JButton("submit");
        sub.setBounds(300,20,75,50);
       // sub.setBackground(new Color(35, 45, 155));
        sub.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
                File userr = new File(("J:\\Chatroom\\test\\users\\User_" +Username.getText()));
          if (userr.exists()){
          try{
          Scanner wordReader = new Scanner(userr);
          String passs = encrypt(Pass.getText());
          if((wordReader.nextLine()).equals(passs)){
          JOptionPane.showMessageDialog(frame, "login complete");
          usernam = Username.getText();
          panel.setEnabled(false);
                  
        placeComponents(panel);

        // Setting the frame visibility to true
        frame.setVisible(true);
          }
          else{
          JOptionPane.showMessageDialog(frame, "login failed");
          
          }
         }
         catch (Exception e){
         System.out.println("hi");
         }
         }
         else{
         try{
           PrintWriter out = new PrintWriter(new FileWriter(userr, true));
           String passs = encrypt(Pass.getText());
           out.println(passs);
           out.close();
           usernam = Username.getText();
           JOptionPane.showMessageDialog(frame, "new login created");
           panel.setEnabled(false);
           placeComponents(panel);

           // Setting the frame visibility to true
           frame.setVisible(true);

         }
         catch (Exception e){
         System.out.println("broke");
         }
         }
        }
        });
        panelll.add(sub);
    
   }
   public static void placeComponents(JPanel panel){
    panel.removeAll();

         panel.revalidate();
         panel.repaint();
      panel.setLayout(null);
      newfam.setVisible(false);
      //panel.setBackground(new Color(17, 35, 8));
        // Creating JLabel
        JLabel userLabel = new JLabel("Chatroom Name");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        /* Creating text field where user is supposed to
         * enter user name.
         */
        GameNam.setBounds(100,20,165,25);
        panel.add(GameNam);
        
          
        JButton sub = new JButton("submit");
        sub.setBounds(300,20,75,50);
       // sub.setBackground(new Color(35, 45, 155));
        sub.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
        try{
         go(GameNam, usernam);

         }
         catch (Exception e){
         System.out.println("hi");
         }
        }
        });
        panel.add(sub);

   }
   public static void go(JTextField namegame, String charsheet){
         panell.removeAll();

         panell.revalidate();
         panell.repaint();

                // Setting the width and height of frame
        fframe.setSize(450, 750);
        fframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        /* Creating panel. This is same as a div tag in HTML
         * We can create several panels and add them to specific 
         * positions in a JFrame. Inside panels we can add text 
         * fields, buttons and other components.
         */
                 // adding panel to frame
        fframe.add(panell);
        /* calling user defined method for adding components
         * to the panel.
         */


       // Schedule to run after every 3 second(3000 millisecond)
         ShowGame(panell, namegame, charsheet);         



        // Setting the frame visibility to true
        fframe.setVisible(true);
   }
   
     public static JLabel file = new JLabel();
           
     public static void ShowGame(JPanel panel, JTextField namegame, String charsheet){
     panel.setLayout(null);
          file.setBounds(10,100,350,500);
        // Creating JLabel
        JLabel userLabel = new JLabel("talk");
        userLabel.setBounds(10,20,80,25);
        panel.add(userLabel);

        /* Creating text field where user is supposed to
         * enter user name.
         */
        JTextField userText = new JTextField();
        userText.setBounds(100,20,165,25);
        panel.add(userText);
                               
        panel.add(file);
        
                
        
        dostuff(namegame, file);
        
        JButton Submit = new JButton("submit");
        Submit.setBounds(60,60, 250,25);
       // Submit.setBackground(new Color(150,50,25));
        Submit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent ae){
        try{
                 String findout = userText.getText();
                 
                 boolean cmd;
         if(findout.length() >= 5){
         cmd = findout.substring(0,5).equals("%roll");
         }
         else{
         cmd = false;
         }
         if(findout.length() <=400){
         if(cmd){ //if user enters comand %roll 1d20# or similar
         Write(namegame, findout,charsheet);
         Roll(findout,namegame);
         }
         else{
         Write(namegame, findout, charsheet);
         }
         }
         else{
         JOptionPane.showMessageDialog(frame, "do not exceed 400 characters!!!!!!!!!!");
         }
         dostuff(namegame,file);
                }
                catch(Exception e){
                System.out.println("error");
                }
                }
        });
        panel.add(Submit);
        
          Timer timer = new Timer();
   
         timer.scheduleAtFixedRate( new TimerTask() { public void run() { 
         JLabel ffile = DNDGame.file;
         JTextField namgame = DNDGame.GameNam;
         DNDGame.dostuff(DNDGame.GameNam, DNDGame.file);
         }}, 0,2* 1000);
  

         
     }
     public static void dostuff(JTextField namegame, JLabel file){
      int numoflines = 0;
        int totlines = 0;
        int curline = 0;
        String w = "<HTML>";
         try{
         Scanner wordReader = new Scanner(new File(namegame.getText()));
         while (wordReader.hasNextLine()){
         String redd = wordReader.nextLine();
         totlines++;
         }
         
         Scanner wordReadr = new Scanner(new File(namegame.getText()));
         while ( wordReadr.hasNextLine() ) {
         if(curline < (totlines- 15)){
         String redHerring = wordReadr.nextLine();
         
         }
         else{
          w += (wordReadr.nextLine() + "<br>");
          numoflines += 1;
          }
          curline += 1;
          System.out.println(curline);
         }
         w+="</HTML>";
         System.out.println("doing stuff");
         file.setText(w);
          }
         
         catch (Exception e){
         w = "Error";
         System.out.println("error");
         }

     }
     public static void Write(JTextField game, String userStuff, String charName) throws Exception{
      File gam = new File(game.getText());
      PrintWriter out = new PrintWriter(new FileWriter(gam, true));
      out.println(charName+ ": "+userStuff);
      out.close();
  } 
  public static void Roll(String userStuff, JTextField game) throws Exception{
        File gam = new File(game.getText());
      PrintWriter outt = new PrintWriter(new FileWriter(gam, true));
      int numofdice = (int) (Double.parseDouble(userStuff.substring(userStuff.indexOf(" "),userStuff.indexOf("d"))));
      int diceroll = (int) (Double.parseDouble(userStuff.substring(userStuff.indexOf("d")+1,userStuff.indexOf("#"))));
      int actroll = 0;
      for (int i = 0; i < numofdice; i++){
         actroll += (int) (1 + Math.round(Math.random() * (diceroll-1)));
      } 
      outt.print("<div stytle= \"font-size:125%\"> " + actroll + "</div>");
      outt.close();
      }
   public static String encrypt(String encrypt){
   String toEncr = encrypt;
   int keylength = 5;
   String ciphertext = "";
   String key = "im_encrypting";
    for (int i=0; i<toEncr.length(); i++) {
   int asciiPT = (int) toEncr.charAt(i);
    int asciiK = key.charAt(((int)(i%keylength)));
   System.out.println(asciiK + ":key");
   int asciishifted =((asciiPT- asciiK)%26) + 65;
   System.out.println(asciishifted);
   char cipherChar = (char)asciishifted;
   ciphertext = ciphertext + cipherChar;
      }
      System.out.println(ciphertext);
      return ciphertext;
   }
}


