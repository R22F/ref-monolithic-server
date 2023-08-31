package com.example.refmonolithicserver.service;

import com.example.refmonolithicserver.dto.FoodDto.FoodRequestDto;
import com.example.refmonolithicserver.repository.FoodRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    public Object getAll() {
        // userId를 찾아 대입
        return foodRepository.findAllByUserId(1L);
    }

    public Object getItem(Long id) {
        return foodRepository.findById(id);
    }

    public Object addItem(FoodRequestDto dto) {
        return foodRepository.save(dto.toEntity(1L));
    }

    public Object modifyItem(Long id,  FoodRequestDto dto) {
        return foodRepository.save(dto.toEntity(1L, id));
    }

    public Long removeItem(Long id) {
        foodRepository.deleteById(id);
        return id;
    }
}
