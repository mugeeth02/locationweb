package com.mugeeth.location.repos;

import com.mugeeth.location.entities.Location;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LocationRepository extends JpaRepository<Location,Integer> {

        @Query("select type,count(type) from location g roup by type")
        public List<Object[]> findTypeAndTypeCount();
}
