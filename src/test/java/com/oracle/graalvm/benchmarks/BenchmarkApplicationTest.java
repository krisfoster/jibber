package com.oracle.graalvm.benchmarks;

import com.oracle.graalvm.benchmarks.jibber.JibberController;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BenchmarkApplicationTest {

    @Autowired
	private JibberController controller;

	@Test
	public void contextLoads() throws Exception {
			assertThat(controller).isNotNull();
	}
}
