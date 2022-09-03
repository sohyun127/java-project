import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BlockPuzzle extends JFrame implements ActionListener,MouseMotionListener,MouseListener{
	private int pieces;
	private int totalPieces;
	private int[] pieceNumber;
	private BufferedImage img;
	private BufferedImage finishimg;
	private BufferedImage startimg;
	private JPanel level1,level2,level3;
	private JPanel levelBar=new JPanel();
	private MyPanel2 start;
	private JButton lv1button,lv2button,lv3button,button;
	private JLabel jLabel = new JLabel("제한시간: 5분");
	private boolean move=false;
	private boolean first=true;
	private boolean finish=true;
	private int[] dx;
	private int[] dy;
	private int id2;
	private int pieceWidth,pieceHeight;

	public BlockPuzzle() {
		setTitle("블록 퍼즐 게임");
		setSize(1280,710);
		setVisible(true);
		setstart();
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}
	
	void setlevel1()
	{   try {
		img = ImageIO.read(new File("level1.jpg"));
		finishimg = ImageIO.read(new File("level1F.jpg"));
	} catch (IOException e) {
		System.out.println(e.getMessage());
		System.exit(0);
	}
		pieces=3;
		information();
		level1 = new JPanel();
		level1.setSize(img.getWidth(null), img.getHeight(null));
		level1.setLayout(new BorderLayout());
		level1.add(new MyPanel(), BorderLayout.CENTER);
		divide();
		button = new JButton("RETRY OR COMPLETE");
		button.addActionListener(this);
		levelBar.add(button);
		levelBar.add(jLabel);
		level1.add(levelBar,BorderLayout.SOUTH);
		add(level1);
		count();
	}
	void setlevel2()
	{   try {
		img = ImageIO.read(new File("level2.jpg"));
		finishimg = ImageIO.read(new File("level2F.jpg"));
	} catch (IOException e) {
		System.out.println(e.getMessage());
		System.exit(0);
	}
		pieces=4;
		information();
		level2 = new JPanel();
		level2.setSize(img.getWidth(null), img.getHeight(null));
		level2.setLayout(new BorderLayout());
		level2.add(new MyPanel(), BorderLayout.CENTER);
		divide();
		button = new JButton("RETRY OR COMPLETE");
		button.addActionListener(this);
		levelBar.add(button);
		levelBar.add(jLabel);
		level2.add(levelBar, BorderLayout.SOUTH);
		add(level2);
		count();
	}
	void setlevel3()
	{   try {
		img = ImageIO.read(new File("level3.jpg"));
		finishimg = ImageIO.read(new File("level3F.jpg"));
	} catch (IOException e) {
		System.out.println(e.getMessage());
		System.exit(0);
	}
	
		pieces=5;
		information();
		level3 = new JPanel();
		level3.setSize(img.getWidth(null), img.getHeight(null));
		level3.setLayout(new BorderLayout());
		level3.add(new MyPanel(), BorderLayout.CENTER);
		divide();
		button = new JButton("RETRY OR COMPLETE");
		button.addActionListener(this);
		levelBar.add(button);
		levelBar.add(jLabel);
		level3.add(levelBar, BorderLayout.SOUTH);
		add(level3);
		count();
	}
	
	
	void setstart()
	{
		try {
			startimg = ImageIO.read(new File("start.jpg"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
			
		start =new MyPanel2();
		lv1button = new JButton("쉬움");
		lv2button = new JButton("보통");
		lv3button = new JButton("어려움");
		lv1button.addActionListener(this);
		lv2button.addActionListener(this);
		lv3button.addActionListener(this);
		start.setLayout(null);
		lv1button.setBounds(250,400,200,100);
		lv2button.setBounds(550,400,200,100);
		lv3button.setBounds(850,400,200,100);
		start.add(lv1button);
		start.add(lv2button);
		start.add(lv3button);
		add(start);
	}
	
	
	void divide() {
		Random rand = new Random();
		int ri;
		for (int i = 0; i < totalPieces; i++) {
			ri = rand.nextInt(totalPieces);
			int tmp = pieceNumber[i];
			pieceNumber[i] = pieceNumber[ri];
			pieceNumber[ri] = tmp;
		}
	}
	
	class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int id=0;
			g.drawImage(finishimg,640,0,this);
			for (int x = 0; x < pieces; x++) {
				int sx = x * pieceWidth; 
				for (int y = 0; y < pieces; y++) {
					int sy = y * pieceHeight;
					int number = pieceNumber[x * pieces + y];
					if(first)
					{
						dx[id]= (number / pieces) * pieceWidth;
						dy[id]= (number % pieces) * pieceHeight;
					}
					g.drawImage(img, dx[id], dy[id], dx[id]+ pieceWidth, 
							dy[id] + pieceHeight, sx, sy, sx + pieceWidth, 
							sy + pieceHeight, null);
					 id++;
				}
			}
		}
	}
	
	class MyPanel2 extends JPanel{
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(startimg,0,0,this);
	}
	}
	
	void information()
	{
		totalPieces = pieces * pieces;
		pieceNumber = new int[totalPieces];
		dx=new int [totalPieces];
		dy=new int [totalPieces];
		for (int i = 0; i < totalPieces; i++) {
			pieceNumber[i] = i;
		}
	    pieceWidth = img.getWidth(null) / pieces; //가로 길이 나누기
	    pieceHeight = img.getHeight(null) / pieces; //세로 길이 나누기
	
	}
	
	void count()
	{   Timer timer1=new Timer();
		TimerTask task1= new TimerTask(){
			public void run()
			{System.out.println("GAME OVER"); System.exit(0); } };
		timer1.schedule(task1, 3000); }

	public static void main(String[] args) {new BlockPuzzle();}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==lv1button)
		{start.setVisible(false); setlevel1();}
		else if(e.getSource()==lv2button)
		{start.setVisible(false); setlevel2();}
		else if(e.getSource()==lv3button)
		{start.setVisible(false); setlevel3();}
		else if(e.getSource()==button)
		{ int id=0;
			for (int x = 0; x < pieces; x++)
			{ int sx = x * pieceWidth+640; 
			   for (int y = 0; y < pieces; y++) {
					int sy = y * pieceHeight;
				   finish= (sx-10<dx[id]&&dx[id]<sx+pieceWidth+10&&sy-10<dy[id]&&dy[id]<sy+pieceHeight+10);
				   if(!finish)
				   { first=true;  repaint();  break;}
				   id++; }}
			if(finish)
			{System.exit(0);}		
		}}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(move)
		{  dx[id2]=e.getX(); dy[id2]=e.getY(); first=false; repaint();}}
	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e){}
	@Override
	public void mousePressed(MouseEvent e) {
		for(int x=0;x<totalPieces;x++)
		{move=(dx[x]<e.getX()&&e.getX()<dx[x]+pieceWidth&&dy[x]<e.getY()&&e.getY()<dy[x]+pieceHeight);
		if(move){ id2=x; break;}}}
	public void mouseReleased(MouseEvent e) {
		if(move) {move=false;}}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}
