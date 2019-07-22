package my.garden.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import my.garden.dao.AdminDAO;
import my.garden.dto.AdminMemDTO;
import my.garden.dto.ShopListDTO;
import my.garden.service.AdminService;

@Controller
public class AdminController {

	@Autowired
	private AdminService dao;

	@RequestMapping("adminIndex")
	public String adminIndex(HttpServletRequest request) {
		List<AdminMemDTO> member;
		try {
			member = dao.serviceAllMembers();
			request.setAttribute("member", member);

			request.setAttribute("totalSale", dao.serviceTotalSale());
			request.setAttribute("totalCancel", dao.serviceTotalCancel());
			request.setAttribute("realSale", dao.serviceTotalSale() - dao.serviceTotalCancel());

			List<ShopListDTO> popular = dao.servicePopularProduct();
			request.setAttribute("popular", popular);
			int totalCount = dao.serviceTotalSaleCount();

			List<Long> count = new ArrayList<>();
			for(int i=0; i<5; i++) {
				count.add(Math.round(((double)popular.get(i).getS_p_count()/totalCount)*100));
			}
			request.setAttribute("count", count);
			request.setAttribute("depositWait", dao.serviceStatCheck("입금 대기"));
			request.setAttribute("shippingWait", dao.serviceStatCheck("결제 완료"));
			request.setAttribute("depositCheckList", dao.serviceOrderCheckList("입금 대기"));
			request.setAttribute("shippingCheckList", dao.serviceOrderCheckList("결제 완료"));
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "forAdmin/adminIndex";
	}

	@RequestMapping("adminStat")
	public String adminStat() {
		return "forAdmin/adminStat";
	}

	@RequestMapping("adminMembers")
	public String adminMember(HttpServletRequest request) {
		List<AdminMemDTO> member;
		try {
			member = dao.serviceAllMembers();
			request.setAttribute("member", member);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return "forAdmin/adminMembers";
	}
	
	@ResponseBody
	@RequestMapping("moneyStatChange")
	public Map<String, Object> moneyStatChange(String no) {
		Map<String, Object> map = new HashMap<>();
		try {
			 int result = dao.serviceUpdateOrder(no, "결제 완료");
			 map.put("result", result);
			 map.put("orderNo", no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("shippingStatChange")
	public Map<String, Object> shippingStatChange(String no) {
		Map<String, Object> map = new HashMap<>();
		try {
			 int result = dao.serviceUpdateOrder(no, "배송중");
			 map.put("result", result);
			 map.put("orderNo", no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
