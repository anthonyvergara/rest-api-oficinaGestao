package com.api.oficina.serviceImpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.api.oficina.component.Invoice;
import com.api.oficina.model.Cliente;
import com.api.oficina.model.Oficina;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.StatusOrdemServico;
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
	private final ParcelamentoServiceImpl PARCELAMENTO_SERVICE;
	
	private final Invoice INVOICE;
	
	public OrdemServicoImpl(OrdemServicoRepository ordemServicoRepository, ClienteRepository clienteRepository, OficinaRepository oficinaRepository,
			Invoice invoice, DetalheServicoImpl detalheServico, PagamentoServiceImpl pagamentoService,
			 StatusOrdemServicoImpl statusOrdemServico, ParcelamentoServiceImpl parcelamentoService) {
		this.ORDEM_SERVICO_REPOSITORY = ordemServicoRepository;
		this.CLIENTE_REPOSITORY = clienteRepository;
		this.OFICINA_REPOSITORY = oficinaRepository;
		this.INVOICE = invoice;
		this.DETALHE_SERVICO_SERVICE = detalheServico;
		this.PAGAMENTO_SERVICE = pagamentoService;
		this.STATUS_ORDEM_SERVICO = statusOrdemServico;
		this.PARCELAMENTO_SERVICE = parcelamentoService;
	}

	@Override
	public List<OrdemServico> listAll() {
		return (List<OrdemServico>) this.ORDEM_SERVICO_REPOSITORY.findAll();
	}
	
	public OrdemServico listById(Long id) {
		Optional<OrdemServico> findById = this.ORDEM_SERVICO_REPOSITORY.findById(id);
		return findById.get();
	}
	
	@Transactional
	@Override
	public OrdemServico save(OrdemServico ordemServico, Long idCliente, Long idOficina) throws Exception{
		
		Optional<Cliente> cliente = this.CLIENTE_REPOSITORY.findById(idCliente);
		
		Optional<Oficina> oficina = this.OFICINA_REPOSITORY.findById(idOficina);
		
		/*StatusOrdemServico statusOS = new StatusOrdemServico(); // verificar a necessidade
		statusOS.setOrdemServico(ordemServico);
		ordemServico.setStatusOrdemServico(statusOS);*/
		
		ordemServico.setCliente(cliente.get());
		ordemServico.setOficina(oficina.get());
		
		ordemServico.setInvoiceNumber(this.generateInvoiceNumber());
		
		ordemServico = this.ORDEM_SERVICO_REPOSITORY.save(ordemServico);
		
		ordemServico.setStatusOrdemServico(this.STATUS_ORDEM_SERVICO.criarStatusOS(ordemServico.getId()));
		
		
		this.DETALHE_SERVICO_SERVICE.save(ordemServico.getId(), ordemServico.getDetalheServico());
		
		if(! ordemServico.getPagamento().isEmpty())
			ordemServico.setPagamento(this.PAGAMENTO_SERVICE.salvar(ordemServico.getPagamento(), ordemServico.getId()));
		
		//ordemServico.setStatusOrdemServico(this.STATUS_ORDEM_SERVICO.save(ordemServico.getId()));
		
		ordemServico.setParcelamento(this.PARCELAMENTO_SERVICE.findByIdOrdemServico(ordemServico.getId()));
		
		return ordemServico;
	}
	
	@Override
	public OrdemServico update(OrdemServico ordemServico) {
		
		for(int i = 0; i < ordemServico.getDetalheServico().size(); i++) {
			ordemServico.getDetalheServico().get(i).setOrdemServico(ordemServico);
		}
		
		double valorTotal = ordemServico.getValorTotal();
		valorTotal = valorTotal + this.INVOICE.calcularServico(ordemServico.getDetalheServico(), new CalculoServicoPadrao(ordemServico.getVat()));
		ordemServico.setValorTotal(valorTotal);
		
		this.ORDEM_SERVICO_REPOSITORY.save(ordemServico);
		
		return ordemServico;
	}

	@Override
	public void delete(Long id) {
		Optional<OrdemServico> ordemServico = this.ORDEM_SERVICO_REPOSITORY.findById(id);
		
		LocalDate dataOrdem = ordemServico.get().getDataInicio().toLocalDate();
		
		LocalDate dateNow = LocalDate.now();
		
		Period periodo = Period.between(dataOrdem, dateNow);
		
		if(periodo.getDays() > 1) { //SE CRIAÇAO DA ORDEM FOR MAIOR QUE 1 DIA - HAVERÁ IMPEDIMENTO DE DELETE
			System.out.println("Ordem criada a "+periodo.getDays()+" dias.");
		}
	}
	
	public void deleteAll() {
		this.ORDEM_SERVICO_REPOSITORY.deleteAll();
	}
	
	public Long generateInvoiceNumber() {
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
