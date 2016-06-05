//This snake game is designed by MiniJava Language
//The MiniJava Compiler is powered by DDrMsdos (minicpp)
//And this game source is used to prove MiniJava Language in Graphic Area.
//Entry Class
class Snake{
    public static void main(String[] a){
	System.out.println(new Window().Run());
    }
}

class Score{
	int total;
	public int PrintTotal(){
		Minijava.print('S');
		Minijava.print('c');
		Minijava.print('o');
		Minijava.print('r');
		Minijava.print('e');
		Minijava.print(':');
		System.out.println(total);
		return 0;
	}
	public int GameOver(){
		Minijava.print('G');
		Minijava.print('a');
		Minijava.print('m');
		Minijava.print('e');
		Minijava.print(' ');
		Minijava.print('o');
		Minijava.print('v');
		Minijava.print('e');
		Minijava.print('r');
		Minijava.print('.');
		Minijava.print(10);
		Minijava.print('F');
		Minijava.print('i');
		Minijava.print('n');
		Minijava.print('a');
		Minijava.print('l');
		Minijava.print(' ');
		this.PrintTotal();
		return 0;
	}
	public int AddScore(int score){
		total=total+score;
		return 0;
	}
}

class Tile{
	public int Create(int width,int height){
	//	buffer=new int[width*height];
		return 0;
	}
	public int Init(){
		return 0;
	}
	public int Draw(int x,int y){
		return 0;
	}
}

class WallTile extends Tile{
	int[] wallBuffer;
	int[] wallBuffer2;
	int[] currentBuffer;
	int	delta;
	public int Create(int width,int height){
		wallBuffer=new int[width*height];
		wallBuffer2=new int[width*height];
		return 0;
	}
	public int Init(){
		int i;
		int j;
		int colorGreen;
		int colorGray;
		int size;
		int base;
		this.Create(25,25);
		size=wallBuffer.length;
		Minijava.color(59,161,41,255,colorGreen);
		Minijava.color(173,110,13,255,colorGray);
		i=0;
		// give color to wallBuffer
		while(i<size)
		{
			if( (((i>0) && (i<25)) || ((i>600) && (i<625)))
				||( ((i%25)==0) || ((i%25)==24) ) )
				wallBuffer[i]=colorGray;
			else
				wallBuffer[i]=colorGreen;
			i=i+1;
		}
		i=0;
		base=(5*25)+5;
		while(i<15){
			j=0;
			while(j<15){
				wallBuffer[base+j]=colorGray;
				j=j+1;
			}
			base=base+25;
			i=i+1;
		}
		// give a different color to wallBuffer2
		i=0;
		while(i<size){
			if( (wallBuffer[i])==colorGray )
			{
				wallBuffer2[i]=colorGreen;
			}
			else
				wallBuffer2[i]=colorGray;
			i=i+1;
		}
		//set Current buffer
		currentBuffer=wallBuffer;
		return 0;
	}
	public int Draw(int x,int y){
		Minijava.bitblt(currentBuffer,25,25,x,y);		
		return 0;
	}
	public int DrawWall(){
		int x;
		int y;
		x=0;
		y=25;
		while(x<500)
		{
			this.Draw(x,0);
			this.Draw(x,475);
			x=x+25;
		}
		while(y<500)
		{
			this.Draw(0,y);
			this.Draw(475,y);
			y=y+25;
		}
		if(delta<200){
			delta=delta+1;
		}
		else{
			delta=0;
			if(currentBuffer==wallBuffer)
				currentBuffer=wallBuffer2;
			else
				currentBuffer=wallBuffer;
		}
		return 0;
	}
}

class SnakeBody{
	int x;
	int y;
	SnakeBody next;
	boolean end;
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int setX(int pX){
		x=pX;
		return 0;
	}
	public int setY(int pY){
		y=pY;
		return 0;
	}
	public int setEnd(boolean e){
		end=e;
		return 0;
	}
	public boolean getEnd(){
		return end;
	}
	public int setNext(SnakeBody n){
		next=n;
		return 0;
	}
	public SnakeBody getNext(){
		return next;
	}
}

