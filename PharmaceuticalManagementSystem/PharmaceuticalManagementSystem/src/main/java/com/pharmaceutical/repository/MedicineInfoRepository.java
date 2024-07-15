package com.pharmaceutical.repository;

import com.pharmaceutical.model.MedicineInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineInfoRepository extends JpaRepository<MedicineInfo, Long> {

    /**
     * Custom query method to find MedicineInfo entities based on search criteria.
     *
     * @param searchTerm The term to search for in medicine name, type, or strength volume.
     *                   If null, ignores this filter.
     * @return A list of MedicineInfo entities that match the search criteria.
     */
    @Query("SELECT m FROM MedicineInfo m " +
           "WHERE (:searchTerm IS NULL OR LOWER(m.name) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) " +
           "OR (:searchTerm IS NULL OR LOWER(m.type) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) " +
           "OR (:searchTerm IS NULL OR LOWER(m.strengthVolume) LIKE LOWER(CONCAT('%', :searchTerm, '%')))")
    List<MedicineInfo> findByCriteria(@Param("searchTerm") String searchTerm);
}
