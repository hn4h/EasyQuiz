package model;

public class CreatePaymentLinkRequestBody {
    private String packageName;
    private int price;
    private String description;
    private String returnUrl;
    private String cancelUrl;

    // Getters
    public String getPackageName() {
        return packageName;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    // Setters
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }
}
