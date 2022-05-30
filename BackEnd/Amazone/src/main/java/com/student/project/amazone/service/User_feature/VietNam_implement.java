package com.student.project.amazone.service.User_feature;

import com.student.project.amazone.entity.A_City;
import com.student.project.amazone.repo.A_CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VietNam_implement implements VietNam_service {

    private final A_CityRepository cityService;
    @Override
    public List<A_City> citys_list() {
        return cityService.findAll().stream().collect(Collectors.toList());
    }
}
