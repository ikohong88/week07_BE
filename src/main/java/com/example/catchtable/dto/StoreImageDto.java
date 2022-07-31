package com.example.catchtable.dto;

import com.example.catchtable.model.StoreImageURL;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreImageDto {
    private String imagePath;

    public StoreImageDto(StoreImageURL storeImageURL) {
        this.imagePath = storeImageURL.getImagePath();
    }
}
