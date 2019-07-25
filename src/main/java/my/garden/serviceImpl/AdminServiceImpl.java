package my.garden.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import my.garden.dao.AdminDAO;
import my.garden.dto.AdminMemDTO;
import my.garden.dto.ShopListDTO;
import my.garden.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO dao;

	public List<AdminMemDTO> serviceAllMembers() throws Exception{
		return dao.allMembers();
	}

	public int serviceTotalSale() throws Exception{	
		return dao.totalSale();
	}

	public int serviceTotalCancel() throws Exception{
		return dao.totalCancel();
	}

	public List<ShopListDTO> servicePopularProduct() throws Exception{
		return dao.popularProduct();
	}

	public int serviceTotalSaleCount() throws Exception{
		return dao.totalSaleCount();
	}

	public int serviceStatCheck(String stat) throws Exception{
		return dao.statCheck(stat);
	}

	public List<ShopListDTO> serviceOrderCheckList(String stat) throws Exception{
		return dao.orderCheckList(stat);
	}

	public int serviceUpdateOrder(String orderNo, String stat) throws Exception{
		return dao.updateOrder(orderNo, stat);
	}
	
	/*public List<ShopListDTO> serviceOrderCheckUpdate(int orderNo, String changeStat, String listStat) throws Exception{
		 dao.updateOrder(orderNo, changeStat);
		 return dao.orderCheckList(listStat);
	}*/

}