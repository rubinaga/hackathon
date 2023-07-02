package com.example.EcoHack.security.DTO;

import com.example.EcoHack.security.thirdPartyLogin.AuthType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MultiAuthIdentityProviderDTO extends BasicCredentialsDTO {
    String token;
    AuthType authType = AuthType.BASIC;
}
