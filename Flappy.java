import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Flappy extends JFrame implements Runnable,KeyListener,MouseListener,MouseMotionListener{
	double tastex=50,tastey=50;
	int score,Hscore;
	double s=0,ds=0,v=0,u=0,g=9.8;
	 Font font = new Font("Meiryo", Font.BOLD, 14);
Image dbImage,bird,Back,obstacle1,obstacle2,birdFly;
private Graphics dbg;
boolean fly=false,flyup=false,birdDown=false,getBar=false,playAgain=false,startback=true,startPlay=false,birdup=false,gameStarted=false;
int birdx=80,birdy=175,by;	
int up,sleep=1,rx=250,rax=380;
ImageIcon k,l,m,o1,o2,bup,clash;
int mousex,mousey,startbackx,startbacky,sizey,r1height,r3height,birdIm=1,birdIma=1,downSpeed=1,downCount=0;;
   Font fnt = new Font("Meiryo",Font.BOLD,20);
int birdWidth=20;
public Flappy() {

	 k = new ImageIcon(this.getClass().getResource("b1.png"));
	 l = new ImageIcon(this.getClass().getResource("b2.png"));
	 m = new ImageIcon(this.getClass().getResource("b3.png"));
	 bup = new ImageIcon(this.getClass().getResource("birdup.png"));
	 o1 = new ImageIcon(this.getClass().getResource("o1.png"));
	 o2 = new ImageIcon(this.getClass().getResource("o2.png"));
	 clash = new ImageIcon(this.getClass().getResource("clash.png"));
	ImageIcon j = new ImageIcon(this.getClass().getResource("back.png"));
	Back = j.getImage();
	obstacle1 = o1.getImage();
	obstacle2 = o2.getImage();
	setTitle("Flappy Bird Prototype");
	setSize(260,300);
	this.setLocation(500,200);
	setVisible(true);
	this.setBackground(Color.WHITE);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	addKeyListener(this);
	addMouseListener(this);
	addMouseMotionListener(this);
	}
	public void move(){
		if(fly==true){
			if(flyup==false){
				birdDown();
			}
		//	birdx+=1;
			rx--;
			rax--;
			if(rx==-10){
				r1height=(int)((Math.random()*130)+50);
				score++;
				rx=250;
		}
			if(rax==-10){
				
				r3height=(int)((Math.random()*130)+50);
				score++;
				rax=250;
		}
		}
	}
	public void birdDown() {
		u=v;
		v=u+(g*.01);
		ds=(((v*v)-(u*u))/(2*g));
		s=s+(ds*1.2);
		if(s<1){
			s=1;
		}
		birdy=birdy+(int)(s);
	}
	public void restart(){
		rx=250;rax=380;
	}
	public void paint(Graphics g){
		dbImage=createImage(getWidth(),getHeight());
		dbg=dbImage.getGraphics();
		paintComponent(dbg);
		g.drawImage(dbImage, 0, 0, this);
	} 
	public void paintComponent(Graphics g){
		Rectangle str = new Rectangle(110, 120, 120, 40);
		Rectangle mouseRect = new Rectangle(mousex,mousey,10,10);
		Rectangle r = new Rectangle(birdx,birdy,birdWidth,25);
		Rectangle r1 = new Rectangle(rx,0, 30,r1height);
		Rectangle r2 = new Rectangle(rx, r1.height+85, 25,300);
		Rectangle r3 = new Rectangle(rax,0, 30,r3height);
		Rectangle r4 = new Rectangle(rax, r3.height+85, 25,300);
		g.drawImage(Back, 0, 0, this);
		if(fly==false){
		g.setFont(font);
		g.drawString("Press 'SPACE' or 'Mouse Button'", 10, 120);
		g.drawString("      To Start Flying !!!", 10, 140);
		}
		
		g.drawImage(obstacle1, r1.x,-197+r1.height, this);
		g.drawImage(obstacle2, r2.x,r2.y, this);
		g.drawImage(obstacle1, r3.x,-197+r3.height, this);
		g.drawImage(obstacle2, r4.x,r4.y, this);
		g.drawImage(bird, birdx-13, birdy-1, this);
		
		//g.fillRect(r1.x, r1.y,r1.width,r1.height);
		//g.fillRect(r2.x, r2.y,r2.width,r2.height);
		g.setColor(Color.getHSBColor(100, 100, 100));
		if(r.intersects(r2)||r.intersects(r1)||r.intersects(r3)||r.intersects(r4)){
			g.setFont(font);
			g.setColor(Color.BLACK);
			g.drawString("OOUCHHH!!!!!!!!", 100, 100);
			g.drawString("PRESS R TO PLAY AGAIN", 100, 150 );
			birdDown=true;
			restart();
			score=0;
		}
		
		g.setFont(font);
		g.setColor(Color.ORANGE);
		g.drawString("Score : "+score, 60, 67);
		g.drawString("High Score : "+Hscore, 60, 53);
		//g.drawString("X = "+mousex+" \n Y = "+mousey, 30, 80);
		g.fillRect(startbackx, startbacky, 300, 300);
		g.setColor(Color.GREEN);
		if(startback)
		g.fillRect(110, 105, 120, 40);
		g.setColor(Color.getHSBColor(100, 100, 100));
		g.setFont(fnt);
		if(startback)
		g.drawString("PLAY !!!", 120, 130);
		if(mouseRect.intersects(str)){
			startPlay=true;
			startback=false;
		}
		repaint();
	}
	public static void main(String[] args) {
		Flappy fp = new Flappy();
		Thread t = new Thread(fp);
		t.start();
		
		}      
	
	public void run() {
		try{
			while(true){
			move();
			ImageProcess();
			if(flyup==true){
			flyup();
			 up++;
			 downSpeed=1;
			 downCount=0;
			if(up>18){
				flyup=false;
				up=0;
				birdWidth=20;
			}
			}
			 if(birdDown==true){
				Thread.sleep(1000);
				birdDown=false;
			}
			if(playAgain==true){
				birdy=175;
				playAgain=false;
				rx=250;
				s=0;ds=0;v=0;u=0;
			}
			if(startPlay){
				if(getBar==false){
				r1height=(int)((Math.random()*120)+25);
				r3height=(int)((Math.random()*120)+25);
				getBar=true;
				}
				startbacky--;
				if(startbacky>10){
					startPlay=false;
					}
				if(startbacky<-200)
					gameStarted=true;
			}
			checkHighscore();
			Thread.sleep(10);
			
			
		}
		
	}
	catch(Exception e) {
		
		System.out.println("Eroor");
	}
		
	}
	public void checkHighscore() {
		if(Hscore<score){
			Hscore=score;
		}
	}
	public void ImageProcess() {
		try {
			birdIma++;
			if(birdDown)
				bird=clash.getImage();
			else if(flyup==true){
				bird=bup.getImage();
				birdWidth=10;
			}
			else if(birdIma>0 && birdIma<10)
			bird = k.getImage();
			else if(birdIma>10 && birdIma<20)
				bird = l.getImage();
			else if(birdIma>20 && birdIma<30)
				bird=k.getImage();
			else if(birdIma>30&&birdIma<40)
				bird = m.getImage();
			else if(birdIma>40)
					birdIma=1;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int keycode=e.getKeyCode();
		if(keycode==e.VK_SPACE){
			flyup=true;
			fly=true;
			up=0;
			sleep=10;
			s=0;ds=0;v=0;u=0;
		}
		if(keycode==e.VK_R){
			playAgain=true;
			birdDown=false;
			score=0;
		}
		
	}
	public void flyup() {
		birdy-=2;
		
	}
	
	public void keyReleased(KeyEvent e) {
		int keycode=e.getKeyCode();
		if(keycode==e.VK_SPACE){
			
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
	}
	
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		mousex=e.getX();
		mousey=e.getY();
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
	
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(gameStarted==true){
			s=0;ds=0;v=0;u=0;
			flyup=true;
			fly=true;
			up=0;
		}
		
		}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
		}
    