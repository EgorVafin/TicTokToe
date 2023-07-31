package org.tictactoe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email
    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "creatorPlayer", cascade = CascadeType.PERSIST, orphanRemoval = true)
    //@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Game> creatorGames;

    @OneToMany(mappedBy = "secondPlayer", cascade = CascadeType.PERSIST, orphanRemoval = true)
    //@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Game> playingGames;

    @OneToMany(mappedBy = "userWhoMadeTurn", cascade = CascadeType.PERSIST, orphanRemoval = true)
    //@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<GameTurn> gameTurns;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public boolean sameAs(User other){
        if(other == null){
            return false;
        }
        return this.id == other.id;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
