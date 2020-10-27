package si.fri.rso.samples.users.models.converters;

import si.fri.rso.samples.users.lib.User;
import si.fri.rso.samples.users.models.entities.UserEntity;

public class UserConverter {

    public static User toDto(UserEntity entity) {

        User dto = new User();
        dto.setUserId(entity.getId());
        dto.setCreated(entity.getCreated());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setLocation(entity.getLocation());

        return dto;

    }

    public static UserEntity toEntity(User dto) {

        UserEntity entity = new UserEntity();
        entity.setCreated(dto.getCreated());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setLocation(dto.getLocation());

        return entity;

    }

}
