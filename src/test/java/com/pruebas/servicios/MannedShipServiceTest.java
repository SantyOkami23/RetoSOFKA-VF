package com.pruebas.servicios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.pruebas.exception.ShipNotFoundException;
import com.pruebas.model.domain.MannedShip;
import com.pruebas.model.entity.MannedShipEntity;
import com.pruebas.repositories.MannedShipDAO;
import com.pruebas.services.ImageService;
import com.pruebas.services.MannedShipService;

@RunWith(MockitoJUnitRunner.class)
class MannedShipServiceTest {

    @Mock
    private MannedShipDAO mannedShipDAO;

    @Mock
    private ImageService imageService;

    @InjectMocks
    private MannedShipService mannedShipService;

    @Test
    public void testGetAllShips() {
        // Arrange
        MannedShipEntity mannedShipEntity1 = new MannedShipEntity(1, 100, "Ship 1", "Type 1", "1000", "Country 1", "image1.jpg", 10, 10);
        MannedShipEntity mannedShipEntity2 = new MannedShipEntity(2, 200, "Ship 2", "Type 2", "2000", "Country 2", "image2.jpg", 20, 20);
        List<MannedShipEntity> mannedShipEntities = Arrays.asList(mannedShipEntity1, mannedShipEntity2);
        when(mannedShipDAO.findAll()).thenReturn(mannedShipEntities);
        when(imageService.getBase("image1.jpg")).thenReturn("base64image1");
        when(imageService.getBase("image2.jpg")).thenReturn("base64image2");

        // Act
        List<MannedShip> result = mannedShipService.getAllShips();

        // Assert
        assertEquals(2, result.size());
        MannedShip ship1 = result.get(0);
        assertEquals(100, ship1.getCosteProduccion());
        assertEquals("Ship 1", ship1.getNombre());
        assertEquals("Type 1", ship1.getTipo());
        assertEquals(1000, ship1.getPeso());
        assertEquals("Country 1", ship1.getPaisFabricacion());
        assertEquals("base64image1", ship1.getImagen());
        assertEquals(10, ship1.getCapacidadPersonas());
        assertEquals(10, ship1.getVelocidad());
        MannedShip ship2 = result.get(1);
        assertEquals(200, ship2.getCosteProduccion());
        assertEquals("Ship 2", ship2.getNombre());
        assertEquals("Type 2", ship2.getTipo());
        assertEquals(2000, ship2.getPeso());
        assertEquals("Country 2", ship2.getPaisFabricacion());
        assertEquals("base64image2", ship2.getImagen());
        assertEquals(20, ship2.getCapacidadPersonas());
        assertEquals(20, ship2.getVelocidad());
    }

    @Test
    void testGetShipById() {
        // Arrange
        MannedShipEntity mannedShipEntity = new MannedShipEntity(1, 100, "Ship 1", "Type 1", "1000", "Country 1", "image1.jpg", 10, 10);
        when(mannedShipDAO.findById(1)).thenReturn(Optional.of(mannedShipEntity));
        when(imageService.getBase("image1.jpg")).thenReturn("base64image1");

        // Act
        MannedShip result = mannedShipService.getShipById(1);

        // Assert
        assertEquals(100, result.getCosteProduccion());
        assertEquals("Ship 1", result.getNombre());
        assertEquals("Type 1", result.getTipo());
        assertEquals(1000, result.getPeso());
        assertEquals("Country 1", result.getPaisFabricacion());
        assertEquals("base64image1", result.getImagen());
        assertEquals(10, result.getCapacidadPersonas());
        assertEquals(10, result.getVelocidad());
    }

    @Test
    void testGetShipById_ShipNotFound() {
        // Arrange
        when(mannedShipDAO.findById(1)).thenReturn(Optional.empty());

        // Act
        try {
            mannedShipService.getShipById(1);
            fail("Expected ShipNotFoundException to be thrown");
        } catch (ShipNotFoundException e) {
            assertEquals("The Ship is not registered", e.getMessage());
        }
    }

    @Test
    void testSaveShip() throws Exception {
        // Arrange
        MannedShip mannedShip = new MannedShip(1, 100, "Ship 1", "Country 1", "image1.jpg", "Type 1", "1000", 10, 10);
        //when(mannedShipDAO.existsById(1)).thenReturn(false);

        // Act
        ResponseEntity<MannedShip> result = mannedShipService.saveShip(mannedShip);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mannedShip, result.getBody());
        verify(mannedShipDAO).save(new MannedShipEntity(1, 100, "Ship 1", "Type 1", "1000", "Country 1", "image1.jpg", 10, 10));
    }       		

        		
}