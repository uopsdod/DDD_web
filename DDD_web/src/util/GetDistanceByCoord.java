package util;

public class GetDistanceByCoord {

	private static double rad(double aD) {
		return aD * Math.PI / 180.0;
	}
		
	/* 給它兩組經緯度 回傳公尺距離 */
	public static double GetDistance(double aLat1, double aLng1, double aLat2, double aLng2) {
		double EARTH_RADIUS = 6378137;
		double radLat1 = rad(aLat1);
		double radLat2 = rad(aLat2);
		double a = radLat1 - radLat2;
		double b = rad(aLng1) - rad(aLng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
		+ Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}	
}
