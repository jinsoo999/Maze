import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.awt.*;
import javax.swing.*;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class rank extends JFrame {
	    
	public static void main(String[] args) throws IOException {
		
		rank f=new rank();
		String ss;
	    String s[] = new String [100];			
	    String tt;
	    String temp[] = new String [100];
	    int tme[]=new int [100];
		int i=0;
	    int j=0;
		try {
		      BufferedReader in = new BufferedReader(new FileReader("user.txt"));
		      BufferedReader in2=new BufferedReader(new FileReader("rank.txt"));
	

		      while ((ss = in.readLine()) != null) {
		    	 s[i]=ss;
		       
		        i++;
		      }
		      in.close();
		     
		      
		      while ((tt = in2.readLine()) !=null) {
		    	  temp[j]=tt;
			   
			    j++;
			  }
		  
		     
			  in2.close();
		     
		    } catch (IOException e) {
		        System.err.println(e); // ������ �ִٸ� �޽��� ���
		        System.exit(1);
		    }
		
		for(int v=0;v<i;v++ )
		{
			tme[v]=Integer.parseInt(temp[v]);
		}
		
		
		for(int m = 0; m < tme.length; m++)
	        {
	            for(int k = m; k < tme.length; k++)         // ù��° �迭�� ���� �ι�° �迭����
	            {                                            // ������ �ڸ��� �ٲ�
	                if(tme[m] < tme[k])                    // ������� ������ �迭�� ���Ͽ� ������쿡
	                {                                        // �ڸ��� �ٲ�
	                    int tmp = tme[m];
	                    String tm=s[m];
	                    tme[m] = tme[k];
	                    s[m]=s[k];
	                    tme[k] = tmp;
	                    s[k]=tm;
	                }
	                
	            }
	        }
		for(int q=0;q<5;q++)
		{
			System.out.println("-----------"+s[q]+" : "+tme[q]);
		}
		JFrame frame=new JFrame();
		Container contentPane=frame.getContentPane();
		contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
		JLabel jlabel[] = new JLabel [6];
		JLabel label5=new JLabel("                                   �̷η�ŷ");
		contentPane.add(label5);
		for(int t=1;t<6;t++) {
			jlabel[t-1]=new JLabel("                                  "+t + "�� " + s[t-1] + " : " + tme[t-1]);
		contentPane.add(jlabel[t-1]);
		frame.setLocation(0, 0);
		frame.setPreferredSize(new Dimension(300,300));
		frame.pack();
		frame.setVisible(true);
		}

	}
	
}
