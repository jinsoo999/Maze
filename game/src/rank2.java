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
             FileWriter fw=new FileWriter("user.txt",true);    //���ϸ�� ���� ���ϸ��� �����ҽ� ���ٿ��������Ǵ�
             BufferedWriter bf=new BufferedWriter(fw);
             
             bf.write(t1.getText()+" ");          
             bf.newLine();
             bf.close();        //���� �� �ؽ�Ʈ�ʵ��� ���� ������ �ڿ����� �����Ѵ�.
             
             t1.setText("");
            
             
             FileReader fr=new FileReader("sava.txt");    //String������ ������ �о�´�.
             BufferedReader br=new BufferedReader(fr);    //���پ��б�����(�׸��� �����ӵ��� �о���δ�)
             String str=null;        //while���Ǻ�
             
             while((str=br.readLine())!=null){
                 System.out.println(str);        //null�� �ɶ����� ���پ� �о�´�.
             }
             
             br.close();        //�о�� �ڿ����� �����Ѵ�.
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
		setTitle("����Ŭ����ȭ��");
		JPanel panel=new JPanel();		
		panel.setLayout(null);
		b2=new JButton("��ŷ���");
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
		Draw_Background(); //��� �̹��� �׸��� �޼ҵ� ����
		g.drawImage(buffImage4, 0, 0, this); 
		}
		public void Draw_Background(){
			//��� �̹����� �׸��� �κ��Դϴ�.
		buffg4.clearRect(0, 0, 900, 700);
		//ȭ�� ����� ����� ���� ���⼭ �����մϴ�.
		buffg4.drawImage(clear_img, 0, 0, this);			
	    }

}
