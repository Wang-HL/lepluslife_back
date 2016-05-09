package com.jifenke.lepluslive.order.controller;

import com.jifenke.lepluslive.global.util.LejiaResult;
import com.jifenke.lepluslive.global.util.MvUtil;
import com.jifenke.lepluslive.order.controller.view.OrderViewExcel;
import com.jifenke.lepluslive.order.domain.criteria.FinancialCriteria;
import com.jifenke.lepluslive.order.domain.criteria.OLOrderCriteria;
import com.jifenke.lepluslive.order.domain.criteria.OrderCriteria;
import com.jifenke.lepluslive.order.service.OffLineOrderService;
import com.jifenke.lepluslive.order.service.OrderService;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by wcg on 16/4/6.
 */
@RestController
@RequestMapping("/manage")
public class OrderController {

  @Inject
  private OrderService orderService;

  @Inject
  private OrderViewExcel orderViewExcell;

  @Inject
  private OffLineOrderService offLineOrderService;

  @RequestMapping("/order")
  public ModelAndView findOrderByPage(
      @RequestParam(value = "page", required = false) Integer offset,
      @RequestParam(value = "orderSid", required = false) String orderSid,
      @RequestParam(required = false) Integer state,
      Model model) {
    OrderCriteria orderCriteria = new OrderCriteria();

    if (state != null) {
      orderCriteria.setState(state);
    }
    if (orderSid != null) {
      orderCriteria.setOrderSid(orderSid);
    }
    if (offset == null) {
      offset = 1;
    }
    Page
        page =
        orderService.findOrderByPage(offset, orderCriteria);
    model.addAttribute("orders", page.getContent());
    model.addAttribute("pages", page.getTotalPages());
    model.addAttribute("currentPage", offset);
    model.addAttribute("orderCriteria", orderCriteria);
    return MvUtil.go("/order/orderList");
  }

  @RequestMapping("/orderCancle/{id}")
  public LejiaResult cancleOrder(@PathVariable Long id) {
    orderService.cancleOrder(id);
    return LejiaResult.build(200, "取消订单成功");
  }

  @RequestMapping("/order/delivery/{id}")
  public LejiaResult orderDelivery(@PathVariable Long id) {
    orderService.orderDelivery(id);
    return LejiaResult.build(200, "发货成功");
  }

  @RequestMapping("/offLineOrder")
  public ModelAndView offLineOrder() {
    return MvUtil.go("/order/offLineOrderList");
  }

  @RequestMapping(value = "/offLineOrder",method = RequestMethod.POST)
  public LejiaResult getOffLineOrder(@RequestBody OLOrderCriteria olOrderCriteria) {
    Page page = offLineOrderService.findOrderByPage(olOrderCriteria,10);
    if(olOrderCriteria.getOffset()==null){
      olOrderCriteria.setOffset(1);
    }
   return LejiaResult.ok(page);
  }

  @RequestMapping(value = "/offLineOrder/{id}",method = RequestMethod.GET)
  public LejiaResult changeOrderStateToPaid(@PathVariable Long  id) {
    offLineOrderService.changeOrderStateToPaid(id);
    return LejiaResult.ok();
  }

  @RequestMapping(value = "/offLineOrder/export",method = RequestMethod.POST)
  public ModelAndView exporeExcel( OLOrderCriteria olOrderCriteria) {
    if(olOrderCriteria.getOffset()==null){
      olOrderCriteria.setOffset(1);
    }
    Page page = offLineOrderService.findOrderByPage(olOrderCriteria,10000);
    Map map = new HashMap();
    map.put("orderList",page.getContent());
    return new ModelAndView(orderViewExcell,map);
  }

  @RequestMapping(value = "/financial",method = RequestMethod.GET)
  public ModelAndView financialList(){

    return MvUtil.go("/order/financialList");
  }


  @RequestMapping(value = "/financial",method = RequestMethod.POST)
  public LejiaResult searchFinancialBycriterial(@RequestBody FinancialCriteria financialCriteria){
    if(financialCriteria.getOffset()==null){
      financialCriteria.setOffset(1);
    }
    Page page = offLineOrderService.findFinancialByCirterial(financialCriteria, 10);
    return LejiaResult.ok(page);
  }


  @RequestMapping(value = "/financial/{id}",method = RequestMethod.GET)
  public LejiaResult changeFinancialStateToTransfer(@PathVariable Long  id) {
    offLineOrderService.changeFinancialStateToTransfer(id);
    return LejiaResult.ok();
  }

  @RequestMapping(value = "/offLineOrder/export",method = RequestMethod.POST)
  public ModelAndView exporeExcel( FinancialCriteria financialCriteria) {
    if(financialCriteria.getOffset()==null){
      financialCriteria.setOffset(1);
    }
    Page page = offLineOrderService.findFinancialByCirterial(financialCriteria, 10000);
    Map map = new HashMap();
    map.put("orderList",page.getContent());
    return new ModelAndView(orderViewExcell,map);
  }

}
