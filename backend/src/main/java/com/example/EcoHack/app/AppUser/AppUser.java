package com.example.EcoHack.app.AppUser;


import com.example.EcoHack.config.configProperties.DatabaseConfig;
import com.example.EcoHack.app.AppUser.DTO.CreateAppUserDTO;
import com.example.EcoHack.app.AppUser.DTO.GetAppUserDTO;
import com.example.EcoHack.common.audit.AuditData;
import com.example.EcoHack.common.audit.Auditable;
import com.example.EcoHack.app.DeliveryAddress;
import com.example.EcoHack.app.AppUser.Role.Role;
import com.example.EcoHack.security.thirdPartyLogin.AuthType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "AppUser")
public class AppUser implements UserDetails, Auditable {
    @Embedded
    AuditData auditData = new AuditData();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String username;
    @JsonIgnore
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;

    private String address;
    private String nipt;

    @Lob
    private byte[] image;

    public void setImage(byte[] bytes){
        image=bytes;
    }

    public String getEncodedImage() throws UnsupportedEncodingException {
        if (image==null)
            return null;
        byte[] encodeBase64 = Base64.getEncoder().encode(getImage());
        return new String(encodeBase64, "UTF-8");
    }

    @OneToMany()
    List<DeliveryAddress> deliveryAddresses;

    @ManyToOne
    private Role role = DatabaseConfig.getUSER_ROLE();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(Optional.ofNullable(this.getRole()).map(Role::getName).orElse(DatabaseConfig.getUSER_ROLE().getName())));
    }

    @Transient
    private boolean passwordUpdated = false;

    private AuthType userAuthType = AuthType.BASIC;

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public long getCreatedAt() {
        return auditData.getCreatedAt();
    }

    @Override
    public long getUpdatedAt() {
        return auditData.getUpdatedAt();
    }


    public AppUser(String name, String surname, String username, String password, String email, String phoneNumber, Boolean registerAsFoodProvider, Locale locale) {
        setName(name);
        setSurname(surname);
        setUsername(username);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setPassword(password);
        Role role = DatabaseConfig.getUSER_ROLE();
        if (registerAsFoodProvider)
            role = DatabaseConfig.getFOOD_PROVIDER_ROLE();
        setRole(role);
    }

    public AppUser(CreateAppUserDTO createAppUserDTO) {
        this(createAppUserDTO.getName(), createAppUserDTO.getSurname(), createAppUserDTO.getEmail(), createAppUserDTO.getPassword(), createAppUserDTO.getEmail(), createAppUserDTO.getPhoneNumber(), createAppUserDTO.getRegisterAsFoodProvider(), createAppUserDTO.getLocale());
    }

    public static GetAppUserDTO toGetAppUserDTO(AppUser appUser, AppUserService appUserService) {
        return new GetAppUserDTO(appUser);
    }

    public void setPassword(String password) {
        if (password!=null&&!password.equals(this.password)) { // to avoid rehashing an already hashed password, causing auth issues
            this.password = password;
            passwordUpdated = true;
        }
    }

    @Transient
    @JsonIgnore
    public org.springframework.security.core.userdetails.User toDomainUser() {
        return new org.springframework.security.core.userdetails.User(this.getUsername(), "", Collections.singleton(new SimpleGrantedAuthority(Optional.ofNullable(this.getRole()).map(Role::getName).orElse(DatabaseConfig.getUSER_ROLE().getName()))));
    }

    public GetAppUserDTO toGetAppUserDTO() {
        return new GetAppUserDTO(this);
    }

}
