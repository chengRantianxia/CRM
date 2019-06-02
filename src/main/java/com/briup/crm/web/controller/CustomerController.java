package com.briup.crm.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jfree.data.category.CategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.briup.crm.common.bean.CstActivity;
import com.briup.crm.common.bean.CstCustomer;
import com.briup.crm.common.bean.CstLinkman;
import com.briup.crm.common.bean.CstLog;
import com.briup.crm.common.bean.CstService;
import com.briup.crm.common.bean.Product;
import com.briup.crm.common.bean.SalChance;
import com.briup.crm.common.bean.SalPlan;
import com.briup.crm.common.bean.Storage;
import com.briup.crm.common.bean.SysRole;
import com.briup.crm.common.bean.SysUser;
import com.briup.crm.common.exception.CrmCommonException;
import com.briup.crm.common.util.JFreeChartUtil;
import com.briup.crm.service.interfaces.IBasicDataService;
import com.briup.crm.service.interfaces.ICstService;
import com.briup.crm.service.interfaces.ICustomerService;
import com.briup.crm.service.interfaces.ILogService;
import com.briup.crm.service.interfaces.IReportFormsService;
import com.briup.crm.service.interfaces.ISalChanceService;
import com.briup.crm.service.interfaces.ISysUserService;
import com.github.pagehelper.PageInfo;
	
