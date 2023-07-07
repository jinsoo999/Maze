import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Random;

class ThirdThread extends Thread
{
    int y = 1;
    JLabel myLabel = null;
    public ThirdThread(JLabel myLabel)
    {
        this.myLabel = myLabel;
 
    }
    public void run()
    {
        while(true)
        {
            myLabel.setText(""+y);
            try {
                Thread.sleep(330);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            y++;
        }         
    }
}

class MyFrame5 extends JFrame{
	
	JButton b1;
	private JButton b2,b3;
	private JTextArea textArea;
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image start_img=new ImageIcon("d.png").getImage();
	Image buffImage5;
	Graphics buffg5;
	String rank;
	public MyFrame5() {
		setSize(900,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("시작화면");
		JPanel panel=new JPanel();
		panel.setLayout(null);
		b1=new JButton("게임 시작");
		b1.addActionListener(new MyListener());
		panel.add(b1);
		b2=new JButton("게임 방법");
		b2.addActionListener(new MyListener());
		panel.add(b2);
		b3=new JButton("랭킹 확인");
		b3.addActionListener(new MyListener());
		panel.add(b3);
		
		b1.setBounds(350,100,200,100);
		b2.setBounds(150,300,200,100);
		b3.setBounds(550,300,200,100);
		
		add(panel);
		setVisible(true);
	}
	int a=0;
	private class MyListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==b1) {
				final Frame fs=new game() ;
				fs.setVisible(true);
				fs.addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent e) {
						fs.setVisible(false);
						fs.dispose();
					}
				});
				fs.setSize(900,700);
				fs.setLocation(100, 100);

			}
			else if(e.getSource()==b2) {
				final Frame fs=new SubFrame2() ;
				fs.setVisible(true);
				fs.addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent e) {
						fs.setVisible(false);
						fs.dispose();
					}
				});
				fs.setSize(480,200);
				fs.setLocation(0, 0);
				
			}
			else if(e.getSource()==b3) {
				final Frame fs=new rank() ;
				fs.setVisible(true);
				fs.addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent e) {
						fs.setVisible(false);
						fs.dispose();
					}
				});
				fs.setSize(300,300);
				fs.setLocation(0, 0);
				}
		}
	}
	public void paint(Graphics g){
		buffImage5 = createImage(900, 700); 
		buffg5 = buffImage5.getGraphics();

		update(g);
		}

		public void update(Graphics g){

		Draw_Background(); //배경 이미지 그리기 메소드 실행
		
		g.drawImage(buffImage5, 0, 0, this); 
		}
		public void Draw_Background(){
			//배경 이미지를 그리는 부분입니다.

			buffg5.clearRect(0, 0, 900, 700);
			//화면 지우기 명령은 이제 여기서 실행합니다.
			buffg5.drawImage(start_img, 0, 0, this);
			
			}
	
}

class SubFrame2 extends JFrame
{
	private JTextArea textArea;

	public SubFrame2() {
		setSize(400,300);
		setTitle("게임방법화면");
		textArea=new JTextArea(20,15);
		textArea.append("                                                            게임 방법"
				+ "\n"
				+ "\n"
				+ "                                               방향키를 통해 이동가능\n"
				+ "            제한시간내에 통과하면 다음 단계로 넘어가며 시간 초과시 게임오버\n"
				+ "총 스테이지는 3단계로 전부 통과시 랭킹입력이 가능하며 소요시간으로 랭킹을 매긴다\n"
				+ "     R버튼을 누르면 시작지점으로 돌아가서 처음부터 다시 미로를 시작할 수 있다\n"
				+ "              게임 클리어 후   점수등록 버튼을 누르면 점수를 등록할 수 있다\n"
				);
		textArea.setEditable(false);
		add(textArea,BorderLayout.CENTER);
		pack();
		setVisible(true);
		
	}
 
}

class MyFrame6 extends JFrame{
	
	private JButton b3;
	Toolkit tk = Toolkit.getDefaultToolkit();
	Image over_img=new ImageIcon("c.png").getImage();
	Image buffImage3;
	Graphics buffg3;
	public MyFrame6() {
		setSize(900,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("게임오버화면");
		JPanel panel=new JPanel();
		panel.setLayout(null);
		b3=new JButton("다시하기");
		b3.addActionListener(new MyListener());
		panel.add(b3);
		b3.setBounds(400,400,90,30);		
		b3.setBackground(new Color(102,102,102));
		b3.setBorderPainted(false);
		b3.setForeground(Color.white);
		add(panel);
		setVisible(true);
	}
	private class MyListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==b3) {
				final Frame fs=new MyFrame5() ;
				fs.setVisible(true);
				fs.addWindowListener(new WindowAdapter(){
					public void windowClosing(WindowEvent e) {
						fs.setVisible(false);
						fs.dispose();
					}
				});
				fs.setSize(900,700);
				fs.setLocation(0, 0);

			}
		}
	}
	public void paint(Graphics g){
		buffImage3 = createImage(900, 700); 
		buffg3 = buffImage3.getGraphics();
		update(g);
		}
		public void update(Graphics g) {
		Draw_Background(); //배경 이미지 그리기 메소드 실행
		g.drawImage(buffImage3, 0, 0, this); 
		}
		public void Draw_Background(){
			//배경 이미지를 그리는 부분입니다.

			buffg3.clearRect(0, 0, 900, 700);
			//화면 지우기 명령은 이제 여기서 실행합니다.
			buffg3.drawImage(over_img, 0, 0, this);
			
			}
			
}//게임오버화면

public class game extends JFrame implements KeyListener, Runnable, ActionListener{

	public static void main(String[] args){
		game f=new game();	

	}
	
	 Random ran2=new Random();
	 
