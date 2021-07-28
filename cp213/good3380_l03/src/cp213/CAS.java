package cp213;

public class CAS extends Professor {

	private String term;

	public CAS(String lastName, String firstName, String department, String term) {
		super(lastName, firstName, department);
		this.setTerm(term);
	}

	public void setTerm(String term) {
		String code = term.substring(term.length() - 2);
		if (term.length() == 6 && (code.equals("01") || code.equals("05") || code.equals("09"))) {
			boolean flag = false;
			for (int i = 0; i < term.length(); i++) {
				char c = term.charAt(i);
				if (c < '0' || c > '9') {
					flag = true;
				}
			}

			if (!flag) {
				this.term = term;
			} else {
				throw new IllegalArgumentException("Term is not valid");
			}
		} else {
			throw new IllegalArgumentException("Term is not valid");
		}
	}

	@Override
	public void print() {
		super.print();
		System.out.println("Term: " + this.term);
	}

    public int compareTo(final CAS cas) {
	// Compares two Person objects by lastName then firstName.
		int flag = super.compareTo(cas);
		
		if (flag == 0) {
			flag = this.term.compareTo(cas.term);
		}
		
	return flag;
    }

}
