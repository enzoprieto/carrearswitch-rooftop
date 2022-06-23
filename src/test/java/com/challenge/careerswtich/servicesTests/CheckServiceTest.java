package com.challenge.careerswtich.servicesTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.challenge.careerswtich.DTOs.ResponseStatsDTO;
import com.challenge.careerswtich.services.CheckService;
import com.challenge.careerswtich.utils.Textos;

@ExtendWith(MockitoExtension.class)
public class CheckServiceTest {

	@InjectMocks
	@Spy
	private CheckService service;

	@Test
	@DisplayName("Caso success check. ")
	public void test1() {

		Mockito.doReturn(DatosCheckServiceTest.getBlocks()).when(service).getBlocks();
		Mockito.doReturn(DatosCheckServiceTest.getValidation()).when(service).validator(Mockito.any());
		ResponseStatsDTO stat = service.check();
		Mockito.verify(service, Mockito.times(1)).getBlocks();
		assertNotNull(stat);
		assertEquals(DatosCheckServiceTest.getResponse().getError(), stat.getError());
	}

	@Test
	@DisplayName("Caso error al consumir servicio que devuelve blocks. ")
	public void test2() {

		Mockito.doReturn(null).when(service).getBlocks();
		ResponseStatsDTO stat = service.check();
		Mockito.verify(service, Mockito.times(1)).getBlocks();
		assertEquals(Textos.ERROR_BLOCKS, stat.getError());

	}

}
