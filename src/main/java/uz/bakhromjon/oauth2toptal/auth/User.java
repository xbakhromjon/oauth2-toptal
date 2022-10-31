package uz.bakhromjon.oauth2toptal.auth;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;

/**
 * @author : Bakhromjon Khasanboyev
 * @since : 31/10/22, Mon, 14:11
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String password;
    private String email;
    private String username;
    private Integer roleCode;

    public User(String email, String password, List<? extends GrantedAuthority> asList) {
        this.email = email;
        this.password = password;
        // TODO: 31/10/22 granted authorities
    }


    @RequiredArgsConstructor
    @Getter
    public enum Role {
        USER("USER", 1),
        ADMIN("ADMIN", 2),
        SUPER_ADMIN("SUPER_ADMIN", 3);

        private final String name;
        private final Integer code;

        public static String getRoleName(Integer code) {
            EnumSet<Role> roles = EnumSet.allOf(Role.class);
            for (Role role : roles) {
                if (role.getCode().equals(code)) return role.getName();
            }
            throw new IllegalArgumentException("code: %s".formatted(code));
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return new BCryptPasswordEncoder().encode(password);
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
