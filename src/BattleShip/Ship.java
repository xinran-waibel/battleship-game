package BattleShip;

/**
*
* @author  Xinran (Duan) Waibel
* @version 1.0
* @since   03/04/2015
*
*/

public class Ship {

	public boolean sink;
	public ShipPart[] parts;
	
	public Ship(){
		this.parts=new ShipPart[3];
		this.parts[0]=new ShipPart();
		this.parts[1]=new ShipPart();
		this.parts[2]=new ShipPart();
	}
	
	public boolean isSunk(){
		if (this.parts[0].isHit()&&this.parts[1].isHit()&this.parts[2].isHit()){
			return true;
		}
		else{
			return false;
		}
	}
}
