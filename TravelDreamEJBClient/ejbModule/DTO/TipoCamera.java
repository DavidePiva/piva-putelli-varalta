package DTO;

public enum TipoCamera {
	LOWCOST,SMART,DREAM;
	
	public String getString(TipoCamera t){

		if( t==TipoCamera.LOWCOST){
			return "lowcost";
		}else if( t==TipoCamera.SMART){
			return "smart";
		}else if( t==TipoCamera.DREAM){
			return "dream";
		}
		return null;
	}
	
	
}
