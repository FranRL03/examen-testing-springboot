package com.salesianostriana.dam.testing.examen.service;

import com.salesianostriana.dam.testing.examen.model.DatoMeteorologico;
import com.salesianostriana.dam.testing.examen.model.DatoMeterologicoPK;
import com.salesianostriana.dam.testing.examen.repo.DatoMeteorologicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServicioMeteorologicoTest {

    @Mock
    DatoMeteorologicoRepository repository;

    @InjectMocks
    ServicioMeteorologico servicioMeteorologico;

    @BeforeEach
    void cargar(){

//        DatoMeterologicoPK datoMeterologicoPK = new DatoMeterologicoPK

        DatoMeteorologico datoMeteorologico = DatoMeteorologico.builder()
                .id(new DatoMeterologicoPK("Sevilla", LocalDate.now()))
                .precipitacion(10000)
                .build();

    }
    @Test
    void mediaDiaSemana() {

        DatoMeterologicoPK datoMeterologicoPK;
        datoMeterologicoPK = new DatoMeterologicoPK("Sevilla", LocalDate.of(2024, 02, 9));

        DatoMeteorologico datoMeteorologico = DatoMeteorologico.builder()
                .id(datoMeterologicoPK)
                .precipitacion(10000.0)
                .build();

        when(repository.buscarPorPoblacion(anyString())).thenReturn(List.of(datoMeteorologico));

        Map<String, Double> result = servicioMeteorologico.mediaDiaSemana("Sevilla");

        assertEquals(Map.of("VIERNES", 10000.0), result);

    }
}