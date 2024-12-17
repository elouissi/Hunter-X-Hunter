package com.elouissi.hunters_league.service;

import com.elouissi.hunters_league.domain.Species;
import com.elouissi.hunters_league.domain.enums.SpeciesType;
import com.elouissi.hunters_league.repository.SpecieRepository;
import com.elouissi.hunters_league.web.errors.NullVarException;
import com.elouissi.hunters_league.web.errors.ObjectAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpecieServiceTest {

    @Mock
    private SpecieRepository specieRepository;

    @InjectMocks
    private SpecieService specieService;

    private Species species;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        species = new Species();
        species.setId(UUID.randomUUID());
        species.setName("Lion");
        species.setCategory(SpeciesType.SEA);
    }

    @Test
    void getALl() {
        when(specieRepository.findAll()).thenReturn(Arrays.asList(species));

        List<Species> result = specieService.getALl();

        assertEquals(1, result.size());
        assertEquals("Lion", result.get(0).getName());
        verify(specieRepository, times(1)).findAll();
    }

    @Test
    void save() {
        when(specieRepository.save(any(Species.class))).thenReturn(species);

        Species result = specieService.save(species);

        assertNotNull(result);
        assertEquals("Lion", result.getName());
        verify(specieRepository, times(1)).save(species);
    }

    @Test
    void getSpeciesByname() {
        when(specieRepository.findByName("Lion")).thenReturn(Optional.of(species));

        Optional<Species> result = specieService.getSpeciesByname("Lion");

        assertTrue(result.isPresent());
        assertEquals("Lion", result.get().getName());
        verify(specieRepository, times(1)).findByName("Lion");
    }

    @Test
    void remove() {
        doNothing().when(specieRepository).delete(species);

        Species result = specieService.remove(species);

        assertEquals("Lion", result.getName());
        verify(specieRepository, times(1)).delete(species);
    }

    @Test
    void getAllByCategory() {
        when(specieRepository.getSpeciesByCategory(SpeciesType.SEA)).thenReturn(Arrays.asList(species));

        List<Species> result = specieService.getAllByCategory(SpeciesType.SEA);

        assertEquals(1, result.size());
        assertEquals(SpeciesType.SEA, result.get(0).getCategory());
        verify(specieRepository, times(1)).getSpeciesByCategory(SpeciesType.SEA);
    }

    @Test
    void updateSpecies_success() {
        when(specieRepository.findById(1L)).thenReturn(Optional.of(species));
        when(specieRepository.save(any(Species.class))).thenReturn(species);

        species.setName("Tiger");

        Species result = specieService.updateSpecies(species);

        assertEquals("Tiger", result.getName());
        verify(specieRepository, times(1)).findById(1L);
        verify(specieRepository, times(1)).save(species);
    }

    @Test
    void updateSpecies_nullId_throwsException() {
        species.setId(null);

        Exception exception = assertThrows(NullVarException.class, () -> {
            specieService.updateSpecies(species);
        });

        assertEquals("id is null", exception.getMessage());
    }

    @Test
    void updateSpecies_existingName_throwsException() {
        Species existingSpecies = new Species();
        existingSpecies.setId(UUID.randomUUID());
        existingSpecies.setName("Tiger");

        when(specieRepository.findById(1L)).thenReturn(Optional.of(species));
        when(specieRepository.findByName("Tiger")).thenReturn(Optional.of(existingSpecies));

        species.setName("Tiger");

        Exception exception = assertThrows(ObjectAlreadyExistException.class, () -> {
            specieService.updateSpecies(species);
        });

        assertEquals("Speciesname already exists", exception.getMessage());
    }
}
