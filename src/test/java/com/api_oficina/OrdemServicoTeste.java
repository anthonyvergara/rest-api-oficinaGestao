package com.api_oficina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.api.oficina.exceptions.ValidacaoException;
import com.api.oficina.exceptions.ValidacaoExceptionHandler;
import com.api.oficina.model.Cliente;
import com.api.oficina.model.DetalheServico;
import com.api.oficina.model.Oficina;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Pagamento;
import com.api.oficina.model.Parcela;
import com.api.oficina.model.StatusOrdemServico;
import com.api.oficina.modelEnum.Status;
import com.api.oficina.modelEnum.TipoPagamento;
import com.api.oficina.repository.ClienteRepository;
import com.api.oficina.repository.OficinaRepository;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.service.DetalheServicoService;
import com.api.oficina.serviceImpl.DetalheServicoImpl;
import com.api.oficina.serviceImpl.OrdemServicoImpl;
import com.api.oficina.serviceImpl.PagamentoServiceImpl;
import com.api.oficina.serviceImpl.ParcelaServiceImpl;
import com.api.oficina.serviceImpl.StatusOrdemServicoImpl;

import jakarta.validation.ConstraintViolationException;

@ExtendWith(MockitoExtension.class)
public class OrdemServicoTeste {
	
	@Mock
	public OrdemServicoRepository ORDEM_SERVICO_REPOSITORY;
	@Mock
	private ClienteRepository CLIENTE_REPOSITORY;
	@Mock
	private OficinaRepository OFICINA_REPOSITORY;
	
	@Mock
	private DetalheServicoImpl DETALHE_SERVICO_SERVICE;
	
	@Mock
	private PagamentoServiceImpl PAGAMENTO_SERVICE;
	@Mock
	private StatusOrdemServicoImpl STATUS_ORDEM_SERVICO;
	@Mock
	private ParcelaServiceImpl PARCELA_SERVICE;
	
	@MockBean
    private ValidacaoExceptionHandler globalExceptionHandler;
	
	@InjectMocks
	private OrdemServicoImpl servico;
	
	private static Cliente cliente;
	private static Oficina oficina;
	private static OrdemServico ordemServico;
	private static DetalheServico detalheServico;
	private static Pagamento pagamento;
	private static StatusOrdemServico status;
	private static Parcela parcela1, parcela2;
	
	@BeforeEach
	void setUp() {
		cliente = new Cliente();
		cliente.setId(1L);
		oficina = new Oficina();
		oficina.setId(1L);
		detalheServico = new DetalheServico();
		parcela1 = new Parcela();
		parcela2 = new Parcela();
		pagamento = new Pagamento();
		ordemServico = new OrdemServico();
		status = new StatusOrdemServico();
		
		ordemServico.setQuantidadeParcelas(2);
		ordemServico.setStatusOrdemServico(status);
	}
	
	@Test
	void deveCriarOrdemServicoParceladaComPagamentoEmpty() {
		
		ordemServico.setId(1L);
		
		List<DetalheServico> detalhesDosServicos = new ArrayList<>();
		
		this.detalheServico.setValor(100);
		detalhesDosServicos.add(detalheServico);
		
		List<Parcela> listaDeParcelas = new ArrayList<>();
		this.parcela1.setValorParcela(50);
		this.parcela2.setValorParcela(50);
		listaDeParcelas.add(parcela1);
		listaDeParcelas.add(parcela2);
		
		ordemServico.setParcela(listaDeParcelas);
		ordemServico.setDetalheServico(detalhesDosServicos);
		ordemServico.setValorTotal(100);
		
		when(CLIENTE_REPOSITORY.findById(1L)).thenReturn(Optional.of(cliente));
		when(OFICINA_REPOSITORY.findById(1L)).thenReturn(Optional.of(oficina));
		
		when(ORDEM_SERVICO_REPOSITORY.save(this.ordemServico)).thenReturn(this.ordemServico);
		
		when(STATUS_ORDEM_SERVICO.save(1L)).thenReturn(status);
		
		when(DETALHE_SERVICO_SERVICE.save(1L, ordemServico.getDetalheServico())).thenAnswer(invocation -> {
			this.status.setTipoStatus(Status.AGENDADO.getCode());
			return detalhesDosServicos;
		});
		
		when(PAGAMENTO_SERVICE.save(1L, new ArrayList<Pagamento>())).thenAnswer(invocation -> {
			this.status.setSaldoDevedor(ordemServico.getValorTotal());
			return new ArrayList<Pagamento>();
		});
		
		when(PARCELA_SERVICE.save(1L, ordemServico.getQuantidadeParcelas())).thenReturn(listaDeParcelas);
		
		OrdemServico ordemCriada = servico.save(ordemServico, cliente.getId(), oficina.getId());
		
		verify(this.PARCELA_SERVICE).save(ordemServico.getId(), ordemServico.getQuantidadeParcelas());
		
		Assertions.assertEquals(Status.AGENDADO, ordemServico.getStatusOrdemServico().getTipoStatus());
		Assertions.assertTrue(this.status.getSaldoDevedor() > 0);
		
		Assertions.assertTrue(ordemServico.getPagamento().isEmpty());
		
		assertEquals(ordemServico.getQuantidadeParcelas(), ordemServico.getParcela().size());
	}
	
	@Test
	public void deveCriarOrdemServicoComPagamentoTotal() {
		
		ordemServico.setId(1L);
		
		List<Pagamento> listaDePagamentos = new ArrayList<>();
		this.pagamento.setValorPago(100);
		
		List<DetalheServico> detalhesDosServicos = new ArrayList<>();
		this.detalheServico.setValor(100);
		detalhesDosServicos.add(detalheServico);
		
		ordemServico.setDetalheServico(detalhesDosServicos);
		ordemServico.setPagamento(listaDePagamentos);
		ordemServico.setValorTotal(100);
		
		when(CLIENTE_REPOSITORY.findById(cliente.getId())).thenReturn(Optional.of(cliente));
		when(OFICINA_REPOSITORY.findById(oficina.getId())).thenReturn(Optional.of(oficina));
		
		when(ORDEM_SERVICO_REPOSITORY.save(this.ordemServico)).thenReturn(this.ordemServico);
		
		when(STATUS_ORDEM_SERVICO.save(ordemServico.getId())).thenReturn(status);
		
		when(DETALHE_SERVICO_SERVICE.save(ordemServico.getId(), ordemServico.getDetalheServico())).thenReturn(detalhesDosServicos);
		
		when(PAGAMENTO_SERVICE.save(ordemServico.getId(), new ArrayList<Pagamento>())).thenAnswer(invocation -> {
			this.ordemServico.getStatusOrdemServico().setTipoStatus(Status.PAGO.getCode());
			return listaDePagamentos;
		});
		
		OrdemServico ordemCriada = this.servico.save(ordemServico, cliente.getId(), oficina.getId());
		
		assertEquals(Status.PAGO, ordemServico.getStatusOrdemServico().getTipoStatus());
	}
	
}
