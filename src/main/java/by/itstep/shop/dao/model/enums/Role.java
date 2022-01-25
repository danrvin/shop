package by.itstep.shop.dao.model.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    USER(Set.of(Permission.ITEMS_READ, Permission.ITEMS_WRITE,
            Permission.USERS_READ, Permission.BASKET_WRITE,
            Permission.BASKET_READ)),
    ADMIN(Set.of(Permission.ITEMS_WRITE, Permission.ITEMS_READ,
            Permission.USERS_WRITE, Permission.USERS_READ,
            Permission.BASKET_WRITE, Permission.BASKET_READ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}