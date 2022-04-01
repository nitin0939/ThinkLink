package com.ThinkLink.repository;

import com.ThinkLink.entities.AssetHistoricalData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DataRepository extends CrudRepository<AssetHistoricalData, Integer> {
    /*@Query(value = "SELECT t FROM Data t WHERE t.date = ?1 ORDER by id asc")
    List<AssetHistoricalData> findByDate(String date, Pageable pageable);*/

    @Query(value = "SELECT * FROM Data t WHERE t.date = ?1 ORDER by id asc limit ?2 offset ?3",nativeQuery = true)
    List<AssetHistoricalData> findByDate(String date, int limit, int offset);
}