@Controller
public class CustomerController {
	@Autowired
	private ISysUserService iSysUserService;
	@Autowired
	private ILogService iLogService;
	@Autowired
	private ICustomerService ICustomerService;
	@Autowired
	private ISalChanceService ISalChanceService;
	@Autowired
	private IBasicDataService iBasicDataService;
	@Autowired
	private IReportFormsService iReportFormsService;
	@Autowired
	private ICstService iCstService;
	
	
	@RequestMapping("{plaId}/sale/dev_detail")
	public String showDev_detail(@PathVariable Integer plaId,HttpSession session){
		try {
			SalPlan plan = ISalChanceService.findSalPlan(plaId);
			session.setAttribute("plan", plan);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "sale/dev_detail";
	}
	@RequestMapping("/sale/dev_execute")
	public String showDev_execute(){
		return "sale/dev_execute";
	}
	@RequestMapping("/sale/dev_plan")
	public String showDev_plan(HttpSession session){
		try {
			List<SalPlan> list = ISalChanceService.findAllSalPlan();
			session.setAttribute("plaId",list.size()+1);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "sale/dev_plan";
	}
	//客户开发计划查找
	@RequestMapping("/sale/dev/find")
	public String showDevFind(HttpSession session,SalPlan salPlan){
		try {
			System.out.println(salPlan);
			PageInfo<SalPlan> list = ISalChanceService.findSalPlansMore(1,5,salPlan);
			session.setAttribute("planByPage", list);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "sale/dev";
	}
	//新建客户开发计划    制定计划
	@RequestMapping("/sale/dev_plan/add")
	public String showDev_plan_add(SalPlan salPlan,HttpSession session){
		
		try {
			List<SalPlan> list = ISalChanceService.findAllSalPlan();
			salPlan.setPlaId((long) (list.size()+1));
			salPlan.setPlaChcId((long) 2);
			ISalChanceService.savePlan(salPlan);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/1/sale/dev";
	}
	//销售机会管理的删除
	@RequestMapping("/{chcId}/sale/list/remove")
	public String removeChance(@PathVariable Integer chcId,HttpSession session){
		try {
			ISalChanceService.deleteSalChance(chcId);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/1/sale/list";
	}
	//销售机会管理的编辑
	@RequestMapping("/{chcId}/sale/edit")
	public String showEdit(@PathVariable Integer chcId,HttpSession session){
		try {
			SalChance salChance = ISalChanceService.findOneSalChance(chcId);
			session.setAttribute("salChance", salChance);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "sale/edit";
	}
	//销售机会管理的编辑的保存
	@RequestMapping("/sale/edit/save")
	public String showEdit_save(HttpSession session,SalChance salChance){
		try {
			SalChance Chance = (SalChance) session.getAttribute("salChance");
			salChance.setChcDueTo(Chance.getChcDueTo());
			salChance.setChcStatus(Chance.getChcStatus());
			System.out.println(salChance);
			ISalChanceService.updateChance(salChance);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/1/sale/list";
	}
	//销售机会管理  指派
	@RequestMapping("/{chcId}/sale/dispatch")
	public String showDIspatch(@PathVariable Integer chcId,HttpSession session){
		try {
			SalChance chance = ISalChanceService.findOneSalChance(chcId);
			session.setAttribute("chance", chance);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "sale/dispatch";
	}
	//销售机会管理  指派的保存
	@RequestMapping("/sale/dispatch/save")
	public String showDIspatch_save(SalChance sc,HttpSession session){
		try {
			SalChance chance = (SalChance) session.getAttribute("chance");
			chance.setChcDueTo(sc.getChcDueTo());
			ISalChanceService.updateChance(chance);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/1/sale/list";
	}
	@RequestMapping("/{curpage}/sale/dev")
	public String showDev(@PathVariable Integer curpage,HttpSession session){
		PageInfo<SalPlan> planByPage;
		try {
			planByPage = ISalChanceService.findSalPlanByPage(curpage, 5);
			session.setAttribute("planByPage", planByPage);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "sale/dev";
	}
	@RequestMapping("/{curpage}/sale/list")
	public String showsaleList(@PathVariable Integer curpage,HttpSession session){
		try {
			PageInfo<SalChance> 	findSalChanceByPage = ISalChanceService.findSalChanceByPage(curpage, 5);
			session.setAttribute("findSalChanceByPage", findSalChanceByPage);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "sale/list";
	}
	//服务管理的服务创建
	@RequestMapping("/cust_service/add")
	public String showAdd(HttpSession session){
		try {
			List<CstCustomer> findAllCustomer = ICustomerService.findAllCustomer();
			session.setAttribute("findAllCustomer", findAllCustomer);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_service/add";
	}
	//服务管理的服务创建的保存
	@RequestMapping("/cust_service/add_save")
	public String showAdd_save(HttpSession session,CstService service){
		try {
			Integer size =iCstService.findAllService2().size();
			service.setSvrId(size+1L);
			iCstService.save(service);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_service/add";
	}
	//服务分配
	@RequestMapping("{curpage}/cust_service/dispatch")
	public String showDispatch(@PathVariable Integer curpage,HttpSession session){
		try {
			PageInfo<CstService> findAllService = iCstService.findAllService(curpage,5);
			session.setAttribute("allService", findAllService);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_service/dispatch";
	}
	//服务分配的保存
	@RequestMapping("/cust_service/dispatch/save")
	public String showDispatch_save(HttpSession session,CstService cstService){
		try {
			Long svrId = cstService.getSvrId();
			CstService cService = iCstService.queryOneByid(svrId);
			cService.setSvrDueTo(cstService.getSvrDueTo());
			iCstService.updateCstService(cService);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/cust_service/dispatch";
	}
	//服务分配的删除
	@RequestMapping("{svrId}/cust_service/dispatch/remove")
	public String showDispatch_remove(@PathVariable Integer svrId,HttpSession session){
		try {
			iCstService.delete(svrId);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/1/cust_service/dispatch";
	}
	//服务分配的查询
	@RequestMapping("cust_service/dispatch/find")
	public String showDispatch_find(HttpSession session,CstService service){
		try {
			PageInfo<CstService> serviceByCondition = iCstService.findCstServiceByCondition(service, 1, 5);
			session.setAttribute("allService", serviceByCondition);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_service/dispatch";
	}
	//服务处理
	@RequestMapping("{curpage}/cust_service/deal")
	public String showDeal(HttpSession session,@PathVariable Integer curpage){
		try {
			PageInfo<CstService> allService2 = iCstService.findAllService(curpage, 5);
			session.setAttribute("allService2", allService2);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_service/deal";
	}
	//服务处理的查询
	@RequestMapping("/cust_service/deal/find")
	public String showDeal_find(HttpSession session,CstService cService){
		try {
			PageInfo<CstService> findCstServiceByCondition = iCstService.findCstServiceByCondition(cService, 1, 4);
			session.setAttribute("allService2", findCstServiceByCondition);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_service/deal";
	}
	//服务处理的编辑
	@RequestMapping("{svrId}/cust_service/deal_detail")
	public String showDeal_detail(@PathVariable Long svrId,HttpSession session){
		try {
			CstService queryOneByid = iCstService.queryOneByid(svrId);
			session.setAttribute("queryOneByid", queryOneByid);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_service/deal_detail";
	}
	//服务处理的编辑的保存
	@RequestMapping("cust_service/deal_detail_save")
	public String showDeal_detail_save(HttpSession session,CstService cstService){
		try {
			CstService service = (CstService) session.getAttribute("queryOneByid");
			service.setSvrDeal(cstService.getSvrDeal());
			iCstService.updateinfo(service);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/1/cust_service/deal";
	}
	//服务反馈
	@RequestMapping("{curPage}/cust_service/feedback")
	public String showFeedback(@PathVariable Integer curPage,HttpSession session){
		try {
			PageInfo<CstService> allService3 = iCstService.findAllService(curPage, 5);
			session.setAttribute("allService3", allService3);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_service/feedback";
	}
	//服务反馈的编辑
	@RequestMapping("{svrId}/cust_service/feedback_detail")
	public String showFeedback_detail(@PathVariable Integer svrId,HttpSession session){
		try {
			CstService service = iCstService.queryOneByid(svrId);
			session.setAttribute("service", service);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_service/feedback_detail";
	}
	//服务反馈的编辑的保存
	@RequestMapping("cust_service/feedback_detail/save")
	public String showFeedback_save(HttpSession session,CstService service){
		try {
			CstService cstService = iCstService.queryOneByid(service.getSvrId());
			cstService.setSvrResult(service.getSvrResult());
			cstService.setSvrSatisfy(service.getSvrSatisfy());
			iCstService.updateCstService(cstService);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/1/cust_service/feedback";
	}
	//服务反馈的查询
	@RequestMapping("cust_service/feedback/find")
	public String showFeedbackFind(HttpSession session,CstService service){
		try {
			PageInfo<CstService> findCstServiceByCondition = iCstService.findCstServiceByCondition(service, 1, 4);
			session.setAttribute("allService3", findCstServiceByCondition);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_service/feedback";
	}
	//添加交往记录
	@RequestMapping("/{custId}/cust_info/activities_add")
	public String showActivities_add(@PathVariable String custId,HttpSession session){
		System.out.println(custId);
		try {
			List<CstActivity> list = ICustomerService.findCstActivity();
			session.setAttribute("newActivitie", list.get(list.size()-1).getAtvId()+1);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_info/activities_add";
	}
	//添加交往记录的保存
	@RequestMapping("/{custId}/cust_info/activities_add/save")
	public String showActivities_add_save(@PathVariable String custId,HttpSession session,CstActivity cstActivity){
		try {
			Long id = (Long) session.getAttribute("newActivitie");
			cstActivity.setAtvId(id);
			cstActivity.setAtvCustId(Long.parseLong(custId));
			cstActivity.setAtvCustNo(custId);
			System.out.println(cstActivity);
			ICustomerService.saveCstActivity(cstActivity);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/"+custId+"/cust_info/activities";
	}
	//编辑交往记录
	@RequestMapping("{atvId}/cust_info/activities_edit")
	public String showActivities_edit(@PathVariable Integer atvId,HttpSession session){
		try {
			CstActivity cstActivityById = ICustomerService.findCstActivityById(atvId);
			session.setAttribute("atvId", atvId);
			session.setAttribute("cstActivityById", cstActivityById);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_info/activities_edit";
	}
	//删除交往记录
	@RequestMapping("{atvId}/cust_info/activities/remove")
	public String showActivities_remove(@PathVariable Integer atvId,HttpSession session){
		try {
			ICustomerService.deleteAtvById(atvId);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		String custId= (String) session.getAttribute("activities_custId");
		System.out.println(custId);
		return "redirect:/"+custId+"/cust_info/activities";
	}
	//编辑交往记录保存
	@RequestMapping("{atvId}/cust_info/activities_edit/save")
	public String showActivities_edit_save(@PathVariable Long atvId,HttpSession session,CstActivity cstActivity){
		try {
			cstActivity.setAtvId(atvId);
			CstActivity activity = ICustomerService.findCstActivityById(atvId);
			cstActivity.setAtvCustId(activity.getAtvCustId());
			cstActivity.setAtvCustNo(activity.getAtvCustNo());
			ICustomerService.updateAtv(cstActivity);
			System.out.println(cstActivity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String custId= (String) session.getAttribute("activities_custId");
		System.out.println(custId);
		return "redirect:/"+custId+"/cust_info/activities";
	}
	@RequestMapping("/cust_info/add_customer")
	public String showAdd_customer(){
		return "cust_info/add_customer";
	}
	//交往记录
	@RequestMapping("/{custId}/cust_info/activities")
	public String showActivities(@PathVariable String custId,HttpSession session){
		try {
			List<CstActivity> findAllCstActivityByCstNo = ICustomerService.findAllCstActivityByCstNo(custId);
			session.setAttribute("findAllCstActivityByCstNo", findAllCstActivityByCstNo);
			session.setAttribute("activities_custId", custId);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_info/activities";
	}
	//
	@RequestMapping("/{custId}/cust_info/customer_edit")
	public String showCustomer_edit(@PathVariable Integer custId,HttpSession session){
		try {
			CstCustomer findCustomerById = ICustomerService.findCustomerById(custId);
			session.setAttribute("findCustomerById", findCustomerById);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_info/customer_edit";
	}
	//给客户新建一个联系人
	@RequestMapping("/{cstId}/cust_info/linkman_add")
	public String showLinkman_add(@PathVariable Integer cstId,HttpSession session){
		try {
			List<CstLinkman> list = ICustomerService.findAllLinkMan();
			session.setAttribute("LkmId", list.get(list.size()-1).getLkmId()+1);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_info/linkman_add";
	}
	//给客户新建一个联系人保存
	@RequestMapping("/{cstId}/linkman_add/baocun")
	public String showLinkman_add_baocun(@PathVariable Integer cstId,CstLinkman cstLinkman,HttpSession session){
		long custId=0;
		try {
			cstLinkman.setLkmId((Long) session.getAttribute("LkmId"));
			System.out.println(session.getAttribute("LkmId"));
			custId = (Integer) session.getAttribute("custId");
			System.out.println(cstId);
			cstLinkman.setLkmCustId(custId);
			cstLinkman.setLkmCustNo(String.valueOf(custId));
			System.out.println(cstLinkman);
			ICustomerService.saveLinkMan(cstLinkman);
			List<CstLinkman> list = ICustomerService.findAllLinkManByCstId(custId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/"+custId+"/cust_info/linkman";
	}
	//联系人中编辑的界面
	@RequestMapping("/{lkmId}/cust_info/linkman_edit")
	public String showLinkman_edit(@PathVariable Integer lkmId,HttpSession session){
		try {
			CstLinkman linkman = ICustomerService.findLinkManById(lkmId);
			session.setAttribute("linkman1", linkman);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_info/linkman_edit";
	}
	//客户的联系人中删除
	@RequestMapping("/{lkmId}/cust_info/linkman/remove")
	public String showLinkman_remove(@PathVariable Long lkmId,HttpSession session){
		int attribute = 0;
		try {
			attribute= (Integer) session.getAttribute("custId");
			ICustomerService.deleteLinkMan(lkmId);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/"+attribute+"/cust_info/linkman";
	}
	//联系人中编辑的保存
	@RequestMapping("/{lkmId}/cust_info/linkman_edit/save")
	public String showLinkman_edit_save(@PathVariable Long lkmId,CstLinkman cstLinkman,HttpSession session){
		Long lkmCustId= null;
		try {
			cstLinkman.setLkmId(lkmId);
			CstLinkman attribute = (CstLinkman) session.getAttribute("linkman1");
			lkmCustId = attribute.getLkmCustId();
			String lkmCustName = attribute.getLkmCustName();
			cstLinkman.setLkmCustId(lkmCustId);
			cstLinkman.setLkmCustNo(String.valueOf(lkmCustId));
			cstLinkman.setLkmCustName(lkmCustName);
			ICustomerService.updateLinkman(cstLinkman);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/"+lkmCustId+"/cust_info/linkman";
	}
	@RequestMapping("/{cstId}/cust_info/linkman")
	public String showLinkman(@PathVariable Integer cstId,HttpSession session){
		try {
			List<CstLinkman> findAllLinkManByCstId = ICustomerService.findAllLinkManByCstId(cstId);
			session.setAttribute("findAllLinkManByCstId", findAllLinkManByCstId);
			session.setAttribute("custId", cstId);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_info/linkman";
	}
	
	//查询所有客户
	@RequestMapping("/{curpage}/cust_info/list")
	public String showcust_infoList(@PathVariable Integer curpage,HttpSession session){
		//执行查询语句
		try {
			//不带分页
//			List<CstCustomer> findAllCustomerlist = ICustomerService.findAllCustomer();
			//带分页
			PageInfo<CstCustomer> pageInfo = ICustomerService.findAllCstCustomer(curpage, 3);
			session.setAttribute("pageInfo", pageInfo);
			List<CstCustomer> list = ICustomerService.findAllCustomer();
			int num=list.size()-1;
			Long custId = list.get(num).getCustId();
			session.setAttribute("currid",custId+1);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_info/list";
	}
	@RequestMapping("/cust_info/list/baocun")
	public String showcust_infobaocun(CstCustomer cstCustomer){
		try {
			ICustomerService.updateCustomer(cstCustomer);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/1/cust_info/list";
	}
	@RequestMapping("/sale/add_salchance")
	public String showAdd_salchance(HttpSession session){
		Long size=0L;
		try {
			size = ISalChanceService.findSalChanceByPage(1, 3).getTotal();
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		session.setAttribute("size", size+1);
		return "sale/add_salchance";
	}
	@RequestMapping("/sale/add_salchance/baocun")
	public String add_salchancebaocun(SalChance sc){
		try {
			ISalChanceService.saveChance(sc);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/1/sale/list";
	}
	//新建客户
	@RequestMapping("/cust_info/list/newAdd")
	public String newAdd(CstCustomer cstCustomer ){
		try {
			ICustomerService.saveCustomer(cstCustomer);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/1/cust_info/list";
	}
	/*@RequestMapping("/{curpage}/cust_info/list/findTiaojian")
	public String findTiaojian(CstCustomer cstCustomer,@PathVariable Integer curpage,HttpSession session){
		try {
			ICustomerService.findCustomerPageByCon(curpage, 5, cstCustomer);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/1/cust_info/list";
	}*/
	//删除客户
	@RequestMapping("/{cstId}/cust_info/list/remove")
	public String listRemovekehu(@PathVariable Integer cstId){
		try {
			ICustomerService.deleteCustomerById(cstId);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/1/cust_info/list";
	}
	
	//根据条件查询销售机会
	@RequestMapping("/sale/list/findchange")
	public String findchange(SalChance sc,HttpSession session){
		try {
			PageInfo<SalChance> findSalPageByCon = ISalChanceService.findSalPageByCon(1, 3, sc);
			session.setAttribute("findSalChanceByPage", findSalPageByCon);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "sale/list";
		
	}
	//根据条件查询customer对象
	@RequestMapping("/cust_info/list/queryByCust")
	public String queryByCust(CstCustomer cstCustomer,HttpSession session){
		//获得jsp页面中的条件来绑定customer对象
		//使用pageinfo接收
		try {
			System.out.println(cstCustomer);
			PageInfo<CstCustomer> findCustomerPageByCon = ICustomerService.findCustomerPageByCon(1, 6, cstCustomer);
			System.out.println(findCustomerPageByCon);
			session.setAttribute("pageInfo", findCustomerPageByCon);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "cust_info/list";
		
	}
	@RequestMapping("/jfreechart/cons")
	public String showCons(Integer condit,HttpServletRequest request){
        String fileName;
		try {
			CategoryDataset dataset = iReportFormsService.findCstMakeup(condit);
			fileName = JFreeChartUtil.createBarChart("客户构成分析表","客户名字", "所占百分比", dataset, request);
			String consURL=request.getContextPath() + "/chart?filename="+fileName;  
			request.getSession().setAttribute("consURL", consURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jfreechart/cons";
	}
	@RequestMapping("/jfreechart/contr")
	public String showContr(String cust_name,HttpServletRequest request){
		try {
			CategoryDataset dataset = iReportFormsService.findCstcontribute(cust_name);
			 // 保存图片 返回图片文件名
	         String fileName = JFreeChartUtil.createBarChart("客户贡献分析表","客户名字", "总金额", dataset, request);
	         // 获取图片路径（内存中） 需要在web.xml中配置一下
	         String chartURL=request.getContextPath() + "/chart?filename="+fileName;  
	         request.getSession().setAttribute("chartURL", chartURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jfreechart/contr";
	}
	//基础数据   查询产品信息
	@RequestMapping("/{curPage}/basic/product")
	public String showProduct(@PathVariable Integer curPage,HttpSession session){
		try {
			PageInfo<Product> findProductAll = iBasicDataService.findProductAll(curPage, 5);
			session.setAttribute("findProductAll", findProductAll);
			System.out.println(findProductAll+"@@@@@@@@@@@@");
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "basic/product";
	}
	//基础数据   查询产品信息的按条件查询
	@RequestMapping("/basic/product/find")
	public String showProduct_find(HttpSession session,Product product){
		try {
			PageInfo<Product> findProduct = iBasicDataService.findProduct(product, 1, 3);
			session.setAttribute("findProductAll", findProduct);
			System.out.println(findProduct+"!!!!!!!!!!");
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "basic/product";
	}
	//基础数据   查询库存
	@RequestMapping("/{curpage}/basic/storage")
	public String showStorage(@PathVariable Integer curpage,HttpSession session){
		try {
			PageInfo<Storage> findStorageAll = iBasicDataService.findStorageAll(curpage, 5);
			session.setAttribute("findStorageAll", findStorageAll);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "basic/storage";
	}
	//基础数据   查询库存的按条件查询
	@RequestMapping("/basic/storage/find")
	public String showStorage_find(HttpSession session,Storage storage){
		try {
			PageInfo<Storage> findStorage = iBasicDataService.findStorage(storage, 1, 3);
			session.setAttribute("findStorageAll", findStorage);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "basic/storage";
	}
	
	//系统设置   角色管理的新建
	@RequestMapping("/systemset/addRole")
	public String showAddRole(HttpSession session){
		try {
			Long roleId=(long) (iSysUserService.findAllRole().size()+1);
			session.setAttribute("roleId", roleId);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "systemset/addRole";
	}
	@RequestMapping("/systemset/addRole/save")
	public String showAddRole_save(SysRole role,HttpSession session){
		try {
			Long roleId = (Long) session.getAttribute("roleId");
			role.setRoleId(roleId);
			iSysUserService.addRole(role);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/systemset/role_manage";
	}
	@RequestMapping("/systemset/log_review")
	public String showLog_review(HttpSession session){
		try {
			List<CstLog> findAllLog = iLogService.findAllLog();
			session.setAttribute("findAllLog", findAllLog);
			//System.out.println(findAllLog.toString());
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "systemset/log_review";
	}
	@RequestMapping("/systemset/role_manage")
	public String showRole_manage(HttpServletRequest request){
		try {
			List<SysRole> allRolelist = iSysUserService.findAllRole();
			HttpSession session = request.getSession();
			session.setAttribute("allRolelist",allRolelist);
			//System.out.println(allRolelist.toString());
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		
		return "systemset/role_manage";
	}
	//系统设置中  角色管理的编辑操作
	@RequestMapping("/systemset/role_update/{roleId}")
	public String showRole_update(@PathVariable Integer roleId,HttpSession session){
		try {
			SysRole roleBySysRoleId = iSysUserService.findRoleBySysRoleId(roleId);
			session.setAttribute("roleBySysRoleId", roleBySysRoleId);
			iSysUserService.updateRole(roleBySysRoleId);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "systemset/role_update";
	}
	//系统设置中  角色管理的编辑的保存
	@RequestMapping("/systemset/role_update/save")
	public String showRole_update_save(HttpSession session,SysRole role){
		try {
			role.setRoleFlag((long) 1);
			iSysUserService.updateRole(role);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/systemset/role_manage";
	}
	//系统设置中  角色管理的删除
	@RequestMapping("/{roleId}/systemset/role_update/remove")
	public String showRole_update_remove(@PathVariable Integer roleId,HttpSession session){
		try {
			iSysUserService.deleteRole(roleId);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/systemset/role_manage";
	}
	//查询所有用户   用户管理
	@RequestMapping("/systemset/user_manage")
	public String showUser_manage(HttpServletRequest request){
		List<SysUser> sysUserlist;
		try {
			sysUserlist = iSysUserService.findAllMgr();
			/*System.out.println(sysUserlist.toString());*/
			HttpSession session = request.getSession();
			session.setAttribute("findAllMgr",sysUserlist);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "systemset/user_manage";
	}
	//用户管理的编辑
	@RequestMapping("{usrId}/systemset/user_update")
	public String showUser_update(@PathVariable Integer usrId,HttpSession session){
		try {
			SysUser sysUser = iSysUserService.findOneUser(usrId);
			session.setAttribute("sysUser", sysUser);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "systemset/user_update";
	}
	//用户管理的编辑的保存
	@RequestMapping("{usrId}/systemset/user_update/save")
	public String showUser_update_save(SysUser user){
		try {
			iSysUserService.updateUser(user);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/systemset/user_manage";
	}
	//用户管理的新建用户
	@RequestMapping("/systemset/add")
	public String showsysAdd(HttpSession session){
		try {
			int userID = iSysUserService.findAllMgr().size();
			session.setAttribute("userID", userID+1);
		} catch (CrmCommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "systemset/add";
	}
	//用户管理的新建用户的保存
	@RequestMapping("{userID}/systemset/add/save")
	public String showsysAdd_save(@PathVariable Long userID,HttpSession session,SysUser user){
		try {
			user.setUsrId(userID);
			iSysUserService.addUser(user);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/systemset/user_manage";
	}
	//用户管理的删除用户
	@RequestMapping("{usrId}/systemset/user_manage/remove")
	public String showUser_update_remove(@PathVariable Integer usrId){
		try {
			iSysUserService.deleteUser(usrId);
		} catch (CrmCommonException e) {
			e.printStackTrace();
		}
		return "redirect:/systemset/user_manage";
	}
}
