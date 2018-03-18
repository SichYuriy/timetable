package com.gmail.at.sichyuriyy.common.dto;

public interface ToDtoTransformer<Entity, Dto> {
    Dto toDto(Entity entity);
}
