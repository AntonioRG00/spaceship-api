package com.antoniorg.spaceship.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.antoniorg.spaceship.application.dto.SpaceshipCreateRequest;
import com.antoniorg.spaceship.application.dto.SpaceshipResponse;
import com.antoniorg.spaceship.application.dto.SpaceshipUpdateRequest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;

public interface SpaceshipRestUI {

	@Operation(summary = "Obtener todas las naves espaciales con paginación")
	@Parameters({ @Parameter(description = "Número de la página", example = "0", name = "page"),
			@Parameter(description = "Tamaño por página", example = "10", name = "size"),
			@Parameter(description = "Campo de ordenación", example = "id", name = "sortBy"),
			@Parameter(description = "Sentido de ordenación", example = "asc", name = "sortDirection") })
	public ResponseEntity<Page<SpaceshipResponse>> getAllSpaceships(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "ASC") Direction sortDirection);

	@Operation(summary = "Obtener la nave espacial por ID")
	@Parameters({ @Parameter(description = "ID de la nave espacial", example = "1", name = "id") })
	public ResponseEntity<SpaceshipResponse> getSpaceshipById(@PathVariable Long id);

	@Operation(summary = "Obtener todas las naves espaciales que contienen en su nombre")
	@Parameters({ @Parameter(description = "Nombre de la nave espacial", example = "Millennium", name = "Nombre") })
	public ResponseEntity<List<SpaceshipResponse>> getSpaceshipsByName(@RequestParam String name);

	@Operation(summary = "Crear nave espacial")
	public ResponseEntity<SpaceshipResponse> createSpaceship(@RequestBody @Valid SpaceshipCreateRequest req);

	@Operation(summary = "Actualizar nave espacial")
	@Parameters({ @Parameter(description = "ID de la nave espacial", example = "1", name = "id") })
	public ResponseEntity<SpaceshipResponse> updateSpaceship(@PathVariable Long id,
			@RequestBody @Valid SpaceshipUpdateRequest req);

	@Operation(summary = "Borrar nave espacial por ID")
	@Parameters({ @Parameter(description = "ID de la nave espacial", example = "1", name = "id") })
	public ResponseEntity<Void> deleteSpaceship(@PathVariable Long id);
}
