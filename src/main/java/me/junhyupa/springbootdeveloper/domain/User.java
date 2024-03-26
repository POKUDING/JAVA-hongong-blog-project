package me.junhyupa.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;

@Table(name="users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails { // userDetails를 상속받아 인증 객체로 사용
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;

    @Column(name="email", nullable = false, unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @Builder
    public User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
    }

    //사용자가 가지고 있는 권한의 목록을 반환합니다.
    //현재 예제 코드에서는 사용자 이외의 권한이 없기 때문에 user 권한만 담아 반환합니다.
    @Override
    public Collection<? extends GrantesAuthoruty> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    //사용자를 식별할 수 있는 사용자 이름을 반환합니다. 이때 사용되는 사용자 이름은 반드시 고유해야 합니다.
    //현재 예제 코드는 유니크 속성이 적용된 이메일을 반환합니다.
    @Override
    public String getUsername() {
        return email;
    }

    //사용자의 비밀번호를 반환합니다. 이때 저장되어있는 비밀번호는 암호화해서 저장해야 합니다.
    @Override
    public String getPassword() {
        return password;
    }

    //계정이 만료되었는지 확인하는 메서드입니다. 만약 만료되지 않은 때는 true를 반환합니다.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠금되었는지 확인하는 메서드입니다. 만약 잠금되니 않은 때는 true를 반환합니다.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호가 만료되었는지 확인하는 메서드입니다. 만약 잠금되지 않은 때는 true를 반환합니다.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 사용 가능한지 확인하는 메서드입니다. 만약 사용가능하다면 true를 반환합니다.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
