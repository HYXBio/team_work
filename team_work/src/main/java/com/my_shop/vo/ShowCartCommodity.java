package com.my_shop.vo;

import com.my_shop.entity.Commodity;
import com.my_shop.entity.ShoppingCart;

public class ShowCartCommodity  extends ShoppingCart {
    private Commodity commodity;

    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    @Override
    public String toString() {
        return "ShowCartCommodity{" +
                "commodity=" + commodity +
                '}';
    }
}
