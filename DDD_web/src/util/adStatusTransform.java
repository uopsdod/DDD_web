package util;

public class adStatusTransform {
	public static String status(String aStatus) {
		String str = null;
		switch (aStatus) {
		case "0":
			str = "未處理";
			break;
		case "1":
			str = "圖片未通過";
			break;
		case "2":
			str = "未繳費";
			break;
		case "3":
			str = "已繳費";
			break;
		case "4":
			str = "已上架";
			break;
		case "5":
			str = "已下架";
			break;

		}
		return str;
	}

}
