package com.api.oficina.serviceImpl;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.oficina.model.OrdemServico;
import com.api.oficina.model.StatusOrdemServico;
import com.api.oficina.modelEnum.StatusOS;
import com.api.oficina.modelEnum.StatusParcela;
import com.api.oficina.repository.OrdemServicoRepository;
import com.api.oficina.repository.StatusOrdemServicoRepository;
import com.api.oficina.service.StatusOrdemServicoService;

@Service
public class StatusOrdemServicoImpl implements StatusOrdemServicoService{

	private final StatusOrdemServicoRepository STATUS_SERVICO_REPOSITORY;
	private final OrdemServicoRepository ORDEM_SERVICO_REPOSITORY;
	
	
	public StatusOrdemServicoImpl(StatusOrdemServicoRepository statusServicoRepository, OrdemServicoRepository ordemServicoRepository) {
		this.STATUS_SERVICO_REPOSITORY = statusServicoRepository;
		this.ORDEM_SERVICO_REPOSITORY = ordemServicoRepository;
	}
	
	@Override
	public StatusOrdemServico save(Long idOrdemServico) {
		
		Optional<OrdemServico> ordemServico = this.ORDEM_SERVICO_REPOSITORY.findById(idOrdemServico);
		
		StatusOrdemServico statusOS = ordemServico.get().getStatusOrdemServico();
		
		double valorTotal = ordemServico.get().getValorTotal();
		double valoresPagos = ordemServico.get().getPagamento().stream()
				.mapToDouble(valores -> valores.getValorPago())
				.sum();
		
		double saldoDevedor = valorTotal - valoresPagos;
		statusOS.setSaldoDevedor(saldoDevedor);
		
		StatusOS status = null;
		
		if(saldoDevedor == 0) {
			status = StatusOS.PAGO;
		}else if(saldoDevedor > 0 && statusOS.getProximoVencimento().isBefore(LocalDate.now())) {
			status = StatusOS.ATRASADO;
		}else {
			status = StatusOS.AGENDADO;
		}
		
		if(ordemServico.get().getQuantidadeParcelas() > 0) {
			Long parcelaAtrasada = ordemServico.get().getParcelamento().stream()
					.filter(parcelas -> parcelas.getStatusParcela() == StatusParcela.ATRASADO)
					.count();
			
			Long parcelaPendente = ordemServico.get().getParcelamento().stream()
					.filter(parcelas -> parcelas.getStatusParcela() == StatusParcela.PENDENTE)
					.count();
			
			if(parcelaAtrasada > 0) {
				status = StatusOS.ATRASADO;
			}else if(parcelaAtrasada == 0 && parcelaPendente > 0) {
				status = StatusOS.AGENDADO;
			}
		}
		statusOS.setTipoStatus(status.getCode());
		
		
		return statusOS;
	}

}
