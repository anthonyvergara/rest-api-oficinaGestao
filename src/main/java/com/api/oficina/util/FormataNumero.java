package com.api.oficina.util;

import java.text.DecimalFormat;

public class FormataNumero extends DecimalFormat{

	private static final long serialVersionUID = 1L;

	public FormataNumero() {
		super("0.##");
	}
	
	public String format(Double number) {
		return super.format(number);
	}
}
