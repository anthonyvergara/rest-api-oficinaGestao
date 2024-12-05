package com.api.oficina.serviceImpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.api.oficina.component.Invoice;
import com.api.oficina.model.Cliente;
import com.api.oficina.model.DetalheServico;
import com.api.oficina.model.Oficina;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Pagamento;
import com.api.oficina.model.StatusOrdemServico;
import com.api.oficina.modelEnum.Status;
import com.api.oficina.repository.ClienteRepository;
import com.api.oficina.repository.OficinaRepository;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.service.OrdemServicoService;
import com.api.oficina.util.servico.CalculoServicoPadrao;

import jakarta.transaction.Transactional;

@Service
public class OrdemServicoImpl implements OrdemServicoService{

	public final OrdemServicoRepository ORDEM_SERVICO_REPOSITORY;
	private final ClienteRepository CLIENTE_REPOSITORY;
	private final OficinaRepository OFICINA_REPOSITORY;
	
	private final DetalheServicoImpl DETALHE_SERVICO_SERVICE;
	private final PagamentoServiceImpl PAGAMENTO_SERVICE;
	private final StatusOrdemServicoImpl STATUS_ORDEM_SERVICO;
	private final ParcelaServiceImpl PARCELA_SERVICE;
	
	
	public OrdemServicoImpl(OrdemServicoRepository ordemServicoRepository, ClienteRepository clienteRepository, OficinaRepository oficinaRepository,
			 DetalheServicoImpl detalheServico, PagamentoServiceImpl pagamentoService,
			 StatusOrdemServicoImpl statusOrdemServico, ParcelaServiceImpl parcelaService) {
		
		this.ORDEM_SERVICO_REPOSITORY = ordemServicoRepository;
		this.CLIENTE_REPOSITORY = clienteRepository;
		this.OFICINA_REPOSITORY = oficinaRepository;
		this.DETALHE_SERVICO_SERVICE = detalheServico;
		this.PAGAMENTO_SERVICE = pagamentoService;
		this.STATUS_ORDEM_SERVICO = statusOrdemServico;
		this.PARCELA_SERVICE = parcelaService;
	}

	@Override
	public List<OrdemServico> listAll() {
		return (List<OrdemServico>) this.ORDEM_SERVICO_REPOSITORY.findAll();
	}
	
