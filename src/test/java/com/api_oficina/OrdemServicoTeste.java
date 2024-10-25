package com.api_oficina;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mockito.junit.jupiter.MockitoExtension;

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
	
	@InjectMocks
	private OrdemServicoImpl servico;
	
	private static Cliente cliente;
	private static Oficina oficina;
	private static OrdemServico ordemServico;
	private static DetalheServico detalheServico;
	private static Pagamento pagamento;
	private static StatusOrdemServico status;
	private static Parcela parcela1, parcela2;
	
	private List<DetalheServico> detalhesDosServicos;
	private List<Parcela> listaDeParcelas;
	private List<Pagamento> listaPagamento;
	
	@BeforeEach
	void setUp() {
		cliente = new Cliente();
		cliente.setId(30L);
		cliente.setNome("Anthony");
		cliente.setSobrenome("Vergara");
		
		oficina = new Oficina();
		oficina.setId(20L);
		oficina.setNomeOficina("Careca Motors");
		
		detalheServico = new DetalheServico();
		detalheServico.setId(1L);
		detalheServico.setData(LocalDateTime.now());
		detalheServico.setDescricao("Realizado troca de oleo");
		detalheServico.setQuantidade(1);
		detalheServico.setValor(100);
		detalheServico.setOrdemServico(ordemServico);
		
		detalhesDosServicos = new ArrayList<DetalheServico>();
		detalhesDosServicos.add(detalheServico);
		
		status = new StatusOrdemServico();
		status.setId(1L);
		status.setTipoStatus(Status.ATRASADO.getCode());
		status.setOrdemServico(ordemServico);
		
		parcela1 = new Parcela();
		parcela1.setDataVencimento(LocalDate.now().plusDays(7));
		parcela1.setValorParcela(50);
		parcela1.setStatusParcela(Status.AGENDADO);
		parcela1.setOrdemServico(ordemServico);
		
		parcela2 = new Parcela();
		parcela2.setDataVencimento(LocalDate.now().plusDays(14));
		parcela2.setValorParcela(50);
		parcela2.setStatusParcela(Status.AGENDADO);
		parcela2.setOrdemServico(ordemServico);
		
		listaDeParcelas = new ArrayList<Parcela>();
		listaDeParcelas.add(parcela1);
		listaDeParcelas.add(parcela2);
		
		pagamento = new Pagamento();
		pagamento.setDataPagamento(LocalDateTime.now());
		pagamento.setId(122L);
		pagamento.setValorPago(100);
		pagamento.setOrdemServico(ordemServico);
		
		listaPagamento = new ArrayList<Pagamento>();
		listaPagamento.add(pagamento);
		
		ordemServico = new OrdemServico();
		ordemServico.setId(1L);
		ordemServico.setQuantidadeParcelas(2);
		ordemServico.setCliente(cliente);
		ordemServico.setOficina(oficina);
		ordemServico.setDetalheServico(detalhesDosServicos);
		ordemServico.setDataInicio(LocalDateTime.now());
		ordemServico.setTipoPagamento(TipoPagamento.SEMANAL);
		ordemServico.setStatusOrdemServico(status);
		
	}
	
	@Test
	void deveCriarOrdemServicoParceladaESemPagamentoDeEntrada() {
		
		when(CLIENTE_REPOSITORY.findById(cliente.getId())).thenReturn(Optional.of(cliente));
		when(OFICINA_REPOSITORY.findById(oficina.getId())).thenReturn(Optional.of(oficina));
		when(ORDEM_SERVICO_REPOSITORY.save(ordemServico)).thenReturn(ordemServico);
		
		when(STATUS_ORDEM_SERVICO.save(ordemServico.getId())).thenReturn(status);
		
		when(DETALHE_SERVICO_SERVICE.save(detalheServico.getId(), ordemServico.getDetalheServico())).thenAnswer(invocation -> {
			status.setTipoStatus(Status.AGENDADO.getCode());
			return detalhesDosServicos;
		});
		
		when(PAGAMENTO_SERVICE.save(ordemServico.getId(), new ArrayList<Pagamento>())).thenReturn(new ArrayList<Pagamento>());
		when(PARCELA_SERVICE.save(ordemServico.getId(), ordemServico.getQuantidadeParcelas())).thenReturn(listaDeParcelas);
		
		OrdemServico ordemCriada = servico.save(ordemServico, cliente.getId(), oficina.getId());
			
		Assertions.assertTrue(ordemCriada.getPagamento().isEmpty());
		Assertions.assertTrue(ordemServico.getQuantidadeParcelas() > 0);
		Assertions.assertEquals(ordemCriada.getParcela().size(), ordemServico.getQuantidadeParcelas());
		Assertions.assertEquals(Status.AGENDADO, ordemCriada.getStatusOrdemServico().getTipoStatus());
	}

}
