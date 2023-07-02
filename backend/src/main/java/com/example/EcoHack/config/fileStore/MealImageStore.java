package com.example.EcoHack.config.fileStore;

import com.example.EcoHack.util.Utilities;
import com.example.EcoHack.util.localFileStore.LocalImageStore;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MealImageStore extends LocalImageStore {
    public MealImageStore() {
        super(Utilities.getResourcesPath_(), Utilities.joinPathsAsString("public", "meals", "images"));
    }
}
