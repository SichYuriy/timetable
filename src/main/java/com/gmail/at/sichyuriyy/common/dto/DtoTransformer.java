package com.gmail.at.sichyuriyy.common.dto;

public interface DtoTransformer<Entity, Dto>
        extends FromDtoTransformer<Entity, Dto>, ToDtoTransformer<Entity, Dto> {
}
