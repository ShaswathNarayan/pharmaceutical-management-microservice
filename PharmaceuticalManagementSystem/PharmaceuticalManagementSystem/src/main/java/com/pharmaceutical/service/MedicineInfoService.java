package com.pharmaceutical.service;

import com.pharmaceutical.model.MedicineInfo;
import com.pharmaceutical.repository.MedicineInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Service class for handling operations related to MedicineInfo entities.
 */
@Service
public class MedicineInfoService {
    
    private static final Logger logger = Logger.getLogger(MedicineInfoService.class.getName());

    private final MedicineInfoRepository medicineInfoRepository;

    @Autowired
    public MedicineInfoService(MedicineInfoRepository medicineInfoRepository) {
        this.medicineInfoRepository = medicineInfoRepository;
    }

    /**
     * Retrieves all medicines.
     *
     * @return A map containing status and either list of medicines (if successful) or error message (if failed).
     */
    public Map<String, Object> findAll() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<MedicineInfo> medicines = medicineInfoRepository.findAll();
            response.put("status", "success");
            response.put("data", medicines);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to retrieve medicines.");
        }
        return response;
    }

    /**
     * Retrieves a medicine by its ID.
     *
     * @param id The ID of the medicine to retrieve.
     * @return A map containing status and either medicine object (if found) or error message (if not found or failed).
     */
    public Map<String, Object> findById(Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            MedicineInfo medicineInfo = medicineInfoRepository.findById(id).orElse(null);
            if (medicineInfo == null) {
                response.put("status", "error");
                response.put("message", "Medicine not found.");
            } else {
                response.put("status", "success");
                response.put("data", medicineInfo);
            }
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to retrieve medicine.");
        }
        return response;
    }

    /**
     * Saves a new medicine or updates an existing one.
     *
     * @param medicineInfo The medicine object to save or update.
     * @return A map containing status and either saved medicine object (if successful) or error message (if failed).
     */
    public Map<String, Object> save(MedicineInfo medicineInfo) {
        Map<String, Object> response = new HashMap<>();
        try {
            if (medicineInfo.getAvailableQuantity() < medicineInfo.getMinQuantity()) {
                System.out.println("Purchase Alert: Available quantity is below the minimum quantity");
            }
            MedicineInfo savedMedicineInfo = medicineInfoRepository.save(medicineInfo);
            response.put("status", "success");
            response.put("data", savedMedicineInfo);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to save medicine.");
        }
        return response;
    }

    /**
     * Deletes a medicine by its ID.
     *
     * @param id The ID of the medicine to delete.
     * @return A map containing status and either success message (if deletion successful) or error message (if failed).
     */
    public Map<String, Object> deleteById(Long id) {
        Map<String, Object> response = new HashMap<>();
        try {
            medicineInfoRepository.deleteById(id);
            response.put("status", "success");
            response.put("message", "Medicine deleted successfully.");
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to delete medicine.");
        }
        return response;
    }
    
    /**
     * Searches medicines based on a search term.
     *
     * @param searchTerm The term to search for in medicine names, types, and strength/volume.
     * @return A map containing status and either list of medicines (if found) or error message (if not found or failed).
     */
    public Map<String, Object> searchMedicines(String searchTerm) {
        Map<String, Object> response = new HashMap<>();
        logger.info("Searching medicines with term: " + searchTerm);
        List<MedicineInfo> results = medicineInfoRepository.findByCriteria(searchTerm);
        if (results.isEmpty()) {
            response.put("status", "error");
            response.put("message", "No medicines found! Try to search other medicines.");
        } else {
            response.put("status", "success");
            response.put("data", results);
        }
        logger.info("Found " + results.size() + " medicines");
        return response;
    }
}
