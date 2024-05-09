package com.project.CarPro.dto.response;

public class FleetResponseDTO {
    private Long id;
    private String fleetName;
    private ManagerResponseDTO managerResponseDTO;

    public FleetResponseDTO() {}

    public FleetResponseDTO(Long id, String fleetName, ManagerResponseDTO managerResponseDTO) {
        this.id = id;
        this.fleetName = fleetName;
        this.managerResponseDTO = managerResponseDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFleetName() {
        return fleetName;
    }

    public void setFleetName(String fleetName) {
        this.fleetName = fleetName;
    }



    public ManagerResponseDTO getManagerResponseDTO() {
        return managerResponseDTO;
    }

    public void setManagerResponseDTO(ManagerResponseDTO managerResponseDTO) {
        this.managerResponseDTO = managerResponseDTO;
    }
}