class Credit{
	int[] buffer1;
	int[] buffer2;
	int[] currentBuffer;
	int color1;
	int color2;
	int type; //2 is normal ,3 is excellent
	int x;
	int y;
	int delta;
	boolean Enable;
	public boolean setEnable(boolean i)
	{
		Enable=i;
		return true;
	}
	public boolean getEnable()
	{
		return Enable;
	}
	public int setType(int t){
		type=t;
		return 0;
	}
	public int getType(){
		return type;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int setX(int sx)
	{
		x=sx;
		return 0;
	}
	public int setY(int sy)
	{
		y=sy;
		return 0;
	}
	public int Init(){
		int i;
		int j;
		buffer1=new int[25*25];
		buffer2=new int[25*25];
		Minijava.color(186,241,82,255,color1);
		Minijava.color(173,110,13,255,color2);
		j=buffer1.length;
		i=0;
		while(i<j)
		{
			buffer1[i]=color1;
			buffer2[i]=color2;
			i=i+1;
		}
		currentBuffer=buffer1;
		return 0;
	}
	public int Draw(int x,int y){
		if(Enable)
		{
			if(type==2)
				Minijava.bitblt(buffer1,25,25,x,y);
			else
			{
				Minijava.bitblt(currentBuffer,25,25,x,y);
				if(delta>10)
				{
					delta=0;
					if(currentBuffer==buffer1)
						currentBuffer=buffer2;
					else
						currentBuffer=buffer1;
				}
				else
					delta=delta+1;
				
			}
		}
		return 0;
	}
}

class SnakeList{
	SnakeBody head;
	SnakeBody tail;
	SnakeBody last;
	Credit	  credit;
	Score	  score;
	int currentDirection;
	int size;
	int delta;
	SnakeTile htitle;
	boolean fall;
	//get direction  0
	// 0 is left to right
	// 1 is right to left
	// 2 is down to up
	// 3 is up to down
	public boolean getFall()
	{
		return fall;
	}
	public int Init(int x,int y,int direct,SnakeTile snake){
		//fist we start
		score=new Score();
		fall=false;
		credit=new Credit();
		credit.Init();
		last=new SnakeBody();
		htitle=snake;
		head=new SnakeBody();
		size=size+1;
		head.setX(x);
		head.setY(y);
		snake.SetSnake(x,y,1);
		head.setEnd(true);
		currentDirection=direct;
		if(direct==0){
			this.AddNode(x-1,y);			
			this.AddNode(x-2,y);
			snake.SetSnake(x-1,y,1);
			snake.SetSnake(x-2,y,1);
		}
		else if(direct==1){
			this.AddNode(x+1,y);
			this.AddNode(x+2,y);
			snake.SetSnake(x+1,y,1);
			snake.SetSnake(x+2,y,1);
		}
		else if(direct==2){
			this.AddNode(x,y+1);
			this.AddNode(x,y+2);
			snake.SetSnake(x,y+1,1);
			snake.SetSnake(x,y+2,1);
		}
		else{
			this.AddNode(x,y-1);
			this.AddNode(x,y-2);
			snake.SetSnake(x,y-1,1);
			snake.SetSnake(x,y-2,1);
		}		
		this.ResetCredit();
		return 0;
	}
	public int ResetCredit(){
		//set credit here
		boolean i;
		int  rx;
		int  ry;
		int  type;
		i=true;
		while(i){
			Minijava.rand(rx);
			Minijava.rand(ry);
			rx=rx%18;
			ry=ry%18;
			if((htitle.GetSnake(rx,ry))==0)
				i=false;
		}
		credit.setX(rx);
		credit.setY(ry);
		Minijava.rand(type);
		if((type%2)==0)
		{
			type=2;
			credit.setType(2);
		}
		else
		{
			type=3;
		   credit.setType(3);
		}
		htitle.SetSnake(rx,ry,type);
		credit.setEnable(true);
		return type;
	}
	public int AddNode(int x,int y){
		SnakeBody temp;
		size=size+1;
		temp=new SnakeBody();
		temp.setEnd(false);
		temp.setX(x);
		temp.setY(y);
		temp.setNext(head);
		head=temp;		
		return 0;		
	}
	public int view(){
		boolean i;
		SnakeBody temp;
		temp=head;
		i=false;
		while(!i)
		{
//			System.out.println(1989);
		//	System.out.println(temp.getX());
		//	System.out.println(temp.getY());
			i=temp.getEnd();
			temp=temp.getNext();
		}
		return 0;
	}
	public int Draw()
	{
		boolean i;
		int j;
		int x;
		int y;
		SnakeBody temp;
		j=size;
		temp=head;
		i=false;
		if((j%2)!=0)
		{
			j=0;
		}
		else{
			j=1;
		}
		while(!i)
		{
			htitle.DrawItem(temp.getX(),temp.getY(),j);
			j=j+1;
			i=temp.getEnd();
			temp=temp.getNext();
		}
		x=credit.getX();
		y=credit.getY();
		credit.Draw(htitle.GetXPos(x,y),htitle.GetYPos(x,y));
		return 0;
	}
	public int AdjustNode(SnakeBody body){
		int x;
		int y;
		int i;
		if(currentDirection==0)
		{
			body.setX((body.getX())+1);
		}
		else if(currentDirection==1)
		{
			body.setX((body.getX())-1);
		}
		else if(currentDirection==2)
		{
			body.setY((body.getY())-1);
		}
		else
		{
			body.setY((body.getY())+1);
		}
		
		//test 
		x=body.getX();
		y=body.getY();
		if(x<0)
			body.setX(17);
		if(x>17)
			body.setX(0);
		if(y<0)
			body.setY(17);
		if(y>17)
			body.setY(0);
		i=htitle.GetSnake(body.getX(),body.getY());
		if(i==1)
		{
		//	System.out.println(88);
			score.GameOver();
			fall=true;
		}
		else if(i==2)
		{
	//		System.out.println(3);
			htitle.SetSnake(body.getX(),body.getY(),1);
			credit.setEnable(false);
			this.AddNode(last.getX(),last.getY());	
			htitle.SetSnake(last.getX(),last.getY(),1);
			score.AddScore(100);	
			score.PrintTotal();			
				
		}
		else if(i==3)
		{
	//		System.out.println(4);
			htitle.SetSnake(body.getX(),body.getY(),1);
			credit.setEnable(false);
			this.AddNode(last.getX(),last.getY());
			htitle.SetSnake(last.getX(),last.getY(),1);
			score.AddScore(500);	
			score.PrintTotal();
		}
		else
		{
			htitle.SetSnake(body.getX(),body.getY(),1);
		}
		if(!(credit.getEnable()))
		{
			this.ResetCredit();
		}
		return 0;
	}
	public int UpdateList(){
		SnakeBody temp;
		SnakeBody temp2;
		int tsize;
		int i;
		int j;
		//int mod;
		tsize=size;
		i=0;
		j=size-1;		
		temp=head;
		
		//mod=size/10;
		if(delta<50)
		{
			delta=delta+1;
		}
		else
		{
			delta=0;
			while(i<tsize)
			{
				// first clean buffer
				if(i==0)
				{
					htitle.SetSnake(temp.getX(),temp.getY(),0);
					last.setX(temp.getX());
					last.setY(temp.getY());
				}
				if(i==j)
				{
					//temp is snake head!
					this.AdjustNode(temp);
				}
				else
				{
					temp2=temp.getNext();
					temp.setX(temp2.getX());
					temp.setY(temp2.getY());
				}				
				temp=temp.getNext();
				i=i+1;
			}
		}
		
		return 0;
	}
	
