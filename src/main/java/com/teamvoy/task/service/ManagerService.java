package com.teamvoy.task.service;

import com.teamvoy.task.entity.PhoneEntity;
import com.teamvoy.task.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ManagerService {
        @Autowired
        private PhoneRepository phoneRepository;
        public PhoneEntity addPhone(PhoneEntity phone){
            return phoneRepository.save(phone);
        }
}
