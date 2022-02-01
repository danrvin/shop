package by.itstep.shop.dao.model.enums;

public enum Permission {
    ITEMS_READ("item:read"),
    ITEMS_WRITE("item:write"),
    USERS_READ("user:read"),
    USERS_WRITE("user:write"),
    BASKET_READ("basket:read"),
    BASKET_WRITE("basket:write"),
    TRANSACTION_BUY("transaction:buy");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
