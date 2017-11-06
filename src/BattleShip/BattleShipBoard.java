package BattleShip;

import java.util.Scanner;

/**
*
* @author  Xinran (Duan) Waibel
* @version 1.0
* @since   03/04/2015
*
*/

public class BattleShipBoard {

	public Object[][] board;
	public int r;
	public int c;
	public int nOfShips;
	public int nOfMines;
	public int turns;
	public int misses;
	public int duplicates;
	public int hits;
	public Ship ship1;
	public Ship ship2;
	public Mine mine1;
	public Mine mine2;
	public boolean end;
	
	public BattleShipBoard(int m,int n){
		this.board=new Object[m][n];
		this.r=m;
		this.c=n;		
		if (m*n<17){
			nOfShips=1;
			nOfMines=1;
			ship1=new Ship();
			ship2=null;
			mine1=new Mine();
			mine2=null;
		}
		else{
			nOfShips=2;
			nOfMines=2;
			ship1=new Ship();
			ship2=new Ship();
			mine1=new Mine();
			mine2=new Mine();
		}
	}
	
	public BattleShipBoard(){
		board=new Ship[3][3];
		nOfShips=1;
		nOfMines=1;
	}
	
	public int getNOS(){
		return this.nOfShips;
	}
	
	public int getNOM(){
		return this.nOfMines;
	}
	
	public int getRow(){
		return this.r;
	}
	
	public int getCol(){
		return this.c;
	}
	
	public void display(){
		System.out.println("There are "+this.getNOS()+" ship(s) and "+this.getNOM()+" mine(s) in the game board!");
		System.out.println("Hit: "+this.hits);
		System.out.println("Miss: "+this.misses);
		System.out.println("Redundant strike: "+this.duplicates);
		System.out.println("Total scores: "+this.turns);		
	}
	
	public void randomShip(Ship name){
		boolean ss=false;
		while(!ss){
		int randr=(int) (Math.floor(Math.random()*(this.r)));
		int randc=(int) (Math.floor(Math.random()*(this.c)));
		int rands=(int)(Math.floor(Math.random()*2));//0 means horizontal, 1 means vertical
		if (rands==0){
			int r1=randr;
			int r2=randr;
			int r3=randr;
			int c1=randc-1;
			int c2=randc;
			int c3=randc+1;
			if (r1<r&&r1>=0&&r2<r&&r2>=0&&r3<r&&r3>=0&&c1>=0&&c1<c&&c2>=0&&c2<c&&c3>=0&&c3<c){
			if (this.board[r1][c1]==null&&this.board[r2][c2]==null&&this.board[r3][c3]==null){
				this.board[r1][c1]=name.parts[0];
				name.parts[0].setC(c1);
				name.parts[0].setR(r1);
				this.board[r2][c2]=name.parts[1];
				name.parts[1].setC(c2);
				name.parts[1].setR(r2);
				this.board[r3][c3]=name.parts[2];
				name.parts[2].setC(c3);
				name.parts[2].setR(r3);
				ss= true;
			}
			}
		}
		else{
			int r1=randr-1;
			int r2=randr;
			int r3=randr+1;
			int c1=randc;
			int c2=randc;
			int c3=randc;
			if (r1<r&&r1>=0&&r2<r&&r2>=0&&r3<r&&r3>=0&&c1>=0&&c1<c&&c2>=0&&c2<c&&c3>=0&&c3<c){
			if (this.board[r1][c1]==null&&this.board[r2][c2]==null&&this.board[r3][c3]==null){
				this.board[r1][c1]=name.parts[0];
				name.parts[0].setC(c1);
				name.parts[0].setR(r1);
				this.board[r2][c2]=name.parts[1];
				name.parts[1].setC(c2);
				name.parts[1].setR(r2);
				this.board[r3][c3]=name.parts[2];
				name.parts[2].setC(c3);
				name.parts[2].setR(r3);
				ss= true;
			}
			}
		}
		}
	}
	
	public void update(){
		this.turns++;
		if (this.mine2!=null){
		if (this.ship1.isSunk()&&this.ship2.isSunk()){
			this.nOfShips=0;
			this.end=true;
		}
		if (this.ship1.isSunk()&&!this.ship2.isSunk()){
			this.nOfShips=1;
			this.end=false;
		}
		if (!this.ship1.isSunk()&&this.ship2.isSunk()){
			this.nOfShips=1;
			this.end=false;
		}
		if (this.mine1.exploded&&this.mine2.exploded){
			this.nOfMines=0;
		}
		
		if (this.mine1.exploded&&!this.mine2.exploded){
			this.nOfMines=1;
		}
		if (!this.mine1.exploded&&this.mine2.exploded){
			this.nOfMines=1;
		}
		}//two
		else{
		if (this.mine1.exploded&&this.mine2==null){
			this.nOfMines=0;
		}
		if (this.ship1.isSunk()&&this.ship2==null){
			this.nOfShips=0;
			this.end=true;
		}
		}
		
	}
	
