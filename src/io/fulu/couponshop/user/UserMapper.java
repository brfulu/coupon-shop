package io.fulu.couponshop.user;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserEntity mapToEntity(User user) {
        if (user == null) {
            return null;
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(user.getId());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setRole(UserRole.valueOf(user.getRole()));
        userEntity.setUsername(user.getUsername());
        userEntity.setPassword(user.getPassword());

        return userEntity;
    }

    public static User mapToModel(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        User user = new User();
        user.setId(userEntity.getId());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setRole(userEntity.getRole().toString());
        user.setUsername(userEntity.getUsername());

        return user;
    }

    public static List<User> mapToModelList(List<UserEntity> userEntities) {
        return userEntities.stream().map(UserMapper::mapToModel).collect(Collectors.toList());
    }
}
