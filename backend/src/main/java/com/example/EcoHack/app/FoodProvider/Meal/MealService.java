package com.example.EcoHack.app.FoodProvider.Meal;

import com.example.EcoHack.app.FoodProvider.Meal.DTO.GetModerateMealDTO;
import com.example.EcoHack.common.BaseService;
import com.example.EcoHack.config.fileStore.MealImageStore;
import com.example.EcoHack.util.localFileStore.LocalImage;
import com.example.EcoHack.util.localFileStore.LocalImageStore;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MealService extends BaseService<Meal> {

    private final MealRepository mealRepository;

    private final MealImageStore mealImageStore;
    public MealService(MealRepository mealRepository, MealImageStore mealImageStore) {
        super(mealRepository, "meal");
        this.mealRepository = mealRepository;
        this.mealImageStore = mealImageStore;
    }


    public Page<GetModerateMealDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<GetModerateMealDTO> meals = mealRepository.findAll().stream().map(GetModerateMealDTO::new).toList();
        return new PageImpl<>(meals, pageable, meals.size());
    }

    public Meal uploadImage(MultipartFile file, Long mealId_) throws IOException {
        Meal meal = getById(mealId_);
        meal.setImage(file.getBytes());
        return save(meal);
    }
}
