package com.ord.modelJDBC;
import java.util.*;

import com.hotelrep.model.HotelRepVO;

public interface OrdDAO_interface {
	/* (一般會員)新增一筆訂單 */
	public int insert(OrdVO aOrdVO);
	/* (一般會員)新增評論及星星數 & (系統)修改訂單狀態 */
	public int update(OrdVO aOrdVO);
	/* (練習用)刪除 */
	public int delete(String aOrdId);
	/* (管理員)依訂單編號查詢 */
	public OrdVO findByPrimaryKey(String aOrdId);
	/* (管理員)查詢所有訂單內容 評論及星星數 */
	public List<OrdVO> getAll();
	/* (一般會員)列出該一般會員的所有訂單 QRCode 驗證碼 */
	public List<OrdVO> getAllByOrdMemId(String aOrdMemId);
	/* (廠商會員)列出該廠商會員的所有訂單 */
	public List<OrdVO> getAllByOrdHotelId(String aOrdHotelId);
	
}
