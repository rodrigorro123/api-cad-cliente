package br.com.apicadcli.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.api.application.controller.ApiController;
import br.com.api.application.service.CadClienteService;
import br.com.api.domain.repository.ClienteRepository;
import br.com.api.domain.repository.EnderecoRepository;
import br.com.api.domain.repository.TelefoneRepository;
import br.com.apicadcli.api.template.ClienteTemplate;
import lombok.RequiredArgsConstructor;

@SpringBootTest(
	    classes = {
	    		ApiController.class,
	    		CadClienteService.class,
	    		ClienteRepository.class,
	    		EnderecoRepository.class,
	    		TelefoneRepository.class,
	    })
	@EnableWebMvc
	@AutoConfigureMockMvc(addFilters = false)
	@RequiredArgsConstructor
public class ApiControllerTest {
	 
	  @Autowired private MockMvc mockMvc;
	  @MockBean private ClienteRepository clienteRepository;
	  @MockBean private EnderecoRepository enderecoRepository;
	  @MockBean private CadClienteService cadClienteService;
	  private final ClienteTemplate clienteTemplate = ClienteTemplate.getInstance();
	  
	  @Test
	  void test_get_all() throws Exception{
		  var clientes = Mockito.mock(Page.class);
		  clientes.getContent().add(clienteTemplate.getValidCliente());
		  Mockito.when(clienteRepository.findAllByStatus(Mockito.any(),Mockito.any()) ).thenReturn(clientes);

		  final MvcResult result =
		            mockMvc
		                .perform(get("/cad-cliente"))
		                .andExpect(MockMvcResultMatchers.status().isOk())
		                .andDo(print())
		                .andReturn();
		        
		        final var response = result.getResponse().getStatus();
		        Assertions.assertEquals(HttpStatus.OK.value() ,response );
	  }
	  
	  @Test
	   void test_get_cliente() throws Exception{
		  
		    Mockito.when(clienteRepository.findByIdAndStatus(Mockito.any(),Mockito.any()))
	        .thenReturn(Optional.of(clienteTemplate.getValidCliente()));
		    
		    final MvcResult result =
		            mockMvc
		                .perform(get("/cad-cliente/555"))
		                .andExpect(MockMvcResultMatchers.status().isOk())
		                .andDo(print())
		                .andReturn();
		        
		        final var response = result.getResponse().getStatus();
		        Assertions.assertEquals(HttpStatus.OK.value() ,response );
	  }

	  @Test
	   void test_create_cliente() throws Exception{
		    Mockito.when(clienteRepository.save(Mockito.any()))
	        .thenReturn(Optional.of(clienteTemplate.getValidCliente())); 
		    
		    String request = clienteTemplate.getValidClienteToString();
		    final MvcResult result =
		            mockMvc
		                .perform(
		                    post("/cad-cliente").contentType(MediaType.APPLICATION_JSON).content(request))
		                .andExpect(MockMvcResultMatchers.status().isAccepted())
		                .andDo(print())
		                .andReturn();
	        final var response = result.getResponse().getStatus();
	        Assertions.assertEquals(HttpStatus.ACCEPTED.value(), response);
	  }
	  
	  
	  @Test
	   void test_edit_cliente() throws Exception{
		  
		    Mockito.when(clienteRepository.findById(Mockito.any()))
	        .thenReturn(Optional.of(clienteTemplate.getValidCliente())); 
		    
		    Mockito.when(clienteRepository.save(Mockito.any()))
	        .thenReturn(Optional.of(clienteTemplate.getValidCliente())); 
		    
		    String request = clienteTemplate.getValidClienteToString();
		    final MvcResult result =
		            mockMvc
		                .perform(
		                    patch("/cad-cliente/55/edit").contentType(MediaType.APPLICATION_JSON).content(request))
		                .andExpect(MockMvcResultMatchers.status().isAccepted())
		                .andDo(print())
		                .andReturn();
	        final var response = result.getResponse().getStatus();
	        Assertions.assertEquals(HttpStatus.ACCEPTED.value(), response );
	  }
	  
	  @Test
	   void test_get_cliente_filter() throws Exception{
		   
			  var clientes = Mockito.mock(Page.class);
			  clientes.getContent().add(clienteTemplate.getValidCliente());
			  Mockito.when(clienteRepository.findAll(Mockito.any(), Mockito.any())).thenReturn(clientes);
		    
		    String request = clienteTemplate.getValidClienteFilterToString();
		    final MvcResult result =
		            mockMvc
		                .perform(
		                    get("/cad-cliente/filter").contentType(MediaType.APPLICATION_JSON).content(request))
		                .andExpect(MockMvcResultMatchers.status().isOk())
		                .andDo(print())
		                .andReturn();
	        final var response = result.getResponse().getStatus();
	        Assertions.assertEquals(HttpStatus.OK.value() , response );
	  }
	  
	  
	  @Test
	   void test_edit_end_cliente() throws Exception{
		  
		    Mockito.when(enderecoRepository.findByClienteAndTipo(Mockito.any(), Mockito.any()))
	        .thenReturn(clienteTemplate.getValidEnd()); 
		    
		    Mockito.when(clienteRepository.findByIdAndStatus(Mockito.any(), Mockito.any()))
	        .thenReturn(Optional.of(clienteTemplate.getValidCliente())); 
		    
		    Mockito.when(clienteRepository.save(Mockito.any()))
	        .thenReturn(Optional.of(clienteTemplate.getValidCliente())); 
		    
		    
		    String request = clienteTemplate.getValidClienteEditEndToString();
		    final MvcResult result =
		            mockMvc
		                .perform(
		                    patch("/cad-cliente/555/edit/comercial/endereco").contentType(MediaType.APPLICATION_JSON).content(request))
		                .andExpect(MockMvcResultMatchers.status().isAccepted())
		                .andDo(print())
		                .andReturn();
	        final var response = result.getResponse().getStatus();
	        Assertions.assertEquals(HttpStatus.ACCEPTED.value() , response);
	  }
	
	  @Test
	   void test_inactive_cliente() throws Exception{
		  
		    Mockito.when(clienteRepository.findByIdAndStatus(Mockito.any(),Mockito.any()))
	        .thenReturn(Optional.of(clienteTemplate.getValidCliente()));
		    
		    final MvcResult result =
		            mockMvc
		                .perform(delete("/cad-cliente/555"))
		                .andExpect(MockMvcResultMatchers.status().isAccepted())
		                .andDo(print())
		                .andReturn();
		        
		        final var response = result.getResponse().getStatus();
		        Assertions.assertEquals(HttpStatus.ACCEPTED.value() , response);
	  }
	  
	  @Test
	   void test_active_cliente() throws Exception{
		  
		    Mockito.when(clienteRepository.findByIdAndStatus(Mockito.any(),Mockito.any()))
	        .thenReturn(Optional.of(clienteTemplate.getValidCliente()));
		    
		    final MvcResult result =
		            mockMvc
		                .perform(delete("/cad-cliente/555/reativar"))
		                .andExpect(MockMvcResultMatchers.status().isAccepted())
		                .andDo(print())
		                .andReturn();
		        
		        final var response = result.getResponse().getStatus();
		        Assertions.assertEquals(HttpStatus.ACCEPTED.value(), response);
	  }

	  
 	  private <D, T> Page<D> mapEntityPageIntoDtoPage(Page<T> entities, Class<D> dtoClass) {
			var modelMapper = new ModelMapper();
		    return entities.map(objectEntity -> modelMapper.map(objectEntity, dtoClass));
		}
}