	JLabel timerLabel = null;
    JLabel secondLabel = null;  
    JButton button;
    JPanel panel1,panel2;
  int f_width = 900;
int f_height = 700;
 int iw=20;
 int ih=20;
int x, y; // 캐릭터의 좌표 변수
 int k=15;//시간
 int c=1;//리셋버튼
 int d=0;//리셋버튼조절
 int a=5;
 int b=0;
 int ran=ran2.nextInt(3);
 int rank;

boolean KeyUp = false; //키보드 입력 처리를 위한 변수
boolean KeyDown = false;
boolean KeyLeft = false;
boolean KeyRight = false;
Thread th; // 스레드 생성
Toolkit tk = Toolkit.getDefaultToolkit(); 
ImageIcon first_Icon=new ImageIcon("a.png");
Image first_img=first_Icon.getImage();
Image second_img=first_img.getScaledInstance(iw,ih,java.awt.Image.SCALE_SMOOTH);
ImageIcon me_icon=new ImageIcon(second_img);
Image me_img=me_icon.getImage();
Image my_img=new ImageIcon(me_img).getImage();
Image buffImage;
Graphics buffg;
Image buffImage2;
Graphics buffg2;
 
public game(){
	init();
	game();
    this.setSize(900,700);
    this.setVisible(true);
	setTitle("미로런");
	setSize(900, 700);
	setVisible(true);
	Dimension screen = tk.getScreenSize();
	setResizable(false);
	setVisible(true);
	
	
	JPanel panel1=new JPanel();
	button=new JButton("점수등록");
	button.addActionListener(this);
	panel1.add(button);
	
	button.setBounds(10,200,100,50);
	
	add(panel1);
	setVisible(true);
	
	
	
	Container c = this.getContentPane();
    c.setLayout(new FlowLayout(FlowLayout.LEFT));
    timerLabel = new JLabel("0");
    timerLabel.setFont(new Font("Gothic",Font.ITALIC,70));
    c.add(timerLabel);
    this.setSize(900,700);
    this.setVisible(true);
    while(true)
    {
        timerLabel.setText(""+k);
       
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(b<=2)
        {
        	if(k>=1)
            {
        	k--; 
            }
        }
           }
    
   
}

public void actionPerformed(ActionEvent e) {
			if(b==3) {
		try {
			 FileWriter fw=new FileWriter("rank.txt",true);    //파일명과 같은 파일명이 존재할시 덧붙여쓸여부판단
		        BufferedWriter bf=new BufferedWriter(fw);
		        String r=String.valueOf(k);
		        bf.write(r); 
		        bf.newLine();
		        bf.flush();        //저장 후 텍스트필드의 값을 가져온 자원들을 해제한다.   
		         }catch(Exception n){
		             System.out.println(n);
		        }
			}
}

public void init(){
	x = 400; 
	y = 80;	
}

public void game(){
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
addKeyListener(this); //키보드 이벤트 실행
th = new Thread(this);  // 스레드 생성
th.start();  // 스레드 실행

}

public void run(){ // 스레드가 무한 루프될 부분
try{ // 예외옵션 설정으로 에러 방지
while(true){ // while 문으로 무한 루프 시키기
KeyProcess(); // 키보드 입력처리를 하여 x,y 갱신
repaint(); // 갱신된 x,y값으로 이미지 새로 그리기
Thread.sleep(20); // 20 milli sec 로 스레드 돌리기 
}
}catch (Exception e){}
}

public void Draw_Char(){ 
	
buffg2.clearRect(0, 0, f_width, f_height);
buffg2.drawImage(my_img, x, y, this);
}
private int[] xArray = {375,225,225,375,225,225,150,150,225,150,150,300,300,225,375,300,300,375};
private int[] yArray = {100,100,190,190,190,100,100,460,460,460,640,640,550,550,550,550,640,640};
private int[] x2Array= {450,600,600,525,525,450,450,450,525,525,600,600,525,525,450,450,375,375,225,375,375,450,450,300,300,225,300,300,525,525,600,600,450};
private int[] y2Array= {100,100,370,370,190,190,280,190,190,370,370,550,550,460,460,370,370,280,280,280,370,370,460,460,370,370,370,460,460,550,550,640,640};

public void paint(Graphics g){
g.drawImage(my_img,x,y,this);
while(c==1) {
    if((b==0))
	{
    	if(ran==0)
    	{
    		g.drawLine(150,100,375,100);
    		g.drawLine(450, 100, 600, 100);
    		g.drawLine(150, 100, 150, 640);
    		g.drawLine(150, 640, 375, 640);
    		g.drawLine(450, 640, 600, 640);
    		g.drawLine(600, 100, 600, 640);
    		g.drawLine(150, 280, 525, 280);
    		g.drawLine(225, 460, 600, 460);	
    	}
    	else if(ran==1)
    	{
    		g.drawLine(150,100,375,100);
    		g.drawLine(450, 100, 600, 100);
    		g.drawLine(150, 100, 150, 640);
    		g.drawLine(150, 640, 375, 640);
    		g.drawLine(450, 640, 600, 640);
    		g.drawLine(600, 100, 600, 640);
    		g.drawLine(150, 460, 525, 460);
    		g.drawLine(225, 280, 600, 280);
    	}
    	else if(ran==2)
    	{
    		g.drawLine(150,100,375,100);
    		g.drawLine(450, 100, 600, 100);
    		g.drawLine(150, 100, 150, 640);
    		g.drawLine(150, 640, 375, 640);
    		g.drawLine(450, 640, 600, 640);
    		g.drawLine(600, 100, 600, 640);
    		g.drawLine(150, 235, 525, 235);
    		g.drawLine(225, 370, 600, 370);
    		g.drawLine(150, 505, 525, 505);
    	}
	}
    if((c==1)&&(b==0))
    {
    	g.clearRect(0, 0, f_width, f_height);
    	x=400;
    	y=80;
    	
    }
    c=0;
}
    
   
    
	if((y>=650)&&(b==0))
	{
		g.clearRect(0, 0, f_width, f_height);
		b++;
		k+=10;
		x=400;
		y=80;
	}
	if((y>=650)&&(b==1))
	{
		g.clearRect(0, 0, f_width, f_height);
		b++;
		k+=15;
		x=400;
		y=80;
	}
	if((y>=650)&&(b==2))
	{
		int v;
		v=k;

	          
             
		final Frame fs=new rank2() ;
		fs.setVisible(true);
		fs.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				fs.setVisible(false);
				fs.dispose();
			}
		});
		fs.setSize(900,700);
		fs.setLocation(100, 100);
		b++;
		
		
	}
