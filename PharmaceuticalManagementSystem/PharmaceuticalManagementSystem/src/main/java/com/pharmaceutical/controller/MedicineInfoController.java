package com.pharmaceutical.controller;

import com.pharmaceutical.model.MedicineInfo;
import com.pharmaceutical.service.MedicineInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller for managing medicine information.
 */
@RestController
@RequestMapping("/pharmaceutical/medicines")
public class MedicineInfoController {

    @Autowired
    private MedicineInfoService medicineInfoService;

    /**
     * Get all medicines.
     *
     * @return a ResponseEntity containing the status and list of all medicines.
     */
    @GetMapping("/getAll")
    public ResponseEntity<Map<String, Object>> getAllMedicines() {
        Map<String, Object> response = medicineInfoService.findAll();
        if ("success".equals(response.get("status"))) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * Get a medicine by its ID.
     *
     * @param id the ID of the medicine.
     * @return a ResponseEntity containing the status and the medicine data if found.
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<Map<String, Object>> getMedicineById(@PathVariable Long id) {
        Map<String, Object> response = medicineInfoService.findById(id);
        if ("success".equals(response.get("status"))) {
            return ResponseEntity.ok(response);
        } else if ("error".equals(response.get("status")) && "Medicine not found.".equals(response.get("message"))) {
            return ResponseEntity.status(404).body(response);
        } else {
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * Create a new medicine.
     *
     * @param medicineInfo the information of the new medicine.
     * @return a ResponseEntity containing the status and the created medicine data.
     */
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> createMedicine(@RequestBody MedicineInfo medicineInfo) {
        Map<String, Object> response = medicineInfoService.save(medicineInfo);
        if ("success".equals(response.get("status"))) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(500).body(response);
        }
    }

    /**
     * Update an existing medicine.
     *
     * @param id the ID of the medicine to be updated.
     * @param medicineInfo the updated information of the medicine.
     * @return a ResponseEntity containing the status and the updated medicine data if successful.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateMedicine(@PathVariable Long id, @RequestBody MedicineInfo medicineInfo) {
        Map<String, Object> findResponse = medicineInfoService.findById(id);
        if ("success".equals(findResponse.get("status"))) {
            MedicineInfo existingMedicineInfo = (MedicineInfo) findResponse.get("data");
            existingMedicineInfo.setName(medicineInfo.getName());
            existingMedicineInfo.setAvailableQuantity(medicineInfo.getAvailableQuantity());
            existingMedicineInfo.setMinQuantity(medicineInfo.getMinQuantity());
            existingMedicineInfo.setPrice(medicineInfo.getPrice());

            Map<String, Object> saveResponse = medicineInfoService.save(existingMedicineInfo);

            if ("success".equals(saveResponse.get("status"))) {
                saveResponse.put("message", "Medicine with ID " + id + " updated successfully.");
                return ResponseEntity.ok(saveResponse);
            } else {
                return ResponseEntity.status(500).body(saveResponse);
            }
        } else if ("error".equals(findResponse.get("status")) && "Medicine not found.".equals(findResponse.get("message"))) {
            return ResponseEntity.status(404).body(findResponse);
        } else {
            return ResponseEntity.status(500).body(findResponse);
        }
    }

    /**
     * Delete a medicine by its ID.
     *
     * @param id the ID of the medicine to be deleted.
     * @return a ResponseEntity containing the status and deletion confirmation.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteMedicine(@PathVariable Long id) {
        Map<String, Object> findResponse = medicineInfoService.findById(id);
        if ("success".equals(findResponse.get("status"))) {
            Map<String, Object> deleteResponse = medicineInfoService.deleteById(id);
            return ResponseEntity.ok(deleteResponse);
        } else if ("error".equals(findResponse.get("status")) && "Medicine not found.".equals(findResponse.get("message"))) {
            return ResponseEntity.status(404).body(findResponse);
        } else {
            return ResponseEntity.status(500).body(findResponse);
        }
    }

    /**
     * Search for medicines based on a search term.
     *
     * @param searchTerm the term to search for.
     * @return a ResponseEntity containing the status and the search results.
     */
    @GetMapping("/searchTerm")
    public ResponseEntity<Map<String, Object>> searchMedicines(@RequestParam(required = false) String searchTerm) {
        Map<String, Object> response = medicineInfoService.searchMedicines(searchTerm);
        if ("success".equals(response.get("status"))) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(404).body(response);
        }
    }
}
