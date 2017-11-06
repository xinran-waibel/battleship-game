package BattleShip;

/**
*
* @author  Xinran (Duan) Waibel
* @version 1.0
* @since   03/04/2015
*
*/

public class Mine {
	public int r;
	public int c;

	public boolean exploded;
	
	public Mine(){
		this.exploded=false;
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
	
	public void explode(){
		this.exploded=true;
	}
	public boolean isExploded(){
		return this.exploded;
	}
}
