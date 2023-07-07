import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class rank2 extends JFrame implements ActionListener {

	public static void main(String[] args) {
		rank2 f=new rank2();

	}
	 public void actionPerformed(ActionEvent ae){
	        
         try{
             FileWriter fw=new FileWriter("user.txt",true);    //파일명과 같은 파일명이 존재할시 덧붙여쓸여부판단
             BufferedWriter bf=new BufferedWriter(fw);
             
             bf.write(t1.getText()+" ");          
             bf.newLine();
             bf.close();        //저장 후 텍스트필드의 값을 가져온 자원들을 해제한다.
             
             t1.setText("");
            
             
             FileReader fr=new FileReader("sava.txt");    //String형으로 파일을 읽어온다.
             BufferedReader br=new BufferedReader(fr);    //한줄씩읽기위해(그리고 빠른속도로 읽어들인다)
             String str=null;        //while조건부
             
             while((str=br.readLine())!=null){
                 System.out.println(str);        //null이 될때까지 한줄씩 읽어온다.
             }
             
             br.close();        //읽어온 자원들을 해제한다.
         }catch(Exception n){
             System.out.println(n);
         }
 }

	Toolkit tk = Toolkit.getDefaultToolkit();
	Image clear_img=new ImageIcon("b.png").getImage();
	Image buffImage4;
	Graphics buffg4;
	private JButton b2;
	JTextField t1;
	public String data;
	public rank2() {
		setSize(900,700);
		setLocation(0,0);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("게임클리어화면");
		JPanel panel=new JPanel();		
		panel.setLayout(null);
		b2=new JButton("랭킹등록");
		b2.addActionListener(this);
		panel.add(b2);
		t1=new JTextField(5);
		panel.add(t1);
		panel.add(b2);		
		b2.setBounds(400,480,100,50);
		t1.setBounds(400,400,100,50);
		add(panel);
		setVisible(true);
	}
	
	
	
	
	public void paint(Graphics g){
		buffImage4 = createImage(900, 700); 
		buffg4 = buffImage4.getGraphics();
		update(g);
		}
		public void update(Graphics g){
		Draw_Background(); //배경 이미지 그리기 메소드 실행
		g.drawImage(buffImage4, 0, 0, this); 
		}
		public void Draw_Background(){
			//배경 이미지를 그리는 부분입니다.
		buffg4.clearRect(0, 0, 900, 700);
		//화면 지우기 명령은 이제 여기서 실행합니다.
		buffg4.drawImage(clear_img, 0, 0, this);			
	    }

}
