package BattleShip;

/**
*
* @author  Xinran (Duan) Waibel
* @version 1.0
* @since   03/04/2015
*
*/

public class ShipPart {
	
	public int r;
	public int c;

	public boolean hit;
	
	public ShipPart(){
		this.hit=false;
	}
	
	public int getC(){
		return this.c;
	}
	
	public int getR(){
		return this.r;
	}
	
	public void setC(int i){
		this.c=i;
	}
	
	public void setR(int i){
		this.r=i;
	}
	
	public void hit(){
		this.hit=true;
	}
	
	public boolean isHit(){
		return this.hit;
	}
}
