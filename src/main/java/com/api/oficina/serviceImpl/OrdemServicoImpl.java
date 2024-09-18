package com.api.oficina.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.oficina.component.Invoice;
import com.api.oficina.component.Parcelas;
import com.api.oficina.model.Cliente;
import com.api.oficina.model.DetalheServico;
import com.api.oficina.model.Oficina;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.Parcelamento;
import com.api.oficina.model.StatusOrdemServico;
import com.api.oficina.modelEnum.StatusOS;
import com.api.oficina.modelEnum.StatusParcela;
import com.api.oficina.modelEnum.TipoPagamento;
import com.api.oficina.os.pagamento.CalculoParcelamentoSemJuros;
import com.api.oficina.os.servico.CalculoServicoPadrao;
import com.api.oficina.repository.ClienteRepository;
import com.api.oficina.repository.OficinaRepository;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.service.CalculoServico;
import com.api.oficina.service.OrdemServicoService;

@Service
public class OrdemServicoImpl implements OrdemServicoService{

	public final OrdemServicoRepository ORDEM_SERVICO_REPOSITORY;
	private final ClienteRepository CLIENTE_REPOSITORY;
	private final OficinaRepository OFICINA_REPOSITORY;
	
	private final Invoice INVOICE;
	private final Parcelas PARCELAS;
	
	private double saldoDevedor;
	private double valorEntrada = 0;
	
	public OrdemServicoImpl(OrdemServicoRepository ordemServicoRepository, ClienteRepository clienteRepository, OficinaRepository oficinaRepository,
			Invoice invoice, Parcelas parcelas) {
		this.ORDEM_SERVICO_REPOSITORY = ordemServicoRepository;
		this.CLIENTE_REPOSITORY = clienteRepository;
		this.OFICINA_REPOSITORY = oficinaRepository;
		this.INVOICE = invoice;
		this.PARCELAS = parcelas;
	}

	@Override
	public List<OrdemServico> listAll() {
		return (List<OrdemServico>) this.ORDEM_SERVICO_REPOSITORY.findAll();
	}

	@Override
	public OrdemServico save(OrdemServico ordemServico, Long idCliente, Long idOficina) {
		Optional<Cliente> cliente = this.CLIENTE_REPOSITORY.findById(idCliente);
		
		Optional<Oficina> oficina = this.OFICINA_REPOSITORY.findById(idOficina);
		
		if(cliente.isEmpty() || oficina.isEmpty()) {
			throw new RuntimeException();
		}else {
			
			ordemServico.setCliente(cliente.get());
			ordemServico.setOficina(oficina.get());
			
			ordemServico.setInvoiceNumber(this.generateInvoiceNumber());
			
			//BLOCO SERVICOS
			for(int i = 0; i<ordemServico.getDetalheServico().size(); i++) {
				ordemServico.getDetalheServico().get(i).setOrdemServico(ordemServico);
			}
			
			//Retorna o VALOR TOTAL dos serviços realizados.
			ordemServico.setValorTotal(this.INVOICE.calcularServico(ordemServico.getDetalheServico(), new CalculoServicoPadrao(ordemServico.getVat())));
			
			// BLOCO PAGAMENTO
			
			if(ordemServico.getPagamento() != null) {
				this.valorEntrada = ordemServico.getPagamento().get(0).getValorPago();
				ordemServico.getPagamento().get(0).setOrdemServico(ordemServico);
				ordemServico.getPagamento().get(0).setDataPagamento(ordemServico.getDataInicio());
			}
			
			// BLOCO PARCELAS
			
			double somatorioParcelas;
			
			Map<LocalDate,Double> listaParcelas = this.PARCELAS.calcularParcelas(ordemServico, new CalculoParcelamentoSemJuros(ordemServico.getQuantidadeParcelas()));
			
			if(ordemServico.getQuantidadeParcelas() != 0) {
			 	
			 	ordemServico.setParcelamento(this.PARCELAS.listarParcelas(listaParcelas));
			 	
			 	
			 	for(int i = 0; i<ordemServico.getQuantidadeParcelas(); i++) {
			 		ordemServico.getParcelamento().get(i).setOrdemServico(ordemServico);
			 	}
			 	
			 	somatorioParcelas = listaParcelas.values().stream().mapToDouble(value -> value.doubleValue()).sum();
			}
			
			// BLOCO STATUS ORDEM SERVIÇO
			StatusOrdemServico statusOS = new StatusOrdemServico();
		 	ordemServico.setStatusOrdemServico(statusOS);
		 	
		 	statusOS.setOrdemServico(ordemServico);
		 	
		 	this.saldoDevedor = ordemServico.getValorTotal() - ordemServico.getPagamento().get(0).getValorPago();
		 	statusOS.setSaldoDevedor(this.saldoDevedor);
		 	
		 	Optional<Double> proximaParcela = listaParcelas.values().stream().findFirst();
		 	statusOS.setValorProximaParcela(proximaParcela.get());
		 	
		 	Optional<LocalDate> proximoVencimento = listaParcelas.keySet().stream().min(Comparator.comparing(value -> value));
		 	statusOS.setProximoVencimento(proximoVencimento.get());
		 	
		 	StatusOS status = ordemServico.getTipoPagamento() == TipoPagamento.AVISTA ? StatusOS.PAGO : StatusOS.AGENDADO;
		 	statusOS.setTipoStatus(status.code);
			
		 	
		 	LocalDateTime dataUltimoPagamento = this.valorEntrada == 0 ? null : ordemServico.getDataInicio();
		 	statusOS.setUltimoPagamento(dataUltimoPagamento);
		 	
			//this.ORDEM_SERVICO_REPOSITORY.save(ordemServico);
		}
		
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
