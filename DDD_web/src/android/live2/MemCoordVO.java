package android.live2;
import com.mem.model.MemVO;

public class MemCoordVO extends MemVO {
	private Double memLat;
	private Double memLng;
	private Double memDis;

	public Double getMemLat() {
		return memLat;
	}
	public void setMemLat(Double aMemLat) {
		this.memLat = aMemLat;
	}
	public Double getMemLng() {
		return memLng;
	}
	public void setMemLng(Double aMemLng) {
		this.memLng = aMemLng;
	}
	public Double getMemDis() {
		return memDis;
	}
	public void setMemDis(Double aMemDis) {
		this.memDis = aMemDis;
	}
	
	/* 為了HashSet 所改寫的hashCode方法 */
	public boolean equals(Object aObj){
		if(this == aObj){
			return true;
		}
		
		if((aObj == null)|| !(aObj instanceof MemCoordVO)){
			return false;
		} 
		
		MemCoordVO tmp = (MemCoordVO) aObj;
		return (this.getMemId().equals(tmp.getMemId())) && (this.getMemLat().equals(this.getMemLat())) && (this.getMemLng().equals(this.getMemLng()));
		
	}
	
	public int hashCode(){
		return this.getMemId().hashCode() + this.getMemLat().hashCode() + this.getMemLng().hashCode();
	}
	
}
