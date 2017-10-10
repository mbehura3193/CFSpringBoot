package com.example.springpostgresql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class SpringpostgresqlApplication implements CommandLineRunner {

	@Autowired
	private ApplicationMetaRepository applicationMetaRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringpostgresqlApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		ApplicationMeta applicationMeta = new ApplicationMeta();
		applicationMeta.setName("MyNewApp");
		applicationMeta.setDescription("This is a completely new application");
		applicationMetaRepository.save(applicationMeta);

		ApplicationMeta applicationMeta1 = new ApplicationMeta();
		applicationMeta1.setName("CoolApp");
		applicationMeta1.setDescription("This is cool application!");
		applicationMetaRepository.save(applicationMeta1);
	}

	@RestController
	public static class ApplicationMetaController {

		@Autowired
		private ApplicationMetaRepository applicationMetaRepository;

		@GetMapping(value = "/apps/{name}")
		public ResponseEntity<ApplicationMeta> applicationData(@PathVariable String name) {

			ApplicationMeta applicationMeta = applicationMetaRepository.findByName(name);
			return ResponseEntity.ok().body(applicationMeta);
		}

		@GetMapping(value = "/apps")
		public ResponseEntity<List<ApplicationMeta>> apps() {

			List<ApplicationMeta> applicationMetaList = applicationMetaRepository.findAll();
			return ResponseEntity.ok(applicationMetaList);
		}
	}
}
