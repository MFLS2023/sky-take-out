package com.sky.mapper;

import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartMapper {
    /**
     * 批量插入购物车数据
     *
     * @param shoppingCartList
     */
    void insertBatch(List<ShoppingCart> shoppingCartList);
}
