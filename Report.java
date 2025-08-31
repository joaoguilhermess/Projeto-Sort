public class Report {
	private int ANO;
	private int EDICAO;
	private String NOME_IES;
	private String SIGLA_IES;
	private String NOME_CAMPUS;
	private String ESTADO_CAMPUS;
	private String MUNICIPIO_CAMPUS;
	private String CURSO;
	private String GRAU;
	private String TURNO;
	private String MODALIDADE;
	private int VAGAS;
	private String CPF;
	private String NOME;
	private String SEXO;
	private String NASCIMENTO;
	private String ESTADO_CANDIDATO;
	private String MUNICIPIO_CANDIDATO;
	private float NOTA_L;
	private float NOTA_CH;
	private float NOTA_CN;
	private float NOTA_M;
	private float NOTA_R;
	private float NOTA_FINAL;
	private float NOTA_DE_CORTE;
	private int CLASSIFICACAO;
	private String APROVADO;

	public Report(String[] columns) {
		int c = 0;

		this.ANO = Util.parseInt(columns[c++]);
		this.EDICAO = Util.parseInt(columns[c++]);
		this.NOME_IES = columns[c++];
		this.SIGLA_IES = columns[c++];
		this.NOME_CAMPUS = columns[c++];
		this.ESTADO_CAMPUS = columns[c++];
		this.MUNICIPIO_CAMPUS = columns[c++];
		this.CURSO = columns[c++];
		this.GRAU = columns[c++];
		this.TURNO = columns[c++];
		this.MODALIDADE = columns[c++];
		this.VAGAS = Util.parseInt(columns[c++]);
		this.CPF = columns[c++];
		this.NOME = columns[c++];
		this.SEXO = columns[c++];
		this.NASCIMENTO = columns[c++];
		this.ESTADO_CANDIDATO = columns[c++];
		this.MUNICIPIO_CANDIDATO = columns[c++];
		this.NOTA_L = Util.parseFloat(columns[c++]);
		this.NOTA_CH = Util.parseFloat(columns[c++]);
		this.NOTA_CN = Util.parseFloat(columns[c++]);
		this.NOTA_M = Util.parseFloat(columns[c++]);
		this.NOTA_R = Util.parseFloat(columns[c++]);
		this.NOTA_FINAL = Util.parseFloat(columns[c++]);
		this.NOTA_DE_CORTE = Util.parseFloat(columns[c++]);
		this.CLASSIFICACAO = Util.parseInt(columns[c++]);
		this.APROVADO = columns[c++];
	}

	public String getNOME() {
		return this.NOME;
	}

	public float getNOTA_FINAL() {
		return this.NOTA_FINAL;
	}

	public static boolean compare(Report reportA, Report reportB) {
		// return reportA.getNOME().compareTo(reportB.getNOME()) > 0;
		return reportA.getNOTA_FINAL() > reportB.getNOTA_FINAL();
	}
}