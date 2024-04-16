package com.lu.cloud.service;


import com.lu.cloud.entities.Pay;

import java.util.List;

/**
 * @auther zzyy
 * @create 2023-11-03 18:40
 */
public interface PayService {
    int add(Pay pay);

    int delete(Integer id);

    int update(Pay pay);

    Pay getById(Integer id);

    List<Pay> getAll();
}
