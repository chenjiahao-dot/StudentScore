package org.example.Demo.Server.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.Demo.DTO.ClassDTO.AddClassDTO;
import org.example.Demo.Server.ClassServer;
import org.example.Demo.entity.Class.Class;
import org.example.Demo.mapper.ClassMapper;
import org.example.Demo.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClassImpl implements ClassServer {
    private final ClassMapper classMapper;
    @Override
    public void addClass(AddClassDTO addClassDTO) {
        Class clazz = new Class();
        BeanUtils.copyProperties(addClassDTO, clazz);
        classMapper.insertClass(clazz);
    }
}