	public void duplicate(){
		this.turns++;
		this.duplicates++;
	}
	
	public void touchMine(){
		this.turns++;
	}
	
	public void randomMine(Mine name){
		boolean ms=false;
		while(!ms){
		int m=(int) (Math.floor(Math.random()*(this.r)));
		int n=(int) (Math.floor(Math.random()*(this.r)));
		if(this.board[m][n]==null){
			this.board[m][n]=name;
			name.setR(m);
			name.setC(n);
			ms= true;
		}
		}
	}
	
	public static void main(String[] args){
		boolean toContinue=true;
	    while (toContinue){
		System.out.println("New Game Start!");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the size of game board between 3*3 and 10*10 (if your enter nothing or out of range, the game board will be 3*3)");
		System.out.println("Enter number rows first:");
		int myr = sc.nextInt();
	    System.out.println("Then enter number collumns:");
	    int myc =sc.nextInt();
	    if (myr>10 || myr<3 || myc>10 || myc<3){
	    	myr=3;
	    	myc=3;
	    }
	    System.out.println("Nice! Now your have a "+myr+"*"+myc+" game board!");
	    BattleShipBoard myBoard=new BattleShipBoard(myr,myc);
	    myBoard.randomShip(myBoard.ship1);
	    myBoard.randomMine(myBoard.mine1);
	    if(myBoard.nOfShips==2){
	    	 myBoard.randomShip(myBoard.ship2);
	    	 myBoard.randomMine(myBoard.mine2);
	    }
		System.out.println("Do you want to turn on test mode? true or false");
		boolean test=sc.nextBoolean();
		if (test){
			
		int s1p1r=myBoard.ship1.parts[0].getR()+1;
		int s1p1c=myBoard.ship1.parts[0].getC()+1;
		
		int s1p2r=myBoard.ship1.parts[1].getR()+1;
		int s1p2c=myBoard.ship1.parts[1].getC()+1;
		
		int s1p3r=myBoard.ship1.parts[2].getR()+1;
		int s1p3c=myBoard.ship1.parts[2].getC()+1;
		
	    System.out.println("The first ship is at ("+s1p1r+","+s1p1c+"),("+s1p2r+","+s1p2c+"),("+s1p3r+","+s1p3c+")");//for test position
	    
	    if(myBoard.nOfShips==2){
	    	
		int s2p1r=myBoard.ship2.parts[0].getR()+1;
		int s2p1c=myBoard.ship2.parts[0].getC()+1;
			
		int s2p2r=myBoard.ship2.parts[1].getR()+1;
		int s2p2c=myBoard.ship2.parts[1].getC()+1;
			
		int s2p3r=myBoard.ship2.parts[2].getR()+1;
		int s2p3c=myBoard.ship2.parts[2].getC()+1;
		
		System.out.println("The second ship is at ("+s2p1r+","+s2p1c+"),("+s2p2r+","+s2p2c+"),("+s2p3r+","+s2p3c+")");//for test position
	    
	    }
	    
	    int m1r=myBoard.mine1.getR()+1;
	    int m1c=myBoard.mine1.getC()+1;
	    
	    System.out.println("The first mine is at ("+m1r+","+m1c+")");
	    
	    if(myBoard.nOfShips==2){
	    	
	    int m2r=myBoard.mine2.getR()+1;
		int m2c=myBoard.mine2.getC()+1;
		
	    System.out.println("The second mine is at ("+m2r+","+m2c+")");
	    
	    }
		}
	    String[] repeat=new String[100];
	    int current=0;
	    	while (!myBoard.end){
	    		myBoard.display();
		    	boolean rightp=false;
		    	int targetr=0;
		    	int targetc=0;
		    	while (!rightp){
		    	System.out.println("Where you want to hit? Enter the row:(between 1 and "+myBoard.getRow()+")");
		    	targetr =sc.nextInt();
		    	System.out.println("Enter the column:(between 1 and "+myBoard.getCol()+")");
		    	targetc =sc.nextInt();
		    	if (targetr<=myBoard.getRow()&&targetr>0&&targetc<=myBoard.getCol()&&targetc>0){
		    		rightp=true;
		    	}
		    	else{
		    		System.out.println("Invalid positon! Enter again!");
		    		rightp=false;
		    	}
		    }//test valid entry
		    	targetr--;
		    	targetc--;
		    	String position=targetr+""+targetc;
		    	boolean contain=false;
		    	for(int i=0;i<100;i++){
		    		if(repeat[i]!=null&&repeat[i].equals(position)){
		    			contain=true;
		    		}
		    	}
		    	if (contain==true){
		    		myBoard.duplicate();
		    		System.out.println("Reduntant strike!!!");
		    	}//repeat
		    	else{
		    		repeat[current]=position;
		    		current++;
		    		if (myBoard.board[targetr][targetc] instanceof ShipPart){
		    			((ShipPart)myBoard.board[targetr][targetc]).hit();
		    			myBoard.hits++;
		    			System.out.println("You hit it!");
		    		}//is part
		    		else{
		    			if (myBoard.board[targetr][targetc] instanceof Mine){
		    				((Mine)myBoard.board[targetr][targetc]).explode();
		    				myBoard.touchMine();
		    				System.out.println("A mine explode!!!");
		    			}//is mine
		    			else{
		    				myBoard.misses++;
		    				boolean print=false;
		    				if(targetr+1<myBoard.getRow()&&targetr+1>=0&&!print){
		    					if (myBoard.board[targetr+1][targetc] instanceof ShipPart){
		    						System.out.println("A Miss, but Very Close");
		    						print=true;
		    					}
		    				}//r+1
		    				
		    					if (targetr-1<myBoard.getRow()&&targetr-1>=0&&!print){
		    						if (myBoard.board[targetr-1][targetc] instanceof ShipPart){
			    						System.out.println("A Miss, but Very Close");
			    						print=true;
			    					}
		    					}//r-1
		    					
		    						if (targetc+1<myBoard.getCol()&&targetc+1>=0&&!print){
			    						if (myBoard.board[targetr][targetc+1] instanceof ShipPart){
				    						System.out.println("A Miss, but Very Close");
				    						print=true;
				    					}
		    					}//c+1
		    						
		    							if (targetc-1<myBoard.getCol()&&targetc-1>=0&&!print){
				    						if (myBoard.board[targetr][targetc-1] instanceof ShipPart){
					    						System.out.println("A Miss, but Very Close");
					    						print=true;
					    					}
		    							}//c-1
				    					
				    						if(targetr+2<myBoard.getRow()&&targetr+2>=0&&!print){
		    			    					if (myBoard.board[targetr+2][targetc] instanceof ShipPart){
		    			    						System.out.println("A Miss, but Close");
		    			    						print=true;
		    			    					}
		    			    				}//r+2
				    						
				    							if (targetr-2<myBoard.getRow()&&targetr+2>=0&&!print){
		    			    						if (myBoard.board[targetr-2][targetc] instanceof ShipPart){
		    				    						System.out.println("A Miss, but Close");
		    				    						print=true;
		    				    					}
		    			    					}//r-2
				    							
				    								if (targetc+2<myBoard.getCol()&&targetc+2>=0&&!print){
		    				    						if (myBoard.board[targetr][targetc+2] instanceof ShipPart){
		    					    						System.out.println("A Miss, but Close");
		    					    						print=true;
		    					    					}
		    			    					}//c+2
				    								
				    									if (targetc-2<myBoard.getCol()&&targetc-2>=0&&!print){
		    					    						if (myBoard.board[targetr][targetc-2] instanceof ShipPart){
		    						    						System.out.println("A Miss, but Close");
		    						    						print=true;
		    						    					}
		    			    						    }//c-2
				    									if(!print){
				    										System.out.println("Oh, you miss!");
				    									}
		    			}//miss
		    		}
		    	}//not duplicate
		    	myBoard.update();
	    	}//enter new value
	    	System.out.println("Game end! Game Summary:");
	    	System.out.println("Hit: "+myBoard.hits);
			System.out.println("Miss: "+myBoard.misses);
			System.out.println("Redundant strike: "+myBoard.duplicates);
			System.out.println("Total scores: "+myBoard.turns);
	    	System.out.println("Do you want to continue? true or false");
		    toContinue=sc.nextBoolean();
	    }//continue game
	    System.out.println("Game End!");
	}//main
}
