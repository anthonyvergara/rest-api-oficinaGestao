package com.api.oficina.modelEnum;

public enum TipoTelefone {
	
	CELULAR(1),
	RESIDENCIAL(2),
	WHATSAPP(3);
	
	private int code;
	
	private TipoTelefone(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
	public static TipoTelefone valueOf(int code) {
		for(TipoTelefone value : TipoTelefone.values()) {
			if(code == value.getCode()) {
				return value;
			}
		}
		throw new IllegalArgumentException("Illegal code");
	}
}
