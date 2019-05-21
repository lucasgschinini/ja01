package ar.com.telecom.shiva.negocio.semaforo;

enum PostReglaEnum {
	//TODO VER SI Agrupar a las reglas en base al semaforo!!!
	R2(1, "R2"),
	R2_NOT_SHIVA(0,"RE2_NOT_SHIVA"),
	R2_SHIVA(1, "RE2_SHIVA"),
	R2_1(2,"R2_1"),
	R3(3, "R3"),
	R5(4, "R5"),
	R6(5, "R6"),
	R7(6, "R7"),
	R8(9, "R8"),
	R9(7, "R9"),
	R10(8, "R10"),
	R11(10, "R11"),
	R12(12, "R12"),
	R13(13, "R13"),
	R14(14, "R14"),
	R15(15, "R15"),
	R17(16, "R17"),
	R19(17, "R19"),
	R20(18, "R20"),
	R18(19, "R18"),
	R21(20, "R21"),
	R22(21, "R22"),
	R23(22, "R23"),
	R24(23, "R24"),
	R26(25, "R26"),
	R27(26, "R27"),
	R28(27, "R28"),
	R29(28, "R29"),
	R30(29, "R30"),
	R31(30, "R31"),
	R32(11, "R32"),
	R2_2(31,"R2_2"),
	R35(-3, "R35"),
	R36(-1, "R36"),
	R37(-2, "R37"),
	R38(-4, "R38"),
	R39(-5, "R39"),
	// Compensacion
	R40(32,"R40"),//32
	R40_1(33,"R40_1"),
	R41(34,"R41"),//33
	R42(35,"R42"),//34
	R43(36,"R43"),//35
	R44(37,"R44"),//36
	R45(38,"R45"),//37
	R46(39,"R46"),//38
	R47(40,"R47");//38

	int posicion;
	String descripcion;

	private PostReglaEnum(int posicion, String descripcion) {
		this.posicion = posicion;
		this.descripcion = descripcion;
	}

	public int posicion() {
		return this.posicion;
	}
	public String descripcion() {
		return this.descripcion;
	}
}
