package com.api.oficina.serviceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.oficina.component.CalculoServicoPadrao;
import com.api.oficina.component.CalculoServicos;
import com.api.oficina.component.Invoice;
import com.api.oficina.model.CalculoInvoice;
import com.api.oficina.model.Cliente;
import com.api.oficina.model.DetalheServico;
import com.api.oficina.model.Oficina;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.repository.ClienteRepository;
import com.api.oficina.repository.OficinaRepository;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.service.CalculoServico;
import com.api.oficina.service.OrdemServicoService;

@Service
public class OrdemServicoImpl implements OrdemServicoService{

	public final OrdemServicoRepository ordemServicoRepository;
	private final ClienteRepository clienteRepository;
	private final OficinaRepository oficinaRepository;
	
	private final Invoice invoice;
	
	public OrdemServicoImpl(OrdemServicoRepository ordemServicoRepository, ClienteRepository clienteRepository, OficinaRepository oficinaRepository,
			Invoice invoice) {
		this.ordemServicoRepository = ordemServicoRepository;
		this.clienteRepository = clienteRepository;
		this.oficinaRepository = oficinaRepository;
		this.invoice = invoice;
	}

	@Override
	public List<OrdemServico> listAll() {
		return (List<OrdemServico>) this.ordemServicoRepository.findAll();
	}

	@Override
	public OrdemServico save(OrdemServico ordemServico, Long idCliente, Long idOficina) {
		Optional<Cliente> cliente = this.clienteRepository.findById(idCliente);
		
		Optional<Oficina> oficina = this.oficinaRepository.findById(idOficina);
		
		if(cliente.isEmpty() || oficina.isEmpty()) {
			throw new RuntimeException();
		}else {
			
			ordemServico.setCliente(cliente.get());
			ordemServico.setOficina(oficina.get());
			
			ordemServico.setInvoiceNumber(this.generateInvoiceNumber());
			
			for(int i = 0; i<ordemServico.getDetalheServico().size(); i++) {
				ordemServico.getDetalheServico().get(i).setOrdemServico(ordemServico);
			}
			
			ordemServico.setValorTotal(this.invoice.calcularServico(ordemServico.getDetalheServico(), new CalculoServicoPadrao(ordemServico.getVat())));
			
			this.ordemServicoRepository.save(ordemServico);
		}
		
		return ordemServico;
	}
	
	@Override
	public OrdemServico update(OrdemServico ordemServico) {
		
		for(int i = 0; i < ordemServico.getDetalheServico().size(); i++) {
			ordemServico.getDetalheServico().get(i).setOrdemServico(ordemServico);
		}
		
		double valorTotal = ordemServico.getValorTotal();
		valorTotal = valorTotal + this.invoice.calcularServico(ordemServico.getDetalheServico(), new CalculoServicoPadrao(ordemServico.getVat()));
		ordemServico.setValorTotal(valorTotal);
		
		this.ordemServicoRepository.save(ordemServico);
		
		return ordemServico;
	}

	@Override
	public void delete(Long id) {
		Optional<OrdemServico> ordemServico = this.ordemServicoRepository.findById(id);
		
		LocalDate dataOrdem = ordemServico.get().getDataInicio().toLocalDate();
		
		LocalDate dateNow = LocalDate.now();
		
		Period periodo = Period.between(dataOrdem, dateNow);
		
		if(periodo.getDays() > 1) { //SE CRIAÇAO DA ORDEM FOR MAIOR QUE 1 DIA - HAVERÁ IMPEDIMENTO DE DELETE
			System.out.println("Ordem criada a "+periodo.getDays()+" dias.");
		}
	}
	
	public Long generateInvoiceNumber() {
		Random invoice = new Random();
		
		String invoiceNumber = "";
		
		for(int i=0; i<9; i++) {
			invoiceNumber = invoiceNumber + String.valueOf(invoice.nextInt(9));
			if(i == 8) {
				Optional<OrdemServico> checkInvoiceNumber = this.ordemServicoRepository.findByInvoice(Long.parseLong(invoiceNumber));
				if(checkInvoiceNumber.isPresent()) {
					i = 0;
				}
			}
		}
		
		return Long.parseLong(invoiceNumber);
	}
	
}
