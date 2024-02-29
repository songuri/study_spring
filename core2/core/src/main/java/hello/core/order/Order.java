package hello.core.order;

public class Order {

    private Long MemberId;
    private String itemName;
    private int itemPirce;
    private int discountPirce;

    public Order(Long memberId, String itemName, int itemPirce, int discountPirce) {
        MemberId = memberId;
        this.itemName = itemName;
        this.itemPirce = itemPirce;
        this.discountPirce = discountPirce;
    }

    @Override
    public String toString() {
        return "Order{" +
                "MemberId=" + MemberId +
                ", itemName='" + itemName + '\'' +
                ", itemPirce=" + itemPirce +
                ", discountPirce=" + discountPirce +
                '}';
    }

    public int calculatePrice() {
        return itemPirce - discountPirce;
    }

    public void setMemberId(Long memberId) {
        MemberId = memberId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPirce(int itemPirce) {
        this.itemPirce = itemPirce;
    }

    public void setDiscountPirce(int discountPirce) {
        this.discountPirce = discountPirce;
    }

    public Long getMemberId() {
        return MemberId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPirce() {
        return itemPirce;
    }

    public int getDiscountPirce() {
        return discountPirce;
    }
}
