package com.api.oficina.modelEnum;

public enum StatusParcela {
	
	PAGO(1),
	PENDENTE(2),
	ATRASADO(3);
	
	public int code;
	
	private StatusParcela(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public static StatusParcela valueOf(int code) {
		for(StatusParcela value : StatusParcela.values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Illegal code");
	}

}
