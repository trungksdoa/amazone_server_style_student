package com.student.project.amazone.service;

import com.student.project.amazone.entity.A_City;
import com.student.project.amazone.repo.A_CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class VietNam_implement implements VietNam_service {

    private final A_CityRepository cityService;
    @Override
    public List<A_City> citys_list() {
        return new ArrayList<>(cityService.findAll());
    }
}
