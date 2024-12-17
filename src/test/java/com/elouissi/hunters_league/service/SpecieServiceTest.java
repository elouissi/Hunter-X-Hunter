package com.elouissi.hunters_league.service;

import com.elouissi.hunters_league.HuntersLeagueApplication;
import com.elouissi.hunters_league.domain.Species;
import com.elouissi.hunters_league.domain.enums.SpeciesType;
import com.elouissi.hunters_league.repository.SpecieRepository;
import com.elouissi.hunters_league.web.errors.NullVarException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

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

        species.setId(UUID.fromString("9c47d5b5-b1a0-4220-a557-d73cf84f0bf4"));
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
    void updateSpecies_nullId_throwsException() {
        species.setId(null);

        Exception exception = assertThrows(NullVarException.class, () -> {
            specieService.updateSpecies(species);
        });

        assertEquals("id is null", exception.getMessage());
    }


}