while(c==2) {
	if((b==1))
	{
		if(ran==0)
		{
			g.drawPolyline(xArray,yArray,18);	
			g.drawPolyline(x2Array, y2Array,33);
		}
		else if(ran==1)
		{
			g.drawLine(150,100,375,100);
    		g.drawLine(450, 100, 600, 100);
    		g.drawLine(150, 100, 150, 640);
    		g.drawLine(150, 640, 375, 640);
    		g.drawLine(450, 640, 600, 640);
    		g.drawLine(600, 100, 600, 640);
    		
    		g.drawLine(450, 100, 450, 190);
    		g.drawLine(300, 190, 450, 190);
    		g.drawLine(225, 190, 225, 460);
    		g.drawLine(150, 460, 225, 460);
    		g.drawLine(450, 280, 600, 280);
    		g.drawLine(300, 280, 375, 280);
    		g.drawLine(375, 280, 375, 370);
    		g.drawLine(375, 370, 600, 370);
    		g.drawLine(300,370,300,460);
    		g.drawLine(300, 460, 525, 460);
    		g.drawLine(525, 460, 525, 640);
    		g.drawLine(225, 550, 375, 550);
    		g.drawLine(375, 550, 375, 640);
    		g.drawLine(450, 550, 450, 640);
		}
		else if(ran==2)
		{
			g.drawLine(150,100,375,100);
    		g.drawLine(450, 100, 600, 100);
    		g.drawLine(150, 100, 150, 640);
    		g.drawLine(150, 640, 375, 640);
    		g.drawLine(450, 640, 600, 640);
    		g.drawLine(600, 100, 600, 640);
    		
    		g.drawLine(450, 190, 525, 190);
    		g.drawLine(525, 190, 525, 460);
    		g.drawLine(450, 370, 525, 370);
    		g.drawLine(375, 280, 450, 280);
    		g.drawLine(375, 460, 450, 460);
    		g.drawLine(450, 280, 450, 460);
    		g.drawLine(375, 190, 375, 280);
    		g.drawLine(225, 190, 375, 190);
    		g.drawLine(225, 190, 225, 460);
    		g.drawLine(150, 370, 225, 370);
    		g.drawLine(300, 280, 300, 540);
    		g.drawLine(300, 370, 375, 370);
    		g.drawLine(225, 540, 600, 540);
    		
		}
    }
	if((c==2)&&(b==1))
    {
    	g.clearRect(0, 0, f_width, f_height);
    	x=400;
    	y=80;
    	
    }
	c=0;
}

