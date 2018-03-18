package com.gmail.at.sichyuriyy.common.dto;

public interface FromDtoTransformer<Entity, Dto> {
    Entity fromDto(Dto dto);
}
