package io.github.hotspacode.neeza.test.mock;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by h on 2017/8/1.
 */
public class ItemPromotionVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 促销优惠金额
     */
    private BigDecimal promotionAmount;

    /**
     * 对应订单id
     */
    private Long orderId;

    /**
     * 商品sku
     */
    private String sku;

    /**
     * 对应商品id
     */
    private Long itemId;

    /**
     * 促销类型
     */
    private PromotionType promotionType;

    private Integer promotionTypeValue;

    private String promotionTypeDesc;

    /**
     * 商品金额（减促销）
     */
    private BigDecimal itemAmount;

    /**
     * 商品数量
     */
    private BigDecimal itemQuantity;

    /**
     * 促销id

     */
    private String promotionId;

    /**
     * 券码
     */
    private String voucherNo;

    /**
     * 券对应sku
     */
    private String voucherSku;

    /**
     * 使用状态（0：未使用，1：已使用）（券订单使用）
     */
    private Integer useStatus;

    /**
     * 促销券详情（券订单使用）
     */
    private HashMap<String, Object>  couponDetailView;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPromotionAmount() {
        return promotionAmount;
    }

    public void setPromotionAmount(BigDecimal promotionAmount) {
        this.promotionAmount = promotionAmount;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(PromotionType promotionType) {
        this.promotionType = promotionType;
    }

    public Integer getPromotionTypeValue() {

        return promotionTypeValue;
    }

    public void setPromotionTypeValue(Integer promotionTypeValue) {
        this.promotionTypeValue = promotionTypeValue;
    }

    public String getPromotionTypeDesc() {

        return promotionTypeDesc;
    }

    public void setPromotionTypeDesc(String promotionTypeDesc) {
        this.promotionTypeDesc = promotionTypeDesc;
    }

    public BigDecimal getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(BigDecimal itemAmount) {
        this.itemAmount = itemAmount;
    }

    public BigDecimal getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(BigDecimal itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public String getVoucherSku() {
        return voucherSku;
    }

    public void setVoucherSku(String voucherSku) {
        this.voucherSku = voucherSku;
    }

    public HashMap<String, Object> getCouponDetailView() {
        return couponDetailView;
    }

    public void setCouponDetailView(HashMap<String, Object> couponDetailView) {
        this.couponDetailView = couponDetailView;
    }
}
