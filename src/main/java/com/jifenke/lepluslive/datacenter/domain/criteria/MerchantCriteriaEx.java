package com.jifenke.lepluslive.datacenter.domain.criteria;

import com.jifenke.lepluslive.merchant.domain.criteria.MerchantCriteria;

/**
 * Created by xf on 2016/9/21.
 */
public class MerchantCriteriaEx extends MerchantCriteria {

  private Long validAmount;                // 有效金额 (大于有效金额为有效订单)

  private Long needNum;                    // 订单数量 (只显示大于该订单成交量的商户)

  public Long getValidAmount() {
    return validAmount;
  }

  public void setValidAmount(Long validAmount) {
    this.validAmount = validAmount * 100; //  1:100 (现金:积分)
  }

  public Long getNeedNum() {
    return needNum;
  }

  public void setNeedNum(Long needNum) {
    this.needNum = needNum;
  }
}
