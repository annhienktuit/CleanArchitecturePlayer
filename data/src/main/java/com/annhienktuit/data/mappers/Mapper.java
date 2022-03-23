package com.annhienktuit.data.mappers;

import java.util.List;

/**
 * Created by Nhien Nguyen on 3/23/2022
 */
public interface Mapper<E, M> {

    E fromModel(M model);

    M fromEntity(E entity);

    List<E> fromModel(List<M> modelList);

    List<M> fromEntity(List<E> entityList);

}