while(c==3) {
	if((b==2))
	{
		if((ran==0)||(ran==2)) {
		g.drawLine(150,100,375,100);
		g.drawLine(450, 100, 600, 100);
		g.drawLine(150, 100, 150, 640);
		g.drawLine(150, 640, 375, 640);
		g.drawLine(450, 640, 600, 640);
		g.drawLine(600, 100, 600, 640);
		g.drawLine(150,154,195,154);
		g.drawLine(285, 100 ,285 ,154 );
		g.drawLine(240,154 ,240 ,208 );
		g.drawLine(510,100 ,510 ,154 );
		g.drawLine(555,154 ,555 ,208 );
		g.drawLine(465,208 ,555 ,208 );
		g.drawLine(465,154 ,465 ,208 );
		g.drawLine(330,154 ,465 ,154 );
		g.drawLine(330,154 ,330 ,262 );
		g.drawLine(330,262 , 285,262 );
		g.drawLine(285,262 ,285 ,208 );
		g.drawLine(285,208 ,195 ,208 );
		g.drawLine(195,208 ,195 ,316 );
		g.drawLine(240,262 ,240 ,586 );
		g.drawLine(195,370 ,240 ,370 );
		g.drawLine(195, 370,195 ,532 );
		g.drawLine(150,586 ,195 ,586 );
		g.drawLine(240, 586,375 ,586 );
		g.drawLine(240, 532, 375,532 );
		g.drawLine(285,478 ,330 ,478 );
		g.drawLine(285,478 ,285 ,370 );
		g.drawLine(285,370 ,375 ,370 );
		g.drawLine(375,370 ,375 ,424 );
		g.drawLine(330,424 ,465 ,424 );
		g.drawLine(420,424 ,420 ,532 );
		g.drawLine(375,586,375,640);
		g.drawLine(420,586,465,586);
		g.drawLine(465,586,465,478);
		g.drawLine(465,478,600,478);
		g.drawLine(510,478,510,424);
		g.drawLine(555,424,555,370);
		g.drawLine(555,370,420,370);
		g.drawLine(465,424,465,316);
		g.drawLine(420,370,420,316);
		g.drawLine(420,316,240,316);
		g.drawLine(375,316,375,208);
		g.drawLine(420,154,420,262);
		g.drawLine(420,262,510,262);
		g.drawLine(510,262,510,316);
		g.drawLine(510,316,600,316);
		g.drawLine(555,262,555,154);
		g.drawLine(465,532,555,532);
		g.drawLine(555,532,555,586);
		g.drawLine(510,586,510,640);
	}
		else if(ran==1)
		{
			g.drawLine(150,100,375,100);
    		g.drawLine(450, 100, 600, 100);
    		g.drawLine(150, 100, 150, 640);
    		g.drawLine(150, 640, 375, 640);
    		g.drawLine(450, 640, 600, 640);
    		g.drawLine(600, 100, 600, 640);
    		
    		g.drawLine(240, 100, 240, 208);
    		g.drawLine(195, 154, 240, 154);
    		g.drawLine(285, 154, 330, 154);
    		g.drawLine(330, 154, 330, 208);
    		g.drawLine(330, 208, 375, 208);
    		g.drawLine(375, 100, 375, 208);
    		g.drawLine(375, 154, 465, 154);
    		g.drawLine(465, 154, 465, 316);
    		g.drawLine(420, 316, 465, 316);
    		g.drawLine(555, 100, 555, 208);
    		g.drawLine(510, 154, 510, 316);
    		g.drawLine(510, 316, 555, 316);
    		g.drawLine(240, 208, 285, 208);
    		g.drawLine(285, 208, 285, 370);
    		g.drawLine(240, 370, 285, 370);
    		g.drawLine(240, 370, 240, 424);
    		g.drawLine(285, 262, 420, 262);
    		g.drawLine(375, 262, 375, 316);
    		g.drawLine(420, 208, 420, 262);
    		
    		g.drawLine(150, 208, 195, 208);
    		g.drawLine(195, 208, 195, 262);
    		g.drawLine(195, 262, 240, 262);
    		g.drawLine(195, 316, 240, 316);
    		g.drawLine(195, 316, 195, 478);
    		g.drawLine(195, 478, 330, 478);
    		g.drawLine(330, 316, 330, 478);
    		g.drawLine(285, 424, 330, 424);
    		g.drawLine(240, 478, 240, 586);
    		g.drawLine(285, 478, 285, 532);
    		g.drawLine(240, 586, 375, 586);
    		g.drawLine(330, 586, 330, 532);
    		g.drawLine(330, 532, 420, 532);
    		g.drawLine(420, 532, 420, 586);
    		g.drawLine(375, 532, 375, 370);
    		g.drawLine(375, 370, 420, 370);
    		g.drawLine(420, 424, 510, 424);
    		g.drawLine(510, 424, 510, 478);
    		g.drawLine(465, 370, 465, 424);
    		g.drawLine(465, 370, 555, 370);
    		g.drawLine(555, 370, 555, 640);
    		g.drawLine(150, 532, 195, 532);
    		g.drawLine(195, 586, 195, 532);
    		
    		g.drawLine(375, 478, 465, 478);
    		g.drawLine(465, 478, 465, 532);
    		g.drawLine(465, 532, 510, 532);
    		g.drawLine(510, 532, 510, 586);
    		g.drawLine(510, 262, 600, 262);
		}
		
	}
	if((c==3)&&(b==2))
    {
    	g.clearRect(0, 0, f_width, f_height);
    	x=400;
    	y=80;
    	
    }
	c=0;
}
	if((b==0)&&(c==0))
	{
		if(ran==0)
		{g.drawLine(150,100,375,100);
		g.drawLine(450, 100, 600, 100);
		g.drawLine(150, 100, 150, 640);
		g.drawLine(150, 640, 375, 640);
		g.drawLine(450, 640, 600, 640);
		g.drawLine(600, 100, 600, 640);
		g.drawLine(150, 280, 525, 280);
		g.drawLine(225, 460, 600, 460);
		}
		else if(ran==1)
		{
			g.drawLine(150,100,375,100);
    		g.drawLine(450, 100, 600, 100);
    		g.drawLine(150, 100, 150, 640);
    		g.drawLine(150, 640, 375, 640);
    		g.drawLine(450, 640, 600, 640);
    		g.drawLine(600, 100, 600, 640);
    		g.drawLine(150, 460, 525, 460);
    		g.drawLine(225, 280, 600, 280);
		}
		else if(ran==2)
		{
			g.drawLine(150,100,375,100);
    		g.drawLine(450, 100, 600, 100);
    		g.drawLine(150, 100, 150, 640);
    		g.drawLine(150, 640, 375, 640);
    		g.drawLine(450, 640, 600, 640);
    		g.drawLine(600, 100, 600, 640);
    		g.drawLine(150, 235, 525, 235);
    		g.drawLine(225, 370, 600, 370);
    		g.drawLine(150, 505, 525, 505);
		}

	}
	if((b==1)&&(c==0))
	{
		if(ran==0)
		{g.drawPolyline(xArray,yArray,18);	
    	g.drawPolyline(x2Array, y2Array,33);
		}
		else if(ran==1)
		{
			g.drawLine(150,100,375,100);
    		g.drawLine(450, 100, 600, 100);
    		g.drawLine(150, 100, 150, 640);
    		g.drawLine(150, 640, 375, 640);
    		g.drawLine(450, 640, 600, 640);
    		g.drawLine(600, 100, 600, 640);
    		
    		g.drawLine(450, 100, 450, 190);
       		g.drawLine(300, 190, 450, 190);
    		g.drawLine(225, 190, 225, 460);
    		g.drawLine(150, 460, 225, 460);
    		g.drawLine(450, 280, 600, 280);
    		g.drawLine(300, 280, 375, 280);
    		g.drawLine(375, 280, 375, 370);
    		g.drawLine(375, 370, 600, 370);
    		g.drawLine(300, 370, 300, 460);
    		g.drawLine(300, 460, 525, 460);
    		g.drawLine(525, 460, 525, 640);
    		g.drawLine(225, 550, 375, 550);
    		g.drawLine(375, 550, 375, 640);
    		g.drawLine(450, 550, 450, 640);
		}
		else if(ran==2)
		{
			g.drawLine(150,100,375,100);
    		g.drawLine(450, 100, 600, 100);
    		g.drawLine(150, 100, 150, 640);
    		g.drawLine(150, 640, 375, 640);
    		g.drawLine(450, 640, 600, 640);
    		g.drawLine(600, 100, 600, 640);
    		
    		g.drawLine(450, 190, 525, 190);
    		g.drawLine(525, 190, 525, 460);
    		g.drawLine(450, 370, 525, 370);
    		g.drawLine(375, 280, 450, 280);
    		g.drawLine(375, 460, 450, 460);
    		g.drawLine(450, 280, 450, 460);
    		g.drawLine(375, 190, 375, 280);
    		g.drawLine(225, 190, 375, 190);
    		g.drawLine(225, 190, 225, 460);
    		g.drawLine(150, 370, 225, 370);
    		g.drawLine(300, 280, 300, 540);
    		g.drawLine(300, 370, 375, 370);
    		g.drawLine(225, 540, 600, 540);
    		
		}

	}
	if((b==2)&&(c==0))
	{
		if((ran==0)||(ran==2))
		{g.drawLine(150,100,375,100);
		g.drawLine(450, 100, 600, 100);
		g.drawLine(150, 100, 150, 640);
		g.drawLine(150, 640, 375, 640);
		g.drawLine(450, 640, 600, 640);
		g.drawLine(600, 100, 600, 640);
		g.drawLine(150,154,195,154);
		g.drawLine(285, 100 ,285 ,154 );
		g.drawLine(240,154 ,240 ,208 );
		g.drawLine(510,100 ,510 ,154 );
		g.drawLine(555,154 ,555 ,208 );
		g.drawLine(465,208 ,555 ,208 );
		g.drawLine(465,154 ,465 ,208 );
		g.drawLine(330,154 ,465 ,154 );
		g.drawLine(330,154 ,330 ,262 );
		g.drawLine(330,262 , 285,262 );
		g.drawLine(285,262 ,285 ,208 );
		g.drawLine(285,208 ,195 ,208 );
		g.drawLine(195,208 ,195 ,316 );
		g.drawLine(240,262 ,240 ,586 );
		g.drawLine(195,370 ,240 ,370 );
		g.drawLine(195, 370,195 ,532 );
		g.drawLine(150,586 ,195 ,586 );
		g.drawLine(240, 586,375 ,586 );
		g.drawLine(240, 532, 375,532 );
		g.drawLine(285,478 ,330 ,478 );
		g.drawLine(285,478 ,285 ,370 );
		g.drawLine(285,370 ,375 ,370 );
		g.drawLine(375,370 ,375 ,424 );
		g.drawLine(330,424 ,465 ,424 );
		g.drawLine(420,424 ,420 ,532 );
		g.drawLine(375,586,375,640);
		g.drawLine(420,586,465,586);
		g.drawLine(465,586,465,478);
		g.drawLine(465,478,600,478);
		g.drawLine(510,478,510,424);
		g.drawLine(555,424,555,370);
		g.drawLine(555,370,420,370);
		g.drawLine(465,424,465,316);
		g.drawLine(420,370,420,316);
		g.drawLine(420,316,240,316);
		g.drawLine(375,316,375,208);
		g.drawLine(420,154,420,262);
		g.drawLine(420,262,510,262);
		g.drawLine(510,262,510,316);
		g.drawLine(510,316,600,316);
		g.drawLine(555,262,555,154);
		g.drawLine(465,532,555,532);
		g.drawLine(555,532,555,586);
		g.drawLine(510,586,510,640);
		}
		else if(ran==1)
		{
			g.drawLine(150,100,375,100);
    		g.drawLine(450, 100, 600, 100);
    		g.drawLine(150, 100, 150, 640);
    		g.drawLine(150, 640, 375, 640);
    		g.drawLine(450, 640, 600, 640);
    		g.drawLine(600, 100, 600, 640);
    		
    		g.drawLine(240, 100, 240, 208);
    		g.drawLine(195, 154, 240, 154);
    		g.drawLine(285, 154, 330, 154);
    		g.drawLine(330, 154, 330, 208);
    		g.drawLine(330, 208, 375, 208);
    		g.drawLine(375, 100, 375, 208);
    		g.drawLine(375, 154, 465, 154);
    		g.drawLine(465, 154, 465, 316);
    		g.drawLine(420, 316, 465, 316);
    		g.drawLine(555, 100, 555, 208);
    		g.drawLine(510, 154, 510, 316);
    		g.drawLine(510, 316, 555, 316);
    		g.drawLine(240, 208, 285, 208);
    		g.drawLine(285, 208, 285, 370);
    		g.drawLine(240, 370, 285, 370);
    		g.drawLine(240, 370, 240, 424);
    		g.drawLine(285, 262, 420, 262);
    		g.drawLine(375, 262, 375, 316);
    		g.drawLine(420, 208, 420, 262);
    		
    		g.drawLine(150, 208, 195, 208);
    		g.drawLine(195, 208, 195, 262);
    		g.drawLine(195, 262, 240, 262);
    		g.drawLine(195, 316, 240, 316);
    		g.drawLine(195, 316, 195, 478);
    		g.drawLine(195, 478, 330, 478);
    		g.drawLine(330, 316, 330, 478);
    		g.drawLine(285, 424, 330, 424);
    		g.drawLine(240, 478, 240, 586);
    		g.drawLine(285, 478, 285, 532);
    		g.drawLine(240, 586, 375, 586);
    		g.drawLine(330, 586, 330, 532);
    		g.drawLine(330, 532, 420, 532);
    		g.drawLine(420, 532, 420, 586);
    		g.drawLine(375, 532, 375, 370);
    		g.drawLine(375, 370, 420, 370);
    		g.drawLine(420, 424, 510, 424);
    		g.drawLine(510, 424, 510, 478);
    		g.drawLine(465, 370, 465, 424);
    		g.drawLine(465, 370, 555, 370);
    		g.drawLine(555, 370, 555, 640);
    		g.drawLine(150, 532, 195, 532);
    		g.drawLine(195, 586, 195, 532);
    		
    		g.drawLine(375, 478, 465, 478);
    		g.drawLine(465, 478, 465, 532);
    		g.drawLine(465, 532, 510, 532);
    		g.drawLine(510, 532, 510, 586);
    		g.drawLine(510, 262, 600, 262);
		}

	}
	
	
	
	if(k==0)
	{
		
		final Frame fs=new MyFrame6() ;
		fs.setVisible(true);
		fs.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				fs.setVisible(false);
				fs.dispose();
			}
		});
		fs.setSize(900,700);
		fs.setLocation(0, 0);
		k=-1;
	}
	
	
		buffImage2=createImage(900,700);
		buffg2=buffImage2.getGraphics();
		buffg=buffImage.getGraphics();//줄 생기게 만드는 곳 
		update(g);
	
}


