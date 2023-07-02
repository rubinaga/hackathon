package com.example.EcoHack.app.AppUser.DTO;

import com.example.EcoHack.app.AppUser.AppUser;
import com.example.EcoHack.common.audit.AuditBaseDTO;
import com.example.EcoHack.app.AppUser.AppUserService;
import com.example.EcoHack.config.configProperties.DatabaseConfig;
import com.example.EcoHack.util.localFileStore.LocalImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAppUserDTO extends AuditBaseDTO {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String phoneNumber;
    private boolean enabled;
    private String image;
    private boolean isFoodProvider;

    public GetAppUserDTO(AppUser appUser) {
        this.id = appUser.getId();
        this.name = appUser.getName();
        this.surname = appUser.getSurname();
        this.username = appUser.getUsername();
        this.email = appUser.getEmail();
        this.phoneNumber = appUser.getPhoneNumber();
        this.enabled = appUser.isEnabled();
        this.isFoodProvider = appUser.getRole().equals(DatabaseConfig.getFOOD_PROVIDER_ROLE());
        this.createdAt = appUser.getCreatedAt();
        this.updatedAt = appUser.getUpdatedAt();
        try {
            this.image = appUser.getEncodedImage();
        } catch (UnsupportedEncodingException e) {
            this.image=null;
        }
    }

    public static GetAppUserDTO fromAppUser(AppUser appUser) {
        return new GetAppUserDTO(appUser);
    }
}
