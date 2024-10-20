package com.api.oficina.modelEnum;

public enum Status {
	
	ATRASADO(1),
	PAGO(2),
	AGENDADO(3);
	
	public int code;
	
	private Status(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	
	public static Status valueOf(int code) {
		for(Status value : values()) {
			if(value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Illegal code");
	}
	

}
