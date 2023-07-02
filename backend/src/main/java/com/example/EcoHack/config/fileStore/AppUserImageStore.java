package com.example.EcoHack.config.fileStore;

import com.example.EcoHack.util.Utilities;
import com.example.EcoHack.util.localFileStore.LocalImageStore;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppUserImageStore extends LocalImageStore {
    public AppUserImageStore() {
        super(Utilities.getResourcesPath_(), Utilities.joinPathsAsString("public", "users", "images"));
    }
}
