package com.api.oficina.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.api.oficina.model.CalculoInvoice;
import com.api.oficina.model.Cliente;
import com.api.oficina.model.DetalheServico;
import com.api.oficina.model.Oficina;
import com.api.oficina.model.OrdemServico;
import com.api.oficina.repository.ClienteRepository;
import com.api.oficina.repository.OficinaRepository;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.service.OrdemServicoService;

@Service
public class OrdemServicoImpl implements OrdemServicoService{

	private final OrdemServicoRepository ordemServicoRepository;
	private final ClienteRepository clienteRepository;
	private final OficinaRepository oficinaRepository;
	
	public OrdemServicoImpl(OrdemServicoRepository ordemServicoRepository, ClienteRepository clienteRepository, OficinaRepository oficinaRepository) {
		this.ordemServicoRepository = ordemServicoRepository;
		this.clienteRepository = clienteRepository;
		this.oficinaRepository = oficinaRepository;
	}

	@Override
	public List<OrdemServico> listAll() {
		generateInvoiceNumber();
		return (List<OrdemServico>) this.ordemServicoRepository.findAll();
	}

	@Override
	public OrdemServico save(OrdemServico ordemServico, Long idCliente, Long idOficina) {
		Optional<Cliente> cliente = this.clienteRepository.findById(idCliente);
		
		Optional<Oficina> oficina = this.oficinaRepository.findById(idOficina);
		
		if(cliente.isEmpty() || oficina.isEmpty()) {
			throw new IllegalArgumentException();
		}else {
			
			ordemServico.setInvoiceNumber(this.generateInvoiceNumber());
			ordemServico.setCliente(cliente.get());
			ordemServico.setOficina(oficina.get());
			
			for(int i = 0; i<ordemServico.getDetalheServico().size(); i++) {
				ordemServico.getDetalheServico().get(i).setOrdemServico(ordemServico);
				ordemServico.getDetalheServico().get(i).setData(ordemServico.getDataInicio());
			}
			
			//CALCULAR VALOR TOTAL DOS SERVICOS
			ordemServico.setValorTotal(CalculoInvoice.calcularServicos(ordemServico.getDetalheServico()));
			
			
			if(ordemServico.getPagamento() != null) {
				ordemServico.getPagamento().get(0).setDataPagamento(ordemServico.getDataInicio());
				ordemServico.getPagamento().get(0).setOrdemServico(ordemServico);
				
				ordemServico.getStatusOrdemServico().setUltimoPagamento(ordemServico.getDataInicio());
				//CALCULA O SALDO DEVEDOR
				ordemServico.getStatusOrdemServico().setSaldoDevedor(CalculoInvoice.calcularSaldoDevedor(ordemServico));
				
				
				ordemServico.getStatusOrdemServico().setProximoVencimento(ordemServico.getDataInicio().toLocalDate().plusDays(7));
			}
			
		}
		
		this.ordemServicoRepository.save(ordemServico);
		
		return ordemServico;
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
