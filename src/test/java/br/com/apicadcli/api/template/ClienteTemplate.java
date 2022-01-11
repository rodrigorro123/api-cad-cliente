package br.com.apicadcli.api.template;

import java.time.LocalDate;
import java.util.Arrays;

import br.com.api.domain.entity.Cliente;
import br.com.api.domain.entity.Endereco;
import br.com.api.domain.entity.Telefone;
import br.com.apicadcli.api.util.CpfUtil;
import lombok.Getter;

public class ClienteTemplate extends BaseTemplate{

	@Getter private static final ClienteTemplate instance = new ClienteTemplate();
	
	private final CpfUtil cpfUtil = new CpfUtil();
	private final static String COMERCIAL = "comercial";
	private final static String RESIDENCIAL = "residencial";
	
	
	public Endereco getValidEnd() {
		return Endereco.builder()
				.bairro(faker.address().stateAbbr())
				.cep(faker.address().zipCode())
				.cidade(faker.address().city())
				.estado(faker.address().state())
				.id(555L)
				.numero(Long.valueOf(faker.address().streetAddressNumber()))
				.pais(faker.address().country())
				.rua(faker.address().streetName())
				.tipo(faker.options().option(COMERCIAL,RESIDENCIAL))
			.build();		
	}
	
	public Cliente getValidCliente() {
		

		
		var telefone = Telefone.builder()
				.ddd(faker.phoneNumber().subscriberNumber())
				.tipo(faker.options().option(COMERCIAL,RESIDENCIAL))
				.numero(faker.phoneNumber().phoneNumber())
				.id(333L)
		.build();
		
		return Cliente.builder()
		.enderecos(Arrays.asList(getValidEnd()) )
		.telefones(Arrays.asList(telefone))
		.cpf(cpfUtil.cpf())
		.dataNascimento(LocalDate.now().minusMonths((int) Math.floor(Math.random()*(60)+18)))
		.id(222L)
		.build();
		
	}

	public String getValidClienteToString() {
		return  "{\r\n"
				+ "    \"cpf\": \"47778573016\",\r\n"
				+ "    \"nome\": \"rodrigo\",\r\n"
				+ "    \"sexo\": \"masculino\",\r\n"
				+ "    \"dataNascimento\": \"03/03/1980\",\r\n"
				+ "    \"telefones\": [\r\n"
				+ "        {\r\n"
				+ "            \"ddd\": \"34\",\r\n"
				+ "            \"numero\": \"12346\",\r\n"
				+ "            \"tipo\": \"residencial\"\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    \"enderecos\": [\r\n"
				+ "        {\r\n"
				+ "            \"tipo\": \"residencial\",\r\n"
				+ "            \"bairro\": \"aaa\",\r\n"
				+ "            \"cep\": \"123456\",\r\n"
				+ "            \"cidade\": \"rio\",\r\n"
				+ "            \"estado\": \"rio\",\r\n"
				+ "            \"numero\": 10,\r\n"
				+ "            \"pais\": \"brasil\",\r\n"
				+ "            \"rua\": \"qq rua\"\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
	}

	public String getValidClienteEditToString() {
		return  "{\r\n"
				+ "    \"cpf\": \"47778573016\",\r\n"
				+ "    \"nome\": \"rodrigo Rodrigues\",\r\n"
				+ "    \"sexo\": \"masculino\",\r\n"
				+ "    \"dataNascimento\": \"03/03/1980\",\r\n"
				+ "    \"telefones\": [\r\n"
				+ "        {\r\n"
				+ "            \"ddd\": \"34\",\r\n"
				+ "            \"numero\": \"12346\",\r\n"
				+ "            \"tipo\": \"residencial\"\r\n"
				+ "        }\r\n"
				+ "    ],\r\n"
				+ "    \"enderecos\": [\r\n"
				+ "        {\r\n"
				+ "            \"tipo\": \"residencial\",\r\n"
				+ "            \"bairro\": \"aaa\",\r\n"
				+ "            \"cep\": \"123456\",\r\n"
				+ "            \"cidade\": \"rio\",\r\n"
				+ "            \"estado\": \"rio\",\r\n"
				+ "            \"numero\": 10,\r\n"
				+ "            \"pais\": \"brasil\",\r\n"
				+ "            \"rua\": \"qq rua\"\r\n"
				+ "        }\r\n"
				+ "    ]\r\n"
				+ "}";
	}

	public String getValidClienteFilterToString() {
		
		return "{\r\n"
				+ "    \"nome\": \"miguel\"\r\n"
				+ "}";
	}
	
	public String getValidClienteEditEndToString() {
		
		return "{\r\n"
				+ "    \"tipo\": \"comercial\",\r\n"
				+ "    \"bairro\": \"bbbbb\",\r\n"
				+ "    \"cep\": \"123456\",\r\n"
				+ "    \"cidade\": \"rio\",\r\n"
				+ "    \"estado\": \"rio\",\r\n"
				+ "    \"numero\": 10,\r\n"
				+ "    \"pais\": \"brasil\",\r\n"
				+ "    \"rua\": \"qq rua\"\r\n"
				+ "}";
	}
}
