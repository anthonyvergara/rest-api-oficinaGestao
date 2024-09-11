package com.api.oficina.modelEnum;

public enum StatusOS {
	
	ATRASADO(1),
	PAGO(2),
	AGENDADO(3);
	
	public int code;
	
	private StatusOS(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	
	public static StatusOS valueOf(int code) {
		for(StatusOS value : values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Illegal code");
	}
	

}
