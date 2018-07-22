package com.zsm.springmvc.pojo;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @Author: zengsm.
 * @Description:
 * @Date:Created in 2018/7/19 23:25.
 * @Modified By:
 */
public class Product implements Serializable
{
    private String name;

    private String description;

    private BigDecimal price;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public BigDecimal getPrice()
    {
        return price;
    }

    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }
}
