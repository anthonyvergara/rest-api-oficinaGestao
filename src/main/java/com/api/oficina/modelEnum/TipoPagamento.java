package com.api.oficina.modelEnum;

public enum TipoPagamento {
	
	AVISTA(1),
	MENSAL(2),
	SEMANAL(3);
	
	private int code;
	
	TipoPagamento(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static TipoPagamento valueOf(int code) {
		for(TipoPagamento value : values()) {
			if(code == value.getCode()) {
				return value;
			}
		}
		throw new IllegalArgumentException("Illegal code");
	}

}