	@Override
	public OrdemServico listById(Long id) {
		Optional<OrdemServico> findById = Optional.of(this.ORDEM_SERVICO_REPOSITORY.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Ordem Servico não existe!")));
		findById.get().getParcela().sort(null);
		return findById.get();
	}
	
	public List<OrdemServico> listByIdCliente(Long idCliente) {
		List<OrdemServico> findByIdCliente = this.ORDEM_SERVICO_REPOSITORY.findOrdemServicoByIdCliente(idCliente);
		
		return findByIdCliente;
	}
	
	@Transactional
	public OrdemServico save(OrdemServico ordemServico, Long idCliente, Long idOficina){
		
		Optional<Cliente> cliente = Optional.of(this.CLIENTE_REPOSITORY.findById(idCliente)
				.orElseThrow(() -> new IllegalArgumentException("Cliente não existe!")));
		
		Optional<Oficina> oficina = Optional.of(this.OFICINA_REPOSITORY.findById(idOficina)
				.orElseThrow(() -> new IllegalArgumentException("Oficina não existe!")));
		
		ordemServico.setCliente(cliente.get());
		ordemServico.setOficina(oficina.get());
		ordemServico.setInvoiceNumber(this.generateInvoiceNumber());
		
		System.out.println("VALOR TOTA1L: "+ordemServico.getValorTotal());
		
		List<Pagamento> pagamentosNoAtoDaCriacaoDaOrdem = new ArrayList<>(ordemServico.getPagamento());
		ordemServico.getPagamento().clear(); // Remove temporariamente os valores para não atualizar SaldoDevedor subtraido de Pagamentos ao chamar StatusOrdemServico em DetalheServicoImpl
		
		System.out.println("VALOR TOTA2L: "+ordemServico.getValorTotal());
		
		ordemServico = this.ORDEM_SERVICO_REPOSITORY.save(ordemServico);
		
		ordemServico.setStatusOrdemServico(this.STATUS_ORDEM_SERVICO.save(ordemServico.getId()));
		
		System.out.println("VALOR TOTA3L: "+ordemServico.getValorTotal());
		
		
		ordemServico.setDetalheServico(this.DETALHE_SERVICO_SERVICE.save(ordemServico.getId(), ordemServico.getDetalheServico()));
		
		System.out.println("VALOR TOTA4L: "+ordemServico.getValorTotal());
		
		ordemServico.setPagamento(this.PAGAMENTO_SERVICE.save(ordemServico.getId(), pagamentosNoAtoDaCriacaoDaOrdem));
		
		System.out.println("VALOR TOTA5L: "+ordemServico.getValorTotal());
		
		validarVerificacaoCondicional(ordemServico);
		
		// CASO O STATUS DA ORDEM_SERVICO SEJA AGENDADO, ENTENDE-SE QUE VALOR_PAGO É 0 -- OU -- VALOR_PAGO É MENOR QUE VALOR_TOTAL_ORDEMSERVICO
		// DEVE OCORRER O PARCELAMENTO.
		if(ordemServico.getStatusOrdemServico().getTipoStatus() == Status.AGENDADO && ordemServico.getStatusOrdemServico().getSaldoDevedor() > 0)
			ordemServico.setParcela(this.PARCELA_SERVICE.save(ordemServico.getId(), ordemServico.getQuantidadeParcelas()));
			
		return ordemServico;
	}
	
	private void validarVerificacaoCondicional(OrdemServico ordemServico) {
		// CASO QUANTIDADE DE PARCELA == 0 E VALOR_PAGAMENTO NAO EXISTA
		// DEVE DEVE PARCELAR O VALOR NO MINIMO 1 VEZ
		if(ordemServico.getQuantidadeParcelas() == 0 && ordemServico.getPagamento().isEmpty()) {
			ordemServico.setQuantidadeParcelas(1);
			System.out.println("passo aqui 1");
		}
		
		if(!ordemServico.getPagamento().isEmpty()) {
			
			// CASO O VALOR_PAGO SEJA MAIOR QUE O VALOR_TOTAL_ORDEMSERVICO
			if(ordemServico.getPagamento().get(0).getValorPago() > ordemServico.getValorTotal()) {
				throw new IllegalArgumentException("O valor pago não pode ser maior que o valor total");
			}
			
			// CASO O PAGAMENTO SEJA MENOR QUE O VALOR_TOTAL_ORDEMSERVICO
			// DEVE PARCELAR O RESTANTE NO MINIMO 1 VEZ
			if(ordemServico.getPagamento().get(0).getValorPago() < ordemServico.getValorTotal() && ordemServico.getQuantidadeParcelas() == 0) {
				ordemServico.setQuantidadeParcelas(1);
				System.out.println("passo aqui 2");
			}
		}
		
		// CASO FOI PASSADO ALGUMA QUANTIDADE DE PARCELAS, POREM O VALOR_PAGAMENTO SEJA IGUAL AO VALOR_TOTAL_ORDEMSERVICO
		// DEVE ZERAR A QUANTIDADE DE PARCELAS.
		if(ordemServico.getStatusOrdemServico().getTipoStatus() == Status.PAGO) {
			ordemServico.setQuantidadeParcelas(0);
		}
	}
	
	@Override
	public OrdemServico update(OrdemServico ordemServico) {
		
		for(int i = 0; i < ordemServico.getDetalheServico().size(); i++) {
			ordemServico.getDetalheServico().get(i).setOrdemServico(ordemServico);
		}
		
		double valorTotal = ordemServico.getValorTotal();
		valorTotal = valorTotal + Invoice.calcularServico(ordemServico.getDetalheServico(), new CalculoServicoPadrao(ordemServico.getVat()));
		ordemServico.setValorTotal(valorTotal);
		
		this.ORDEM_SERVICO_REPOSITORY.save(ordemServico);
		
		return ordemServico;
	}

	@Override
	public void delete(Long id) {
		OrdemServico ordemServico = this.listById(id);
		
		LocalDate dataDeCriacaoDaOrdemServico = ordemServico.getDataInicio().toLocalDate();
		
		LocalDate dataDeSolicitacaoDeRemocao = LocalDate.now();
		
		Period periodoEntreDatas = Period.between(dataDeCriacaoDaOrdemServico, dataDeSolicitacaoDeRemocao);
		
		if(periodoEntreDatas.getDays() > 1) { //SE CRIAÇAO DA ORDEM FOR MAIOR QUE 1 DIA - HAVERÁ IMPEDIMENTO DE DELETE
			throw new RuntimeException("[ALERTA] Não foi possivel remover a Ordem de Servico. Por favor, entre em contato com um Administrador!");
		}else {
			this.ORDEM_SERVICO_REPOSITORY.deleteById(id);
		}
	}
	
	private void deleteAll() {
		this.ORDEM_SERVICO_REPOSITORY.deleteAll();
	}
	
	private Long generateInvoiceNumber() {
		Random invoice = new Random();
		
		String invoiceNumber = "";
		
		for(int i=0; i<9; i++) {
			invoiceNumber = invoiceNumber + String.valueOf(invoice.nextInt(9));
			if(i == 8) {
				Optional<OrdemServico> checkInvoiceNumber = this.ORDEM_SERVICO_REPOSITORY.findByInvoice(Long.parseLong(invoiceNumber));
				if(checkInvoiceNumber.isPresent()) {
					i = 0;
				}
			}
		}
		
		return Long.parseLong(invoiceNumber);
	}
	
}
