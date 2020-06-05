package io.github.hotspacode.neeza.test.mock;

/**
 * 促销类型
 *
 * @author john.lee
 */
public enum PromotionType {

    /**
     * 交款折扣促销单
     */
    PROMOTION_SALE_JKZK(10, "交款折扣促销单"),

    /**
     * 预存销售券促销单
     */
    PROMOTION_SALE_YCSQ(11, "预存销售券促销单"),

    /**
     * 交款返券促销单
     */
    PROMOTION_SALE_JKFQ(12, "交款返券促销单"),

    /**
     * 拼单返券促销单
     */
    PROMOTION_SALE_PDFQ(13, "拼单返券促销单"),

    /**
     * 收券促销单
     */
    PROMOTION_SALE_SQ(14, "收券促销单"),

    /**
     * 交款直降促销单
     */
    PROMOTION_SALE_JKZJ(15, "交款直降促销单"),

    /**
     * 交款返现促销单
     */
    PROMOTION_SALE_JKFX(16, "交款返现促销单"),

    /**
     * 拼单交款返现促销单
     */
    PROMOTION_SALE_PDJKFX(17, "拼单交款返现促销单"),

    /**
     * 赠品促销单
     */
    PROMOTION_SALE_ZP(18, "赠品促销单"),

    /**
     * 通汇卡折扣促销单
     */
    PROMOTION_SALE_THK(19, "通汇卡折扣促销单"),

    /**
     * 预售卡促销单
     */
    PROMOTION_SALE_YCSK(20, "预售卡促销单"),

    /**
     * 拼单折扣促销单
     */
    PROMOTION_SALE_PDZK(21, "拼单折扣促销单"),

    /**
     * 拼团购
     */
    GROUP_BUY(28, "拼团购"),

    /**
     * 限时购
     */
    ITEMPROMOTION_LIMITED_PURCHASE(29, "限时购"),

    /**
     * 商品预定
     */
    ITEMPROMOTION_BOOKING(30, "商品预定"),

    /**
     * 爆品直降
     */
    SKUPROMOTION(31, "爆品直降"),

    /**
     * 订单满减
     */
    SUBPROMOTION_FULL_MINUS(32, "订单满减"),

    /**
     * 订单叠加满减
     */
    SUBPROMOTION_PER_FULL_MINUS(33, "订单叠加满减"),

    /**
     * 订单阶梯满减
     */
    SUBPROMOTION_STEP_FULL_MINUS(34, "订单阶梯满减"),

    /**
     * 订单折扣
     */
    SUBPROMOTION_DISCOUNT(35, "订单折扣"),

    /**
     * 订单满赠
     */
    SUBPROMOTION_COUPON(36, "订单满赠"),

    /**
     * 订单满赠
     */
    SUBPROMOTION_GIFT(37, "满额返券"),

    /**
     * 赠送星钻
     */
    SUBPROMOTION_INVENTORY(38, "满额赠星钻"),

    /**
     * 每满额赠星钻
     */
    SUBPROMOTION_STAR_DIAMOND_PER(39, "每满额赠星钻"),

    /**
     * 阶梯满额赠星钻
     */
    SUBPROMOTION_STAR_DIAMOND_STEP(40, "阶梯满额赠星钻"),

    /**
     * 无门槛现金抵用券
     */
    COUPON_CASH(51, "无门槛现金抵用券"),

    /**
     * 满减券
     */
    COUPON_FULL_MINUS(52, "满减券"),

    /**
     * 每满减券
     */
    COUPON_PER_FULL_MINUS(53, "每满减券"),

    /**
     * 阶梯满减券
     */
    COUPON_STEP_FULL_MINUS(54, "阶梯满减券"),

    /**
     * 折扣券
     */
    COUPON_DISCOUNT(55, "折扣券"),

    /**
     * 赠品券
     */
    COUPON_GIFT(56, "赠品券"),

    /**
     * 礼品券
     */
    COUPON_PRESENT(57, "礼品券"),

    /**
     * 收银京东优惠
     */
    PAYMENT_JD_PROMOTION(1001, "收银京东优惠");

    private int value;

    private String description;

    private PromotionType(int value, String description) {
        this.value = value;
        this.description = description;
    }



}
