package com.isa.bloodtransfusion;

import com.isa.bloodtransfusion.models.*;
import com.isa.bloodtransfusion.repositories.AddressRepository;
import com.isa.bloodtransfusion.repositories.AppointmentsRepository;
import com.isa.bloodtransfusion.repositories.CenterRepository;
import com.isa.bloodtransfusion.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
public class BloodTransfusionApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloodTransfusionApplication.class, args);
	}

	@Bean
	public CommandLineRunner preloadData(CenterRepository centerRepository,
										 UserRepository userRepository,
										 AppointmentsRepository appointmentsRepository,
										 AddressRepository addressRepository,
										 PasswordEncoder passwordEncoder) {
		return (args) -> {
			var user1 = User.builder().id(1L)
					.email("ana@gmail.com")
					.name("Ana")
					.lastName("Markovic")
					.username("ana")
					.password(passwordEncoder.encode("test123"))
					.role(ERole.MEDICAL_WORKER)
					.gender(Gender.F)
					.accountVerified(true)
					.appointments(new ArrayList<>())
					.centers(new ArrayList<>())
					.phone("123-324")
					.cancellations(new ArrayList<>())
					.jmbg("9090909090909")
					.proffesion("test1")
					.state("SRB")
					.city("NS")
					.build();

			var user2 = User.builder().id(2L)
					.email("petar@gmail.com")
					.name("Petar")
					.lastName("Petrovic")
					.username("petar")
					.password(passwordEncoder.encode("test123"))
					.role(ERole.ADMIN)
					.gender(Gender.M)
					.accountVerified(true)
					.appointments(new ArrayList<>())
					.centers(new ArrayList<>())
					.phone("222-324")
					.cancellations(new ArrayList<>())
					.jmbg("8080808080808")
					.proffesion("test3")
					.state("SRB")
					.city("NS")
					.build();

			var user3 = User.builder().id(3L)
					.email("irina@gmail.com")
					.name("Irina")
					.lastName("Jobanovic")
					.username("irina")
					.password(passwordEncoder.encode("test123"))
					.role(ERole.CUSTOMER)
					.gender(Gender.M)
					.accountVerified(true)
					.appointments(new ArrayList<>())
					.centers(new ArrayList<>())
					.phone("555-324")
					.cancellations(new ArrayList<>())
					.jmbg("5050505050505")
					.proffesion("test56")
					.state("SRB")
					.city("NS")
					.build();

			userRepository.save(user1);
			userRepository.save(user2);
			userRepository.save(user3);

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

			var address3 = Address.builder()
					.id(2L)
					.streetName("Nisavska")
					.streetNumber("55")
					.city("Nis")
					.state("Srbija")
					.build();

			addressRepository.save(address1);
			addressRepository.save(address2);
			addressRepository.save(address3);

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

			var center3 = Center.builder()
					.id(3L)
					.name("Zdravlje")
					.address(address3)
					.description("Description 3")
					.averageRate(3.7)
					.build();

			centerRepository.save(center1);
			centerRepository.save(center2);
			centerRepository.save(center3);

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

			var appointment4 = Appointment.builder()
					.id(4L)
					.start(LocalDateTime.now().plusDays(10))
					.center(center3)
					.build();

			var appointment5 = Appointment.builder()
					.id(5L)
					.start(LocalDateTime.now().plusDays(11))
					.center(center3)
					.build();

			var appointment6 = Appointment.builder()
					.id(6L)
					.start(LocalDateTime.now().plusDays(12))
					.center(center3)
					.build();

			var appointment7 = Appointment.builder()
					.id(7L)
					.start(LocalDateTime.now().minusMonths(1))
					.center(center1)
					.user(user3)
					.medicalWorker(user1)
					.build();

			appointmentsRepository.save(appointment1);
			appointmentsRepository.save(appointment2);
			appointmentsRepository.save(appointment3);
			appointmentsRepository.save(appointment4);
			appointmentsRepository.save(appointment5);
			appointmentsRepository.save(appointment6);
			appointmentsRepository.save(appointment7);

			user3.getAppointments().add(appointment7);
			userRepository.save(user3);

		};
	}

}
