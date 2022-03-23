package com.annhienktuit.data.mappers;

/**
 * Created by Nhien Nguyen on 3/23/2022
 */
public interface Mapper<E, M> {

    E fromModel(M model);

    M fromEntity(E entity);

}
