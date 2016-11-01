package com.ord.model;
import java.util.*;

import com.hotelrep.model.HotelRepVO;

public interface OrdDAO_interface {
	public int insert(OrdVO aOrdVO);
	public int update(OrdVO aOrdVO);
	public int delete(String aOrdId);
	/* 依訂單編號 */
	public OrdVO findByPrimaryKey(String aOrdId);
	public List<OrdVO> getAll();
	/* 列出該一般會員的所有訂單 */
	public List<OrdVO> getAllByOrdMemId(String aOrdMemId);
	/* 列出該廠商會員的所有訂單 */
	public List<OrdVO> getAllByOrdHotelId(String aOrdHotelId);
}
