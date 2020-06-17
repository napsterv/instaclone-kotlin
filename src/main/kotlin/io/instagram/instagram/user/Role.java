package io.instagram.instagram.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String authority;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<Permission> permissions = new ArrayList<>();

    @ManyToMany(mappedBy = "roles")
    private List<AppBaseUser> users = new ArrayList<>();

    @Override
    public String getAuthority() {
        return authority;
    }
}
