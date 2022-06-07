package com.student.project.amazone.service;

import com.student.project.amazone.entity.Banner_model;
import com.student.project.amazone.repo.Banner_modelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Banner_implement implements Banner_service {
    private final Banner_modelRepository service;

    public Banner_implement(Banner_modelRepository service) {
        this.service = service;
    }

    @Override
    public List<Banner_model> getList() {
        return service.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public Banner_model SaveOrUpdate(Banner_model banner) {
        return service.save(banner);
    }
}
