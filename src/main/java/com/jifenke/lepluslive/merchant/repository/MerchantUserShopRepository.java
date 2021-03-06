package com.jifenke.lepluslive.merchant.repository;

import com.jifenke.lepluslive.merchant.domain.entities.MerchantUser;
import com.jifenke.lepluslive.merchant.domain.entities.MerchantUserShop;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 账号对应的门店列表 Created by wcg on 16/5/11.
 */
public interface MerchantUserShopRepository extends JpaRepository<MerchantUserShop, Long> {

  /**
   * 获取账户下管理的门店列表  2017/02/07
   *
   * @param merchantUser 账户
   */
  List<MerchantUserShop> findByMerchantUser(MerchantUser merchantUser);
}
