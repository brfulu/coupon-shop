package io.fulu.couponshop.user;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;

public class UserService {
    public List<User> getUsers() {
        return UserMapper.mapToModelList(UserRepository.getUsers());
    }

    public User register(User user) {
        UserEntity userEntity = UserRepository.addUser(UserMapper.mapToEntity(user));
        return UserMapper.mapToModel(userEntity);
    }

    public User login(User user) {
        UserEntity userEntity = UserRepository.getUserByUsername(user.getUsername());

        if (userEntity != null && DigestUtils.sha512Hex(user.getPassword()).equals(userEntity.getPassword())) {
            return UserMapper.mapToModel(userEntity);
        }

        return null;
    }
}