	// 0 is left to right
	// 1 is right to left
	// 2 is down to up
	// 3 is up to down
	public int Go(int direction)
	{
		//right
		if(direction==0){
			if(currentDirection!=1)
				currentDirection=0;
		}
		//left
		else if(direction==1){
			if(currentDirection!=0)
				currentDirection=1;
		}
		//up
		else if(direction==2){
			if(currentDirection!=3)
				currentDirection=2;
		}
		//down
		else{
			if(currentDirection!=2)
				currentDirection=3;
		}
		return 0;
	}
}

class SnakeTile extends Tile{
	int[] xPosArray;
	int[] yPosArray;
	int[] snakeArray;// 0 is normal;1 snake; and 2 is credit,3 is excellent credit :)
	int[] snakeBuffer1;
	int[] snakeBuffer2;
	int bColorRed;
	int bColorOrg;
	int random;
	SnakeList list;	
	public int GetXPos(int x,int y){
		return xPosArray[((y*18)+x)];
	}
	public int GetYPos(int x,int y){
		return yPosArray[((y*18)+x)];
	}
	public int SetSnake(int x,int y,int i){
		snakeArray[((y*18)+x)]=i;
		return 0;
	}
	public int GetSnake(int x,int y){
		return (snakeArray[((y*18)+x)]);
	}
	public int DrawItem(int x,int y,int c){
		if((c%2)==0)
			Minijava.bitblt(snakeBuffer1,25,25,xPosArray[((y*18)+x)],yPosArray[((y*18)+x)]);
		else
			Minijava.bitblt(snakeBuffer2,25,25,xPosArray[((y*18)+x)],yPosArray[((y*18)+x)]);			
		return 0;
	}
	public int SnakeDraw(){
		list.Draw();
		return 0;
	}
	public int InitSnake(){
		int i;
		int j;
		Minijava.color(238,74,16,255,bColorRed);
		Minijava.color(239,156,2,255,bColorOrg);		
		snakeBuffer1=new int[25*25];
		snakeBuffer2=new int[25*25];
		snakeArray=new int[18*18];
		
		//set snake tile here
		i=0;
		j=snakeBuffer1.length;
		while(i<j){
			snakeBuffer1[i]=bColorRed;
			snakeBuffer2[i]=bColorOrg;
			i=i+1;
		}
		//rand snake position, at first snake is 3 size long
		//get x pos
		Minijava.rand(random);
		i=(random%8)+5;		
		//get y pos
		Minijava.rand(random);
		j=(random%8)+5;
		
		Minijava.rand(random);
		random=random%4;
	//	System.out.println(9999);
		list=new SnakeList();			
//		System.out.println(8888);
		list.Init(i,j,random,this);
		list.view();
		return 0;
	}
	public int Init(){
		int baseX;
		int baseY;
		int i;
		int j;
		xPosArray=new int[18*18];
		yPosArray=new int[18*18];
		baseX=25;
		baseY=25;
		i=0;
		j=0;
		//System.out.println(12345);
		//Init two array to get correct coordinate
		while(i<18){
			j=0;
			baseX=25;
			while( j<18 )
			{
				xPosArray[(i*18)+j]=baseX;
				baseX=baseX+25;
				yPosArray[(i*18)+j]=baseY;
				j=j+1;				
			}
			baseY=baseY+25;
			i=i+1;
		}
		//Init snake list
		this.InitSnake();
		return 0;
	}
	public int Update()
	{
		int key;
		key=0;
		/*
		VK_LEFT (25)
LEFT ARROW key

VK_UP (26)
UP ARROW key

VK_RIGHT (27)
RIGHT ARROW key

VK_DOWN (28)
DOWN ARROW key
		*/
		Minijava.getkey(39,key);
		if(key!=0)
		{
			//System.out.println(456);
			list.Go(0);
		}
		else{ 
			Minijava.getkey(37,key);
			if(key!=0)
				list.Go(1);
			else{
				Minijava.getkey(38,key);
				if(key!=0)
					list.Go(2);
				else{
					Minijava.getkey(40,key);
					if(key!=0)
						list.Go(3);
				}
				
			}
			
		}
		if(!(list.getFall()))
			list.UpdateList();			
		return 0;
	}
}

//The class to deal all  game function here
class GameMain{
	int ColorBlue; //background
	int[] block;
	int aColorGreen;  
	int aColorYellow; 
	int bColorGray;
	int bColorRed;
	int bColorOrg;
	WallTile wall;
	SnakeTile snake;
	public int Draw(){
		//System.out.println(5);
	//	Minijava.bitblt(block,50,50,0,0);
	//	Minijava.bitblt(block,50,50,450,0);
	//	Minijava.bitblt(block,50,50,0,450);
	//	Minijava.bitblt(block,50,50,450,450);
		wall.DrawWall();
		snake.SnakeDraw();
		Minijava.present(1,ColorBlue);
		return 0;
	}
	public int Init(){
		int leng;
		int i;
		Minijava.srand(0);// set random seed here
		Minijava.color(0,178,235,255,ColorBlue);
		Minijava.color(59,161,41,255,aColorGreen);
		Minijava.color(255,217,17,255,aColorYellow);
		Minijava.color(173,110,13,255,bColorGray);
		Minijava.color(238,74,16,255,bColorRed);
		Minijava.color(239,156,2,255,bColorOrg);
		block=new int[50*50];
		leng=block.length;
		i=0;
		while(i<leng){
			if( ((i%50)==0) || ((i%50)==49) )
				block[i]=aColorYellow;
			else if( ((i>=0) && (i<=49)) || ((i>=2450) && (i<=2499) ) )
				block[i]=bColorRed;
			else
				block[i]=bColorGray;
			i=i+1;
		}
	//	System.out.println(5);
		wall=new WallTile();
	//	System.out.println(6);
		wall.Init();
		snake=new SnakeTile();
		snake.Init();
		return 0;
	}
	public int Update(){
		//System.out.println(4);
		snake.Update();
		return 0;
	}
}
//Call back object
class Game extends MiniDraw{
	int init;
	GameMain game;
	//Control speed here
	public int Delay(int delta){
		//if(delta<1)
			Minijava.wait(1);
		return 0;
	}
	//Virtual function called by system everytime
	public final int MessageHandler(int[] array,int num){
		if(init == 0 ){
			init=1;
			//init here
			game=new GameMain();
			game.Init();
		}
		game.Update();
		game.Draw();
		this.Delay(array[0]);
		return 0;
	}
}

//Create Window and register object
class Window{
	//Register call back object
	public int Run(){
		int[] title;
		int i;
		//MiniJava Snake -Powered by DDrMsdos
		//init title
		title=new int[35];
		title[0]='M';title[1]='i';title[2]='n';title[3]='i';title[4]='J';title[5]='a';title[6]='v';
		title[7]='a';title[8]=' ';title[9]='S';title[10]='n';title[11]='a';title[12]='k';title[13]='e';
		title[14]=' ';title[15]='-';title[16]='P';title[17]='o';title[18]='w';title[19]='e';title[20]='r';
		title[21]='e';title[22]='d';title[23]=' ';title[24]='b';title[25]='y';title[26]=' ';title[27]='D';
		title[28]='D';title[29]='r';title[30]='M';title[31]='S';title[32]='d';title[33]='o';title[34]='s';
		Minijava.window(500,500,title,new Game());
		return 0;
	}
}



