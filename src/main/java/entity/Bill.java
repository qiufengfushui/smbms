package entity;

import java.sql.Date;

public class Bill {
    private long id;
    private String billCode;
    private String productName;
    private String productDesc;
    private String productUnit;
    private String productCount;
    private String totalPrice;
    private int isPayment;
    private long createdBy;
    private Date creationDate;
    private long modifyBy;
    private Date modifyDate;
    private Provider provider;
    private int providerId;

    public Bill() {
    }

    public Bill(long id, String billCode, String productName, String productDesc, String productUnit, String productCount, String totalPrice, int isPayment, long createdBy, Date creationDate, long modifyBy, Date modifyDate, Provider provider, int providerId) {
        this.id = id;
        this.billCode = billCode;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productUnit = productUnit;
        this.productCount = productCount;
        this.totalPrice = totalPrice;
        this.isPayment = isPayment;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.provider = provider;
        this.providerId = providerId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getProductCount() {
        return productCount;
    }

    public void setProductCount(String productCount) {
        this.productCount = productCount;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(int isPayment) {
        this.isPayment = isPayment;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", billCode='" + billCode + '\'' +
                ", productName='" + productName + '\'' +
                ", productDesc='" + productDesc + '\'' +
                ", productUnit='" + productUnit + '\'' +
                ", productCount='" + productCount + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", isPayment=" + isPayment +
                ", createdBy=" + createdBy +
                ", creationDate=" + creationDate +
                ", modifyBy=" + modifyBy +
                ", modifyDate=" + modifyDate +
                ", provider=" + provider +
                ", providerId=" + providerId +
                '}';
    }
}