package com.tiger.pocs;

import com.tiger.pocs.payload.SampleResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class AppTests {

	@Test
	void contextLoads() {

		//given
		var underTest = new SampleResponse();
		//when
		underTest.setStatus(SampleResponse.StatusEnum.PENDING);
		//then
		assertThat(underTest).isNotNull();
	}

}
