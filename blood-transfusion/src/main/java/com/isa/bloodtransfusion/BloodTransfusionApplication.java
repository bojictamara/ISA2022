package com.isa.bloodtransfusion;

import com.isa.bloodtransfusion.models.Address;
import com.isa.bloodtransfusion.models.Appointment;
import com.isa.bloodtransfusion.models.Center;
import com.isa.bloodtransfusion.models.User;
import com.isa.bloodtransfusion.repositories.AddressRepository;
import com.isa.bloodtransfusion.repositories.AppointmentsRepository;
import com.isa.bloodtransfusion.repositories.CenterRepository;
import com.isa.bloodtransfusion.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class BloodTransfusionApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodTransfusionApplication.class, args);
	}

	@Bean
	public CommandLineRunner preloadData(CenterRepository centerRepository,
										 UserRepository userRepository,
										 AppointmentsRepository appointmentsRepository,
										 AddressRepository addressRepository) {
		return (args) -> {
			var user1 = User.builder().id(1L)
					.email("ana@gmail.com")
					.name("Ana")
					.lastName("Markovic")
					.username("ana")
					.password("$2a$10$v43BWTGh8kF8jj68BpMpnuaf5Cq4rciYoAV.9w0OKxxob.bchlgBK")
					.accountVerified(true)
					.build();

			var user2 = User.builder().id(2L)
					.email("petar@gmail.com")
					.name("Petar")
					.lastName("Petrovic")
					.username("petar")
					.password("$2a$10$v43BWTGh8kF8jj68BpMpnuaf5Cq4rciYoAV.9w0OKxxob.bchlgBK")
					.accountVerified(true)
					.build();

			userRepository.save(user1);
			userRepository.save(user2);

			var address1 = Address.builder()
					.id(1L)
					.streetName("Radnicka")
					.streetNumber("2b")
					.city("Novi Sad")
					.state("Srbija")
					.build();

			var address2 = Address.builder()
					.id(2L)
					.streetName("Pobednicka")
					.streetNumber("144")
					.city("Beograd")
					.state("Srbija")
					.build();

			addressRepository.save(address1);
			addressRepository.save(address2);

			var center1 = Center.builder()
					.id(1L)
					.address(address1)
					.name("Krv nije voda")
					.description("Description 1")
					.averageRate(3.2)
					.build();

			var center2 = Center.builder()
					.id(2L)
					.name("Plava krv")
					.address(address2)
					.description("Description 2")
					.averageRate(4.5)
					.build();

			centerRepository.save(center1);
			centerRepository.save(center2);

			var appointment1 = Appointment.builder()
					.id(1L)
					.start(LocalDateTime.now().plusDays(2))
					.user(user1)
					.center(center1)
					.build();

			var appointment2 = Appointment.builder()
					.id(2L)
					.start(LocalDateTime.now().plusDays(3))
					.center(center1)
					.build();

			var appointment3 = Appointment.builder()
					.id(3L)
					.start(LocalDateTime.now().plusDays(1))
					.center(center2)
					.build();

			appointmentsRepository.save(appointment1);
			appointmentsRepository.save(appointment2);
			appointmentsRepository.save(appointment3);

		};
	}

}
