package com.example.EcoHack.app.AppUser.DTO;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAppUserDTO {
    @Nullable
    private String name;
    @Nullable
    private String surname;
    @Nullable
    private String phoneNumber;
}
