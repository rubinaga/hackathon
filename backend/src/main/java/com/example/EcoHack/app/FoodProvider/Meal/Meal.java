package com.example.EcoHack.app.FoodProvider.Meal;

import com.example.EcoHack.app.FoodProvider.Cuisine.Cuisine;
import com.example.EcoHack.common.audit.AuditData;
import com.example.EcoHack.common.audit.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Base64;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Meal")
public class Meal implements Auditable {
    @Embedded
    AuditData auditData = new AuditData();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private int amount;

    BigDecimal price;

    @ManyToOne
    private Cuisine cuisine;

    @OneToMany
    private Set<MealIngredient> ingredients;

    @Lob
    private byte[] image;

    @Override
    public long getCreatedAt() {
        return auditData.getCreatedAt();
    }

    @Override
    public long getUpdatedAt() {
        return auditData.getUpdatedAt();
    }

    public void setImage(byte[] bytes){
        image=bytes;
    }

    public String getEncodedImage() throws UnsupportedEncodingException {
        if (image==null)
            return null;
        byte[] encodeBase64 = Base64.getEncoder().encode(getImage());
        return new String(encodeBase64, "UTF-8");
    }
}
