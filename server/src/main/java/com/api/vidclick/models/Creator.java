package com.api.vidclick.models;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "creators")
public class Creator implements UserDetails {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String password;
    private String email;
    private String creatorProfileImage;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "account_created_on")
    private Date accountCreatedOn; // needed to be converted into sql
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role == null)?Role.CREATOR.toString():role.toString()));
    }

    @Override
    public String getUsername() {
        return name;
    }

    @PrePersist
    protected void onCreate() {
        accountCreatedOn = new Date();
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

    @Override
    public String getPassword(){
        return password;
    }
}
