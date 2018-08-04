package org.lay.order.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Create by Lay
 * 2018-03-14 13:18
 */
@Data
@Entity
public class ProductInfo {

    @Id
    private String productId;

    /** 名称. */
    private String productName;

    /** 价格. */
    private BigDecimal productPrice;

    /** 库存. */
    private Integer productStock;

    /** 描述. */
    private String productDescription;

    /** 小图. */
    private String productIcon;

    /** 状态,0正常, 1下架. */
    private Integer productStatus = 0;

    /** 类目编号. 用来和商品类目表进行关联. */
    private Integer categoryType;

    /** 创建时间 */
    private Date createTime;

    /** 更新时间 */
    private Date updateTime;
}
