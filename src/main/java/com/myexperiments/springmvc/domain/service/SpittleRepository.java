package com.myexperiments.springmvc.domain.service;

import com.myexperiments.springmvc.domain.model.Spittle;

import java.util.List;

public interface SpittleRepository {

    /*
    * The findSpittles() method takes two parameters. The max parameter is a Spittle ID
    * that represents the maximum ID of any Spittle that should be returned. As for the
    * count parameter, it indicates how many Spittle objects to return.
    **/
    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long id);
}
