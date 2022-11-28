

import java.io.Serializable;

@SuppressWarnings("serial")
public class BookBean implements Serializable{
	Integer ListID;
	Integer Year;
	Double BCOSR;// breast observe Survival ratio
	Double BCRSR;//relatively
	Double CCOSR;//cervical cancer
	Double CCRSR;
	Double CRCOSR;//colorectal cancer
	Double CRCRSR;

	public BookBean(Integer YEAR, double BCOSR, double BCRSR, double CCOSR, 
			double CCRSR, double CRCOSR, double CRCRSR) {
		this.Year = YEAR;
		this.BCOSR = BCOSR;
		this.BCRSR = BCRSR;
		this.CCOSR = CCOSR;
		this.CCRSR = CCRSR;
		this.CRCOSR = CRCOSR;
		this.CRCRSR = CRCRSR;
	}

	public BookBean(Integer ListID,Integer YEAR, double BCOSR, double BCRSR, double CCOSR, 
			double CCRSR, double CRCOSR, double CRCRSR) {
		// TODO Auto-generated constructor stub
		this.ListID = ListID;
		this.Year = YEAR;
		this.BCOSR = BCOSR;
		this.BCRSR = BCRSR;
		this.CCOSR = CCOSR;
		this.CCRSR = CCRSR;
		this.CRCOSR = CRCOSR;
		this.CRCRSR = CRCRSR;
	}

	public Integer getYear() {
		return Year;
	}

	public void setYear(Integer year) {
		Year = year;
	}

	public Double getBCOSR() {
		return BCOSR;
	}

	public void setBCOSR(Double bCOSR) {
		BCOSR = bCOSR;
	}

	public Double getBCRSR() {
		return BCRSR;
	}

	public void setBCRSR(Double bCRSR) {
		BCRSR = bCRSR;
	}

	public Double getCCOSR() {
		return CCOSR;
	}

	public void setCCOSR(Double cCOSR) {
		CCOSR = cCOSR;
	}

	public Double getCCRSR() {
		return CCRSR;
	}

	public void setCCRSR(Double cCRSR) {
		CCRSR = cCRSR;
	}

	public Double getCRCOSR() {
		return CRCOSR;
	}

	public void setCRCOSR(Double cRCOSR) {
		CRCOSR = cRCOSR;
	}

	public Double getCRCRSR() {
		return CRCRSR;
	}

	public void setCRCRSR(Double cRCRSR) {
		CRCRSR = cRCRSR;
	}

	@Override
	public String toString() {
		return "[" + "ListID=" + ListID + ", 依年度別分=" + Year + ", 乳癌觀察存活率=" + BCOSR + ", 乳癌相對存活率=" + BCRSR + ", 子宮頸癌觀察存活率=" + CCOSR + ", 子宮頸癌相對存活率="
				+ CCRSR + ", 大腸直腸癌觀察存活率=" + CRCOSR + ", 大腸直腸癌相對存活率=" + CRCRSR + "]";
	}

	
	
}