public void update(Graphics g){
	Draw_Char();
	g.drawImage(buffImage2,0,0,this);
	}
public void keyPressed(KeyEvent e){
// 키보드가 눌러졌을때 이벤트 처리하는 곳 
  
switch(e.getKeyCode()){
case KeyEvent.VK_UP :
KeyUp = true;
break;
case KeyEvent.VK_DOWN :
KeyDown = true;
break;
case KeyEvent.VK_LEFT :
KeyLeft = true;
break;
case KeyEvent.VK_RIGHT :
KeyRight = true;
break;
case KeyEvent.VK_R:
{
	if(b==0)
	   c=1;
    else if(b==1)
	   c=2;
    else if(b==2)
	   c=3;
}
}
}

public void keyReleased(KeyEvent e){
// 키보드가 눌러졌다가 때어졌을때 이벤트 처리하는 곳
  
switch(e.getKeyCode()){
case KeyEvent.VK_UP :
KeyUp = false;
break;
case KeyEvent.VK_DOWN :
KeyDown = false;
break;
case KeyEvent.VK_LEFT :
KeyLeft = false;
break;
case KeyEvent.VK_RIGHT :
KeyRight = false;
break;

	
}
}

public void keyTyped(KeyEvent e){}

public void KeyProcess(){
//실제로 캐릭터 움직임 실현을 위해
//위에서 받아들인 키값을 바탕으로
	
//키 입력시마다 5만큼의 이동을 시킨다.
	if((x<=150)&&(100<y)&&(y<=620))
	{
		if(KeyUp == true)  y -= a;
		if(KeyDown == true) y += a;
		if(KeyLeft == true)  x=x;
		if(KeyRight == true) x += a;
	}//좌측 세로선
	else if((x<=150)&&(100==y)&&(y<=640))
	{
		if(KeyUp == true)  y =y;
		if(KeyDown == true) y += a;
		if(KeyLeft == true)  x=x;
		if(KeyRight == true) x += a;
	}//꼭짓점
	else if((100<=x)&&(x<=375)&&(y<=100))
	{
		if(KeyUp == true)  y =y;
		if(KeyDown == true) y += a;
		if(KeyLeft == true)  x -= a;
		if(KeyRight == true) x += a;
	}//맨위 가로선 1
	else if((375<=x)&&(x<=450)&&(y<=80))
	{
		if(KeyUp == true)  y = y;
		if(KeyDown == true) y += a;
		if(KeyLeft == true)  x = x;
		if(KeyRight == true) x = x;
	}//시작부분
	else if((450<=x)&&(x<=580)&&(y<=100))
	{
		if(KeyUp == true)  y =y;
		if(KeyDown == true) y += a;
		if(KeyLeft == true)  x -= a;
		if(KeyRight == true) x += a;
	}//맨위 가로선 2
	else if((580<=x)&&(x<=600)&&(y==100))
	{
		if(KeyUp == true)  y =y;
		if(KeyDown == true) y += a;
		if(KeyLeft == true)  x -= a;
		if(KeyRight == true) x =  x;
	}//꼭짓점
	else if((580<=x)&&(100<=y)&&(y<620))
	{
		if(KeyUp == true)  y -= a;
		if(KeyDown == true) y += a;
		if(KeyLeft == true)  x -= a;
		if(KeyRight == true) x = x;
	}//우측 세로선
	else if((150<=x)&&(x<=375)&&(y>=620))
	{
		if(KeyUp == true)  y -= a;
		if(KeyDown == true) y = y;
		if(KeyLeft == true)  x -= a;
		if(KeyRight == true) x += a;
	}//아래 가로선 1
	else if((x<=150)&&(y>=620))
	{
		if(KeyUp == true)  y -= a;
		if(KeyDown == true) y = y;
		if(KeyLeft == true)  x = x;
		if(KeyRight == true) x += a;
	}//꼭짓점
	else if((450<=x)&&(x<580)&&(y>=620))
	{
		if(KeyUp == true)  y -= a;
		if(KeyDown == true) y = y;
		if(KeyLeft == true)  x -= a;
		if(KeyRight == true) x += a;
	}//아래 가로선 2
	else if((580<=x)&&(y>=620))
	{
		if(KeyUp == true)  y -= a;
		if(KeyDown == true) y = y;
		if(KeyLeft == true)  x -= a;
		if(KeyRight == true) x = x;
	}//꼭짓점
	else {
		if(KeyUp == true)  y -= a;
		if(KeyDown == true) y += a;
		if(KeyLeft == true)  x -= a;
		if(KeyRight == true) x += a;
	}
	if((b==0))
	{
		
		if(ran==0)
		{if((x<=525)&&(x>=150)&&(y<=280)&&(y>=265))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;

		}
		else if((x>=225)&&(x<=600)&&(y<=460)&&(y>=445))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		}
		else if(ran==1)
		{
			if((x<=525)&&(x>=150)&&(y<=460)&&(y>=445))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;

			}
			else if((x>=225)&&(x<=600)&&(y<=280)&&(y>=265))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
		}
		else if(ran==2)
		{
			if((x<=525)&&(x>=150)&&(y<=235)&&(y>=220))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x>=225)&&(x<=600)&&(y<=370)&&(y>=355))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=525)&&(x>=150)&&(y<=505)&&(y>=490))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
		}
	}
	if((b==1))
	{
		if(ran==0)
		{if((y<=190)&&(y>=100)&&(x<=225)&&(x>=210))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x>=225)&&(x<=375)&&(y<=190)&&(y>=175))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x>=450)&&(x<=525)&&(y<=190)&&(y>=175))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=280)&&(y>=190)&&(x<=450)&&(x>=435))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y>=190)&&(y<=370)&&(x<=525)&&(x>=510))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=600)&&(x>=525)&&(y<=370)&&(y>=355))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=375)&&(x>=225)&&(y<=280)&&(y>=265))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=370)&&(y>=280)&&(x<=375)&&(x>=360))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=450)&&(x>=375)&&(y<=370)&&(y>=355))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=460)&&(y>=370)&&(x<=450)&&(x>=435))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=525)&&(x>=300)&&(y<=460)&&(y>=435))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=460)&&(y>=370)&&(x<=300)&&(x>=285))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=300)&&(x>=225)&&(y<=370)&&(y>=355))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=550)&&(y>=460)&&(x<=525)&&(x>=510))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=600)&&(x>=525)&&(y<=550)&&(y>=535))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=640)&&(y>=550)&&(x<=300)&&(x>=285))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=375)&&(x>=225)&&(y<=550)&&(y>=535))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=225)&&(x>=150)&&(y<=460)&&(y>=445))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		}
		else if(ran==1)
		{
			if((y<=190)&&(y>=100)&&(x<=450)&&(x>=435))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=450)&&(x>=300)&&(y<=190)&&(y>=175))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=460)&&(y>=190)&&(x<=225)&&(x>=210))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=225)&&(x>=150)&&(y<=460)&&(y>=445))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=600)&&(x>=450)&&(y<=280)&&(y>=265))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=375)&&(x>=300)&&(y<=280)&&(y>=265))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=370)&&(y>=280)&&(x<=375)&&(x>=360))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=600)&&(x>=375)&&(y<=370)&&(y>=355))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=460)&&(y>=370)&&(x<=300)&&(x>=285))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=525)&&(x>=300)&&(y<=460)&&(y>=445))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=640)&&(y>=460)&&(x<=525)&&(x>=510))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=375)&&(x>=225)&&(y<=550)&&(y>=535))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=640)&&(y>=550)&&(x<=375)&&(x>=360))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=640)&&(y>=550)&&(x<=450)&&(x>=435))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
		}
		else if(ran==2)
		{
			if((x<=525)&&(x>=450)&&(y<=190)&&(y>=175))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=375)&&(x>=225)&&(y<=190)&&(y>=175))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=460)&&(y>=190)&&(x<=525)&&(x>=510))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=525)&&(x>=450)&&(y<=370)&&(y>=355))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=460)&&(y>=280)&&(x<=450)&&(x>=435))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=450)&&(x>=375)&&(y<=460)&&(y>=445))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=450)&&(x>=375)&&(y<=280)&&(y>=265))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=280)&&(y>=190)&&(x<=375)&&(x>=360))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=460)&&(y>=190)&&(x<=225)&&(x>=210))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=225)&&(x>=150)&&(y<=370)&&(y>=355))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=375)&&(x>=300)&&(y<=370)&&(y>=355))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=540)&&(y>=280)&&(x<=300)&&(x>=285))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=600)&&(x>=225)&&(y<=540)&&(y>=525))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
		}
	}
	if((b==2))
	{
		if((ran==0)||(ran==2))
		{if((x<=195)&&(x>=150)&&(y<=154)&&(y>=139))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=154)&&(y>=100)&&(x<=285)&&(x>=270))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y>=154)&&(y<=208)&&(x<=240)&&(x>=225))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x>=195)&&(x<=285)&&(y<=208)&&(y>=193))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y>=208)&&(y<=316)&&(x<=195)&&(x>=180))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=262)&&(y>=208)&&(x<=285)&&(x>=270))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x>=285)&&(x<=330)&&(y<=262)&&(y>=247))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=262)&&(y>=154)&&(x<=330)&&(x>=315))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=154)&&(y>=100)&&(x<=510)&&(x>=495))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x>=330)&&(x<=465)&&(y<=154)&&(y>=139))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y>=154)&&(y<=208)&&(x<=465)&&(x>=450))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x>=465)&&(x<=555)&&(y<=208)&&(y>=193))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y>=154)&&(y<=262)&&(x<=555)&&(x>=540))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=600)&&(x>=510)&&(y<=316)&&(y>=301))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=316)&&(y>=262)&&(x<=510)&&(x>=495))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=510)&&(x>=420)&&(y<=262)&&(y>=247))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y>=154)&&(y<=262)&&(x<=420)&&(x>=405))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=195)&&(x>=150)&&(y<=586)&&(y>=571))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=532)&&(y>=370)&&(x<=195)&&(x>=180))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x>=195)&&(x<=240)&&(y<=370)&&(y>=355))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=586)&&(y>=262)&&(x<=240)&&(x>=225))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x>=240)&&(x<=375)&&(y<=586)&&(y>=571))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=375)&&(x>=360)&&(y<=640)&&(y>=586))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x>=240)&&(x<=420)&&(y<=316)&&(y>=301))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y>=208)&&(y<=316)&&(x<=375)&&(x>=360))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=370)&&(y>=316)&&(x<=420)&&(x>=405))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y>=316)&&(y<=424)&&(x<=465)&&(x>=450))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x>=420)&&(x<=555)&&(y<=370)&&(y>=355))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=424)&&(y>=370)&&(x<=555)&&(x>=540))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=465)&&(x>=330)&&(y<=424)&&(y>=409))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=424)&&(y>=370)&&(x<=375)&&(x>=360))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=375)&&(x>=285)&&(y<=370)&&(y>=355))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=478)&&(y>=370)&&(x<=285)&&(x>=270))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=330)&&(x>=285)&&(y<=478)&&(y>=463))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=375)&&(x>=240)&&(y<=532)&&(y>=517))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=532)&&(y>=424)&&(x<=420)&&(x>=405))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=465)&&(x>=420)&&(y<=586)&&(y>=571))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=586)&&(y>=478)&&(x<=465)&&(x>=451))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=600)&&(x>=465)&&(y<=478)&&(y>=463))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=478)&&(y>=424)&&(x<=510)&&(x>=495))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((x<=555)&&(x>=465)&&(y<=532)&&(y>=517))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=586)&&(y>=532)&&(x<=555)&&(x>=540))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		else if((y<=640)&&(y>=586)&&(x<=510)&&(x>=495))
		{
			if(KeyUp == true)  y += a;
			if(KeyDown == true) y -= a;
			if(KeyLeft == true)  x += a;
			if(KeyRight == true) x -= a;
		}
		}
		else if(ran==1)
		{
			if((y<=208)&&(y>=100)&&(x<=240)&&(x>=225))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=240)&&(x>=195)&&(y<=154)&&(y>=139))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=285)&&(x>=240)&&(y<=208)&&(y>=193))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=370)&&(y>=208)&&(x<=285)&&(x>=270))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=285)&&(x>=240)&&(y<=370)&&(y>=355))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=424)&&(y>=370)&&(x<=240)&&(x>=225))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=420)&&(x>=285)&&(y<=262)&&(y>=247))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=316)&&(y>=262)&&(x<=375)&&(x>=360))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=262)&&(y>=208)&&(x<=420)&&(x>=405))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=330)&&(x>=285)&&(y<=154)&&(y>=139))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=208)&&(y>=154)&&(x<=330)&&(x>=315))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=375)&&(x>=330)&&(y<=208)&&(y>=193))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=208)&&(y>=100)&&(x<=375)&&(x>=360))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=465)&&(x>=375)&&(y<=154)&&(y>=139))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=316)&&(y>=154)&&(x<=465)&&(x>=450))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=465)&&(x>=420)&&(y<=316)&&(y>=301)) 
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=208)&&(y>=100)&&(x<=555)&&(x>=540))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=316)&&(y>=154)&&(x<=510)&&(x>=495))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=600)&&(x>=510)&&(y<=262)&&(y>=247))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=555)&&(x>=510)&&(y<=316)&&(y>=301))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=195)&&(x>=150)&&(y<=208)&&(y>=193))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=262)&&(y>=208)&&(x<=195)&&(x>=180))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=240)&&(x>=195)&&(y<=262)&&(y>=247))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;

			}
			else if((y<=640)&&(y>=370)&&(x<=555)&&(x>=540))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;

			}
			else if((x<=555)&&(x>=465)&&(y<=370)&&(y>=355))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=424)&&(y>=370)&&(x<=465)&&(x>=450))
			{
				if(KeyUp == true)  y += a;
			    if(KeyDown == true) y -= a;
			    if(KeyLeft == true)  x += a;
			    if(KeyRight == true) x -= a;
			 }
			else if((x<=510)&&(x>=420)&&(y<=424)&&(y>=409))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=478)&&(y>=424)&&(x<=510)&&(x>=495))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=195)&&(x>=150)&&(y<=532)&&(y>=517))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=586)&&(y>=532)&&(x<=195)&&(x>=180))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=240)&&(x>=195)&&(y<=316)&&(y>=301))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y>=370)&&(y<=532)&&(x<=375)&&(x>=360))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=420)&&(x>=375)&&(y<=370)&&(y>=355))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=465)&&(x>=375)&&(y<=478)&&(y>=463))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=532)&&(y>=478)&&(x<=465)&&(x>=450))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=510)&&(x>=465)&&(y<=532)&&(y>=517))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=586)&&(y>=532)&&(x<=510)&&(x>=495))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=420)&&(x>=330)&&(y<=532)&&(y>=517))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=586)&&(y>=532)&&(x<=420)&&(x>=405))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=586)&&(y>=532)&&(x<=330)&&(x>=315))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=375)&&(x>=240)&&(y<=586)&&(y>=571))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=586)&&(y>=478)&&(x<=240)&&(x>=225))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=330)&&(x>=195)&&(y<=478)&&(y>=463))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=532)&&(y>=478)&&(x<=285)&&(x>=270))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=478)&&(y>=316)&&(x<=330)&&(x>=315))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=330)&&(x>=285)&&(y<=424)&&(y>=409))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((y<=478)&&(y>=316)&&(x<=195)&&(x>=180))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
			else if((x<=240)&&(x>=195)&&(y<=316)&&(y>=301))
			{
				if(KeyUp == true)  y += a;
				if(KeyDown == true) y -= a;
				if(KeyLeft == true)  x += a;
				if(KeyRight == true) x -= a;
			}
		
		}
		
	}
}


}
