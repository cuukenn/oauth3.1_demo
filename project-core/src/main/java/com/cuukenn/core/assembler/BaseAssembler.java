/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.cuukenn.core.assembler;

import com.cuukenn.core.base.BaseDTO;
import com.cuukenn.core.base.BaseEntity;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * dto entity转换描述
 * 作为mapstruct的mapper基类使用
 *
 * @author changgg
 */
public interface BaseAssembler<D extends BaseDTO, E extends BaseEntity> {

    /**
     * DTO转Entity
     *
     * @param dto 传输对象
     * @return 实体
     */
    E toEntity(D dto);

    /**
     * Entity转DTO
     *
     * @param entity 实体
     * @return 传输对象
     */
    D toDto(E entity);

    /**
     * 根据dto更新entity的内容
     *
     * @param dto    传输对象
     * @param entity 实体
     */
    void updateEntity(D dto, @MappingTarget E entity);

    /**
     * DTO集合转Entity集合
     *
     * @param dtoList 传输对象集合
     * @return 实体集合
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * Entity集合转DTO集合
     *
     * @param entityList 实体集合
     * @return 传输对象集合
     */
    List<D> toDto(List<E> entityList);
}
