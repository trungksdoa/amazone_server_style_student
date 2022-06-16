package com.student.project.amazone.service;

import com.student.project.amazone.entity.Banner_model;

import java.util.List;

public interface Banner_service {
    public List<Banner_model> getList();
    public Banner_model SaveOrUpdate(Banner_model banner);

    public void delete_file(String fileName);
}
