package by.itstep.shop.dao.model.enums;

public enum Permission {
    ITEMS_READ("items:read"),
    ITEMS_WRITE("items:write"),
    USERS_READ("users:read"),
    USERS_WRITE("users:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
